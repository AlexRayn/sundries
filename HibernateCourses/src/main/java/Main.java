import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        // Настройка Hibernate и создание SessionFactory (делается один раз на приложение)
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build(); // Загружаем настройки из конфигурационного файла

        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        // Получаем сессию
        Session session = sessionFactory.openSession();

        // Получаем объект Course с id = 46 из базы данных
        Course course = session.get(Course.class, 1);
        // session.get() сразу возвращает объект или null, если запись не найдена

        //  Выводим данные курса
        System.out.println("Название курса: " + course.getName() + "  Количество учащихся - " + course.getStudentsCount());
        System.out.println("Тип курса: " + course.getType());
        System.out.println("Продолжительность курса - " + course.getDuration());

        // Закрываем ресурсы
        session.close();
        sessionFactory.close();
    }
}
