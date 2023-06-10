package factory;

import bank.IBank;
import product.ConcreteRegularAccount;
import product.ConcreteValuableAccount;
import product.IInvestmentAccount;
import product.IRegularAccount;
import product.IValuableAccount;
import user.IUser;
import valuable.Dollar;
import valuable.Euro;
import valuable.Gold;
import valuable.TurkishLira;

public class InterestAccountFactory implements AccountFactory {

	private IBank bank;
	private IUser user;
	
	public InterestAccountFactory(IBank bank, IUser user) {
		this.bank = bank;
		this.user = user;
	}
	
	@Override
	public IRegularAccount createRegularAccount() {
		IRegularAccount regularAccount = new ConcreteRegularAccount(0, bank.getAccountCount(), bank, this.user, true);
		return regularAccount;
	}

	@Override
	public IValuableAccount createUsdAccount() {
		IValuableAccount valuableAccount = new ConcreteValuableAccount(0, Dollar.getInstance(), bank, true, bank.getAccountCount(), this.user);
		return valuableAccount;
	}

	@Override
	public IValuableAccount createEuroAccount() {
		IValuableAccount valuableAccount = new ConcreteValuableAccount(0, Euro.getInstance(), bank, true, bank.getAccountCount(), this.user);
		return valuableAccount;
	}

	@Override
	public IValuableAccount createGoldAccount() {
		IValuableAccount valuableAccount = new ConcreteValuableAccount(0, Gold.getInstance(), bank, true, bank.getAccountCount(), this.user);
		return valuableAccount;
	}

	@Override
	public IInvestmentAccount createInvestmentAccount() {
		System.out.println("InvestmentAccount is unsuppoerted operation for InterestAccountFactory");
		return null;
	}

	@Override
	public IBank getBank() {
		return bank;
	}
	
	@Override
	public IUser getUser() {
		return this.user;
	}

}
