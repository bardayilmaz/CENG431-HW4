package valuable;

public class Dollar implements Valuable {
	
	private static Dollar uniqueInstance = new Dollar();
	
	private double value;
	private double interestRate;
	
	public static Dollar getInstance() {
		return uniqueInstance;
	}
	
	private Dollar() {}
	
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
		return "Dollar [value=" + value + ", interestRate=" + interestRate + "]";
	}
	
}
