
public class Main {
    public static void main(String[] args) {
        String capitals ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase ="abcdefghijklmnopqrstuvwxyz";

        //метод substring возвращает значение строки по указаному индексу
        // и если указать второй индекс то оканчивая этим индексом

        //печатаем код для заглавных букв
        for(int i = 0; i <capitals.length(); i++){
            int a = i + 1;
            char unicode = capitals.charAt(i);
            int intUnicode = (int) unicode;
            System.out.println("Код для буквы " + capitals.substring(i,a) + " - " + intUnicode);
        }

        //печатаем код для строчных букв
        for(int i = 0; i <lowercase.length(); i++){
            int a = i + 1;
            char unicode = lowercase.charAt(i);
            int intUnicode = (int) unicode;
            System.out.println("Код для буквы " + lowercase.substring(i,a) + " - " + intUnicode);
        }
    }
}