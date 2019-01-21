package ex3;

public class Addition extends Expression {

	public Addition(Number first, Number second) {
		super(first, second);
	}

	@Override
	public double eval() {
		return this.first.doubleValue() + this.second.doubleValue();
	}

	@Override
	public String toString() {
		return "(" + this.first.toString() + " + " + second.toString() + ")";
	}

}
