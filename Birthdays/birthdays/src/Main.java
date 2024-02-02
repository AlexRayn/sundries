import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        int day = 22;
        int month = 12;
        int year = 1999;
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy - EEEE ");
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.MONTH, month -1);
        calendar.set(calendar.DAY_OF_MONTH, day);
        calendar.set(calendar.YEAR, year);
        Calendar date = Calendar.getInstance();
        //System.out.println(dateFormat.format(calendar.getTime()));
                //calendar.add(Calendar.YEAR, 1);
        for(int i = 0; i < date.get(Calendar.YEAR) - year; i++)
        {
            System.out.print(i + " - ");
            System.out.println(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.YEAR,1);
        }
    }
}