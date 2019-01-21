package ex3;

abstract public class Expression extends Number{
	
	protected Number first, second;
	
	protected Expression(Number first)
	{
		this.first = first;
	}	
	
	protected Expression(Number first, Number second) {
		this.first = first;
		this.second = second;
	}
	
	public double doubleValue()
	{
		return this.eval();
	}
	
	abstract public double eval();
	
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
