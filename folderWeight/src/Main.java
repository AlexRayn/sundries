import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                // Приглашение к вводу
                System.out.println("Введите путь к директории (или введите exit для выхода):");

                String input = scanner.nextLine().trim();

                // Возможность завершить выполнение программы
                if ("exit".equalsIgnoreCase(input)) {
                    System.out.println("Выход из программы.");
                    break;
                }

                // Создание объекта File
                File dir = new File(input);

                if (!dir.exists() || !dir.isDirectory()) {
                    // Сообщение об ошибке, если путь не существующий или не является папкой
                    System.out.println("Указанный путь не существует или не является директорией. Попробуйте снова.");
                    continue;
                }

                // Вызываем метод подсчета размера папки
                long totalSize = calculateDirectorySize(dir);

                // Вывод результата в мегабайтах или гигабайтах
                if (totalSize >= 1024L * 1024 * 1024) {
                    System.out.printf("Размер папки %s составляет: %.2f Гб\n", dir.getAbsolutePath(), totalSize / (1024.0 * 1024 * 1024));
                } else {
                    System.out.printf("Размер папки %s составляет: %.2f Мб\n", dir.getAbsolutePath(), totalSize / (1024.0 * 1024));
                }
            }
        } catch (Exception ex) {
            System.out.println("Произошла ошибка:");
            ex.printStackTrace(); // Печать трассировки ошибки
        }
    }

    // Метод для подсчета размера директории
    public static long calculateDirectorySize(File dir) {
        long size = 0;

        // Получаем содержимое папки
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    // Суммируем размеры файлов
                    size += file.length();
                } else if (file.isDirectory()) {
                    // Рекурсивно считаем размер вложенных папок
                    size += calculateDirectorySize(file);
                }
            }
        }

        return size;
    }
}