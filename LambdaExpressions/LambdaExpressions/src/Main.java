import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main
{
    private static String staffFile = "LambdaExpressions/data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        ArrayList<Employee> staff = loadStaffFromFile();

        Collections.sort( staff,
                //сравнение с помощью лямбда выражения.
                (o1, o2) -> {
                    // Сравнение по зарплате
                    int result = o1.getSalary().compareTo(o2.getSalary());
                    // Если зарплата одинаковая — сравнение по имени
                    if (result == 0) {
                        result = o1.getName().compareTo(o2.getName());
                    }
                    return result;
                }
        );
        //распечатываем список из файла
        for(Employee employee : staff){
            System.out.println(employee);
        }

        // Выведите в консоль с помощью Stream API сотрудника с максимальной зарплатой среди тех, кто пришёл в 2017 году.

        //Вывод сотрудника с максимальной з.п, который пришёл в 2017 г
        System.out.println("\t\t\t\tВывод сотрудника с максимальной зарплатой, который пришел в компанию  в 2017г.");

        //Employee maxSalaryEmployee2017 = staff.stream().filter(e -> e.getWorkStart().getYear() == 2017).
        staff.stream().filter(e -> e.getWorkStart().getYear() == 117).max(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}