package product;

import java.util.ArrayList;
import java.util.List;

public class AccountGroup implements IAccountGroup {

	List<Groupable> groupables;
	private String name;
	
	public AccountGroup(List<Groupable> groupables, String name) {
		this.groupables = groupables;
		this.name = name;
	}
	
	public AccountGroup(String name) {
		this.groupables = new ArrayList<>();
		this.name = name;
	}
	
	public AccountGroup() {
		this.groupables = new ArrayList<>();
	}
	
	@Override
	public double getBalanceInTRY() {
		double sum = 0;
		for(Groupable groupable : groupables) {
			sum+=groupable.getBalanceInTRY();
		}
		return sum;
	}

	@Override
	public void add(Groupable groupable) {
		this.groupables.add(groupable);
	}

	@Override
	public void remove(Groupable groupable) {
		this.groupables.remove(groupable);
	}

	@Override
	public List<Groupable> getAll() {
		return groupables;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public void display() {
		System.out.println(this.getName());
		for(Groupable groupable : this.groupables) {
			System.out.println(groupable.toString());
		}
	}
}
