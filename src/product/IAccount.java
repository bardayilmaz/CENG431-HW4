package product;

import java.util.List;

import bank.IBank;
import bank.Investable;
import user.IUser;
import valuable.Valuable;

public interface IAccount extends Interactable, Groupable {

	void deposit(double amount);
	
	double getExpectedBalance(int days);
	
	void transferFromRegularAccount(IRegularAccount account, double amount);
	
	IBank getBank();
	
	double getInterestRate();
	
	Valuable getValuable();
	
	void exchange(IAccount account, double amount);
	
	double getBalance();
	void setBalance(double amount);
	
	int getId();
	
	List<IAccount> getDailyExchangedInterestAccounts();
	
	void applyInterest(int days);
	
	IUser getUser();
	
	List<Investable> getInvestables();
}
