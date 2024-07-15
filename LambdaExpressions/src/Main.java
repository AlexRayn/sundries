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

        //сортировка сотрудников одновременно по заработной плате и алфавиту.
        Collections.sort(staff, (o1, o2) -> {
            int salaryCompare = o1.getSalary().compareTo(o2.getSalary());
            if(salaryCompare == 0) {
                return o1.getName().compareTo(o2.getName());
            } else {return salaryCompare;
            }
        });

        //метод для распечатывания списка сотрудников
        for (Employee employee : staff){
            System.out.println(employee);
        }

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