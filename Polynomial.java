import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

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

    public Polynomial(File inputFile) {
        try {
            Scanner input = new Scanner(inputFile);
            String line = input.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Polynomial add(Polynomial p) {

        // find largest exponent between input and calling polynomials
        int max = maxInt(p.exponents);
        int thisMax = maxInt(this.exponents);
        if (max < thisMax) {
            max = thisMax;
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

    private int maxInt(int[] input) {
        int max = 0;
        for (int i = 0; i<input.length; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }
        return max;
    }

    private Polynomial delZeroes(Polynomial p) {
        int zeroCount = 0;
        for (int i=0; i<p.coefficients.length; i++) {
            if (p.coefficients[i] == 0) {
                zeroCount++;
            }
        }

        double[] qCoefficients = new double[p.coefficients.length-zeroCount];
        for (int i = 0; i<zeroCount+1; i++) {
            qCoefficients[i] = 0;
        }
        int[] qExponents = new int[p.coefficients.length-zeroCount];
        for (int i = 0; i<zeroCount+1; i++) {
            qExponents[i] = i;
        }

        int nonzeroCount = 0;
        for (int i=0; i<zeroCount; i++) {
            if (p.coefficients[i] != 0) {
                qCoefficients[nonzeroCount] = p.coefficients[i];
                qExponents[nonzeroCount] = p.exponents[i];
                nonzeroCount++;
            }
        }

        this.coefficients = qCoefficients;
        this.exponents = qExponents;
        return this;
    }

    public Polynomial multiply(Polynomial p) {
        
        // declare and initialize new polynomial of degree (deg(p)+deg(this))
        int max = maxInt(p.exponents) + maxInt(this.exponents);
        double[] qCoefficients = new double[max+1];
        for (int i = 0; i<max+1; i++) {
            qCoefficients[i] = 0;
        }
        int[] qExponents = new int[max+1];
        for (int i = 0; i<max+1; i++) {
            qExponents[i] = i;
        }

        // loop through input polynomial and multiply with each term in the calling polynomial
        for (int i=0; i<p.exponents.length; i++) {
            for (int j=0; j<this.exponents.length; j++) {
                qCoefficients[p.exponents[i]+this.exponents[j]] += p.coefficients[i] * this.coefficients[j];
            }
        }

        // set calling polynomial to q and return
        this.coefficients = qCoefficients;
        this.exponents = qExponents;
        return this;
    }

    public void saveToFile(String filename) {
        try {
			PrintStream output = new PrintStream(filename);
            String out = new String(this.coefficients[0] + "x" + this.exponents[0]);
            for (int i=1; i<this.coefficients.length; i++) {
                out = out + "+" + this.coefficients[i] + "x" + this.exponents[i];
            }
			output.println(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}