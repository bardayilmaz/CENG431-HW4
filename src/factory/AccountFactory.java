package factory;

import bank.IBank;
import product.IInvestmentAccount;
import product.IRegularAccount;
import product.IValuableAccount;
import user.IUser;

public interface AccountFactory {
	
	IRegularAccount createRegularAccount();
	IValuableAccount createUsdAccount();
	IValuableAccount createEuroAccount();
	IValuableAccount createGoldAccount();
	IInvestmentAccount createInvestmentAccount();
	
	IBank getBank();
	IUser getUser();
}
