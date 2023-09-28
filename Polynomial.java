import java.rmi.AccessException;

public class Polynomial {
    
    double[] coefficients;
    int[] exponents;

    public Polynomial() {
        this.coefficients = new double[] {0};
        this.exponents = new int[] {0};
    }

    public Polynomial(double[] givenCoefficients, int[] givenExponents) {
        this.coefficients = givenCoefficients;
        this.exponents = givenExponents;
    }

    public Polynomial add(Polynomial p) {

        // find largest exponent between input and calling polynomials
        int max = 0;
        for (int i = 0; i<p.exponents.length; i++) {
            if (p.exponents[i] > max) {
                max = p.exponents[i];
            }
        }
        for (int i = 0; i<this.exponents.length; i++) {
            if (this.exponents[i] > max) {
                max = this.exponents[i];
            }
        }

        // declare and initialize new polynomial of max exponent
        double[] qCoefficients = new double[max+1];
        for (int i = 0; i<max+1; i++) {
            qCoefficients[i] = 0;
        }
        int[] qExponents = new int[max+1];
        for (int i = 0; i<max+1; i++) {
            qExponents[i] = i;
        }

        // loop through input polynomial and add corresponding coefficients
        for (int i = 0; i<p.exponents.length; i++) {
            qCoefficients[p.exponents[i]] += p.coefficients[i];
        }

        // loop through calling polynomial and add corresponding coefficients
        for (int i = 0; i<this.exponents.length; i++) {
            qCoefficients[this.exponents[i]] += this.coefficients[i];
        }

        // set calling polynomial to q and return
        this.coefficients = qCoefficients;
        this.exponents = qExponents;
        return this;
    }

    public double evaluate(double x) {
        double sum = 0;
        for (int i=0; i<this.coefficients.length; i++) {
            sum = sum + this.coefficients[i] * Math.pow(x,this.exponents[i]);
        }
        return sum;
    }

    public boolean hasRoot(double x) {
        return (this.evaluate(x) == 0);
    }

    public Polynomial multiply(Polynomial p) {
        



        return p;
    }

}