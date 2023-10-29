public class Perceptron {
    /**
     * @param nn store the number of wights of the Perceptron (int)
     */
    private int nn;

    /**
     * @param w store the wights of the Perceptron (Array)
     */
    private double[] w;

    /**
     * Creates a perceptron with n inputs.
     * It should create an array of n weights
     * and initialize them all to 0.
     *
     * @param n number of weights (int)
     */
    public Perceptron(int n) {
        this.nn = n;
        this.w = new double[this.nn];
    }

    /**
     * Returns the number of inputs n.
     *
     * @return the number of inputs n (int)
     */
    public int numberOfInputs() {
        return this.nn;
    }

    /**
     * Returns the weighted sum of the weight vector and x.
     *
     * @param x the input picture (Array double[])
     * @return the dot product of the input and weights (double)
     */
    public double weightedSum(double[] x) {
        double sum = 0;

        for (int i = 0; i < numberOfInputs(); ++i)
            sum += x[i] * this.w[i];

        return sum;
    }

    /**
     * Predicts the label (+1 or -1) of input x. It returns +1
     * if the weighted sum is positive and -1 if it is negative (or zero).
     *
     * @param x the input picutre (Array double[])
     * @return [+1/-1] positive or negative (int)
     */
    public int predict(double[] x) {
        if (weightedSum(x) > 0)
            return 1;
        else
            return -1;
    }

    /**
     * Trains this perceptron on the labeled (+1 or -1) input x.
     * The weights vector is updated accordingly.
     *
     * @param x the input picutre (Array double[])
     * @param label [+1/-1] to indicate if it is the positive photo or not (int)
     */
    public void train(double[] x, int label) {
        /***********************
         **** write comment ****
         ***********************/

        int p = predict(x);

        if (p != label) {
            if (p > 0) {
                for (int i = 0; i < this.nn; ++i)
                    this.w[i] -= x[i];
            } else {
                for (int i = 0; i < this.nn; ++i)
                    this.w[i] += x[i];
            }
        }
    }
    /**
     * Returns a String representation of the weight vector, with the
     * individual weights separated by commas and enclosed in parentheses.
     * Example: (2.0, 1.0, -1.0, 5.0, 3.0)
     *
     * @return the string representation of weights in (##, ...) form (String)
     */
    public String toString() {
        String s = "";
        s += String.format("(%f", this.w[0]);
        for (int i = 1; i < numberOfInputs(); ++i)
            s += String.format(", %f", this.w[i]);
        s += ")";

        return s;
    }

    /**
     * Tests this class by directly calling all instance methods.
     *
     * @param args
     */
    public static void main(String[] args) {
        Perceptron pp = new Perceptron(9);
        Picture pic = new Picture("image3-by-3.png");
        double[] arr = new double[pic.height() * pic.width()];

        // get pic
        for (int i = 0; i < pic.height(); ++i)
            for (int j = 0; j < pic.width(); ++j)
                // get any color; they are the same since pics in greyscale
                arr[i * pic.width() + j] = pic.get(i, j).getRed();

        System.out.printf("numberOfInputs: %d\n", pp.numberOfInputs());
        System.out.printf("weightedSum: %f\n", pp.weightedSum(arr));
        System.out.printf("predict: %d\n", pp.predict(arr));
        pp.train(arr, 1);
        System.out.printf("W: %s\n", pp.toString());

    }
}
