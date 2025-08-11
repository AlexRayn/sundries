import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.*;

public class RouteCalculatorTest extends TestCase {
    private StationIndex stationIndex; // Хранит индекс станций, линий и соединений
    private RouteCalculator calculator; // Объект для тестирования маршрутов

    //Метод setUp для установки значений необходимых для тестирования
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Инициализация StationIndex (содержит все данные о метро)
        stationIndex = new StationIndex();

        // Создание линий метро
        Line line1 = new Line(1, "Линия 1");
        Line line2 = new Line(2, "Линия 2");
        Line line3 = new Line(3, "Линия 3");

        // Добавление созданных линий в StationIndex
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        // Создание станций для Линии 1
        Station stationA1 = new Station("A1", line1);
        Station stationA2 = new Station("A2", line1);
        Station stationA3 = new Station("A3", line1);
        line1.addStation(stationA1);
        line1.addStation(stationA2);
        line1.addStation(stationA3);

        // Создание станций для Линии 2
        Station stationB1 = new Station("B1", line2);
        Station stationB2 = new Station("B2", line2);
        Station stationB3 = new Station("B3", line2);
        line2.addStation(stationB1);
        line2.addStation(stationB2);
        line2.addStation(stationB3);

        // Создание станций для Линии 3
        Station stationC1 = new Station("C1", line3);
        Station stationC2 = new Station("C2", line3);
        Station stationC3 = new Station("C3", line3);
        line3.addStation(stationC1);
        line3.addStation(stationC2);
        line3.addStation(stationC3);

        // Добавление станций в StationIndex для быстрого доступа
        stationIndex.addStation(stationA1);
        stationIndex.addStation(stationA2);
        stationIndex.addStation(stationA3);
        stationIndex.addStation(stationB1);
        stationIndex.addStation(stationB2);
        stationIndex.addStation(stationB3);
        stationIndex.addStation(stationC1);
        stationIndex.addStation(stationC2);
        stationIndex.addStation(stationC3);

        // Установка пересадок между линиями
        // Пересадка: Линия 1 -> Линия 2
        stationIndex.addConnection(Arrays.asList(stationA3, stationB1));
        // Пересадка: Линия 2 -> Линия 3
        stationIndex.addConnection(Arrays.asList(stationB3, stationC1));

