package Employees;
import Company.Company;


public class TopManager implements Employee {



    //переменная, хранящая тип сотрудника
    String typeOfEmployee = "TopManager";
    // переменная, для хранения ID сотрудника
    private String idOfEmployee;
    private Company company;
    // Конструктор для создания топМенеджера с уникальным ID
    public TopManager(String idOfEmployee, Company company) {
        this.idOfEmployee = idOfEmployee;
        this.company = company;
    }


    //реализация метода получения ID сотрудника
    public String getId(){
        return idOfEmployee;
    }

    //Реализация метода получения месячной зарплаты
     /* TopManager — зарплата складывается из фиксированной части и бонуса в виде 150% от
        заработной платы, если доход компании более 10 млн рублей. */
    public double getMonthlySalary() {
        if (company.getIncome() > 10_000_000) {
            return basicSalary + (basicSalary * 1.5);
        } else {
            return basicSalary;
        }
    }


    //гетер для получения типа работника
    public String getTypeOfEmployee(){
        return typeOfEmployee;
    }
}
