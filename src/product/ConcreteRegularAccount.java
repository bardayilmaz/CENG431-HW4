package product;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bank.IBank;
import bank.Investable;
import factory.AccountFactory;
import user.IUser;
import util.InputUtil;
import valuable.TurkishLira;
import valuable.Valuable;

public class ConcreteRegularAccount implements IRegularAccount {

	private double balance;
	private IBank bank;
	private int id;
	private IUser user;
	private boolean interest;
	private List<IAccount> dailyExchangedInterestAccounts;
	
	public ConcreteRegularAccount(double balance, int id, IBank bank, IUser user, boolean interest) {
		this.balance = balance;
		this.id = id;
		this.bank = bank;
		this.user = user;
		this.interest = interest;
		this.dailyExchangedInterestAccounts = new ArrayList<>();
	}
	
	@Override
	public void deposit(double amount) {
		if(amount > 0) {
			this.balance += amount;
		} else {
			System.out.println("Amount must be positive");
		}
	}

	@Override
	public void exchange(IAccount account, double amount) {
		if(!(account instanceof IRegularAccount)) {
			System.out.println("Incompatible amounts");
		}
		if(amount <= 0) {
			System.out.println("Amount must be positive");
			return;
		}
		if(dailyExchangedInterestAccounts.contains(account)) {
			System.out.println("Cannot exchange with this account in same day.");
		}
		account.setBalance(account.getBalance() - amount);
		if(account.getInterestRate() > 0) {
			this.dailyExchangedInterestAccounts.add(account);
			account.getDailyExchangedInterestAccounts().add(this);
		}
		this.balance += amount;
	}

	@Override
	public double getExpectedBalance(int days) {
		return balance + (balance * getInterestRate() * days);
	}

	@Override
	public IBank getBank() {
		return bank;
	}

	@Override
	public double getInterestRate() {
		return this.interest ? TurkishLira.getInstance().getInterestRate() : 0;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public void displayOptions() {
		System.out.println("1. Deposit Money\n2. Get Balance\n3. Get Interest Rate\n4. Get Future Balance\n0. Exit");
	}

	@Override
	public void evaluateOptionInput(int option) {
		Scanner scanner = new Scanner(System.in);
		switch (option) {
		case 1: {
			double amount;
			try {
				System.out.println("Amount: ");
				amount = scanner.nextDouble();
			} catch(InputMismatchException exception) {
				System.out.println("Invalid input");
				break;
			}
			this.deposit(amount);
			System.out.println("Current balance: " + getBalanceInTRY());
			return;
		} case 2: {
			System.out.println("Balance: " + this.getBalance());
			break;
		} case 3: {
			System.out.println("Interest rate: " + this.getInterestRate());
			break;
		} case 4: {
			int days = 0;
			System.out.println("Type days");
			try {
				days = scanner.nextInt();
			} catch(InputMismatchException exception) {
				System.out.println("Invalid input");
				break;
			}
			System.out.println("Expected balance in " + days + " days is: " + getExpectedBalance(days));
			break;
		} case 0: {
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + option);
		}		
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
	public void transferFromRegularAccount(IRegularAccount account, double amount) {
		System.out.println("Unsupported operation");
		return;
	}

	@Override
	public Valuable getValuable() {
		return TurkishLira.getInstance();
	}

	@Override
	public List<IAccount> getDailyExchangedInterestAccounts() {
		return this.dailyExchangedInterestAccounts;
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
		return balance;
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
		return "ConcreteRegularAccount [balance=" + balance + ", id=" + id + ", getInterestRate()=" + getInterestRate()
				+ ", getBalanceInTRY()=" + getBalanceInTRY() + "]";
	}
	
}
