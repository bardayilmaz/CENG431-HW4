package user;

import java.util.List;

import bank.IBank;
import bank.Investable;
import product.IAccount;
import product.IAccountGroup;
import product.Interactable;
import valuable.Valuable;

public interface IUser extends Interactable {

	String getName();
	
	double getTotalBalance();
	
	boolean isBankUser();
	IBank getBank();
	void setBank(IBank bank);
	
	Investable createFund(String name, double value);
	
	void determineInterestRate(Valuable valuable, double rate);
	void determineValue(Valuable valuable, double value);
	
	void passTime(int days);
	
	List<IAccount> getAccounts();
	List<IAccountGroup> getAccountGroups();
	
	IAccount getAccount(int id);
	IAccountGroup getAccountGroup(String name);
	
	IAccountGroup createAccountGroup(String name);
	IAccount createAccount(String type, boolean interest);
	
	IUser createNewUser(String name, boolean bankUser);
	
	void addAccountToAccountGroup(String accountGroupName, int accountId);
	
	void displayOwnedInvestables();
	
}
