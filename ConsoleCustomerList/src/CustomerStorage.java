import java.util.HashMap;
public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws IllegalAccessException {
        String[] components = data.split("\\s+");
        String name = components[0] + " " + components[1];

        // Проверка, что команда содержит ровно 4 элемента
        if (components.length != 4){throw new IllegalArgumentException("КОМАНДА add ДОЛЖНА СОДЕРЖАТЬ 4 ЭЛЕМЕНТА" + Main.commandError);}

        // Проверка, что email заканчивается на "@gmail.com"
        if(!components[2].endsWith("@gmail.com")){throw new IllegalArgumentException("Email ВВЕДЕН НЕ ВЕРНО " + Main.commandError);}

        // Удаление всех нецифровых символов из строки номера телефона
        String cleanPhone = components[3].replaceAll("[^0-9]","");

        // Проверка, что очищенный номер телефона содержит ровно 11 цифр
        if(cleanPhone.length() != 11){throw new IllegalArgumentException("4 ЭЛЕМЕНТ НЕ СОДЕРЖИТ НОМЕРА ИЛИ ОН ВВЕДЕН НЕ КОРРЕКТНО " + Main.commandError);}

        // Создание нового объекта Customer и добавление его в хранилище
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        System.out.println("Listing all customers. Current size: " + storage.size());
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        System.out.println("Removing customer with name: " + name);
        storage.remove(name);
        System.out.println("Customer removed. Current storage size: " + storage.size());
    }
    public boolean customerExists(String name) {
        return storage.containsKey(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}