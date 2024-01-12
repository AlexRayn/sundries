import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        /*
        System.out.println(45 % 10); // вывод будет 5.

        В контексте предыдущего примера, Math.ceil((double) countCrates / boxesInContainer)
         используется для определения общего количества контейнеров.
         Это гарантирует, что даже если результат деления не является целым числом,
        метод Math.ceil() округлит его вверх до ближайшего целого, чтобы удовлетворить условия задачи.
        */
        //Создаем класс сканер
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество ящиков,");
        System.out.println(" которые необходимо распределить: ");
        //Считываем количество ящиков с консоли и задаем переменной.
        int countCrates = scanner.nextInt();

        int crateInContainers = 27;
        int containersInTruck = 12;


        int totalContainers = (int) Math.ceil((double) countCrates / crateInContainers);
        int totalTruck = (int) Math.ceil((double) totalContainers / containersInTruck);

        int container = 0;
        int boxes = 0;

        for(int i = 1; i <= totalTruck; i++)
        {
            System.out.println("Грузовик - " + i);
            for(int a = 1;container < totalContainers && a <= containersInTruck; a++)
            {
                container += 1;
                System.out.println("\t\t\tКонтейнер - " + container);
                for(int j = 0; j < crateInContainers && boxes < countCrates; j++)
                {
                    boxes += 1;
                    System.out.println("\t\t\t\t\t\tЯщик - " + boxes);
                }
            }

        }
        System.out.println(" ");
        System.out.println("Для - " + countCrates + " Ящиков, Вам потребуется :");
        System.out.println(totalTruck + " - Грузовиков.");
        System.out.println(totalContainers + "- Контейнеров.");

    }
}