package ex3;

/**
 * Implements an addition expression
 */
public class Addition extends Expression {

	/**
	 * Construct a new addition expression that consists from two numbers or expressions.
	 * @requires first != null && second != null
	 */
	public Addition(Number first, Number second) {
		super(first, second);
	}

	/**
	 * @return first + second
	 */
	@Override
	public double eval() {
		return this.first.doubleValue() + this.second.doubleValue();
	}

	/**
	 * @return "(first + second)"
	 */
	@Override
	public String toString() {
		return "(" + this.first.toString() + " + " + second.toString() + ")";
	}

}
