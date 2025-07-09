import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

        //получает текущею дату
        LocalDateTime now = LocalDateTime.now();
        //время через два часа
        LocalDateTime twoHoursFromNow = now.plusHours(2);

        //создаем новый объект аэропорта
        Airport airport = Airport.getInstance();

        //Распечатайте с помощью библиотеки airport.jar время вылета и модели самолётов, вылетающих в ближайшие два часа.

        //Получаем стрим из самолетов из терминалов
        airport.getTerminals().stream()
                // Получаем потоки рейсов из терминалов
                .flatMap(terminal -> terminal.getFlights().stream())
                // Фильтруем только исходящие рейсы
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE)
                // Фильтруем только те рейсы, которые вылетают в ближайшие 2 часа
                .filter(flight -> {
                    Date flightDate = flight.getDate();
                    LocalDateTime flightDateTime = flightDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    return flightDateTime.isAfter(now) && flightDateTime.isBefore(twoHoursFromNow);
                })
                // Печатаем модель самолёта и время вылета
                .forEach(flight -> {
                    System.out.println("Модель самолёта: " + flight.getAircraft().getModel());
                    System.out.println("Время вылета: " + flight.getDate());
                });
    }
}