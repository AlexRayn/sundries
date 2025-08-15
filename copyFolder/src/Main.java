import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (;;) { // Бесконечный цикл
            try {
                // Приглашение пользователя к вводу пути исходной папки или файла
                System.out.println("Введите путь к директории или файлу, который хотите скопировать:");
                String oldFolderPath = scanner.nextLine().trim();

                // Приглашение пользователя к вводу пути целевой директории
                System.out.println("Введите путь в директорию, куда нужно скопировать (оканчивается именем новой директории или файла):");
                String newFolderPath = scanner.nextLine().trim();

                // Вызов метода для копирования папки или файла
                copyFolderOrFile(new File(oldFolderPath), new File(newFolderPath));

                // Сообщение пользователю об успешном завершении операции
                System.out.println("Копирование завершено!");

            }
            // Обработка ошибок
            catch (Exception ex) {

                System.out.println("Произошла ошибка: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    //Метод для копирования файлов и папок.
    public static void copyFolderOrFile(File source, File dest) {
        // Проверяем, существует ли исходный файл или папка
        if (!source.exists()) {
            System.out.println("Файл или папка не существует: " + source.getAbsolutePath());
            return; // Если не существует, выходим из метода
        }

        try {
            if (source.isDirectory()) {
                // Проверяем и создаем директорию
                if (!dest.exists() && !dest.mkdirs()) {
                    System.out.println("Не удалось создать директорию: " + dest.getAbsolutePath());
                    return; // Если директория не создается, выходим из метода
                }

                // Получаем список файлов и папок внутри исходной папки
                File[] files = source.listFiles();
                if (files != null) {
                    // обрабатываем каждый файл и папку
                    for (File file : files) {
                        copyFolderOrFile(file, new File(dest, file.getName()));
                    }
                }
            } else if (source.isFile()) {
                // Если объект является файлом, копируем его с использованием потоков
                try (InputStream in = new FileInputStream(source);
                     OutputStream out = new FileOutputStream(dest)) {

                    // Создаем буфер размером 1024 байта для чтения данных
                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    // читаем данные из файлов и записываем их в целевой файл
                    while ((bytesRead = in.read(buffer)) > 0) {
                        out.write(buffer, 0, bytesRead); // Записываем только прочитанные байты
                    }
                }
                // Вывод сообщения об успешном копировании файла
                System.out.println("Файл скопирован: " + source.getAbsolutePath());
            }
            // обрабатываем ошибки
        } catch (IOException e) {

            System.out.println("Ошибка при копировании: " + source.getAbsolutePath());
            e.printStackTrace();
        }
    }
}