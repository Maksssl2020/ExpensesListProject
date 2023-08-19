package sys.expenselist;

import java.util.Scanner;

public class ExpenseListUserInterface {
    private String userEnteredData;
    private boolean checkingResult;
    private final Scanner getDataFromUser = new Scanner(System.in);

    public ExpenseListUserInterface() {}

    protected String choiceMenu() {
        return """
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
    }

    protected String askUserForEnterNumber() {
        do {
            userEnteredData = getDataFromUser.nextLine();
            checkingResult = checkUserChoice(userEnteredData);
            if (!checkingResult) {
                System.out.println("You entered incorrect number! Pleaser try again: ");
            }
        } while (!checkingResult);

        return userEnteredData;
    }

    protected boolean checkUserChoice(String userChoice) {
        String userChoiceRegex = "[1-8]";

        return userChoice.matches(userChoiceRegex);
    }

    protected String askUserForEnterYear() {
        System.out.println("Enter a expense list year: ");
        do {
            userEnteredData = getDataFromUser.nextLine();
            checkingResult = checkEnteredUserYear(userEnteredData);
            if (!checkingResult) {
                System.out.println("You entered incorrect year! Please try again: ");
            }
        } while (!checkingResult);

        return userEnteredData;
    }

    protected boolean checkEnteredUserYear(String userYear) {
        String yearRegex = "[0-9]{4}";

        return userYear.matches(yearRegex);
    }


    protected int askUserForEnterMonthNumber() {
        System.out.println("Enter month number between 1 and 12: ");
        do {
            userEnteredData = getDataFromUser.nextLine();
            checkingResult = checkEnteredUserMonthNumber(userEnteredData);
            if (!checkingResult) {
                System.out.println("Sorry, you entered invalid month number! Please try again: ");
            }
        } while (!checkingResult);

        return Integer.parseInt(userEnteredData);
    }

    protected boolean checkEnteredUserMonthNumber(String userEnteredData) {
        String monthNumberRegex = "[0-9][1-2]*";

        return userEnteredData.matches(monthNumberRegex) && Integer.parseInt(userEnteredData) < 13;
    }

    public ExpenseType askUserForEnterExpenseType() {
        System.out.printf("There are possible expense types:%n%s%nPlease enter expense type:%n", possibleTypesToChoose());;
        do {
            userEnteredData = getDataFromUser.nextLine();
            checkingResult = checkEnteredUserType(userEnteredData);
        } while (!checkingResult);

        return ExpenseType.valueOf(userEnteredData.toUpperCase());
    }

    protected String possibleTypesToChoose() {
        StringBuilder possibleTypesBuilder = new StringBuilder();

        for (int i = 0; i < ExpenseType.values().length; i++) {
            possibleTypesBuilder
                    .append(ExpenseType.values()[i])
                    .append("\n");
        }

        return possibleTypesBuilder.toString();
    }

    protected boolean checkEnteredUserType(String userType) {
        ExpenseType[] possibleTypes = ExpenseType.values();
        boolean result = false;

        for (ExpenseType possibleType : possibleTypes) {
            if (possibleType.toString().equalsIgnoreCase(userType)) {
                result = true;
                break;
            }
        }

        if (!result) {
            System.out.println("You entered incorrect expense type! Please try again: ");
        }

        return result;
    }

    public String askUserForEnterExpenseName() {
        System.out.println("Enter a expense name: ");
        do {
            userEnteredData = getDataFromUser.nextLine();
            checkingResult = checkEnteredUserExpenseName(userEnteredData);
            if (!checkingResult) {
                System.out.println("You entered incorrect expense name! Please try again: ");
            }
        } while (!checkingResult);

        return userEnteredData.toLowerCase();
    }

    protected boolean checkEnteredUserExpenseName(String userEnteredData) {
        String expenseNameRegex = "(\\w+\\s*)+";

        return userEnteredData.matches(expenseNameRegex);
    }

    public String askUserForEnterExpenseCost() {
        System.out.println("Enter expense cost, number must be greater than 0: ");
        do {
            userEnteredData = getDataFromUser.nextLine();
            checkingResult = checkEnteredUserExpenseCost(userEnteredData);
        } while (!checkingResult);

        return userEnteredData;
    }

    protected boolean checkEnteredUserExpenseCost(String userEnteredData) {
        String expenseCostRegex = "[0-9]+.[0-9]*";
        boolean result;

        if (userEnteredData.matches(expenseCostRegex) && Double.parseDouble(userEnteredData) > 0) {
            result = true;
        } else {
            System.out.println("You entered incorrect expense cost! Please try again: ");
            result = false;
        }

        return result;
    }
}
