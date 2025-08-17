import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    final static String pathToFile = "fileForParsing/movementList.csv";
    public static void main(String[] args) {

        ArrayList<Company> companies = loadCompaniesFromFile();

        //Распечатываем общие доходы и расходы компаний
        System.out.println("Общий доход компаний - " + String.format("%.2f", Company.getTotalIncome()));
        System.out.println("Общий расход компаний - " + String.format("%.2f", Company.getTotalExpense()) + "\n");
        //распечатываем список из файла
        System.out.println("\t\t\t\t\t\t\t\t Список компаний из файла\t\t\t\t");
        System.out.println("\t\t\t\t ИМЯ КОМПАНИИ \t\t\t\t\t\t ДОХОД \t\t\t\t\t\t РАСХОД\n" );
        for(Company company : companies){
            System.out.println("\t\t" + company.getCompanyName() + " \t\t\t\t" + company.getIncome() + " \t\t\t\t" + company.getExpense());
        }

    }
    // Метод, который загружает компании из файла и возвращает список объектов класса Company
    private static ArrayList<Company> loadCompaniesFromFile(){

        //Создаем новый список элементов от класса Company, который будет хранить компании
        ArrayList<Company> companies = new ArrayList<>();



        //Добавляем обработку исключений
        try{
            //Создаем временный список элементов, который будет хранить необработанные строки файла
            List<String> lines = Files.readAllLines(Paths.get(pathToFile));

            //Цикл для разбивки lines, строк из файла на разные элементы
            for ( String line : lines) {
                //код для разбивки на элементы

                //делим строку по запятым
                String [] fragments = line.split(",");

                //если элементов больше чем нужно, сообщаем строку,
                // и завершаем выполнение текущей итерации цикла `for` т.e переходим к следующей строке

                // Убираем все не цифры (кроме запятой) и заменяем запятую на точку
                String income = fragments[6].replace(",",".").replaceAll("[^0-9.]","");
                String expense = fragments[7].replace(",",".").replaceAll("[^0-9.]","");
                String name = shortenName(fragments[5]);

                // Переменная-флаг показывает, находимся ли мы внутри кавычек,
                // изначально нет, если встретим кавычки, заменим на true
                boolean insideQuotes = false;

                // Для накопления текущего поля (сбор символов в StringBuilder)
                StringBuilder currentField = new StringBuilder();

                //обрабатываем строки, имеющие лишнюю запятую внутри кавычек ","
                // Читаем строку символ за символом
                if(fragments.length != 8){
                    int index = 0;
                    String [] fragmentsWithQuotes  = new String[8];
                    HashMap<Integer,String> fragmentsWithQuote = new HashMap<>();

                    // метод toCharArray  преобразует строку `line` в массив символов ( char[] )
                    for (char c : line.toCharArray()){
                        if(c == '"' && insideQuotes){
                            // Если мы сейчас внутри кавычек и встретили символ `"`
                            // Это означает, что кавычка закрывается, прекращаем режим "внутри кавычек"
                            insideQuotes = false; //Закрываем кавычки
                        }
                        else if (c == '"' && !insideQuotes){
                            // Если символ `"` встретился, и мы НЕ были внутри кавычек
                            // Это означает, что начинаем текст внутри кавычек
                            insideQuotes = true; // Вошли в кавычки
                        } else if (c == ',' && !insideQuotes) {
                            // Если символ запятая (,) и мы НЕ в кавычках, то
                            // Это означает, что поле завершилось

                            //добавляем элемент
                            fragmentsWithQuote.put(index,currentField.toString().trim());

                            currentField.setLength(0); // Очищаем StringBuilder для нового поля
                            index++; //добавляем индекс +1
                        } else {
                            // Если символ — обычная буква, цифра, запятая внутри кавычек или другой текст

                            // Мы просто добавляем его в текущее поле
                            currentField.append(c);
                        }
                    }

                    // Добавляем последний элемент, если он существует
                    if (currentField.length() > 0) {
                        fragmentsWithQuote.put(index, currentField.toString().trim());
                    }

                    //заменяем значения
                    income = fragmentsWithQuote.get(6).replace(",",".").replaceAll("[^0-9.]","");
                    expense = fragmentsWithQuote.get(7).replace(",",".").replaceAll("[^0-9.]","");
                    name = shortenName(fragmentsWithQuote.get(5));

                }
                //==============================================


                //проверка, если в строках дохода и расхода не цифровые значения
                if(income.isEmpty() || expense.isEmpty())
                {
                    System.out.println("\t\tв строке - " + line + " \n\t\tв столбце доходов:[ " + fragments[6] + " ] и в строке расходов [ " + fragments[7] + " ]" + "\n\t\tне цифровое значение, поэтому пропускаем строку \n");
                    continue;
                }
                boolean companyFound = false; // Флаг, указывающий, найдена ли компания

                for (Company company : companies) {
                    if (company.getCompanyName().equals(name)) {
                        // Если компания существует, обновить её доход и расход
                        company.setExpense(company.getExpense() + Double.parseDouble(expense));
                        company.setIncome(company.getIncome() + Double.parseDouble(income));
                        companyFound = true; // Устанавливаем флаг, что компания найдена
                        break; // Выходим из цикла, так как обновление выполнено
                    }
                }

                // Если компания не найдена, создаём новую и добавляем в список
                if (!companyFound) {
                    companies.add(new Company(name, Double.parseDouble(income), Double.parseDouble(expense)));
                }
            }

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        //возвращаем список компаний
        return companies;
    }
    //Метод, сокращающий строку имени от первого символа\ или / до 10 пробелов+
    public static String shortenName(String name) {
        if (name == null || name.isEmpty()) {
            return ""; // Проверка на пустую строку
        }

        // Регулярное выражение: ищем часть строки от символов '/' или '\' до 10+ пробелов
        String regex = "[/\\\\].*?\\s{10,}";

        // Найти совпадение
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);

        // Если есть совпадение
        if (matcher.find()) {
            String result = matcher.group().trim(); // Получаем найденную часть
            return result.substring(1); // Удаляем первый символ, который равен '/' или '\'
        }

        // Если совпадений нет, возвращаем исходную строку
        return name;
    }
}