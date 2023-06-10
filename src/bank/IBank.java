package bank;

import java.util.List;

import product.IAccount;
import user.IUser;
import valuable.Valuable;

public interface IBank {
	
	void setInterestRate(Valuable valuable, double rate);
	double getInterestRate(Valuable valuable);
	void listValuables();
	
	int getAccountCount();
	void incrementAccountCount();
	
	void applyInterest(int days);
	
	IAccount createAccount(String type, boolean interest, IUser owner);
	List<IUser> getUsers();
	IUser getUserByName(String name);
	void listUsers();
	
	int getCurrentDay();
	
	List<Investable> getInvestables();
	boolean investableContainsByName(String name);
	IUser addUser(IUser user);
	
	Investable createInvestable(String name, double value);
}
