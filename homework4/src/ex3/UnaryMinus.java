package ex3;

public class UnaryMinus extends Expression {

	public UnaryMinus(Number first) {
		super(first);
	}

	@Override
	public double eval() {
		return -this.first.doubleValue();
	}

	@Override
	public String toString() {
		return "(-(" + this.first.toString() + "))";
	}

}
