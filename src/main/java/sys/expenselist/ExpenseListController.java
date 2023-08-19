package sys.expenselist;

import java.util.HashMap;
import java.util.Map;

public class ExpenseListController {
    private final ExpenseListUserInterface userInterface;
    private final Map<String, ExpenseList> expenseListYears;
    private String currentExpenseListYear;

    public ExpenseListController() {
        userInterface = new ExpenseListUserInterface();
        expenseListYears = new HashMap<>();
    }

    protected Map<String, ExpenseList> getExpenseListYears() {
        return expenseListYears;
    }

    public void turnOnExpenseListProgram() {
        String userEnteredData;
        String enteredYearForFirstExpenseList;

        enteredYearForFirstExpenseList = userInterface.askUserForEnterYear();
        expenseListYears.put(enteredYearForFirstExpenseList, new ExpenseList());
        currentExpenseListYear = enteredYearForFirstExpenseList;

        do {
            System.out.println(userInterface.choiceMenu());
            userEnteredData = userInterface.askUserForEnterNumber();
            checkUserChoice(userEnteredData);
        } while (!userEnteredData.equalsIgnoreCase("8"));
    }

    protected void checkUserChoice(String userEnteredData) {
        ExpenseList currentExpenseList = expenseListYears.get(currentExpenseListYear);

        switch (userEnteredData) {
            case "1" -> {
                String year = userInterface.askUserForEnterYear();
                if (expenseListYears.containsKey(year)) {
                    System.out.println("Sorry, there is a expense list with the same year!");
                } else {
                    expenseListYears.put(year, new ExpenseList());
                }
            }
            case "2" -> currentExpenseListYear = getCertainYearExpenseList();
            case "3" -> {
                int month = userInterface.askUserForEnterMonthNumber();
                ExpenseType expenseType = userInterface.askUserForEnterExpenseType();
                String expenseName = userInterface.askUserForEnterExpenseName();
                String expenseCost = userInterface.askUserForEnterExpenseCost();

                currentExpenseList.addExpense(month, new Expense(expenseType, expenseName, expenseCost));
            }
            case "4" -> {
                int month = userInterface.askUserForEnterMonthNumber();
                if (!currentExpenseList.isChosenMonthEmpty(month)) {
                    System.out.println(currentExpenseList.toString(month));
                }
            }
            case "5" -> {
                int month = userInterface.askUserForEnterMonthNumber();
                if (!currentExpenseList.isChosenMonthEmpty(month)) {
                    System.out.println(currentExpenseList.findTheBiggestExpenseInCertainMonth(month));
                }
            }
            case "6" -> {
                int month = userInterface.askUserForEnterMonthNumber();
                if (!currentExpenseList.isChosenMonthEmpty(month)) {
                    System.out.println(currentExpenseList.countExpensesCostInCertainMonth(month));
                }
            }
            case "7" -> {
                int month = userInterface.askUserForEnterMonthNumber();
                if (!currentExpenseList.isChosenMonthEmpty(month)) {
                    System.out.println(currentExpenseList.calcAverageCostOfExpensesInCertainMonth(month, currentExpenseListYear));
                }
            }
            case "8" -> System.out.println("Goodbye!");
            default -> System.out.println("Invalid data!");
        }
    }

    protected String getCertainYearExpenseList() {
        String year = selectExpenseListYear();

        if (!expenseListYears.containsKey(year)) {
            System.out.println("Sorry, there isn't list with year that you entered!");
            year = currentExpenseListYear;
        }

        return year;
    }

    protected String selectExpenseListYear() {
        for (String year : expenseListYears.keySet()) {
            System.out.println(year);
        }

        return userInterface.askUserForEnterYear();
    }
}
