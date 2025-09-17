import java.sql.*;

public class Main {
    public static void main(String[] args) {

        //данные  необходимые для подключения к Б.Д.
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=UTF-8";
        String user = "root";
        String pass = "6728";

        //переменная строка хранящая sql-запрос: средние покупки за месяц
        String sql = "SELECT course_name, " +
                "COUNT(course_name)/(MAX(MONTH(subscription_date)) - MIN(MONTH(subscription_date)) + 1) AS Coefficient " +
                "FROM PurchaseList " +
                "WHERE YEAR(subscription_date) = 2018 " +
                " GROUP BY course_name";

        try{
            //Устанавливаем соединение с БД
            Connection connection = DriverManager.getConnection(url, user, pass);

            // Создаём объект для выполнения SQL-запросов
            Statement statement = connection.createStatement();

            //Выполняем запрос
            ResultSet resultSet = statement.executeQuery(sql);

            //Печатаем результат
            while ( resultSet.next()){
                String courseName = resultSet.getString("course_name");
                double coefficient = resultSet.getDouble("Coefficient");

                System.out.printf("%s — %.2f%n", courseName, coefficient);
            }

            // Закрываем соединения
            resultSet.close();
            statement.close();
            connection.close();

        }
        catch(Exception ex){
            System.out.println("❌ Ошибка подключения к базе данных");
            ex.printStackTrace();
        }
    }
}