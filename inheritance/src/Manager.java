import java.util.Random;

public class Manager implements Employee {

    // Manager — зарплата складывается из фиксированной части и бонуса в виде 5%
    // от заработанных для компании денег. Количество заработанных денег для компании
    // генерируйте случайным образом от 115 000 до 140 000 рублей.


    private static final double BASE_SALARY = 50000;//переменная которая хранит базовую зарплату менеджера

    private double earnedForCompany;//инициализируем переменную заработанных для компании денежнхы средств

    private Company company;//переменная для хранения ссылки на объект компании, к которой принадлежит данный сотрудник

    //конструктор для создания объекта Manager с привязкой к конкретной компании.
    public Manager(Company company) {
        this.company = company;

        //генерируем заработанные для компании деньги, в заданном диапазоне
        this.earnedForCompany = new Random().nextInt(25000) + 115000;
        company.addIncome(earnedForCompany);
    }

    //переопределяем метод инициализированный в классе Employee
    @Override
    public double getMonthSalary() {
        return BASE_SALARY + 0.05 * earnedForCompany;
    }
}
