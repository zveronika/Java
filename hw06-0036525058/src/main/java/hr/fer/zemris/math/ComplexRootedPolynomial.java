package hr.fer.zemris.math;

/**
 *
 * @author Veronika Žunar
 */

public class ComplexRootedPolynomial {

	// ...
	private Complex constant;
	private Complex[] roots;

	// constructor
	public ComplexRootedPolynomial(Complex constant, Complex... roots) {
		this.constant = constant;
		this.roots = roots;
	}

	// computes polynomial value at given point z
	public Complex apply(Complex z) {
		Complex polynomialValue = constant;
		for (int i = 0; i < this.roots.length; i++) {
			Complex c = this.roots[i];
			polynomialValue = polynomialValue.multiply(z.sub(c));
		}
		return polynomialValue;
	}

	// converts this representation to ComplexPolynomial type
	public ComplexPolynomial toComplexPolynom() {
		ComplexPolynomial complexPolynom = new ComplexPolynomial(this.constant);
		
		for (Complex r : roots)
			complexPolynom = complexPolynom.multiply(new ComplexPolynomial(r.negate(), Complex.ONE));
		
		return complexPolynom;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(constant.toString()).append(")");
		for (Complex c : roots) {
			sb.append("*").append("(z-(").append(c.toString()).append("))");
		}

		return sb.toString();
	}

	// finds index of closest root for given complex number z that is within
	// treshold; if there is no such root, returns -1
	// first root has index 0, second index 1, etc
	public int indexOfClosestRootFor(Complex z, double treshold) {
		int index = -1;
		double closest = treshold;
		for (int i = 0; i < roots.length; i++) {
			Complex c = roots[i];
			if (c.sub(z).module() < closest) {
				closest = c.sub(z).module();
				index = i;
			}
		}

		return index;
	}
}
