package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Integer salary;
    private Integer age;

    // Связь один-ко-многим: один учитель → много курсов
    @OneToMany(mappedBy = "teacher")
    // mappedBy указывает, что связь управляется полем "teacher" в классе entity.Course
    private List<Course> courses;

    // Геттеры и сеттеры для каждой переменной
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // Геттер и сеттер для entity.Course
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}