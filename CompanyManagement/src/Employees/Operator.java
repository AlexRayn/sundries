package Employees;

public class Operator implements Employee {

    //переменная, хранящая тип сотрудника
    private final String typeOfEmployee = "Operator";
    // переменная, для хранения ID сотрудника
    private String idOfEmployee;

    // Конструктор для создания оператора с уникальным ID
    public Operator(String idOfEmployee){
        this.idOfEmployee = idOfEmployee;
    }


    //реализация метода получения месячной зарплаты
    // зарплата складывается только из фиксированной части.
    public double getMonthlySalary(){
        return basicSalary;
    }

    //реализация метода получения ID сотрудника
    public String getId(){
        return idOfEmployee;
    }

    //гетер для получения типа работника
    public String getTypeOfEmployee(){
        return typeOfEmployee;
    }
}

