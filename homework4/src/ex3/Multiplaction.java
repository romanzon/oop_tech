package ex3;

public class Multiplaction extends Expression {

	public Multiplaction(Number first, Number second) {
		super(first, second);
	}

	@Override
	public double eval() {
		return this.first.doubleValue() * this.second.doubleValue();
	}

	@Override
	public String toString() {
		return "(" + this.first.toString() + " * " + this.second.toString() + ")";
	}

}
