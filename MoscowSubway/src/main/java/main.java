import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class main {
    public static void main(String[] args) {

        try {

            //Загружаем HTML (без ограничения размера ответа)
            // maxBodySize(0) снимает ограничение по объёму ответа (по умолчанию 2 Мб).
            Document doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();

            // Находим линии метро
            Elements lines = doc.select("span.js-metro-line");

            //Инициализируем массив JSON, который хранит объекты линий [ { "number": 1, "name": "..." }, ... ]
            JSONArray linesArray = new JSONArray();
            //Инициализируем JSON объекты, хранящие станции и их (строчные) ключи (номера)
            //{ "1": ["Станция1", "Станция2"], "2": [ ... ], ... }
            JSONObject stationsObject = new JSONObject();
            //Инициализируем массив массивов для хранения пересадок
            JSONArray connectionsArray = new JSONArray();

            //Переменная для хранения номера линии
            Long lineNumber = 1L;

            //Обход всех линий метро
            for (Element line : lines){

                //(строчный) Номер линии берется из атрибута data-line
                String lineIdStr = line.attr("data-line");
                String lineName = line.text();


                //Создаем библиотеку объектов линий для добавления в массив, хранящий линий
                JSONObject lineObject = new JSONObject();
                lineObject.put("number", lineNumber);
                lineObject.put("name", lineName);
                //Добавляем объекты линии в массив объектов линий
                linesArray.add(lineObject);

                //Находим станции текущей линии
                Elements lineStations = doc.select("div.js-metro-stations[data-line=" + lineIdStr + "] a");


                //собираем массив с названиями станций для этой линии
                JSONArray stationNames = new JSONArray();
                for (Element st : lineStations){
                    Element nameEl = st.selectFirst("span.name");
                    if (nameEl != null){
                        String stationName = nameEl.text();
                        stationNames.add(stationName);
                    }
                }

                //кладем список станций в основную коллекцию
                stationsObject.put(String.valueOf(lineNumber), stationNames);

                //Увеличиваем переменную для номера линии
                lineNumber++;
            }

            //Собираем все элементы в JSON файл в требуемой структуре
            // {
            //   "stations": { ... },
            //   "connections": [ ... ],
            //   "lines": [ ... ]
            // }
            JSONObject mainObject = new JSONObject();
            mainObject.put("stations", stationsObject);
            mainObject.put("connections", connectionsArray);// пустой массив,
            mainObject.put("lines", linesArray);

            //сохраняем Джейсон файл
            Path out = Path.of("data/subwayMap.json");
            Files.writeString(out, mainObject.toJSONString(), StandardCharsets.UTF_8);
            System.out.println("JSON сохранён в: " + out.toAbsolutePath());


            // Читаем файл, распечатываем
            String readJson = Files.readString(out, StandardCharsets.UTF_8);

            // JSON-строку в объект при помощи JSONParser
            JSONParser parser = new JSONParser();
            JSONObject readRoot = (JSONObject) parser.parse(readJson);

            // Парсим массив линий и станций
            JSONArray readLines = (JSONArray) readRoot.get("lines");
            JSONObject readStations = (JSONObject) readRoot.get("stations");

            System.out.println("Количество станций на каждой линии:");
            for (Object o : readLines) {
                JSONObject ln = (JSONObject) o;

                // number в JSON — число
                Long number = (Long) ln.get("number");
                String name = (String) ln.get("name");

                // Ключи в stations — строки, поэтому приводим number к строке
                JSONArray stList = (JSONArray) readStations.get(String.valueOf(number));
                int count = (stList == null) ? 0 : stList.size();

                System.out.printf("Линия %d (%s): %d%n", number, name, count);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());

        }
    }
}
