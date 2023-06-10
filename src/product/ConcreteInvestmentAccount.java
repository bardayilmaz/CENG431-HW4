package product;

import java.util.ArrayList;
import java.util.List;

import bank.IBank;
import bank.Investable;
import user.IUser;
import util.InputUtil;
import valuable.TurkishLira;
import valuable.Valuable;

public class ConcreteInvestmentAccount implements IInvestmentAccount {

	private List<Investable> investables;
	private IBank bank;
	private IUser user;
	private int id;
	
	public ConcreteInvestmentAccount(List<Investable> investables, IBank bank, IUser user, int id) {
		this.investables = investables;
		this.bank = bank;
		this.user = user;
		this.id = id;
	}
	
	public ConcreteInvestmentAccount(IBank bank, IUser user, int id) {
		this.investables = new ArrayList<>();
		this.bank = bank;
		this.user = user;
		this.id = id;
	}

	@Override
	public void displayOptions() {
		System.out.println("1. List Valuables\n2. Get Balance\n0. Exit");
		
	}

	@Override
	public void evaluateOptionInput(int option) {
		switch (option) {
		case 1: {
			for(Investable investable : this.investables) {
				System.out.println(investable.toString());
			}
			break;
		} case 2: {
			System.out.println("Balance in TRY of account with id " + this.getId() + " is: " + this.getBalanceInTRY());
		} case 0: {
			return;
		}
		default:
			return;
		}
	}

	@Override
	public double getBalanceInTRY() {
		double sum = 0;
		for(Investable investable : investables) {
			sum += investable.getValue();
		}
		return sum;
	}

	@Override
	public void deposit(double amount) {
		System.out.println("Operation not supported");
	}

	@Override
	public double getExpectedBalance(int days) {
		return getBalance();
	}

	@Override
	public void transferFromRegularAccount(IRegularAccount account, double amount) {
		System.out.println("Operation not supported");
	}

	@Override
	public IBank getBank() {
		return bank;
	}

	@Override
	public double getInterestRate() {
		return 0;
	}

	@Override
	public Valuable getValuable() {
		return TurkishLira.getInstance();
	}

	@Override
	public void exchange(IAccount account, double amount) {
		System.out.println("Operation not supported");
		
	}

	@Override
	public double getBalance() {
		return getBalanceInTRY();
	}

	@Override
	public void setBalance(double amount) {
		System.out.println("Operation not supported");
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public List<IAccount> getDailyExchangedInterestAccounts() {
		return null;
	}

	@Override
	public void applyInterest(int days) {
		System.out.println("Operation not supported");
	}

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public List<Investable> getInvestables() {
		return investables;
	}

	@Override
	public int getOption() {
		return InputUtil.getOption();
	}

	@Override
	public String toString() {
		return "ConcreteInvestmentAccount [investables=" + investables + ", id=" + id + ", getBalanceInTRY()="
				+ getBalanceInTRY() + "]";
	}
	
}
