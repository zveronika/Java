package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Veronika Žunar
 *
 */
public class Complex {

	/**
	 * Real part of complex number.
	 */
	private double re;
	/**
	 * Imaginary part of complex number.
	 */
	private double im;

	/**
	 * Complex interpretation of number 0, in format 0+i0.
	 */
	public static final Complex ZERO = new Complex(0, 0);
	/**
	 * Complex interpretation of number 1, in format 1+i0.
	 */
	public static final Complex ONE = new Complex(1, 0);
	/**
	 * Complex interpretation of number -1, in format -1+i0.
	 */
	public static final Complex ONE_NEG = new Complex(-1, 0);
	/**
	 * Complex interpretation of number i, in format 0+i1.
	 */
	public static final Complex IM = new Complex(0, 1);
	/**
	 * Complex interpretation of number -i, in format 0-i1.
	 */
	public static final Complex IM_NEG = new Complex(0, -1);

	/**
	 * Public empty constructor.
	 */
	public Complex() {
	}

	/**
	 * Public basic constructor.
	 * 
	 * @param re real part of new complex number
	 * @param im imaginary part of new complex number
	 */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	/**
	 * Getter for real part of complex number.
	 * 
	 * @return the real part of complex number
	 */
	public double getRe() {
		return re;
	}

	/**
	 * Setter for real part of Complex number.
	 * 
	 * @param re the real part of complex number to set
	 */
	public void setRe(double re) {
		this.re = re;
	}

	/**
	 * Getter for imaginary part of complex number.
	 * 
	 * @return the imaginary part of complex number
	 */
	public double getIm() {
		return im;
	}

	/**
	 * Setter for imaginary part of Complex number.
	 * 
	 * @param im the imaginary part of complex number to set
	 */
	public void setIm(double im) {
		this.im = im;
	}

	/**
	 * 
	 * @return module of complex number
	 */
	public double module() {
		return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
	}

	/**
	 * 
	 * @param c other complex to multiply with
	 * @return multiplication
	 */
	public Complex multiply(Complex c) {
		double real = this.re * c.re - this.im * c.im;
		double imaginary = this.re * c.im + c.re * this.im;
		return new Complex(real, imaginary);
	}

	/**
	 * 
	 * @param c other complex to divide with
	 * @return quotient
	 */
	public Complex divide(Complex c) {
		
		Complex conjugate = new Complex(c.getRe(), (-1) *c.getIm());
		double denominator = c.multiply(conjugate).getRe();
		
		Complex numerator = this.multiply(conjugate);

		return new Complex(numerator.getRe() / denominator, numerator.getIm() / denominator);
	}

	/**
	 * 
	 * @param c other complex to add
	 * @return sum
	 */
	public Complex add(Complex c) {
		double real = this.re + c.re;
		double imaginary = this.im + c.im;
		return new Complex(real, imaginary);
	}

	/**
	 * 
	 * @param c other complex to subtract
	 * @return difference
	 */
	public Complex sub(Complex c) {
		double real = this.re - c.re;
		double imaginary = this.im - c.im;
		return new Complex(real, imaginary);
	}

	/**
	 * 
	 * @return negative value of this complex
	 */
	public Complex negate() {
		return new Complex((-1) * this.re, (-1) * this.im);
	}

	/**
	 * n is non-negative integer
	 * @param n 
	 * @return this complex power to n
	 */
	public Complex power(int n) {
		if (n < 0)
			throw new IllegalArgumentException();

		double r_n = Math.pow(this.module(), n);
		double n_fi = n * normaliseAngle(Math.atan2(this.im, this.re));

		double real = (double) (r_n * Math.cos(n_fi) * 100) / 100;
		double imaginary = (double) (r_n * Math.sin(n_fi) * 100) / 100;

		return new Complex(real, imaginary);
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public List<Complex> root(int n) {
		List<Complex> roots = new ArrayList<>();

		if (n <= 0)
			throw new IllegalArgumentException();

		if (this.re == 0 && this.im == 0) {
			roots.add(ZERO);
			return roots;
		}

		if (n == 1) {
			roots.add(this);
			return roots;
		}

		double r = Math.pow(this.module(), 1 / n);
		double fi = Math.atan2(this.im, this.re);

		for (int k = 0; k < n; k++) {
			double angle = normaliseAngle(fi + 2 * k * Math.PI) / n;
			roots.add(new Complex(r * Math.cos(angle), r * Math.sin(angle)));
		}

		return roots;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getRe());

		if (this.getIm() >= 0)
			sb.append("+i").append(this.getIm());
		else
			sb.append("-i").append(-this.getIm());

		return sb.toString();
	}

	/**
	 * 
	 * @param fi
	 * @return
	 */
	public double normaliseAngle(double fi) {
		if (fi >= 2 * Math.PI)
			fi -= 2 * Math.PI;
		if (fi < 0)
			fi += 2 * Math.PI;

		return fi;
	}
}
