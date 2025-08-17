public class Company {

    //Создание переменных для компании
    private String companyName; //Имя компании
    private double income; // доход компании
    private double expense; //расход компании

    private static double totalIncome; //общий доход компаний
    private static double totalExpense; // общий расход компаний

    public Company(String companyName, double income, double expense)
    {
        this.companyName = companyName;
        this.income = income;
        this.expense = expense;


        // Обновляем статические переменные
        totalIncome += this.income;   // Добавляем доход этой компании к общему доходу
        totalExpense += this.expense; // Добавляем расход этой компании к общему расходу
    }

    // Геттеры
    public String getCompanyName() { return companyName; }
    public double getIncome() { return income; }
    public double getExpense() { return expense; }

    //геттеры для (общих) статических переменных
    public static double getTotalIncome() { return totalIncome; }
    public static double getTotalExpense() { return totalExpense; }

    //сеттеры
    public void setIncome(double income){
        this.income = income;
        totalIncome += income;
    }
    public void setExpense(double expense){
        this.expense = expense;
        totalExpense += expense;
    }
}
