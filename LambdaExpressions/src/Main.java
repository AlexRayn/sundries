import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main
{
    // Путь к файлу со списком сотрудников
    private static String staffFile = "data/staff.txt";
    // Формат даты, используемый в файле
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        // Загружаем список сотрудников из файла
        ArrayList<Employee> staff = loadStaffFromFile();

        /*
        ====================мое дополнение========================================
        при задании отсортировать сотрудников по заработной плате staff-список
        ===================обычная сортировка=====================================

        Collections.sort(staff, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2)Сделайте сортировку сотрудников одновременно по заработной плате и алфавиту.

            {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });

        Лямбда выражения позволяют эту всю конструкцию сократить,
        в коде выше есть класс реализующий интерфейс компоратор который принемает
        два параметра которые дальше каким либо образом сравниваются.


        !!!!!!!!!!!!!СОРТИРОВКА С ИСПОЛЬЗОВАНИЕМ ЛЯМБДА ВЫРАЖЕНИЙ!!!!!!!!!!!!!!!!!

        Collections.sort(staff ,(o1, o2) -> o1.getSalary().compareTo(o2.getSalary()));

        /* синтаксис лямбда выражений () -> {};
             в круглых скобках пишутся параметры их может и не быть,
             дальше ставится стрелка, и замет м фигурных скомбках пишется сам код
         */

//        сортировка сотрудников одновременно по заработной плате и алфавиту.
//        Collections.sort(staff, (o1, o2) -> {
//            int salaryCompare = o1.getSalary().compareTo(o2.getSalary());
//            if(salaryCompare == 0) {
//                return o1.getName().compareTo(o2.getName());
//            } else {return salaryCompare;
//            }
//        });
//
//        //метод для распечатывания списка сотрудников
//        for (Employee employee : staff){
//            System.out.println(employee);
//        }
        /*
        Разделение на шаги:
Создание потока из списка сотрудников:

java
Копировать код
staff.stream()
Метод stream() преобразует список staff в поток данных (Stream<Employee>), что позволяет использовать функциональные операции для обработки данных.
Фильтрация сотрудников, пришедших в 2017 году:

java
Копировать код
.filter(e -> {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(e.getWorkStart());
    return calendar.get(Calendar.YEAR) == 2017;
})
Метод filter принимает лямбда-выражение (или предикат), которое возвращает true для сотрудников, пришедших в 2017 году.
Внутри лямбда-выражения:
Создается объект Calendar с текущей датой и временем: Calendar calendar = Calendar.getInstance();.
Устанавливается дата начала работы сотрудника в объекте Calendar: calendar.setTime(e.getWorkStart());.
Проверяется, соответствует ли год начала работы 2017 году: return calendar.get(Calendar.YEAR) == 2017;.
Нахождение сотрудника с максимальной зарплатой:

java
Копировать код
.max(Comparator.comparing(Employee::getSalary));
Метод max принимает компаратор, который определяет правила сравнения объектов.
Компаратор создается с помощью метода Comparator.comparing, который принимает ссылку на метод (Employee::getSalary), возвращающий зарплату сотрудника.
Этот компаратор сравнивает сотрудников по их зарплатам и находит сотрудника с максимальной зарплатой.
Результат операции:

java
Копировать код
Optional<Employee> highestPaidEmployee2017
Метод max возвращает объект типа Optional<Employee>, так как в потоке может не быть ни одного сотрудника, удовлетворяющего условиям фильтрации. Optional указывает на то, что результат может отсутствовать.
Что делает каждый компонент:
staff.stream(): Начинает обработку списка сотрудников как потока данных.
.filter(...): Фильтрует сотрудников, оставляя только тех, кто пришел в 2017 году.
.max(...): Находит максимальное значение (в данном случае, сотрудника с максимальной зарплатой) среди отфильтрованных сотрудников.
Optional<Employee>: Указывает на то, что результат может быть пустым, если нет сотрудников, удовлетворяющих условиям фильтрации.
         */
        //фильтрация сотрудников, которые пришли в 2017 году, и назождение с максимальной зарплатой
        Optional<Employee> highestPaidEmployee2017 = staff.stream()
                .filter(e -> {Calendar calendar = Calendar.getInstance();
                calendar.setTime(e.getWorkStart());
                return calendar.get(Calendar.YEAR) == 2017;
                })
                .max(Comparator.comparing(Employee::getSalary));

        //печать рузультата
        highestPaidEmployee2017.ifPresent(System.out::println);
    }

    // Статический метод, который загружает список сотрудников из файла
    private static ArrayList<Employee> loadStaffFromFile()
    {
        // Инициализируем список сотрудников
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            // Читаем все строки из файла
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            // Обрабатываем каждую строку
            for(String line : lines)
            {
                // Разделяем строку на части, используя табуляцию как разделитель
                String[] fragments = line.split("\t");
                // Проверяем, что строка содержит ровно 3 части
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                // Добавляем нового сотрудника в список
                staff.add(new Employee(
                        fragments[0], // Имя сотрудника
                        Integer.parseInt(fragments[1]), // Зарплата сотрудника
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2]) // Дата принятия на работу
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        // Возвращаем список сотрудников
        return staff;
    }
}