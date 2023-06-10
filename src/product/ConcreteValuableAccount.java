package product;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bank.IBank;
import bank.Investable;
import user.IUser;
import util.InputUtil;
import valuable.Euro;
import valuable.Valuable;

public class ConcreteValuableAccount implements IValuableAccount {

	private double balance;
	private Valuable valuable;
	private IBank bank;
	private boolean interest;
	private int id;
	private List<IAccount> dailyExchangedInterestAccounts;
	private IUser user;
	
	public ConcreteValuableAccount(double balance, Valuable valuable, IBank bank, boolean interest, int id, IUser user) {
		this.balance = balance;
		this.valuable = valuable;
		this.bank = bank;
		this.interest = interest;
		this.id = id;
		dailyExchangedInterestAccounts = new ArrayList<>();
		this.user = user;
	}
	
	@Override
	public void displayOptions() {
		System.out.println("1. Transfer From Regular Account\n2. Get Expected Balance\n3. Get Interest Rate\n4. Exchange\n5. Get Balance\n6. Get Balance in TRY\n0. Exit");
		
	}

	@Override
	public void evaluateOptionInput(int option) {
		Scanner scanner = new Scanner(System.in);
		switch (option) {
		case 1: {
			int amount = 0;
			int regularAccountId;
			System.out.println("Type amount to be transferred from regular account: ");
			try {
				amount = scanner.nextInt();
				System.out.println("Type regular account id: ");
				regularAccountId = scanner.nextInt();
			} catch(InputMismatchException exception) {
				System.out.println("Invalid input");
				break;
			}
			IAccount regularAccount = this.user.getAccount(regularAccountId);
			if(!(regularAccount instanceof IRegularAccount)) {
				System.out.println("Given account is not regular account");
			}
			this.transferFromRegularAccount((IRegularAccount)regularAccount, amount);
			break;
		} case 2: {
			int days = 0;
			System.out.println("Type days: ");
			try {
				days = scanner.nextInt();
			} catch(InputMismatchException exception) {
				System.out.println("Invalid input");
				break;
			}
			System.out.println("Expected balance in TRY in " + days + " days: " + this.getExpectedBalance(days));
		} case 3: {
			System.out.println("Interest rate: " + this.getInterestRate());
		} case 4: {
			int exchangedAccountId = 0;
			double amount = 0;
			System.out.println("Type account id to be exchanged: ");
			try {
				exchangedAccountId = scanner.nextInt();
				System.out.println("Type amount");
				amount = scanner.nextDouble();
			} catch (InputMismatchException exception) {
				System.out.println("Invalid input");
				break;
			}
			IAccount account = this.user.getAccount(exchangedAccountId);
			this.exchange(account, amount);
			break;
		} case 5: {
			System.out.println("Balance in valuable is: " + this.getBalance());
			break;
		} case 6: {
			System.out.println("Balance in TRY is: " + this.getBalanceInTRY());
		} case 0: {
			break;
		}
		default:
			break;
		}
	}

	@Override
	public double getExpectedBalance(int days) {
		double balanceTRY = this.getValuable().getValue() * this.getBalance();
		if(interest) {
			return balanceTRY + (balanceTRY * valuable.getInterestRate() * days);
		} else {
			return balanceTRY;
		}
	}

	@Override
	public void transferFromRegularAccount(IRegularAccount account, double amount) {
		if(this.getDailyExchangedInterestAccounts().contains(account)) {
			System.out.println("This interest account can not be exchanged twice in a day");
			return;
		}
		if(amount <= 0) {
			System.out.println("Amount can not be negative");
			return;
		}
		if (amount <= account.getBalance()) {
			account.setBalance(account.getBalance() - amount);
			double convertedBalance = amount / Euro.getInstance().getValue();
			setBalance(getBalance() + convertedBalance);
		} else {
			System.out.println("Insufficient balance.");
		}
		if(account.getInterestRate() > 0) {
			this.getDailyExchangedInterestAccounts().add(account);
			account.getDailyExchangedInterestAccounts().add(this);
		}
	}

	@Override
	public IBank getBank() {
		return bank;
	}

	@Override
	public double getInterestRate() {
		if(interest) {
			return valuable.getInterestRate(); 
		}
		return 0;
	}

	@Override
	public Valuable getValuable() {
		return valuable;
	}

	@Override
	public void exchange(IAccount account, double amount) {
		if(!(account instanceof IValuableAccount)) {
			System.out.println("Incompatible accounts");
			return;
		}
		if(!this.getValuable().equals(account.getValuable())) {
			System.out.println("Incompatible valuable types");
			return;
		}
		if(getDailyExchangedInterestAccounts().contains(account)) {
			System.out.println("Cannot exchange twice in a day with same account.");
			return;
		}
		if(amount <= 0) {
			System.out.println("exchane amount can not be negative");
			return;
		}
		if (amount <= this.getBalance()) {
			this.setBalance(this.getBalance() - amount);
			account.setBalance(account.getBalance() + amount);
		}
		if(this.getInterestRate() > 0 || account.getInterestRate() > 0) {
			this.dailyExchangedInterestAccounts.add(account);
			account.getDailyExchangedInterestAccounts().add(this);
		}
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public void setBalance(double amount) {
		this.balance = amount;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public List<IAccount> getDailyExchangedInterestAccounts() {
		return dailyExchangedInterestAccounts;
	}

	@Override
	public void deposit(double amount) {
		System.out.println("Unsupported opreation");
		return;
	}

	@Override
	public void applyInterest(int days) {
		this.balance += this.balance * days * getInterestRate();
	}

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public double getBalanceInTRY() {
		return balance * getValuable().getValue();
	}

	@Override
	public List<Investable> getInvestables() {
		System.out.println("Unsupported opreation");
		return null;
	}

	@Override
	public int getOption() {
		return InputUtil.getOption();
	}

	@Override
	public String toString() {
		return "ConcreteValuableAccount [balance=" + balance + ", valuable=" + valuable + ", id=" + id
				+ ", getInterestRate()=" + getInterestRate() + ", getBalanceInTRY()=" + getBalanceInTRY() + "]";
	}

}
