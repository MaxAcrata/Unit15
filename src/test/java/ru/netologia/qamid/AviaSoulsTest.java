package ru.netologia.qamid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {

    /**
     * Тест проверяет поиск билетов по маршруту с сортировкой по цене (по возрастанию).
     */
    @Test
    public void testSearchAndSortByPrice() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Moscow", "Sochi", 5000, 10, 12)); // 3-й по цене
        manager.add(new Ticket("Moscow", "Sochi", 3000, 8, 10));  // 1-й по цене
        manager.add(new Ticket("Moscow", "Sochi", 4000, 9, 11));  // 2-й по цене
        manager.add(new Ticket("Moscow", "Kazan", 2000, 7, 8));   // Другой маршрут

        // Ожидаемый результат - билеты Moscow-Sochi, отсортированные по цене
        Ticket[] expected = {
                new Ticket("Moscow", "Sochi", 3000, 8, 10),
                new Ticket("Moscow", "Sochi", 4000, 9, 11),
                new Ticket("Moscow", "Sochi", 5000, 10, 12)
        };

        Ticket[] actual = manager.search("Moscow", "Sochi");
        Assertions.assertArrayEquals(expected, actual);
    }

    /**
     * Тест проверяет поиск билетов по маршруту с сортировкой по времени полета.
     */
    @Test
    public void testSearchAndSortByFlightTime() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Moscow", "Sochi", 5000, 10, 12)); // 2 часа
        manager.add(new Ticket("Moscow", "Sochi", 3000, 8, 11));  // 3 часа
        manager.add(new Ticket("Moscow", "Sochi", 4000, 9, 12));  // 3 часа
        manager.add(new Ticket("Moscow", "Kazan", 2000, 7, 8));   // 1 час

        // Создаем компаратор для сортировки по времени полета
        Comparator<Ticket> comparator = new TicketTimeComparator();

        // Ожидаемый результат - билеты Moscow-Sochi, отсортированные по времени полета
        Ticket[] expected = {
                new Ticket("Moscow", "Sochi", 5000, 10, 12), // 2 часа (первый по времени)
                new Ticket("Moscow", "Sochi", 3000, 8, 11),  // 3 часа
                new Ticket("Moscow", "Sochi", 4000, 9, 12)    // 3 часа
        };

        Ticket[] actual = manager.searchAndSortBy("Moscow", "Sochi", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }

    /**
     * Тест проверяет случай, когда по указанному маршруту билетов нет.
     */
    @Test
    public void testSearchNoResults() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Moscow", "Sochi", 5000, 10, 12));
        manager.add(new Ticket("Moscow", "Kazan", 2000, 7, 8));

        // Ожидаемый результат - пустой массив (нет билетов по маршруту Sochi-Moscow)
        Ticket[] expected = {};

        Ticket[] actual = manager.search("Sochi", "Moscow");
        Assertions.assertArrayEquals(expected, actual);
    }

    /**
     * Тест проверяет случай, когда по указанному маршруту найден только один билет.
     */
    @Test
    public void testSearchSingleResult() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Moscow", "Sochi", 5000, 10, 12));
        manager.add(new Ticket("Moscow", "Kazan", 2000, 7, 8));

        // Ожидаемый результат - массив с одним билетом Moscow-Kazan
        Ticket[] expected = {new Ticket("Moscow", "Kazan", 2000, 7, 8)};

        Ticket[] actual = manager.search("Moscow", "Kazan");
        Assertions.assertArrayEquals(expected, actual);
    }
}