package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @Column(name = "students_count")
    private Integer studentsCount;

    private int price;

    @Column(name = "price_per_hour")
    private float pricePerHour;

    // Связь многие-к-одному: много курсов → один учитель
    // Свойство cascade задаёт, как операции с этим объектом (сохранение, обновление, удаление) будут распространяться на связанные сущности.
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    // @JoinColumn указывает колонку в таблице Courses, которая хранит внешний ключ на Teachers
    private Teacher teacher;

    // Связь один-ко-многим: один курс → много подписок
    @OneToMany(mappedBy = "course")
    // mappedBy указывает, что связь управляется полем "course" в классе Subscription
    private List<Subscription> subscriptions;

    // Геттеры и сеттеры
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(float pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    // Геттер и сеттер для Teacher
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    // Геттер и сеттер для Subscription
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}