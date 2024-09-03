import java.util.Scanner;

public class Main
{
    public static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    public static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    public static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    public static String helpText = "Command examples:\n" + commandExamples;

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        for(;;)
        {
            String command = scanner.nextLine();
            //Число 2 в методе split указывает на максимальное количество частей,
            // на которые должна быть разбита строка. То есть, строка будет разбита на максимум два токена.
            String[] tokens = command.split("\\s+", 2);
            try{
                if(tokens[0].equals("add")) {

                    if(tokens.length == 1){throw new IllegalAccessException("КОМАНДА add ДОЛЖНА СОДЕРЖАТЬ 4 ЭЛЕМЕНТА " + commandError);}
                    executor.addCustomer(tokens[1]);
                }
                else if(tokens[0].equals("list")) {
                    if(tokens.length != 1){throw new IllegalAccessException("КОМОНДА list НЕ ДОЛЖНА СОДЕРЖАТЬ ДОПОЛНЕНИЯ " + commandError);}
                    executor.listCustomers();
                }
                else if(tokens[0].equals("remove"))
                {
                    if(tokens.length == 1){throw new IllegalAccessException("КОМАНДА remove ДОЛЖНА СОДЕРЖАТЬ ИМЯ ЭЛЕМЕНТА " + commandError);}

                    if(!executor.customerExists(tokens[1])){throw new IllegalArgumentException("ЭЛЕМЕНТ С ТАКИМ ИМЕННЕМ НЕ НАЙДЕН В КОЛЛЕКЦИИ " + commandError);}
                    executor.removeCustomer(tokens[1]);

                }
                else if(tokens[0].equals("count")) {
                    if(tokens.length != 1){throw new IllegalAccessException("КОМОНДА count НЕ ДОЛЖНА СОДЕРЖАТЬ ДОПОЛНЕНИЯ " + commandError);}
                    System.out.println("There are " + executor.getCount() + " customers");
                }
                else if(tokens[0].equals("help")) {
                    if(tokens.length != 1){throw new IllegalAccessException("КОМОНДА help НЕ ДОЛЖНА СОДЕРЖАТЬ ДОПОЛНЕНИЯ " + commandError);}
                    System.out.println(helpText);

                }
                else {
                    System.out.println(commandError);
                }
            }catch(Exception Ex){
                System.out.println(Ex.getMessage()); }
        }
    }
}