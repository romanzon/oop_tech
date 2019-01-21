package ex3;

/**
 * Implements a general expression that consists from one or two numbers or expressions
 */
abstract public class Expression extends Number{
	
	/** The first number that received in the constructor **/
	protected Number first;
	
	/** The second number that received in the constructor **/
	protected Number second;
	
	/**
	 * Construct a new expression that consists from one number or expression.
	 * @requires first != null
	 */
	protected Expression(Number first)
	{
		this.first = first;
	}	

	/**
	 * Construct a new expression that consists from two numbers or expressions.
	 * @requires first != null && second != null
	 */
	protected Expression(Number first, Number second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * @return The double value of the expression which is its value
	 */
	public double doubleValue()
	{
		return this.eval();
	}
	
	/**
	 * @return The expression value
	 */
	abstract public double eval();
	
	/**
	 * @return A string that represents the expression
	 */
	abstract public String toString();

	@Override
	public float floatValue() {
		return (float)this.eval();
	}

	@Override
	public int intValue() {
		return (int)this.eval();
	}

	@Override
	public long longValue() {
		return (long)this.eval();
	}
}
