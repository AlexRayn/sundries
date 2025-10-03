package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// @Embeddable означает, что этот класс будет встроен в другой Entity как составной ключ
@Embeddable
public class SubscriptionKey implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    // Конструктор по умолчанию (обязателен для JPA)
    public SubscriptionKey() {
    }

    public SubscriptionKey(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Геттеры и сеттеры
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    // equals и hashCode обязательны для композитного ключа
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionKey that = (SubscriptionKey) o;
        return studentId == that.studentId && courseId == that.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}