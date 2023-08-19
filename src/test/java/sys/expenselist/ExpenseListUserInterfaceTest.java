package sys.expenselist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ExpenseListUserInterfaceTest {

    ExpenseListUserInterface userInterface;

    @BeforeEach
    void setUp() {
        userInterface = new ExpenseListUserInterface();
    }

    @Test
    void testChoiceMenuToDisplay() {
        String menu = """
                Possible activities:
                
                1. Add new expense list
                2. Select expense list
                3. Add expense to certain month
                4. Display expenses in a certain month
                5. Display the biggest expense in a certain month
                6. Calculate the total cost of expenses in a certain month
                7. Calculate average expenses cost in a certain month
                8. Quit
                
                Please select an activity:""";

        assertEquals(userInterface.choiceMenu(), menu);
    }

    @Test
    void testCorrectUserChoice() {
        String userChoice = "1";
        String result;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userChoice.getBytes());
        System.setIn(inputStream);

        result = userInterface.askUserForEnterNumber();
        assertEquals(userChoice, result);

        System.setIn(System.in);
    }

    @Test
    void testUserEnteringGreaterNumberThanEight() {
        String userChoice = "9";
        boolean result;

        result = userInterface.checkUserChoice(userChoice);

        assertFalse(result);
    }

    @Test
    void testUserEnteringLetter() {
        String userChoice = "B";
        boolean result;

        result = userInterface.checkUserChoice(userChoice);

        assertFalse(result);
    }

    @Test
    void testUserEnteringAExpenseListCorrectYear() {
        String year = "2022";
        String result;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(year.getBytes());
        System.setIn(inputStream);

        result = userInterface.askUserForEnterYear();
        assertEquals(year, result);

        System.setIn(System.in);
    }

    @Test
    void testUserEnteringTooLittleNumbersInYear() {
        String year = "222";
        boolean result;

        result = userInterface.checkEnteredUserYear(year);

        assertFalse(result);
    }

    @Test
    void testUserEnteringLettersInsteadOfNumbers() {
        String year = "ABC";
        boolean result;

        result = userInterface.checkEnteredUserYear(year);

        assertFalse(result);
    }

    @Test
    void testUserEnteringCorrectMonthNumber() {
        String userMonthNumber = "4";
        int result;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userMonthNumber.getBytes());
        System.setIn(inputStream);

        result = userInterface.askUserForEnterMonthNumber();
        assertEquals(4, result);

        System.setIn(System.in);
    }

    @Test
    void testUserEnteringMonthNumberGreaterThanTwelve() {
        String userMonthNumber = "13";
        boolean result;

        result = userInterface.checkEnteredUserMonthNumber(userMonthNumber);

        assertFalse(result);
    }

    @Test
    void testUserEnteringMonthNumberLessThanOne() {
        String userMonthNumber = "-5";
        boolean result;

        result = userInterface.checkEnteredUserMonthNumber(userMonthNumber);

        assertFalse(result);
    }

    @Test
    void userEnteringCorrectExpenseType() {
        String expenseType = "food";
        ExpenseType result;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(expenseType.getBytes());
        System.setIn(inputStream);

        result = userInterface.askUserForEnterExpenseType();
        assertEquals(ExpenseType.FOOD, result);

        System.setIn(System.in);
    }

    @Test
    void testUserEnteringNotExistingExpenseType() {
        String userExpenseType = "games";
        boolean result;

        result = userInterface.checkEnteredUserType(userExpenseType);

        assertFalse(result);
    }

    @Test
    void tesPossibleExpenseTypesToDisplay() {
        String result = userInterface.possibleTypesToChoose();
        StringBuilder expectedResultBuilder = new StringBuilder();

        for (int i = 0; i < ExpenseType.values().length; i++) {
            expectedResultBuilder
                    .append(ExpenseType.values()[i])
                    .append("\n");
        }

        assertEquals(expectedResultBuilder.toString(), result);
    }

    @Test
    void testUserEnteringCorrectExpenseName() {
        String userExpenseName = "Pizza";
        String result;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userExpenseName.getBytes());
        System.setIn(inputStream);

        result = userInterface.askUserForEnterExpenseName();
        assertEquals(userExpenseName.toLowerCase(), result);

        System.setIn(System.in);
    }

    @Test
    void testUserEnteringEmptyExpenseName() {
        String userExpenseName = "";
        boolean result;

        result = userInterface.checkEnteredUserExpenseName(userExpenseName);

        assertFalse(result);
    }

    @Test
    void testUserEnteringCorrectExpenseCost() {
        String userExpenseCost = "12.99";
        String result;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userExpenseCost.getBytes());
        System.setIn(inputStream);

        result = userInterface.askUserForEnterExpenseCost();
        assertEquals(result, userExpenseCost);

        System.setIn(System.in);
    }

    @Test
    void testUserEnteringExpenseCostLessThanZero() {
        String userExpenseCost = "-1.11";
        boolean result;

        result = userInterface.checkEnteredUserExpenseCost(userExpenseCost);

        assertFalse(result);
    }

    @Test
    void testUserEnteringExpenseCostEqualZero() {
        String userExpenseCost = "0";
        boolean result;

        result = userInterface.checkEnteredUserExpenseCost(userExpenseCost);

        assertFalse(result);
    }
}