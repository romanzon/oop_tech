package ex3;

public class Division extends Expression {

	public Division(Number first, Number second) {
		super(first, second);
	}

	@Override
	public double eval() {
		return this.first.doubleValue() / this.second.doubleValue();
	}

	@Override
	public String toString() {
		return "(" + this.first.toString() + " / " + this.second.toString() + ")";
	}

}
