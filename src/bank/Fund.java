package bank;

public class Fund implements Investable {

	private double value;
	private String name;
	
	public Fund(double value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	
	@Override
	public double getValue() {
		return this.value;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "Fund [value=" + value + ", name=" + name + "]";
	}
	

}
