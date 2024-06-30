public class TopManager implements Employee {

    //TopManager — зарплата складывается из фиксированной части и бонуса в виде 150%
    // от заработной платы, если доход компании более 10 млн рублей.

    private static final double BASE_SALARY = 100000;//переменная которая хранит базовую зарплату топменеджера

    private Company company;//переменная для хранения ссылки на объект компании, к которой принадлежит данный сотрудник

    //конструктор для создания объекта Manager с привязкой к конкретной компании.
    public TopManager(Company company) {
        this.company = company;
    }

    //переопределяем метод инициализированный в классе Employee
    @Override
    public double getMonthSalary() {
        //если доход компании более 10 млн. руб.
        if (company.getIncome() > 10000000) {
            return BASE_SALARY * 2.5;
        }
        //если доход компании менше
        else {
            return BASE_SALARY;
        }
    }
}
