public class Operator implements Employee {

    //Operator — зарплата складывается только из фиксированной части.

    private static final double BASE_SALARY = 30000;//переменная которая хранит базовую зарплату оператора

    //переопределяем метод инициализированный в классе Employee
    @Override
    public double getMonthSalary() {
        return BASE_SALARY;
    }
}
