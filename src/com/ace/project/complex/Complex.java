package com.ace.project.complex;

/**
 * Complex Class (real) + j(imaginary)
 * 
 * @author Administrator
 *
 */
public class Complex {
    /* ATTRIBUTES */

    private final double real;
    private final double imaginary;
    public static final Complex NaN = new Complex(0.0 / 0.0, 0.0 / 0.0);
    public static final Complex ZERO = new Complex(0.0, 0.0);
    public static final Complex INF = new Complex(1.0 / 0.0, 1.0 / 0.0);
    private final boolean isNaN;
    private final boolean isInfinite;

    /* METHODS */

    public Complex(double real, double imaginary) {
	this.real = real;
	this.imaginary = imaginary;

	this.isNaN = (Double.isNaN(real) || Double.isNaN(imaginary));
	this.isInfinite = ((!this.isNaN)
		&& (Double.isInfinite(real) || Double.isInfinite(imaginary)));
    }

    public Complex(double real) {
	this.real = real;
	this.imaginary = 0.0;

	this.isNaN = Double.isNaN(real);
	this.isInfinite = Double.isInfinite(real);
    }

    public Complex(Complex complex) {
	this.real = complex.real;
	this.imaginary = complex.imaginary;

	this.isNaN = complex.isNaN;
	this.isInfinite = complex.isInfinite;
    }

    public boolean isNaN() {
	return isNaN;
    }

    public boolean isInfinite() {
	return isInfinite;
    }

    public double getReal() {
	return this.real;
    }

    public double getImag() {
	return this.imaginary;
    }

    public boolean equals(Complex complex) {
	return ((this.real == complex.real)
		&& (this.imaginary == complex.imaginary));
    }

    public double magnitude() {
	if (this.isNaN)
	    return (0.0 / 0.0);
	if (this.isInfinite)
	    return (1.0 / 0.0);

	return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
    }

    public double angle() {
	if (this.real < 0 && this.imaginary > 0)
	    return Math.atan(this.imaginary / this.real) + Math.PI;
	if (this.real < 0 && this.imaginary < 0)
	    return Math.atan(this.imaginary / this.real) - Math.PI;

	return Math.atan(this.imaginary / this.real);
    }

    public Complex add(double real) {
	if (this.isNaN || Double.isNaN(real))
	    return NaN;
	if (this.isInfinite || Double.isInfinite(real))
	    return INF;

	return new Complex(this.real + real, this.imaginary);
    }

    public Complex add(Complex complex) {
	if (this.isNaN || complex.isNaN)
	    return NaN;
	if (this.isInfinite || complex.isInfinite)
	    return INF;

	return new Complex(this.real + complex.real,
		this.imaginary + complex.imaginary);
    }

    public Complex sub(double real) {
	if (this.isNaN || Double.isNaN(real))
	    return NaN;
	if (this.isInfinite || Double.isInfinite(real))
	    return INF;

	return new Complex(this.real - real, this.imaginary);
    }

    public Complex sub(Complex complex) {
	if (this.isNaN || complex.isNaN)
	    return NaN;
	if (this.isInfinite || complex.isInfinite)
	    return INF;

	return new Complex(this.real - complex.real,
		this.imaginary - complex.imaginary);
    }

    public Complex multiply(double real) {
	if (this.isNaN || Double.isNaN(real))
	    return NaN;
	if (this.isInfinite || Double.isInfinite(real))
	    return INF;

	return new Complex(this.real * real, this.imaginary * real);
    }

    public Complex multiply(Complex complex) {
	// need to consider more conditions for multiplying...
	double realpart = this.real * complex.real
		- this.imaginary * complex.imaginary;
	double imagpart = this.real * complex.imaginary
		+ this.imaginary * complex.real;

	return new Complex(realpart, imagpart);
    }

    public Complex divide(double real) {
	if (this.isNaN || Double.isNaN(real))
	    return NaN;

	// Need to add more conditions for dividing

	return new Complex(this.real / real, this.imaginary / real);
    }

    public Complex divide(Complex complex) {

	// Need to add more conditions for dividing

	double den = Math.pow(complex.real, 2) + Math.pow(complex.imaginary, 2);
	double realpart = (this.real * complex.real
		+ this.imaginary * complex.imaginary) / den;
	double imagpart = (this.imaginary + complex.real
		- this.real * complex.imaginary) / den;
	return new Complex(realpart, imagpart);
    }

    public String toString() {
	if (this.isNaN)
	    return new String("NaN");
	if (this.isInfinite)
	    return new String("INF");

	return new String("(" + this.real + ") + j(" + this.imaginary + ")");
    }
}