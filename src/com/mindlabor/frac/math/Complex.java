package com.mindlabor.frac.math;

import java.util.Objects;
import org.apache.commons.math3.complex.ComplexFormat;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.util.FastMath;

public class Complex {

    private final double re;
    private final double im;

    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }
    
    public Complex pow(double a) { // a^this
    	return scale(Math.log(a)).exp();
    }
    
    public void print() {
        System.out.println(toString());
    }
    
    public Complex sinh() {
        return (exp().minus(scale(-1).exp()).divides(new Complex(2, 0)));
    }
    
    public Complex sq(){
        return this.times(this);
    }
    
    @Override
    public String toString() {
        if (im == 0) {
            return re + "";
        }
        if (re == 0) {
            return im + "i";
        }
        if (im < 0) {
            return re + " - " + (-im) + "i";
        }
        return re + " + " + im + "i";
    }

    public double abs() {
        return re*re+im*im;
    }
    
    public Complex labs(){
        if (im>=0) {
            return this;
        } else {
            return conjugate();
        }
    }

    public double phase() {
        return Math.atan2(im, re);
    }

    public Complex plus(Complex b) {
        Complex a = this;           
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    public Complex minus(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    public Complex times(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    public Complex scale(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    public Complex conjugate() {
        return new Complex(re, -im);
    }

    public Complex reciprocal() {
        double scale = re * re + im * im;
        return new Complex(re / scale, -im / scale);
    }

    public double re() {
        return re;
    }

    public double im() {
        return im;
    }

    public Complex divides(Complex b) {
        Complex a = this;
        return a.times(b.reciprocal());
    }

    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    public Complex tan() {
        return sin().divides(cos());
    }
    
    public Complex log(){
        return new Complex(FastMath.log(abs()), FastMath.atan2(im, re));
    }

    public static Complex plus(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }

    @Override
    public boolean equals(Object x) {
        if (x == null) {
            return false;
        }
        if (this.getClass() != x.getClass()) {
            return false;
        }
        Complex that = (Complex) x;
        return (this.re == that.re) && (this.im == that.im);
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }
    public static Complex parseComplex(String str) throws MathParseException {
        ComplexFormat cf = new ComplexFormat();
        org.apache.commons.math3.complex.Complex c = cf.parse(str);
        return new Complex(c.getReal(), c.getImaginary());
    }
    
    
}
