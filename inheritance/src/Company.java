import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company {
    //List<Employee> означает, что список будет содержать объекты типа Employee или
    // объекты любого класса, который реализует интерфейс Employee.

    //создаем список элементов который будет хранить сотрудников
    private ArrayList<Employee> employees = new ArrayList<>();

    //создаем переменную которая хранит баланс
    private double income;

    //создаем метод hire, найм одного сотрудника, employee — это объект, который реализует интерфейс Employee
    public void hire(Employee employee)
    {
        employees.add(employee);
    }

    //создаем метод hireAll, найм списка сотрудников
    public void hireAll(List<Employee> employeesList)
    {
        for(Employee employee : employeesList)
        {
            hire(employee);
        }
    }

    //создаем метод fire, для удаления сотрудника
    public void fire(Employee employee)
    {
        employees.remove(employee);
    }

    //создаем метод возвращения дохода
    public double getIncome()
    {
        return income;
    }

    //метод добавления дохода компании
    public void addIncome(double amount)
    {
        income += amount;
    }

    /*
    employees.sort(...) — сортирует список employees
    Comparator.comparingDouble(Employee::getMonthSalary) — создает компаратор, который сравнивает сотрудников по их месячной зарплате (метод getMonthSalary интерфейса Employee).
    .reversed() — меняет порядок сортировки на обратный. Без этого метода сортировка была бы по возрастанию зарплаты, но с .reversed() она становится по убыванию.

    return employees.subList(0, Math.min(count, employees.size()));
    employees.subList(0, ...) — метод subList возвращает представление части списка от индекса 0 до указанного конечного индекса (не включительно). Это позволяет получить подсписок первых count элементов.
    Math.min(count, employees.size()) — возвращает меньшее из двух чисел: count или размер списка сотрудников (employees.size()).
    Если count больше количества сотрудников в списке, вернется количество сотрудников. Это предотвращает выброс исключения IndexOutOfBoundsException.
    */

    // Метод для сортировки списка сотрудников по зарплате в порядке убывания
    public List<Employee> getTopSalaryStaff(int count) {
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary).reversed());
        return employees.subList(0, Math.min(count, employees.size()));
    }

    //Метод для сортировки списка сотрудников по зарплате в порядке возростания
    public List<Employee> getLowestSalaryStaff(int count) {
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        return employees.subList(0, Math.min(count, employees.size()));
    }
    //метод для получения списка всех сотрудников
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
}
