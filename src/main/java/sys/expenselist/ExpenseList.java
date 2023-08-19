package sys.expenselist;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.*;

public class ExpenseList {
    private final Map<Months, List<Expense>> expenseListMonths;
    private final NumberFormat costFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    public ExpenseList() {
        expenseListMonths = new HashMap<>();
        initExpenseListMonths();
    }

    protected void initExpenseListMonths() {
        for (int i = 1; i <= 12; i++) {
            expenseListMonths.put(Months.getMonth(i), new ArrayList<>());
        }
    }

    protected List<Expense> getListFromArray(int index) {
        return expenseListMonths.get(Months.getMonth(index));
    }

    public void addExpense(int chosenMonth, Expense newExpense) {
        expenseListMonths.get(Months.getMonth(chosenMonth)).add(newExpense);
    }

    public String toString(int chosenMonth) {
        StringBuilder expensesBuilder = new StringBuilder();
        String chosenMonthAsText = turnChosenMonthNumberIntoText(chosenMonth);

        expensesBuilder
                .append("All expenses in ")
                .append(chosenMonthAsText)
                .append(":")
                .append("\n");

        for (int i = 0; i < expenseListMonths.get(Months.getMonth(chosenMonth)).size(); i++) {
            expensesBuilder
                    .append(expenseListMonths.get(Months.getMonth(chosenMonth)).get(i))
                    .append("\n");
        }

        return expensesBuilder.toString();
    }

    protected String calcAverageCostOfExpensesInCertainMonth(int chosenMonth, String year) {
        String chosenMonthAsText = turnChosenMonthNumberIntoText(chosenMonth);
        String formattedAverageCost = costFormatter.format(calcAverageExpensesCost(chosenMonth, year));

        return String.format("Average expenses cost in %s: %s", chosenMonthAsText, formattedAverageCost);
    }

    private BigDecimal calcAverageExpensesCost(int chosenMonth, String year) {
        int daysInCertainMonth;
        MathContext divideTotalCostByDaysInMonthContext = new MathContext(2);
        BigDecimal totalCost = calcTotalExpensesCost(chosenMonth);

        if (isLeapYear(year) && chosenMonth == 2) {
            Months.FEBRUARY.setDaysInMonth(29);
            daysInCertainMonth = Months.FEBRUARY.getDaysInMonth();
        } else {
            daysInCertainMonth = Months.getMonth(chosenMonth).getDaysInMonth();
        }

        return totalCost.divide(new BigDecimal(daysInCertainMonth), divideTotalCostByDaysInMonthContext);
    }

    protected boolean isLeapYear(String year) {
        int yearAsNumber = Integer.parseInt(year);
        boolean result;

        if ((yearAsNumber % 4 == 0 && yearAsNumber % 100 != 0) || (yearAsNumber % 400 == 0)) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    protected String countExpensesCostInCertainMonth(int chosenMonth) {
        String chosenMonthAsText = turnChosenMonthNumberIntoText(chosenMonth);
        String formattedTotalCost = costFormatter.format(calcTotalExpensesCost(chosenMonth));

        return String.format("Total cost in %s: %s", chosenMonthAsText, formattedTotalCost);
    }

    private BigDecimal calcTotalExpensesCost(int chosenMonth) {
        BigDecimal totalExpensesCost = BigDecimal.ZERO;

        for (Expense expense : expenseListMonths.get(Months.getMonth(chosenMonth))) {
            totalExpensesCost = totalExpensesCost.add(expense.getCost());
        }

        return totalExpensesCost;
    }

    public String findTheBiggestExpenseInCertainMonth(int chosenMonth) {
        List<Expense> certainMonthList = expenseListMonths.get(Months.getMonth(chosenMonth));
        StringBuilder expensesBuilder = new StringBuilder();
        String chosenMonthAsText = turnChosenMonthNumberIntoText(chosenMonth);
        int cost = 0;

        expensesBuilder
                .append("The biggest expenses in ")
                .append(chosenMonthAsText)
                .append(":")
                .append("\n");

        for (Expense expense : certainMonthList) {
            int expenseCost = expense.getCost().intValue();

            if (expenseCost > cost) {
                cost = expenseCost;
            }
        }

        for (Expense expense : certainMonthList) {
            int expenseCost = expense.getCost().intValue();

            if (expenseCost == cost) {
                expensesBuilder
                        .append(expense)
                        .append("\n");
            }
        }

        return expensesBuilder.toString();
    }

    protected boolean isChosenMonthEmpty(int chosenMonth) {
        boolean result = expenseListMonths.get(Months.getMonth(chosenMonth)).isEmpty();

        if (result) {
            System.out.println("There are no expenses in a certain month!");
        }

        return result;
    }

    protected String turnChosenMonthNumberIntoText(int chosenMonth) {
        return Months.getMonth(chosenMonth).toString().toLowerCase();
    }
}
