package factory;

import bank.IBank;
import product.ConcreteInvestmentAccount;
import product.ConcreteRegularAccount;
import product.ConcreteValuableAccount;
import product.IInvestmentAccount;
import product.IRegularAccount;
import product.IValuableAccount;
import user.IUser;
import valuable.Dollar;
import valuable.Euro;
import valuable.Gold;

public class WithoutInterestAccountFactory implements AccountFactory {

	private IBank bank;
	private IUser user;
	
	public WithoutInterestAccountFactory(IBank bank, IUser user) {
		this.bank = bank;
		this.user = user;
	}
	
	@Override
	public IRegularAccount createRegularAccount() {
		IRegularAccount regularAccount = new ConcreteRegularAccount(0, bank.getAccountCount(), bank, user, false);
		return regularAccount;
	}

	@Override
	public IValuableAccount createUsdAccount() {
		IValuableAccount valuableAccount = new ConcreteValuableAccount(0, Dollar.getInstance(), bank, false, bank.getAccountCount(), this.user);
		return valuableAccount;
	}

	@Override
	public IValuableAccount createEuroAccount() {
		IValuableAccount valuableAccount = new ConcreteValuableAccount(0, Euro.getInstance(), bank, false, bank.getAccountCount(), this.user);
		return valuableAccount;
	}

	@Override
	public IValuableAccount createGoldAccount() {
		IValuableAccount valuableAccount = new ConcreteValuableAccount(0, Gold.getInstance(), bank, false, bank.getAccountCount(), this.user);
		return valuableAccount;
	}

	@Override
	public IInvestmentAccount createInvestmentAccount() {
		IInvestmentAccount investmentAccount = new ConcreteInvestmentAccount(bank, this.user, bank.getAccountCount());
		return investmentAccount;
	}

	@Override
	public IBank getBank() {
		return this.bank;
	}
	
	@Override
	public IUser getUser() {
		return this.user;
	}

}
