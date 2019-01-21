package ex3;

public class ExpressionTest {
	public static void main(String[] args) {
		Expression e = new Multiplaction(new Addition(new Double(2.5), new Double(3.5)), new UnaryMinus(new Integer(5)));
		
		System.out.println(e.eval()); // should print out -30.0
		System.out.println(e.toString()); // should print out ((2.5 + 3.5) * (-(5)))
		
		Expression e1 = new Multiplaction(new Double(6), new Addition(new Integer(4), new Division(new Float(2), new Double(8))));
		System.out.println(e1.eval()); // should print out 25
		System.out.println(e1.toString()); // should print out (6 * (4 + (2 / 8)))
		
		Expression e2 = new UnaryMinus(new Substraction(new Double(3), new Double(5)));
		System.out.println(e2.eval()); // should print out 2
		System.out.println(e2.toString()); // should print out (-((3 - 5)))		
	}
}
