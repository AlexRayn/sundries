import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        1.	Создайте и наймите в компанию: 180 операторов Operator, 80 менеджеров по продажам Manager, 10 топ-менеджеров TopManager.
        2.	Распечатайте список из 10–15 самых высоких зарплат в компании.
        3.	Распечатайте список из 30 самых низких зарплат в компании.
        4.	Увольте 50% сотрудников.
        5.	Распечатайте список из 10–15 самых высоких зарплат в компании.
        6.	Распечатайте список из 30 самых низких зарплат в компании.Примеры вывода списка зарплат

         */

        Company company = new Company();

        //найм 180 операторов
        List<Employee> operators = new ArrayList<>();
        for (int i = 0; i < 180; i++) {
            operators.add(new Operator());
        }
        company.hireAll(operators);

        //найм 80 менеджеров
        List<Employee> managers = new ArrayList<>();
        for(int i = 0;i < 80; i++)
        {
            managers.add(new Manager(company));
        }
        company.hireAll(managers);

        //найм 10 топменеджеров
        List<Employee> topManagers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            topManagers.add(new TopManager(company));
        }
        company.hireAll(topManagers);

        // Печать списка самых высоких зарплат
        System.out.println("Топ 15 самых высоких зарплат:");
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }

        // Печать списка самых низких зарплат
        System.out.println("\nТоп 30 самых низких зарплат:");
        for (Employee employee : company.getLowestSalaryStaff(30)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }

        // Увольнение 50% сотрудников
        List<Employee> employeesToFire = new ArrayList<>(company.getEmployees());
        for (int i = 0; i < employeesToFire.size() / 2; i++) {
            company.fire(employeesToFire.get(i));
        }

        // Печать списка самых высоких зарплат после увольнения
        System.out.println("\nТоп 15 самых высоких зарплат после увольнения 50% сотрудников:");
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }

        // Печать списка самых низких зарплат после увольнения
        System.out.println("\nТоп 30 самых низких зарплат после увольнения 50% сотрудников:");
        for (Employee employee : company.getLowestSalaryStaff(30)) {
            System.out.println(employee.getMonthSalary() + " руб.");
        }

    }
}