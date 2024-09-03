
public class Loader
{
    public static void main(String[] args)
    {
        //indexOf(), - возвращает номер индекса
        // lastIndexOf(), возвращает номер индекса с конца
        // substring(...) - возвращает подстроку начиная с указаного символа
        // и trim(). удаляем пробелы с начала и конца сроки

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        int firstSpace = text.indexOf(' ');
        int secondSpace = text.indexOf(' ', firstSpace + 1);
        int thirdSpace = text.indexOf(' ', secondSpace + 1);
        int fourthSpace = text.indexOf(' ', thirdSpace + 1);
        int fifthSpace = text.indexOf(' ', fourthSpace + 1);
        int sixthSpace = text.indexOf(' ', fifthSpace + 1);
        int seventhSpace = text.indexOf(' ', sixthSpace + 1 );
        int lastSpace = text.lastIndexOf(' ');
        int penultSpace = text.lastIndexOf(' ', lastSpace - 1);
        String wageVasya = text.substring(secondSpace, thirdSpace).trim();
        String wagePetr = text.substring(sixthSpace, seventhSpace).trim();
        String wageMisha = text.substring(penultSpace, lastSpace).trim();
        int sum = Integer.parseInt(wageVasya) + Integer.parseInt(wageMisha) + Integer.parseInt(wagePetr);
        System.out.println("Сумма заработка всех друзей: " + sum + " рублей");
    }
}