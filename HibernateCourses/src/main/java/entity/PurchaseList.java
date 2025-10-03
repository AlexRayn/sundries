package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PurchaseList {

    @Column(name = "student_name")
    String studentName;
    @Column(name ="course_name")
    String courseName;
    int price;
    @Column(name ="subscription_date")
    Date subscriptionDate;

    //геттеры и сеттеры
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
