import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data)
    {
        String[] components = data.split("\\s+");
        if(components.length != 4){throw new IllegalArgumentException("Не правильный формат, правильный формат: \n add Василий Петров vasily.petrov@gmail.com +79215637722");}
        String name = components[0] + " " + components[1];
        //Добавляем проверку корректности email
        if (!components[2].matches(".+@gmail\\.com")) {
            throw new IllegalArgumentException("Не корректный формат email, правильный формат: vasily.petrov@gmail.com");
        }
        if (!components[3].matches("\\+7\\d{10}")) {
            throw new IllegalArgumentException("Не корректный формат email, правильный формат: vasily.petrov@gmail.com");
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        // Если метод remove возвращает null, это значит, что элемент с ключом name не существует
        Customer removedCustomer = storage.remove(name);
        if (removedCustomer == null) {
            // Генерируем исключение, если клиент с таким именем отсутствует
            throw new IllegalArgumentException("Клиент с именем \"" + name + "\" не найден в коллекции!");
        } else {
            // Уведомляем об успешном удалении
            System.out.println("Клиент \"" + name + "\" был успешно удален из коллекции.");
        }
    }

    public int getCount()
    {
        return storage.size();
    }
}