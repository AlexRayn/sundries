public class Main {
    public static String searchAndReplaceDiamonds (String text, String placeholder)
    {
    int firstIndex = text.indexOf('<');
    int secondIndex = text.indexOf('>');
    return text.substring(0, firstIndex) + placeholder + text.substring(secondIndex + 1);
    //String safe = searchAndReplaceDiamonds(«Номер кредитной карты <4008 1234 5678> 8912», «***»);
    }
    public static void main(String[] args)
    {
        // Пример работы метода
        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe);
    }
}