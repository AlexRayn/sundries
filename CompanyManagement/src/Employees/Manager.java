package Employees;

public class Manager implements Employee {

    //переменная, хранящая тип сотрудника
    String typeOfEmployee = "Manager";
    // переменная, для хранения ID сотрудника
    private String idOfEmployee;

    //индивидуальный вклад работника в доход компании
    int individualContributionIncome;
    //Бонус сотрудника
    double bonusOfEmployee;

    // Конструктор для создания оператора с уникальным ID
    public Manager(String idOfEmployee){
        //генерация случайного числа, который менеджер заработал для компании от 115к до 140к
        individualContributionIncome = 115000 + (int) (Math.random() * (140000 - 115000 + 1));
        //бонус в размере 5% от заработанных для компании д.с.
        bonusOfEmployee = individualContributionIncome * 0.05;
        this.idOfEmployee = idOfEmployee;
    }

    //реализация метода получения ID сотрудника
    public String getId(){
        return idOfEmployee;
    }

    //Геттер для получения индивидуального вклада
    public int getIndividualContributionIncome(){
        return individualContributionIncome;
    }


    //Реализация метода получения месячной зарплаты для менеджера
    /*  Manager — зарплата складывается из фиксированной части и бонуса в виде 5% от заработанных для компании денег.
    Количество заработанных денег для компании генерируйте случайным образом от 115 000 до 140 000 рублей. */
    public double getMonthlySalary() {
        return bonusOfEmployee + basicSalary;
    }

    //гетер для получения типа работника
    public String getTypeOfEmployee() {
        return typeOfEmployee;
    }
}
