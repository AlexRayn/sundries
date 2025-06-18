import Company.Company;

import Employees.Employee;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаем объект компании с названием Great Company
        Company greatCompany = new Company();

        // 1. Нанимаем сотрудников
        greatCompany.hire("Operator", 180);
        greatCompany.hire("Manager", 80);
        greatCompany.hire("TopManager", 10);

        // Печатаем общий доход компании
        System.out.printf("Доход Great Company: %,.2f руб.\n", greatCompany.getIncome());

        // 2. Вывод топ-10 самых высоких зарплат
        System.out.println("\nТоп 10 самых высоких зарплат в Great Company:");
        greatCompany.printSalaries(greatCompany.getTopSalaryStaff(10));

        // 3. Вывод топ-30 самых низких зарплат
        System.out.println("\nТоп 30 самых низких зарплат в Great Company:");
        greatCompany.printSalaries(greatCompany.getLowestSalaryStaff(30));

        // 4. Увольняем 50% сотрудников
        int halfSize = greatCompany.employees.size() / 2;
        for (int i = 0; i < halfSize; i++) {
            greatCompany.fire(i + 1); // Увольнение происходит по ID
        }

        // 5. Вывод обновленного списка топ-10 самых высоких зарплат
        System.out.println("\nТоп 10 самых высоких зарплат в Great Company (после сокращения):");
        greatCompany.printSalaries(greatCompany.getTopSalaryStaff(10));

        // 6. Вывод обновленного списка топ-30 самых низких зарплат
        System.out.println("\nТоп 30 самых низких зарплат в Great Company (после сокращения):");
        greatCompany.printSalaries(greatCompany.getLowestSalaryStaff(30));

    }
}
