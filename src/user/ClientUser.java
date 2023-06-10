package user;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bank.IBank;
import bank.Investable;
import product.AccountGroup;
import product.Groupable;
import product.IAccount;
import product.IAccountGroup;
import util.InputUtil;
import valuable.Valuable;

public class ClientUser implements IUser {

	private List<IAccount> accounts;
	private List<IAccountGroup> accountGroups;
	private IBank bank;
	private String name;
	
	public ClientUser(List<IAccount> accounts, List<IAccountGroup> accountGroups, IBank bank, String name) {
		super();
		this.accounts = accounts;
		this.accountGroups = accountGroups;
		this.bank = bank;
		this.name = name;
	}

	public ClientUser(IBank bank, String name) {
		this.accounts = new ArrayList<>();
		this.accountGroups = new ArrayList<>();
		this.bank = bank;
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getTotalBalance() {
		double sum = 0;
		for(IAccount account : getAccounts()) {
			sum += account.getBalanceInTRY();
		}
		return sum;
	}

	@Override
	public boolean isBankUser() {
		return false;
	}

	@Override
	public Investable createFund(String name, double value) {
		System.out.println("Unsupported operation");
		return null;
	}

	@Override
	public void determineInterestRate(Valuable valuable, double value) {
		System.out.println("Unsupported operation");
		
	}

	@Override
	public void determineValue(Valuable valuable, double value) {
		System.out.println("Unsupported operation");
	}

	@Override
	public void passTime(int days) {
		System.out.println("Unsupported operation");
	}

	@Override
	public List<IAccount> getAccounts() {
		return accounts;
	}

	@Override
	public List<IAccountGroup> getAccountGroups() {
		return accountGroups;
	}

	@Override
	public void displayOptions() {
		System.out.println("1. Get Total Balance (in TRY)\n2. Create Account\n3. Create Account Group\n4. Add Account to Account Group\n"
				+ "5. Get Account\n6. Get Account Group\n7. Buy Investable\n8. List Accounts\n9. List Account Groups\n10. Get Balance of Account Group\n0. Exit");
	}

	@Override
	public void evaluateOptionInput(int option) {
		Scanner scanner = new Scanner(System.in);
		switch (option) {
		case 1: {
			System.out.println("Total balance in TRY: " + getTotalBalance());
			return;
		} case 2: {
			String interestString;
			boolean interest;
			String type;
			System.out.println("Interest: (Y/N)");
			interestString = scanner.next();
			if(interestString.equalsIgnoreCase("y")) {
				interest = true;
			} else if(interestString.equalsIgnoreCase("n")) {
				interest = false;
			} else {
				System.out.println("invalid interest input");
				return;
			}
			System.out.println("Type: (regular, EUR, USD, Gold, Invest)");
			type = scanner.next();
			IAccount newAccount = this.bank.createAccount(type, interest, this);
			if(newAccount == null) {
				return;
			}
			this.accounts.add(newAccount);
			System.out.println("Account succesfully created");
			return;
		} case 3: {
			String name;
			System.out.println("Type account group name");
			name = scanner.next();
			createAccountGroup(name);
			return;
		} case 4: {
			String name;
			int accountId;
			System.out.println("Type account group name");
			name = scanner.next();
			System.out.println("Type account id");
			try {
				accountId = scanner.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("invalid id input");
				return;
			}
			this.addAccountToAccountGroup(name, accountId);
			return;
		}  case 5: {
			int accountId = -1;
			System.out.println("Type account id");
			try {
				accountId = scanner.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("invalid id input");
				return;
			}
			IAccount account = getAccount(accountId);
			if(account == null) {
				break;
			}
			System.out.println(account.toString());
			account.displayOptions();
			int accountOption = account.getOption();
			account.evaluateOptionInput(accountOption);
			return;
		} case 6: {
			String name;
			System.out.println("Type account group name");
			name = scanner.next();
			IAccountGroup accountGroup = getAccountGroup(name);
			System.out.println(accountGroup.toString());
			return;
		} case 7: {
			return;
		} case 8: {
			for(IAccount account : this.accounts) {
				System.out.println(account.toString());
			}
			break;
		} case 9: {
			for(IAccountGroup accountGroup : accountGroups) {
				accountGroup.display();
				System.out.println();
			}
			break;
		} case 10: {
			String accountGroupName;
			System.out.println("Type account group name");
			accountGroupName = scanner.nextLine();
			IAccountGroup accountGroup = getAccountGroup(accountGroupName);
			if(accountGroup != null) {
				System.out.println("Balance of group " + accountGroup.getName() + " is: " + accountGroup.getBalanceInTRY());
			}
			break;
		}
		default:
			System.out.println("invalid input");
			return;
		}
	}

	@Override
	public IAccountGroup createAccountGroup(String name) {
		if(checkAccountGroupWithGivenNameExists(name)) {
			System.out.println("Account group with given name already exists");
			return null;
		}
		IAccountGroup newAccountGroup = new AccountGroup(name);
		accountGroups.add(newAccountGroup);
		return newAccountGroup;
	}
	
	private boolean checkAccountGroupWithGivenNameExists(String name) {
		for(IAccountGroup accountGroup : accountGroups) {
			if(accountGroup.getName().equals(name)) {
				return true;
			}
		}
		return false;	
	}

	@Override
	public IAccount createAccount(String type, boolean interest) {
		IAccount account = this.bank.createAccount(type, interest, this);
		this.accounts.add(account);
		return account;
	}

	@Override
	public IBank getBank() {
		return this.bank;
	}

	@Override
	public IUser createNewUser(String name, boolean bankUser) {
		System.out.println("unsuppoerted operation for client user");
		return null;
	}

	@Override
	public void addAccountToAccountGroup(String accountGroupName, int accountId) {
		IAccountGroup accountGroup = getAccountGroup(accountGroupName);
		IAccount account = getAccount(accountId);
		if(accountGroup == null || account == null) {
			return;
		}
		for(Groupable currentAccount : accountGroup.getAll()) {
			IAccount accountGroupable  = (IAccount) currentAccount;
			if(accountGroupable.getId() == accountId) {
				System.out.println("Account already in this group");
				return;
			}
		}
		accountGroup.add(account);
		System.out.println("Account with id " + accountId + " succesfully added to account group " + accountGroupName);
	}

	@Override
	public IAccount getAccount(int id) {
		for(IAccount account : accounts) {
			if(account.getId() == id) {
				return account;
			}
		}
		System.out.println("Account does not exists for user " + this.name);
		return null;
	}

	@Override
	public IAccountGroup getAccountGroup(String name) {
		for(IAccountGroup accountGroup : accountGroups) {
			if(accountGroup.getName().toLowerCase().equals(name)) {
				return accountGroup;
			}
		}
		System.out.println("Account group does not exists.");
		return null;
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
		return "ClientUser [accounts=" + accounts + ", accountGroups=" + accountGroups + ", bank=" + bank + ", name="
				+ name + "]";
	}
	
	

}
