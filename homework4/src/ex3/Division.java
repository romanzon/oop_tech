package ex3;

/**
 * Implements a division expression
 */
public class Division extends Expression {


	/**
	 * Construct a new division expression that consists from two numbers or expressions.
	 * @requires first != null && second != null
	 */
	public Division(Number first, Number second) {
		super(first, second);
	}

	/**
	 * @return first / second
	 */
	@Override
	public double eval() {
		return this.first.doubleValue() / this.second.doubleValue();
	}

	/**
	 * return "(first / second)"
	 */
	@Override
	public String toString() {
		return "(" + this.first.toString() + " / " + this.second.toString() + ")";
	}

}
