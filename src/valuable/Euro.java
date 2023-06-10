package valuable;

public class Euro implements Valuable {

	private static Euro uniqueInstance = new Euro();
	
	private double value;
	private double interestRate;
	
	public static Euro getInstance() {
		return uniqueInstance;
	}
	
	private Euro() {}
	
	@Override
	public double getValue() {
		return value;
	}

	@Override
	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public double getInterestRate() {
		return interestRate;
	}
	
	@Override
	public String toString() {
		return "Euro [value=" + value + ", interestRate=" + interestRate + "]";
	}

}
