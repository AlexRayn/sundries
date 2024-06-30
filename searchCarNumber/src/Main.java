import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Создаем ArrayList для хранения номеров автомобилей
        ArrayList<String> carNumbers = new ArrayList<>();

        //создаем массив с подходящими буквами
        String [] letters = {"С", "М", "Т", "В", "А", "Р", "О", "Н", "Е", "У", "Х", "К"};

        // Основной цикл генерации номеров
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < letters.length; j++) {
                for (int k = 0; k < letters.length; k++) {
                    for (int l = 111; l <= 999; l += 111) {
                        for (int m = 1; m <= 190; m++) {
                            //создаем строку с номером
                            String number = letters[i] + l + letters[j] + letters[k] + m;
                            carNumbers.add(number);
                        }
                    }
                }
            }
        }

        // Выводим общее количество сгенерированных номеров
        System.out.println("было сгенерированно - " + carNumbers.size() + " номеров");

        //добавляем колекцию HashSet и копируем в нее все элементы коллекции ArrayList
        HashSet<String> carNumbersHashSet = new HashSet<>();
        carNumbersHashSet.addAll(carNumbers);

        //добавляем колекцию TreeSet и копируем в нее все элементы коллекции ArrayList
        TreeSet<String> carNumbersTreeSet = new TreeSet<>();
        carNumbersTreeSet.addAll(carNumbers);


        //Создаем класс сканер
        Scanner scanner = new Scanner(System.in);

        //вечный цикл для поиска номера
        for (;true;)
        {
            //Вводим приветствие для терминала и присваиваем значение переменной
            System.out.println("Введите номер который необходимо найти, пример: В888РА190");
            String numberForFind = scanner.nextLine();

            //устанавливаем метку времени старата
            long start = System.nanoTime();
            //Поиск методом прямого перебора номеров с помощью list.contains()
            carNumbers.contains(numberForFind);
            //вычисляем время операции
            long duration = System.nanoTime() - start;
            System.out.println("Время поиска методом прямого перебора ArrayList - " + duration);


            //Сортировка ArrayList carNumbers в (алфавитном порядке)
            Collections.sort(carNumbers);

            //устанавливаем метку времени старта
            long start1 = System.nanoTime();
            //Бинарный поиск с помощью метода Collection.binarySearch()
            //Метод Collections.binarySearch()возвращает int, если возвращаемое значение больше или равно нулю - это означает, что элемент найден.
            //Если возвращаемое значение int меньше нуля — элемент в коллекции не найден.
            int index = Collections.binarySearch(carNumbers, numberForFind);
            //вычисляем время операции
            long duration1 = System.nanoTime() - start1;
            System.out.println("Время поиска методом Бинарного поиска - " + duration1);

            //устанавливаем метку времени старта
            long start2 = System.nanoTime();
            //Поиск коллекции TreeSet
            carNumbersTreeSet.contains(numberForFind);
            //вычисляем время операции
            long duration2 = System.nanoTime() - start2;
            System.out.println("Время поиска методом прямого перебора TreeSet - " + duration2);

            //устанавливаем метку времени старта
            long start3 = System.nanoTime();
            //Поиск коллекции HashSet
            carNumbersHashSet.contains(numberForFind);
            //вычисляем время операции
            long duration3 = System.nanoTime() - start3;
            System.out.println("Время поиска методом прямого перебора HashSet - " + duration3);

            System.out.println();
        }
    }
}