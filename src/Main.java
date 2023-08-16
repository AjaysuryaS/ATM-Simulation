import java.util.Scanner;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class ATM {
    private double balance;

    public ATM(double initialBalance) {
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid amount for deposit.");
        }
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid amount for withdrawal.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: " + balance);
    }
}

class IndianBankATM extends ATM {
    public IndianBankATM(double initialBalance) {
        super(initialBalance);
    }

    public void initialize() throws InvalidAmountException, InsufficientFundsException {
        Scanner scanner = new Scanner(System.in);
        int userChoice, inputAmount;

        do {
            System.out.println("________________________________");
            System.out.println("1 - Deposit Amount");
            System.out.println("2 - Withdraw Amount");
            System.out.println("3 - Check Balance");
            System.out.println("4 - Exit");
            System.out.println("________________________________");
            System.out.print("Enter your preferred option ... ");
            userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1:
                    System.out.print("Enter the amount to be deposited ... Rs.");
                    inputAmount = Integer.parseInt(scanner.nextLine());
                    if (inputAmount <= 0) {
                        throw new InvalidAmountException("Amount must be greater than 0.");
                    }
                    deposit(inputAmount);
                    break;

                case 2:
                    System.out.print("Enter the amount to be withdrawn ... Rs.");
                    inputAmount = Integer.parseInt(scanner.nextLine());
                    if (inputAmount <= 0) {
                        throw new InvalidAmountException("Amount must be greater than 0.");
                    }
                    withdraw(inputAmount);
                    break;

                case 3:
                    System.out.println("Your balance is Rs. " + checkBalance());
                    break;

                case 4:
                    System.out.println("Exiting ATM...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Choose a valid option.");
            }
        } while (true);
    }
}

public class Main {
    public static void main(String[] args) throws InvalidAmountException, InsufficientFundsException {
        double initialBalance = 4000.0;
        IndianBankATM indianBankATM = new IndianBankATM(initialBalance);
        indianBankATM.initialize();
    }
}
