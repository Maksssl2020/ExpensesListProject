package sys.expenselist;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Expense {
    private ExpenseType expenseType;
    private String expenseName;
    private BigDecimal cost;
    private final NumberFormat costFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    public Expense(ExpenseType expenseType, String expenseName, String cost) {
        this.expenseType = expenseType;
        this.expenseName = expenseName;
        this.cost = new BigDecimal(cost);
    }

    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Expense type: %s, name: %s, cost: %s", expenseType, expenseName, costFormatter.format(cost));
    }
}
