package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    // @EmbeddedId указывает, что это композитный первичный ключ (состоит из student_id и course_id)
    @EmbeddedId
    private SubscriptionKey id;

    // Связь многие-к-одному: много подписок → один студент
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    // insertable=false, updatable=false - потому что это поле уже управляется через @EmbeddedId
    private Student student;

    // Связь многие-к-одному: много подписок → один курс
    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    // insertable=false, updatable=false - потому что это поле уже управляется через @EmbeddedId
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    // Конструктор по умолчанию
    public Subscription() {
    }

    // Геттеры и сеттеры
    public SubscriptionKey getId() {
        return id;
    }

    public void setId(SubscriptionKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
