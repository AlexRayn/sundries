public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;
        System.out.println(container.count);
        // 12345, 10, 5059191
        System.out.print("должно получится 15 - ");
        System.out.println(sumDigits(12345));
        System.out.print("должно получится 1 - ");
        System.out.println(sumDigits(10));
        System.out.print("должно получится 30 - ");
        System.out.println(sumDigits(5059191));
    }
    public static Integer sumDigits(Integer number)
    {
        String stringNumber = Integer.toString(number);
        int sum = 0;
        for(int i = 0; i < stringNumber.length(); i++)
        {
            sum += Integer.parseInt(String.valueOf(stringNumber.charAt(i)));
        }
        return sum;
    }
}
