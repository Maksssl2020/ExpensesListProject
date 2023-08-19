package sys.expenselist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    void testExpenseFormatToDisplay() {
        Expense expense = new Expense(ExpenseType.FOOD, "Pizza", "12");
        String result = expense.toString();

        assertEquals("Expense type: FOOD, name: Pizza, cost: $12.00", result);
    }
}