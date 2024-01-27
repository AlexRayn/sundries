import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        8-905-1234567	79051234567	Символов 11 в номере, первый символ 8
        (код выхода на мобильный номер) заменяем на код страны 7 — номер верный.
        */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер телефона в любом формате");
        String number = scanner.nextLine().replaceAll("[^0-9]","");
        String cleanNumber = "";
        //System.out.println(number);
        int firstSymbolNumber = Integer.parseInt(number.substring(0,1));
        if(number.length() > 11){System.out.println("Неверный формат номера, цифр в номере не может быть больше 11.");}
        else if (number.length() < 10){System.out.println("Неверный формат номера, цифр в номере не может быть меньше чем 10.");}
        else if (number.length() == 11 && firstSymbolNumber == 7){cleanNumber = number;}
        else if (number.length() == 11 && firstSymbolNumber != 8)
        {
            System.out.println("Неверный формат номера,");
            System.out.println("если символов в номере больше чем 11, то первая цифра должна быть либо 7 либо 8");
            System.out.println("(7 - код страны, 8 - код выхода на мобильный номер)");
        }
        else if(number.length() == 10){cleanNumber = "7" + number;}
        else if(number.length() == 11 && firstSymbolNumber == 8){cleanNumber = "7" + number.substring(1);}

        //выводим номер в едином формате если он оказался верным
        if (cleanNumber.length() > 1){System.out.println("номер приведеный к единному формату - " + cleanNumber);}
    }
}