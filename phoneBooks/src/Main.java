import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Создаем коллекцию, где ключом является имя контакта
        HashMap<String, String> contacts = new HashMap<>();

        // Создание бесконечного цикла for
        for (;;) {
            // Выводим приглашение для ввода
            System.out.println("\t\tВведите номер телефона или имя контакта.");
            System.out.println("\t\tИли введите LIST для вывода списка контактов.");

            String input = scanner.nextLine();

            // Распечатываем коллекцию при вводе LIST
            if (input.equals("LIST")) {
                // Вызываем метод для распечатывания элементов
                printMap(contacts);
                continue;
            }

            // Переводим номер из ввода, если он есть, в число
            String number = input.replaceAll("[^0-9]", "");

            boolean isNumber = !number.isEmpty();
            boolean isInCollection = false;

            if (isNumber) {
                String name = "";
                // Проверяем, есть ли в коллекции номер телефона
                for (HashMap.Entry<String, String> entry : contacts.entrySet()) {
                    if (entry.getValue().equals(number)) {
                        isInCollection = true;
                        name = entry.getKey();
                        break;
                    }
                }
                if (isInCollection) {
                    System.out.println(number + " данный номер привязан к контакту - " + name);}
                else {
                    System.out.println("Введите имя контакта для номера - " + number);
                    String nameInput = scanner.nextLine();
                    contacts.put(nameInput, number);
                    System.out.println("\t\tКонтакт с именем " + nameInput + " и номером " + number + " создан");}
            }
            else {
                // Проверяем, содержит ли коллекция данный ключ (имя контакта)
                if (contacts.containsKey(input)) {
                    System.out.println(input + " данный контакт привязан к номеру - " + contacts.get(input));}
                else {
                    // Если имени нет в коллекции, запрашиваем номер и добавляем в коллекцию
                    System.out.println("Введите номер телефона для контакта - " + input);
                    String numberInput = scanner.nextLine();
                    contacts.put(input, numberInput);
                    System.out.println("\t\tКонтакт с именем " + input + " и номером " + numberInput + " создан");}
            }
        }
    }

    private static void printMap(HashMap<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println("\tконтакт " + key + " с номером телефона - " + map.get(key));
        }
    }
}