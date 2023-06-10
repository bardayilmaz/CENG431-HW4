package valuable;

public class TurkishLira implements Valuable {
	private static TurkishLira uniqueInstance = new TurkishLira();

	private double value;
	private double interestRate;
	
	public static TurkishLira getInstance() {
		return uniqueInstance;
	}
	
	private TurkishLira() {}

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
		return "TurkishLira [value=" + value + ", interestRate=" + interestRate + "]";
	}
	
}
