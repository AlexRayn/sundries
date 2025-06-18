package Employees;

public interface Employee {

    public static final double basicSalary = 10000;
    public double getMonthlySalary();

    // Метод для получения ID сотрудника
    String getId();
    String getTypeOfEmployee();


    /*
        Каждый класс сотрудника должен имплементировать интерфейс Employee. В интерфейсе Employee должен
        быть объявлен метод, возвращающий зарплату сотрудника:   getMonthSalary()
        Аргументы и возвращаемое значение метода выберите в соответствии с логикой начисления зарплат.
    * */
}
