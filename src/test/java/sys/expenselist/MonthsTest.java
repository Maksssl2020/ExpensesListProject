package sys.expenselist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonthsTest {

    @Test
    void testGettingJanuary() {
        int calendarMonthValue = 1;
        Months january = Months.getMonth(calendarMonthValue);
        assertEquals(Months.JANUARY, january);
    }

    @Test
    void testGettingMarch() {
        int calendarMonthValue = 3;
        Months march = Months.getMonth(calendarMonthValue);
        assertEquals(Months.MARCH, march);
    }

    @Test
    void testGettingDecember() {
        int calendarMonthValue = 12;
        Months december = Months.getMonth(calendarMonthValue);
        assertEquals(Months.DECEMBER, december);
    }

    @Test
    void testGettingAmountOfDaysInJanuary() {
        int result = Months.JANUARY.getDaysInMonth();
        assertEquals(31, result);
    }

    @Test
    void testGettingAmountOfDaysInMay() {
        int result = Months.MAY.getDaysInMonth();
        assertEquals(31, result);
    }

    @Test
    void testGettingAmountOfDaysInDecember() {
        int result = Months.DECEMBER.getDaysInMonth();
        assertEquals(31, result);
    }
}