public class Main {
    public static void main(String[] args) {

        float maxValueFloat = Float.MAX_VALUE;
        float minValueFloat = Float.MIN_VALUE;

        double maxValueDouble = Double.MAX_VALUE;
        double minValueDouble = Double.MIN_VALUE;

        int manValueInteger = Integer.MAX_VALUE;
        int minValueInteger = Integer.MIN_VALUE;

        long maxValueLong = Long.MAX_VALUE;
        long minValueLong = Long.MIN_VALUE;

        short maxValueShort = Short.MAX_VALUE;
        short minValueShort = Short.MIN_VALUE;

        byte maxValueByte = Byte.MAX_VALUE;
        byte minValueByte = Byte.MIN_VALUE;

        char maxValueCharacter = Character.MAX_VALUE;
        char minValueCharacter = Character.MIN_VALUE;

        //Выводим в консоль максимальное и минимальное число для типа float
        System.out.println("Максимльное число для типа float " + Float.MAX_VALUE);
        System.out.println("Минимальное число для типа float " + Float.MIN_VALUE);

        //Выводим в консоль максимальное и минимальное число для типа double
        System.out.println("Максимльное число для типа double " + Double.MAX_VALUE);
        System.out.println("Минимальное число для типа double " + Double.MIN_VALUE);

        //Выводим в консоль максимальное и минимальное число для типа int
        System.out.println("Максимльное число для типа int " + Integer.MAX_VALUE);
        System.out.println("Минимальное число для типа int " + Integer.MIN_VALUE);

        //Выводим в консоль максимальное и минимальное число для типа long
        System.out.println("Максимльное число для типа long " + Long.MAX_VALUE);
        System.out.println("Минимальное число для типа long " + Long.MIN_VALUE);

        //Выводим в консоль максимальное и минимальное число для типа short
        System.out.println("Максимльное число для типа short " + Short.MAX_VALUE);
        System.out.println("Минимальное число для типа short " + Short.MIN_VALUE);

        //Выводим в консоль максимальное и минимальное число для типа byte
        System.out.println("Максимльное число для типа byte " + Byte.MAX_VALUE);
        System.out.println("Минимальное число для типа byte " + Byte.MIN_VALUE);

        //Выводим в консоль максимальное и минимальное число для типа char
        //c помощью (int) приводим char к числовому значению / по умолчанию используется 0
        System.out.println("Максимльное число для типа cher (0) " + (int) Character.MAX_VALUE);
        System.out.println("Минимальное число для типа cher (0) " + (int) Character.MIN_VALUE);
    }
}