import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee
{
    //инициализация переменных
    private String name;
    private Integer salary;
    private Date workStart;

    //конструктор(метод) сотрудника, содержит имя, з.п., и дату начала работы
    public Employee(String name, Integer salary, Date workStart)
    {
        this.name = name;
        this.salary = salary;
        this.workStart = workStart;
    }

    //геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Date workStart) {
        this.workStart = workStart;
    }

    //метод который печатает всю информацию о сотруднике
    public String toString()
    {
        return name + " - " + salary + " - " +
                (new SimpleDateFormat("dd.MM.yyyy")).format(workStart);
    }
}
