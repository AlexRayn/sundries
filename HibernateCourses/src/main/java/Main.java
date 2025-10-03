import entity.Course;
import entity.Student;
import entity.Subscription;
import entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Настройка Hibernate и создание SessionFactory (делается один раз на приложение)
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build(); // Загружаем настройки из конфигурационного файла

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        // Получаем сессию
        Session session = sessionFactory.openSession();

        // Проверяем класс entity.Course
        // Получаем объект entity.Course с id = 1 из базы данных
        Course course = session.get(Course.class, 1);
        // session.get() сразу возвращает объект или null, если запись не найдена

        if (course != null) {
            System.out.println("=== Информация о курсе ===");
            System.out.println("Название курса: " + course.getName() + "  Количество учащихся - " + course.getStudentsCount());
            System.out.println("Тип курса: " + course.getType());
            System.out.println("Продолжительность курса - " + course.getDuration());

            // Проверяем класс entity.Teacher
            // Получаем имя учителя напрямую
            System.out.println("\n=== Учитель курса ===");
            System.out.println(course.getTeacher().getName());

            // Проверяем класс entity.Student через подписки
            // Получаем всех студентов курса через подписки, выводим их имена
            System.out.println("\n=== Студенты курса ===");
            List<Subscription> subscriptions = course.getSubscriptions();
            for (Subscription subscription : subscriptions) {
                Student student = subscription.getStudent();
                System.out.println("Студент: " + student.getName() +
                        ", дата подписки: " + subscription.getSubscriptionDate());
            }
        }

        // Пример: получаем студента и его курсы
        Student student = session.get(Student.class, 1);
        if (student != null) {
            System.out.println("\n=== Курсы студента " + student.getName() + " ===");
            List<Subscription> studentSubscriptions = student.getSubscriptions();
            for (Subscription sub : studentSubscriptions) {
                Course studentCourse = sub.getCourse();
                System.out.println("Курс: " + studentCourse.getName() +
                        ", дата подписки: " + sub.getSubscriptionDate());
            }
        }

        // Пример: получаем учителя и его курсы
        Teacher teacher = session.get(Teacher.class, 1);
        if (teacher != null) {
            System.out.println("\n=== Курсы учителя " + teacher.getName() + " ===");
            List<Course> teacherCourses = teacher.getCourses();
            for (Course teacherCourse : teacherCourses) {
                System.out.println("Курс: " + teacherCourse.getName());
            }
        }

        // Закрываем ресурсы
        session.close();
        sessionFactory.close();
    }
}