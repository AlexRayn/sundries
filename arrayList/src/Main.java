import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean bool = true;
        char symbolSpace = ' ';
        //создаем список элементов
        ArrayList <String> toDoList = new ArrayList<>();

        //Создаем класс сканер
        Scanner scanner = new Scanner(System.in);
        //Создаем вечный цикл
        while(bool){
            String text = scanner.nextLine().trim();

            //Код для команды ADD
            if(text.charAt(0) == 'A')
            {
                //Проверяем есть ли двухзначный индекс
                if(Character.isDigit(text.charAt(5)))
                {
                    int index = (toDoList.size() >= Integer.parseInt(text.substring(4,6)) - 1 ? Integer.parseInt(text.substring(4,6)) - 1 : toDoList.size());

                    String textAdd = text.substring(6).trim();
                    toDoList.add(index, textAdd);
                }

                //Проверяем есть ли однозначный индекс
                else if(Character.isDigit(text.charAt(4)))
                    {
                        int index = (toDoList.size() >= Integer.parseInt(text.substring(4,5)) - 1 ? Integer.parseInt(text.substring(4,5)) - 1 : toDoList.size());
                        String textAdd = text.substring(5).trim();
                        toDoList.add(index, textAdd);
                    }

                //добавляем дело без индекса
                else
                {

                    String textAdd = text.substring(3).trim();
                    toDoList.add(textAdd);
                }
            }

            //Код для команды LIST
            if(text.charAt(0) == 'L'){
                System.out.println("\t\tСписок дел");
                for(int i = 0; i < toDoList.size();i++)
                {
                    System.out.println((i + 1) + " " + toDoList.get(i));
                }
            }

            //Код для команды EDIT
            if(text.charAt(0) == 'E')
            {
                //Проверяем есть ли двухзначный индекс
                if(Character.isDigit(text.charAt(6)))
                {
                    if(toDoList.size() >= Integer.parseInt(text.substring(5,7)))
                    {
                        int index = Integer.parseInt(text.substring(5,7)) - 1;
                        String textEdit = text.substring(7).trim();
                        toDoList.set(index, textEdit);
                    }
                    else System.out.println("Дело с таким индексом не существует");
                }
                //код для однозначного индекса
                else if(Character.isDigit(text.charAt(5)))
                {
                    if(toDoList.size() >= Integer.parseInt(text.substring(5,6)))
                    {
                    int index = Integer.parseInt(text.substring(5,6)) - 1;
                    String textEdit = text.substring(6).trim();
                    toDoList.set(index, textEdit);
                    }
                    else System.out.println("Дело с таким индексом не существует");
                }
            }

            //Код для команды DELETE
            if(text.charAt(0) == 'D')
            {
                if(toDoList.size() >= Integer.parseInt(text.substring(6).trim()))
                {
                    int index = Integer.parseInt(text.substring(6).trim()) - 1;
                    toDoList.remove(index);
                }
                else System.out.println("Дело с таким индексом не существует");
            }
            if(text.equals("EXIT"))
            {bool = false;}
        }
    }
}