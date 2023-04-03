package hr.fer.zemris.math;

/**
 *
 * @author Veronika Žunar
 */

public class ComplexPolynomial {

	// ...
	private Complex[] factors;

	// constructor
	public ComplexPolynomial(Complex...factors) {
		this.factors = factors;
	}

	// returns order of this polynom; eg. For (7+2i)z^3+2z^2+5z+1 returns 3
	public short order() {
		return (short) (this.factors.length - 1);
	}

	// computes a new polynomial this*p
	public ComplexPolynomial multiply(ComplexPolynomial p) {
		int len = this.factors.length + p.factors.length - 1;
		Complex[] newFactors = new Complex[len];
		
		for (int i = 0; i < len; i++) {
			newFactors[i] = Complex.ZERO;
		}
		
		Complex coeffT = new Complex();
		Complex coeffP = new Complex();

		for (int i = 0; i < this.factors.length; i++) {
			coeffT = this.factors[i];
			for (int j = 0; j < p.factors.length; j++) {
				coeffP = p.factors[j];
				newFactors[i + j] = newFactors[i+j].add(coeffT.multiply(coeffP));
			}
		}

		return new ComplexPolynomial(newFactors);
	}

	public ComplexPolynomial derive() {
		int len = this.factors.length - 1;
		Complex[] newFactors = new Complex[len];

		for (int i = 0; i < len; i++) {
			Complex factor = this.factors[i + 1];
			Complex mul = new Complex(i + 1, 0);
			newFactors[i] = factor.multiply(mul);
		}

		return new ComplexPolynomial(newFactors);
	}

	// computes polynomial value at given point z
	public Complex apply(Complex z) {
		Complex polynomialValue = this.factors[0];
		for (int i = 1; i < this.factors.length; i++) {
			Complex c = this.factors[i];
			polynomialValue = polynomialValue.add(c.multiply(z.power(i)));
		}

		return polynomialValue;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = this.factors.length - 1; i >= 0; i--) {
			sb.append("(").append(factors[i].toString()).append(")");
			if (i != 0) {
				sb.append("*z^").append(i).append("+");
			}
		}
		return sb.toString();
	}

}
