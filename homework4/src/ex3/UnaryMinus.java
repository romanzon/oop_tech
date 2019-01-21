package ex3;

/**
 * Implements an unary minus expression
 */
public class UnaryMinus extends Expression {

	/**
	 * Construct a new unary minus expression that consists from one number or expression.
	 * @requires first != null
	 */
	public UnaryMinus(Number first) {
		super(first);
	}

	/**
	 * @return -first
	 */
	@Override
	public double eval() {
		return -this.first.doubleValue();
	}

	/**
	 * @return "(-(first))"
	 */
	@Override
	public String toString() {
		return "(-(" + this.first.toString() + "))";
	}

}
