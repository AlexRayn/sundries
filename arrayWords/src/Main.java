public class Main {
    public static void main(String[] args) {
        String text = "Каждый охотник желает знать, где сидит фазан";

        //создаем масив строк и заполняем разделив строку текста по словам
        String [] arrayWords = text.split(",?\\s+");

        //перебераем элементы массива в обратном порядке
        for( int i = arrayWords.length - 1; i >= 0; i--){System.out.println(arrayWords[i]);}
    }
}