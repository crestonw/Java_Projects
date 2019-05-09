
public class Fractions implements Comparable<Fractions> {

	private Integer numerator;
	private Integer denominator;
	
	public Fractions(int num1, int num2) {
		numerator = new Integer(num1);
		denominator = new Integer(num2);
	}
	
	public String toString() {
		String output = numerator.toString() + "/" + denominator.toString();
		return output;
	}
	
	public int getDenominator() {
		return denominator;
	}

	public int getNumerator() {
		return numerator;
	}

	@Override
	public int compareTo(Fractions o) {
		int common1 = numerator * o.getDenominator();
		int common2 = denominator * o.getNumerator();
		return common1 - common2;
	}

}
