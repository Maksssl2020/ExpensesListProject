package sys.expenselist;

<<<<<<< HEAD
public enum Months {
=======
enum Months {
>>>>>>> 4b331d2 (Initial commit)
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    SEPTEMBER(30),
    OCTOBER(31),
    NOVEMBER(30),
    DECEMBER(31);

    private int daysInMonth;

    Months(int daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

<<<<<<< HEAD
    public int getDaysInMonth() {
        return daysInMonth;
    }

    public void setDaysInMonth(int daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    public static Months getMonth(int calendarValue) {
=======
    int getDaysInMonth() {
        return daysInMonth;
    }

    void setDaysInMonth(int daysInMonth) {
        this.daysInMonth = daysInMonth;
    }

    static Months getMonth(int calendarValue) {
>>>>>>> 4b331d2 (Initial commit)
        return values()[calendarValue - 1];
    }
}
