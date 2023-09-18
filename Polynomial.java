public class Polynomial {
    
    double[] coefficients;

    public Polynomial() {
        this.coefficients = new double[] {0};
    }

    public Polynomial(double[] givenCoefficients) {
        this.coefficients = givenCoefficients;
    }

    public Polynomial add(Polynomial p) {
        if (p.coefficients.length > this.coefficients.length) {
            for (int i=0; i<this.coefficients.length; i++) {
                p.coefficients[i] = p.coefficients[i] + this.coefficients[i];
            }
            this.coefficients = p.coefficients;
        } else {
            for (int i=0; i<p.coefficients.length; i++) {
                this.coefficients[i] = this.coefficients[i] + p.coefficients[i];
            }
        }
        return this;
    }

    public double evaluate(double x) {
        double sum = 0;
        for (int i=0; i<this.coefficients.length; i++) {
            sum = sum + this.coefficients[i] * Math.pow(x,i);
        }
        return sum;
    }

    public boolean hasRoot(double x) {
        return (this.evaluate(x) == 0);
    }

}