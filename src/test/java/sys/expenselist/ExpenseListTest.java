package sys.expenselist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseListTest {
    private ExpenseList expenseList;
    private final NumberFormat costFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    @BeforeEach
    void setUp() {
        expenseList = new ExpenseList();
    }

    @Test
    void testInitExpenseList() {
        assertNotNull(expenseList.getListFromArray(8));
    }

    @Test
    void testAddExpenseToMarch() {
        int month = 3;
        Expense marchExpense = new Expense(ExpenseType.FOOD, "Bread", "0.99");
        expenseList.addExpense(month, marchExpense);

        Expense addedExpense = expenseList.getListFromArray(month).get(0);
        assertEquals(marchExpense, addedExpense);
    }

    @Test
    void testExpensesFormatToDisplay() {
        int month = 3;
        StringBuilder expenseBuilder = new StringBuilder();

        Expense marchExpense1 = new Expense(ExpenseType.FOOD, "Bread", "0.99");
        Expense marchExpense2 = new Expense(ExpenseType.FOOD, "Pizza", "12");
        Expense marchExpense3 = new Expense(ExpenseType.FOOD, "Cheese", "1.50");

        expenseList.addExpense(month, marchExpense1);
        expenseList.addExpense(month, marchExpense2);
        expenseList.addExpense(month, marchExpense3);


        expenseBuilder
                .append("All expenses in march:")
                .append("\n")
                .append(marchExpense1)
                .append("\n")
                .append(marchExpense2)
                .append("\n")
                .append(marchExpense3)
                .append("\n");

        String expensesToDisplay =  expenseBuilder.toString();
        String result = expenseList.toString(month);

        assertEquals(expensesToDisplay, result);
    }

    @Test
    void testCountingTheWholeCostOfExpenses() {
        int month = 2;
        BigDecimal totalCost = BigDecimal.ZERO;
        String result;

        Expense marchExpense1 = new Expense(ExpenseType.FOOD, "Bread", "0.99");
        Expense marchExpense2 = new Expense(ExpenseType.FOOD, "Pizza", "12");
        Expense marchExpense3 = new Expense(ExpenseType.FOOD, "Cheese", "1.50");

        expenseList.addExpense(month, marchExpense1);
        expenseList.addExpense(month, marchExpense2);
        expenseList.addExpense(month, marchExpense3);

        totalCost = totalCost.add(marchExpense1.getCost());
        totalCost = totalCost.add(marchExpense2.getCost());
        totalCost = totalCost.add(marchExpense3.getCost());

        result = expenseList.countExpensesCostInCertainMonth(month);
        String formattedTotalCost = String.format("Total cost in february: %s", costFormatter.format(totalCost));

        assertEquals(formattedTotalCost, result);
    }

    @Test
    void testFindingTheBiggestExpenseInMonth() {
        int month = 3;
        String result;
        StringBuilder expectedResult = new StringBuilder();

        Expense marchExpense1 = new Expense(ExpenseType.FOOD, "Bread", "0.99");
        Expense marchExpense2 = new Expense(ExpenseType.FOOD, "Pizza", "12");
        Expense marchExpense3 = new Expense(ExpenseType.FOOD, "Cheese", "1.50");

        expenseList.addExpense(month, marchExpense1);
        expenseList.addExpense(month, marchExpense2);
        expenseList.addExpense(month, marchExpense3);

         expectedResult
                 .append("The biggest expenses in march:")
                 .append("\n")
                 .append(marchExpense2)
                 .append("\n");
         
        result = expenseList.findTheBiggestExpenseInCertainMonth(month);

        assertEquals(expectedResult.toString(), result);
    }

    @Test
    void testTwoExpensesWithBiggestCost() {
        int month = 3;
        String result;
        StringBuilder expectedResult = new StringBuilder();

        Expense marchExpense1 = new Expense(ExpenseType.FOOD, "Bread", "0.99");
        Expense marchExpense2 = new Expense(ExpenseType.FOOD, "Pizza", "12");
        Expense marchExpense3 = new Expense(ExpenseType.FOOD, "Hamburger", "12");

        expenseList.addExpense(month, marchExpense1);
        expenseList.addExpense(month, marchExpense2);
        expenseList.addExpense(month, marchExpense3);

        expectedResult
                .append("The biggest expenses in march:")
                .append("\n")
                .append(marchExpense2)
                .append("\n")
                .append(marchExpense3)
                .append("\n");
        result = expenseList.findTheBiggestExpenseInCertainMonth(month);

        assertEquals(expectedResult.toString(), result);
    }

    @Test
    void choosingEmptyMonth() {
        boolean result = expenseList.isChosenMonthEmpty(2);
        assertTrue(result);
    }

    @Test
    void choosingNotEmptyMonth() {
        expenseList.addExpense(2, new Expense(ExpenseType.FOOD, "pizza", "12"));
        boolean result = expenseList.isChosenMonthEmpty(2);
        assertFalse(result);
    }

    @Test
    void testTurningJanuaryNumberIntoText() {
        int chosenMonth = 1;

        String result = expenseList.turnChosenMonthNumberIntoText(chosenMonth);

        assertEquals("january", result);
    }

    @Test
    void testTurningMayNumberIntoText() {
        int chosenMonth = 5;

        String result = expenseList.turnChosenMonthNumberIntoText(chosenMonth);

        assertEquals("may", result);
    }

    @Test
    void testTurningDecemberNumberIntoText() {
        int chosenMonth = 12;

        String result = expenseList.turnChosenMonthNumberIntoText(chosenMonth);

        assertEquals("december", result);
    }

    @Test
    void testCalculatingAverageCostOfExpensesInMarch() {
        int month = 3;
        String year = "2022";
        String result;

        Expense marchExpense1 = new Expense(ExpenseType.FOOD, "Bread", "0.99");
        Expense marchExpense2 = new Expense(ExpenseType.FOOD, "Pizza", "12");
        Expense marchExpense3 = new Expense(ExpenseType.FOOD, "Hamburger", "12");

        expenseList.addExpense(month, marchExpense1);
        expenseList.addExpense(month, marchExpense2);
        expenseList.addExpense(month, marchExpense3);

        result = expenseList.calcAverageCostOfExpensesInCertainMonth(month, year);
        String expectedResult = "Average expenses cost in march: $0.81";

        assertEquals(expectedResult, result);
    }

    @Test
    void test2020YearIsLeapYear() {
        String year = "2020";
        boolean result = expenseList.isLeapYear(year);
        assertTrue(result);
    }

    @Test
    void test2021yearIsLeapYear() {
        String year = "2021";
        boolean result = expenseList.isLeapYear(year);
        assertFalse(result);
    }

    @Test
    void testCalculatingAverageExpensesCostInFebruaryInLeapYear() {
        int month = 2;
        String year = "2020";
        String result;

        Expense marchExpense1 = new Expense(ExpenseType.FOOD, "Bread", "0.99");
        Expense marchExpense2 = new Expense(ExpenseType.FOOD, "Pizza", "12");
        Expense marchExpense3 = new Expense(ExpenseType.FOOD, "Hamburger", "12");

        expenseList.addExpense(month, marchExpense1);
        expenseList.addExpense(month, marchExpense2);
        expenseList.addExpense(month, marchExpense3);

        result = expenseList.calcAverageCostOfExpensesInCertainMonth(month, year);
        String expectedResult = "Average expenses cost in february: $0.86";

        assertEquals(expectedResult, result);
    }

    @Test
    void testCalculatingAverageExpensesCostInFebruaryInNonLeapYear() {
        int month = 2;
        String year = "2021";
        String result;

        Expense marchExpense1 = new Expense(ExpenseType.FOOD, "Bread", "0.99");
        Expense marchExpense2 = new Expense(ExpenseType.FOOD, "Pizza", "12");
        Expense marchExpense3 = new Expense(ExpenseType.FOOD, "Hamburger", "12");

        expenseList.addExpense(month, marchExpense1);
        expenseList.addExpense(month, marchExpense2);
        expenseList.addExpense(month, marchExpense3);

        result = expenseList.calcAverageCostOfExpensesInCertainMonth(month, year);
        String expectedResult = "Average expenses cost in february: $0.89";

        assertEquals(expectedResult, result);
    }
}