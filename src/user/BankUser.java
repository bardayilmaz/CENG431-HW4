package user;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bank.Fund;
import bank.IBank;
import bank.Investable;
import product.IAccount;
import product.IAccountGroup;
import util.InputUtil;
import valuable.Valuable;

public class BankUser implements IUser {
	
	private String name;
	private IBank bank;

	public BankUser(IBank bank, String name) {
		this.bank = bank;
		this.name = name;
	}

	@Override
	public void displayOptions() {
		System.out.println("1. Create Fund\n2. Determine Interest Rate\n3. Determine Value\n4. Pass Time\n5. Create New User\n6. List valuables\n0. Exit User");
	}

	@Override
	public void evaluateOptionInput(int option) {
		Scanner scanner = new Scanner(System.in);
		switch (option) {
		case 1: {
			String name;
			double value = 0;
			System.out.println("Type name of Fund:");
			name = scanner.nextLine();
			System.out.println("Type value of Fund:");
			try {
				value = scanner.nextDouble();
			} catch(InputMismatchException exception) {
				System.out.println("Invalid value input");
				break;
			}
			createFund(name, value);
			break;
		} case 2: {
			String valuableName = "";
			double interestRate = 0;
			System.out.println("type valuable name: (eur, usd, gold, try)");
			valuableName = scanner.nextLine();
			Valuable valuable;
			valuable = InputUtil.getValuable(valuableName);
			if(valuable == null) {
				break;
			}
			try {
				System.out.println("type interest: ");
				interestRate = scanner.nextDouble();
			} catch(InputMismatchException exception) {
				System.out.println("Invalid interest input");
				break;
			}
			valuable.setInterestRate(interestRate);
			break;
		} case 3: {
			String valuableName;
			double value = 0;
			System.out.print("type valuable name: (eur, usd, gold, try)");
			valuableName = scanner.nextLine();
			Valuable valuable;
			valuable = InputUtil.getValuable(valuableName);
			if(valuable == null) {
				break;
			}
			try {
				value = scanner.nextDouble();
			} catch(InputMismatchException exception) {
				System.out.println("Invalid value input");
				break;
			}
			valuable.setValue(value);
			break;
		} case 4: {
			int days = -1;
			System.out.println("Type days: ");
			try {
				days = scanner.nextInt();
			} catch(InputMismatchException exception) {
				System.out.println("invalid day input");
				break;
			}
			if(days < 1) {
				System.out.println("day count can not be negative");
				break;
			}
			passTime(days);
			break;
		} case 5: {
			String name;
			boolean bankUser = false;
			String bankUserString;
			System.out.println("Type name of user: ");
			name = scanner.nextLine();
			System.out.println("is user a bank user? (Y/N)");
			bankUserString = scanner.nextLine();
			if(bankUserString.equalsIgnoreCase("y")) {
				bankUser = true;
			} else if(bankUserString.equalsIgnoreCase("n")) {
				bankUser = false;
			} else {
				System.out.println("invalid interest input");
				return;
			}
			createNewUser(name, bankUser);
		} case 6: {
			this.bank.listValuables();
			break;
		}	case 0: {
			System.out.println("exit from bankuser " + this.name);
			break;
		}
		default:
			break;
		} 
		
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getTotalBalance() {
		System.out.println("Unsupported operation");
		return -1;
	}

	@Override
	public boolean isBankUser() {
		return true;
	}

	@Override
	public Investable createFund(String name, double value) {
		if (this.bank.investableContainsByName(name)) {
			System.out.println("Investable already exists");
			return null;
		}
		return this.bank.createInvestable(name, value);
	}

	@Override
	public void determineInterestRate(Valuable valuable, double value) {
		valuable.setInterestRate(value);
	}

	@Override
	public void determineValue(Valuable valuable, double value) {
		valuable.setValue(value);
	}

	@Override
	public void passTime(int days) {
		this.bank.applyInterest(days);
	}

	@Override
	public List<IAccount> getAccounts() {
		System.out.println("Unsupported operation for Bank User");
		return null;
	}

	@Override
	public List<IAccountGroup> getAccountGroups() {
		System.out.println("Unsupported operation for Bank User");
		return null;
	}

	@Override
	public IAccountGroup createAccountGroup(String name) {
		System.out.println("Unsupported operation for bank user");
		return null;
	}

	@Override
	public IAccount createAccount(String type, boolean interest) {
		System.out.println("Unsupported operation for bank user");
		return null;
	}

	@Override
	public IBank getBank() {
		return bank;
	}

	@Override
	public IUser createNewUser(String name, boolean bankUser) {
		IUser user = null;
		if (bankUser) {
			user = new BankUser(this.bank, name);
		} else {
			user = new ClientUser(this.bank, name);
			IAccount firstAccount = bank.createAccount("regular", false, user);
			user.getAccounts().add(firstAccount);
		}
		user = this.bank.addUser(user);
		return user;
	}

	@Override
	public IAccount getAccount(int id) {
		System.out.println("Operation is not suppoerted for bank user");
		return null;
	}

	@Override
	public IAccountGroup getAccountGroup(String name) {
		System.out.println("Operation is not suppoerted for bank user");
		return null;
	}

	@Override
	public void addAccountToAccountGroup(String accountGroupName, int accountId) {
		System.out.println("Operation is not suppoerted for bank user");

	}

	@Override
	public int getOption() {
		return InputUtil.getOption();
	}

	@Override
	public void setBank(IBank bank) {
		this.bank = bank;
	}

	@Override
	public String toString() {
		return "BankUser [name=" + name + ", bank=" + bank + "]";
	}
	
	

}
