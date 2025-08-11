import core.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class RouteCalculator {
    private StationIndex stationIndex;

    private static double interStationDuration = 2.5;
    private static double interConnectionDuration = 3.5;

    public RouteCalculator(StationIndex stationIndex) {
        this.stationIndex = stationIndex;
    }

    //* Поиск кратчайшего пути между станциями
    public List<Station> getShortestRoute(Station from, Station to) {

        // Попытка найти маршрут без пересадок, проверяем, находятся ли обе станции на одной линии
        List<Station> route = getRouteOnTheLine(from, to);
        if (route != null && !route.isEmpty()) {
            return route; // Если маршрут найден, возвращаем его
        }

        // Если маршрут без пересадок не найден, ищем маршрут с одной пересадкой
        route = getRouteWithOneConnection(from, to);
        if (route != null && !route.isEmpty()) {
            return route;// ТУТ // Если маршрут с одной пересадкой найден, возвращаем его
        }

        // Если маршрут с одной пересадкой не найден, попытка найти маршрут с двумя пересадками
        route = getRouteWithTwoConnections(from, to);
        if (route != null && !route.isEmpty()) {
            return route; // Если маршрут найден, возвращаем его
        }

        // Возвращаем null, если маршрут не найден
        return null; //ТУТ
    }

    //* Вычисляет длительность маршрута между станциями.
    public static double calculateDuration(List<Station> route) {
        double duration = 0;// Переменная для накопления длительности маршрута
        Station previousStation = null; // Переменная для хранения предыдущей станции при итерации
        //Проходимся по всем станциям на маршруте
        for (int i = 0; i < route.size(); i++) {
            Station station = route.get(i);// Текущая станция маршрута

            // Если это не первая станция, прибавляем длительность участка маршрута
            if (i > 0) {
                // Проверка: если текущая и предыдущая станции на одной линии, используем interStationDuration,
                // иначе добавляем interConnectionDuration (пересадка между линиями)
                duration += previousStation.getLine().equals(station.getLine()) ?
                        interStationDuration : interConnectionDuration;
            }
            // Обновляем предыдущую станцию на текущую перед следующей итерацией
            previousStation = station;
        }
        return duration;// Возвращаем итоговую длительность маршрута
    }

    //=========================================================================

    //* Поиск маршрута между станциями, на одной линии
    private List<Station> getRouteOnTheLine(Station from, Station to) {

        // Проверяем, находятся ли станции на одной линии
        if (!from.getLine().equals(to.getLine())) {
            return null;// Если линии разные, маршрут невозможно построить
        }
        ArrayList<Station> route = new ArrayList<>(); // Маршрут в виде списка станций
        List<Station> stations = from.getLine().getStations(); // Получаем список станций линии
        int direction = 0;// Направление движения: 0 - неопределено, 1 - вперёд, -1 - назад

        // Итерируем по станциям линии
        for (Station station : stations) {
            if (direction == 0) {
                // Определяем направление движения при встрече начальной или конечной станции
                if (station.equals(from)) {
                    direction = 1;// Движение вперёд
                } else if (station.equals(to)) {
                    direction = -1;// Движение назад
                }
            }

            // Если направление определено, добавляем станцию в маршрут
            if (direction != 0) {
                route.add(station);
            }

            // Если маршрут построен в обратном направлении, разворачиваем его
            if ((direction == 1 && station.equals(to)) ||
                    (direction == -1 && station.equals(from))) {
                break;
            }
        }
        if (direction == -1) {
            Collections.reverse(route);
        }
        return route; // Возвращаем построенный маршрут
    }

    //* Определяет маршрут между двумя станциями с одной пересадкой.
    private List<Station> getRouteWithOneConnection(Station from, Station to) {

        // Если станции находятся на одной линии, маршрут с пересадкой не требуется
        if (from.getLine().equals(to.getLine())) {
            return null;
        }

        ArrayList<Station> route = new ArrayList<>(); // Итоговый маршрут

        // Получаем список станций на линиях "from" и "to"
        List<Station> fromLineStations = from.getLine().getStations();
        List<Station> toLineStations = to.getLine().getStations();

        // Ищем соединения между двумя линиями
        for (Station srcStation : fromLineStations) {
            for (Station dstStation : toLineStations) {
                if (isConnected(srcStation, dstStation)) {// Проверяем, соединены ли станции
                    ArrayList<Station> way = new ArrayList<>();

                    // Добавляем маршрут до станции пересадки на первой линии
                    way.addAll(getRouteOnTheLine(from, srcStation));
                    // Добавляем маршрут от станции пересадки до конечной станции
                    way.addAll(getRouteOnTheLine(dstStation, to));

                    // Проверяем, является ли текущий маршрут короче найденного ранее
                    if (route.isEmpty() || route.size() > way.size()) {
                        route.clear();
                        route.addAll(way); // Обновляем маршрут
                    }
                }
            }
        }
        return route; // Возвращаем найденный маршрут с одной пересадкой
    }

    //* Проверяет, соединены ли две станции между собой
    private boolean isConnected(Station station1, Station station2) {
        // Получаем все станции, соединённые с первой станцией
        Set<Station> connected = stationIndex.getConnectedStations(station1);
        // Проверяем, содержится ли вторая станция в списке соединений
        return connected.contains(station2);
    }

    //* Строит маршрут через соединённую линию, если станции не находятся на одной линии,
    //            но для них существуют пересадки через промежуточные соединённые линии.\
    private List<Station> getRouteViaConnectedLine(Station from, Station to) {
        // Получаем станции, соединённые с линией начальной станции
        Set<Station> fromConnected = stationIndex.getConnectedStations(from);
        // Получаем станции, соединённые с линией конечной станции
        Set<Station> toConnected = stationIndex.getConnectedStations(to);

        // Проверяем каждую пару соединённых станций
        for (Station srcStation : fromConnected) {
            for (Station dstStation : toConnected) {
                // Если обе станции соединены с одной и той же линией
                if (srcStation.getLine().equals(dstStation.getLine())) {
                    // Получаем маршрут между этими станциями и возвращаем его
                    return getRouteOnTheLine(srcStation, dstStation);
                }
            }
        }
        // Если подходящий маршрут не найден, возвращаем null
        return null;
    }

    // Метод для нахождения маршрута с двумя пересадками
    private List<Station> getRouteWithTwoConnections(Station from, Station to) {
        // Если станции находятся на одной линии, возвращается null, так как пересадка не требуется
        if (from.getLine().equals(to.getLine())) {
            return null;
        }

        // Переменная для хранения наилучшего маршрута
        ArrayList<Station> route = new ArrayList<>();

        // Получение списка всех станций на линии начальной и конечной станции
        List<Station> fromLineStations = from.getLine().getStations();
        List<Station> toLineStations = to.getLine().getStations();

        // Перебираем все станции на линии начальной станции
        for (Station srcStation : fromLineStations) {
            // Перебираем все станции на линии конечной станции
            for (Station dstStation : toLineStations) {
                // Получаем маршрут между пересадочными станциями
                List<Station> connectedLineRoute = getRouteViaConnectedLine(srcStation, dstStation);

                // Если маршрут с пересадкой отсутствует, переходим к следующей итерации
                if (connectedLineRoute == null) {
                    continue;
                }

                // Формируем полный маршрут
                ArrayList<Station> way = new ArrayList<>();
                way.addAll(getRouteOnTheLine(from, srcStation)); // участок маршрута от начальной станции до станции пересадки
                way.addAll(connectedLineRoute);                 // маршрут с одной пересадкой
                way.addAll(getRouteOnTheLine(dstStation, to));  // участок маршрута от другой станции пересадки до конечной

                // Сравниваем текущий маршрут с сохранённым. Если он короче либо маршрут ещё не найден — заменяем
                if (route.isEmpty() || route.size() > way.size()) {
                    route.clear();
                    route.addAll(way);
                }
            }
        }

        // Возвращаем наилучший найденный маршрут
        return route;
    }
}