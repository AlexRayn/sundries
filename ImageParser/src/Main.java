import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            // Подключаемся к указанному URL
            // и загружаем HTML-код страницы в объект `Document`
            Document doc = Jsoup.connect("https://lenta.ru").get();

            Elements elements = doc.select("img");


            /*
            *         Протокольно-независимые URL-адреса.
            *    Они не указывают конкретный протокол (`http` или д.р), а позволяют браузеру автоматически
            * определить его, основываясь на используемом протоколе текущей страницы
            * (той, с которой загружено HTML-содержимое), должны быть приведены к полному формату с указанием протокола.
            * Обычно это https://,или http://
             * */

            File folder = new File("/home/alex/Рабочий стол/images");
            if(!folder.exists())
            {
                folder.mkdirs(); //создаем папку, если ее нет
            }
            else {
                System.out.println("Папка " + folder + " уже создана");
            }
            // Выводим src (ссылки на изображения) для каждого <img>
            // src от слова "ресурс, ссылка", метод attr возвращает значение по ключу.
            for (Element img : elements) {
                String src = img.attr("abs:src"); // "abs:src" преобразует относительные пути в абсолютные

                if (src.isEmpty()) continue; // Пропускаем пустые ссылки (если такие есть)

                System.out.println("Скачиваем изображение: " + src);

                // Загружаем изображение и сохраняем его в папку:

                // Извлекаем имя файла из ссылки
                String fileName = src.substring(src.lastIndexOf("/") + 1);

                // Полный путь для сохранения файла
                Path outputPath = Paths.get(folder.getAbsolutePath(), fileName);

                // Вложите проверку перед скачиванием
                if (Files.exists(outputPath)) {
                    System.out.println("Файл " + fileName + " уже существует, пропускаем скачивание.");
                    continue; // Пропускаем итерацию, если файл уже есть
                }

                //скачиваем и сохраняем файла
                // Открытие потока для чтения данных из указанного URL
                try (InputStream inputStream = new URL(src).openStream()) {

                    // Копируем данные из потока (URL) в файл на локальной системе
                    Files.copy(inputStream, outputPath);

                    System.out.println("Изображение " + fileName + " сохранено");
                }

                catch (Exception ex){
                    System.out.println("Не удалось скачать изображение: " + img);
                    ex.printStackTrace();

                }
            }
        }
        //обработка ошибок
        catch (Exception ex){
            System.out.println("Ошибочка, вышла!");
            ex.printStackTrace();
        }
    }
}