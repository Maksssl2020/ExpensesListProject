package sys.expenselist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExpenseListControllerTest {

    ExpenseListController controller;

    @BeforeEach
    void setUp() {
        controller = new ExpenseListController();
    }

    @Test
    void testExpenseListInNotNull() {
        assertNotNull(controller.getExpenseListYears());
    }
}