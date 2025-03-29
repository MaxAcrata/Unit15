package ru.netologia.qamid;
import java.util.Comparator;

/**
 * Компаратор для сравнения билетов по времени полета.
 * Реализует интерфейс Comparator<Ticket> для возможности сортировки билетов.
 */
public class TicketTimeComparator implements Comparator<Ticket> {

    /**
     * Сравнивает два билета по времени полета.
     * Время полета вычисляется как разница между временем прилета и временем вылета.
     *
     * @param t1 первый билет для сравнения
     * @param t2 второй билет для сравнения
     * @return отрицательное число, если время полета t1 меньше времени полета t2;
     *         положительное число, если время полета t1 больше времени полета t2;
     *         0, если времена полета равны
     */
    @Override
    public int compare(Ticket t1, Ticket t2) {
        // Вычисляем время полета для каждого билета (время прилета - время вылета)
        int flightTime1 = t1.getTimeTo() - t1.getTimeFrom();
        int flightTime2 = t2.getTimeTo() - t2.getTimeFrom();

        // Сравниваем полученные значения времени полета
        return flightTime1 - flightTime2;
    }
}