        // Инициализация RouteCalculator с подготовленным StationIndex
        calculator = new RouteCalculator(stationIndex);
    }

    // Тест для метода - calculateDuration - Вычисляет длительность маршрута между станциями.
    public void testCalculateDuration() {

        // Создаем маршрут: A1 -> A2 -> A3 -> B1
        ArrayList<Station> route = new ArrayList<>();
        route.add(stationIndex.getStation("A1"));
        route.add(stationIndex.getStation("A2"));
        route.add(stationIndex.getStation("A3"));
        route.add(stationIndex.getStation("B1"));

        // Ожидаемое время: 2 участка * 2.5 минуты = 5.0 минут
        double expected = 8.5;

        // Вычисляем фактическое время через метод calculateDuration
        double actual = RouteCalculator.calculateDuration(route);

        // Проверяем, совпадает ли ожидаемое и фактическое время маршрута
        assertEquals(expected, actual);
    }

    // Тест для метода getRouteOnTheLine - Поиск маршрута между станциями, на одной линии
    public void testGetRouteOnTheLine() {
        // Поиск кратчайшего маршрута: A1 -> A3 (без пересадок)
        List<Station> route = calculator.getShortestRoute(
                stationIndex.getStation("A3"),
                stationIndex.getStation("A1")
        );

        // Ожидаемый маршрут
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("A3"));
        expected.add(stationIndex.getStation("A2"));
        expected.add(stationIndex.getStation("A1"));

        //Сравнение результата и ожидаемого маршрута
        assertEquals(expected, route);
    }

    // Тест метода isConnected - Проверяет, соединены ли две станции между собой
    public void testIsConnected() {
        // Получаем станции начала и конца маршрута
        Station stationA3 = stationIndex.getStation("A3");
        Station stationC3 = stationIndex.getStation("C3");

        // Получаем маршрут от A3 до C3
        List<Station> route = calculator.getShortestRoute(stationA3, stationC3);

        // Проверяем, что маршрут найден
        assertNotNull(route); // Проверяем, что маршрут существует
        assertTrue(route.contains(stationC3)); // Убедимся, что маршрут включает конечную станцию C3

        // Ожидаемый маршрут
        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(stationIndex.getStation("A3")); // A3 (начало маршрута)
        expectedRoute.add(stationIndex.getStation("B1")); // Пересадка на линию 2
        expectedRoute.add(stationIndex.getStation("B2")); // Маршрут внутри линии 2
        expectedRoute.add(stationIndex.getStation("B3")); // Конец линии 2 (начало пересадки)
        expectedRoute.add(stationIndex.getStation("C1")); // Переход на линию 3
        expectedRoute.add(stationIndex.getStation("C2")); // Движение по линии 3
        expectedRoute.add(stationIndex.getStation("C3")); // C3 (конечная станция)

        // Сравниваем ожидаемый и фактический маршруты
        assertEquals(expectedRoute, route); // Проверяем, что маршрут полностью совпадает
    }

    // Теста для getRouteWithOneConnection - Определяет маршрут между двумя станциями с одной пересадкой.
    public void testGetRouteWithOneConnection() {
        //Поиск кратчайшего маршрута: A2 -> B2 (с одной пересадкой)
        List<Station> route = calculator.getShortestRoute(
                stationIndex.getStation("A2"),
                stationIndex.getStation("B2")
        );

        // Ожидаемый маршрут
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("A2"));
        expected.add(stationIndex.getStation("A3"));
        expected.add(stationIndex.getStation("B1"));
        expected.add(stationIndex.getStation("B2"));

        //Сравнение результата и ожидаемого маршрута
        assertEquals(expected, route);
    }

    // Тест для getRouteWithTwoConnections - Метод для нахождения маршрута с двумя пересадками
    public void testGetRouteWithTwoConnections() {
        //Поиск кратчайшего маршрута: A2 -> C2 (с одной пересадкой)
        List<Station> route = calculator.getShortestRoute(
                stationIndex.getStation("A2"),
                stationIndex.getStation("C2")
        );

        // Ожидаемый маршрут
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("A2"));
        expected.add(stationIndex.getStation("A3"));
        expected.add(stationIndex.getStation("B1"));
        expected.add(stationIndex.getStation("B2"));
        expected.add(stationIndex.getStation("B3"));
        expected.add(stationIndex.getStation("C1"));
        expected.add(stationIndex.getStation("C2"));

        //Сравнение результата и ожидаемого маршрута
        assertEquals(expected, route);
    }


    // Тест для getShortestRoute Поиск кратчайшего пути между станциями
    public void testGetShortestRoute() {
        // Тест маршрута на одной линии
        List<Station> route = calculator.getShortestRoute(
                stationIndex.getStation("A1"),
                stationIndex.getStation("A3")
        );

        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("A1"));
        expected.add(stationIndex.getStation("A2"));
        expected.add(stationIndex.getStation("A3"));

        assertEquals(expected, route);

        // Тест маршрута с одной пересадкой
        route = calculator.getShortestRoute(
                stationIndex.getStation("A1"),
                stationIndex.getStation("B2")
        );

        expected.clear();
        expected.add(stationIndex.getStation("A1"));
        expected.add(stationIndex.getStation("A2"));
        expected.add(stationIndex.getStation("A3"));
        expected.add(stationIndex.getStation("B1"));
        expected.add(stationIndex.getStation("B2"));

        assertEquals(expected, route);

        // Тест маршрута с двумя пересадками
        route = calculator.getShortestRoute(
                stationIndex.getStation("A1"),
                stationIndex.getStation("C3")
        );

        expected.clear();
        expected.add(stationIndex.getStation("A1"));
        expected.add(stationIndex.getStation("A2"));
        expected.add(stationIndex.getStation("A3"));
        expected.add(stationIndex.getStation("B1"));
        expected.add(stationIndex.getStation("B2"));
        expected.add(stationIndex.getStation("B3"));
        expected.add(stationIndex.getStation("C1"));
        expected.add(stationIndex.getStation("C2"));
        expected.add(stationIndex.getStation("C3"));

        assertEquals(expected, route);
    }


    //Тест для getRouteViaConnectedLine - Строит маршрут через соединённую линию, если станции не находятся на одной линии,
    // но для них существуют пересадки через промежуточные соединённые линии.\
    public void testGetRouteViaConnectedLine() {
        // Тестируем маршрут через соединённую линию: B1 -> C1
        List<Station> route = calculator.getShortestRoute(
                stationIndex.getStation("B1"),
                stationIndex.getStation("C1")
        );

        // Ожидаемый маршрут
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("B1"));
        expected.add(stationIndex.getStation("B2"));
        expected.add(stationIndex.getStation("B3")); // Конец линии 2
        expected.add(stationIndex.getStation("C1")); // Начало линии 3

        assertEquals(expected, route);
    }
}







//
