package valuable;

public class Gold implements Valuable{
	private static Gold uniqueInstance = new Gold();

	private double value;
	private double interestRate;
	
	public static Gold getInstance() {
		return uniqueInstance;
	}
	
	private Gold() {}

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
		return "Gold [value=" + value + ", interestRate=" + interestRate + "]";
	}
	
}
