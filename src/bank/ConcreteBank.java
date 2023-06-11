package bank;

import java.util.ArrayList;
import java.util.List;

import factory.AccountFactory;
import factory.InterestAccountFactory;
import factory.WithoutInterestAccountFactory;
import product.IAccount;
import user.ClientUser;
import user.IUser;
import valuable.Dollar;
import valuable.Euro;
import valuable.Gold;
import valuable.TurkishLira;
import valuable.Valuable;

public class ConcreteBank implements IBank {

	private int accountCount;
	private List<IAccount> accounts;
	private List<IUser> users;
	private int currentDay;
	private List<Investable> investables;
	
	public ConcreteBank() {
		this.accountCount = 0;
		this.accounts = new ArrayList<>();
		this.users = new ArrayList<>();
		this.currentDay = 1;
		this.investables = new ArrayList<>();
	}
	
	@Override
	public void setInterestRate(Valuable valuable, double rate) {
		valuable.setInterestRate(rate);
	}

	@Override
	public double getInterestRate(Valuable valuable) {
		return valuable.getInterestRate();
	}

	@Override
	public int getAccountCount() {
		return accountCount;
	}

	@Override
	public void incrementAccountCount() {
		this.accountCount++;
	}

	@Override
	public void applyInterest(int days) {
		for(IAccount account : accounts) {
			account.applyInterest(days);
			List<IAccount> dailyExchangeds = account.getDailyExchangedInterestAccounts();
			if(dailyExchangeds != null) {
				dailyExchangeds.clear();
			}
		}
		this.currentDay += days;
	}

	@Override
	public IAccount createAccount(String type, boolean interest, IUser owner) {
		AccountFactory factory = determineFactory(interest, owner);
		IAccount account = null;
		if(type.toLowerCase().equals("regular")) {
			account = factory.createRegularAccount();
		} else if(type.toLowerCase().equals("usd")) {
			account = factory.createUsdAccount();
		} else if(type.toLowerCase().equals("eur")) {
			account = factory.createEuroAccount();
		} else if(type.toLowerCase().equals("gold")) {
			account = factory.createGoldAccount();
		} else if(type.toLowerCase().equals("invest")) {
			account = factory.createInvestmentAccount();
		} else {
			System.out.println("Invalid type input");
			return null;
		}
		if(account != null) {
			this.accountCount++;
			this.accounts.add(account);
		}
		return account;
	}

	@Override
	public List<IUser> getUsers() {
		return users;
	}
	
	private AccountFactory determineFactory(boolean interest, IUser owner) {
		if(interest) {
			return new InterestAccountFactory(this, owner);
		} else {
			return new WithoutInterestAccountFactory(this, owner);
		}
	}

	@Override
	public int getCurrentDay() {
		return currentDay;
	}

	@Override
	public List<Investable> getInvestables() {
		return investables;
	}

	@Override
	public boolean investableContainsByName(String name) {
		for(Investable investable : investables) {
			if(investable.getName().toLowerCase().equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IUser addUser(IUser user) {
		for(IUser currentUser : this.users) {
			if(currentUser.getName().toLowerCase().equals(user.getName())) {
				System.out.println("user already exists");
				return null;
			}
		}
		user.setBank(this);
		this.users.add(user);
		return user;
	}


	@Override
	public Investable createInvestable(String name, double value) {
		for(Investable investable : this.investables) {
			if(investable.getName().equalsIgnoreCase(name)) {
				System.out.println("Investable already exists!");
				return null;
			}
		}
		Investable investable = new Fund(value, name);
		this.investables.add(investable);
		return investable;
	}

	@Override
	public IUser getUserByName(String name) {
		for(IUser user : this.users) {
			if(user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		System.out.println("User not found!");
		return null;
	}

	@Override
	public void listUsers() {
		for(IUser user : this.users) {
			System.out.println(user.toString());
		}
	}

	@Override
	public String toString() {
		return "ConcreteBank [accountCount=" + accountCount + ", currentDay=" + currentDay + "]";
	}

	@Override
	public void listValuables() {
		System.out.println(Dollar.getInstance().toString());
		System.out.println(Euro.getInstance().toString());
		System.out.println(TurkishLira.getInstance().toString());
		System.out.println(Gold.getInstance().toString());
	}

	@Override
	public void displayInvestables() {
		for(Investable investable : this.investables) {
			investable.display();
		}
	}

	@Override
	public Investable getInvestableByName(String name) {
		for(Investable investable : this.investables) {
			if(investable.getName().equalsIgnoreCase(name)) {
				return investable;
			}
		}
		System.out.println("Investable not found");
		return null;
	}
}
