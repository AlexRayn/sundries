import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        //создаем список уникальных элементов
        TreeSet<String> emails = new TreeSet<>();

        //Принимаем эмейл из консоли и передаем в переменную
        Scanner scanner = new Scanner(System.in);
        while(true){
            String command = scanner.nextLine().trim();

            int indexDogSymbol = command.indexOf('@');
            //	LIST — выводит список электронных адресов.
            //	ADD — проверяет и, если формат адреса верный, добавляет в множество.
            if(command.substring(0,3).equalsIgnoreCase("ADD"))
            {
                if(command.substring(indexDogSymbol).equalsIgnoreCase("@gmail.com"))
                {
                    String email = command.substring(4).trim();
                    emails.add(email);
                }
                else if(command.substring(indexDogSymbol).equalsIgnoreCase("@mail.ru"))
                {
                    String email = command.substring(4).trim();
                    emails.add(email);
                }
                else System.out.println("Введите коректный емэйл адресс");
            }

            else if(command.substring(0,4).equalsIgnoreCase("LIST")) {
                for (String email : emails) {
                    System.out.println(email);
                }
            }
            else System.out.println("Введите коректную команду");
        }

    }
}