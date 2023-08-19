package sys.expenselist;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

<<<<<<< HEAD
public class Expense {
=======
class Expense {
>>>>>>> 4b331d2 (Initial commit)
    private ExpenseType expenseType;
    private String expenseName;
    private BigDecimal cost;
    private final NumberFormat costFormatter = NumberFormat.getCurrencyInstance(Locale.US);

<<<<<<< HEAD
    public Expense(ExpenseType expenseType, String expenseName, String cost) {
=======
    protected Expense(ExpenseType expenseType, String expenseName, String cost) {
>>>>>>> 4b331d2 (Initial commit)
        this.expenseType = expenseType;
        this.expenseName = expenseName;
        this.cost = new BigDecimal(cost);
    }

<<<<<<< HEAD
    public BigDecimal getCost() {
=======
    protected BigDecimal getCost() {
>>>>>>> 4b331d2 (Initial commit)
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Expense type: %s, name: %s, cost: %s", expenseType, expenseName, costFormatter.format(cost));
    }
}
