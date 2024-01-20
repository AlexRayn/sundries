import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String fullName = scanner.nextLine();
        String text = fullName.trim();

        int FistSpaceIndex = text.indexOf(' ');
        int secondSpaceIndex = text.lastIndexOf(' ');

        String lastName = text.substring(0,FistSpaceIndex).trim();
        String firstName = text.substring(FistSpaceIndex, secondSpaceIndex).trim();
        String paternalName = text.substring(secondSpaceIndex).trim();

        System.out.println("Фамилия - " + lastName);
        System.out.println("Имя - " + firstName);
        System.out.println("Отчество - " + paternalName);
    }
}