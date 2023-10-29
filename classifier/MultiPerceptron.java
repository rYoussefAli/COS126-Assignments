public class MultiPerceptron {
    /**
     * number of classes for such a classifier
     */
    private int mm;
    /**
     * number of inputs (lengths of Array) for each perceptron
     */
    private int nn;

    /**
     *
     */
    private Perceptron[] mp;

    /**
     * Creates a multi-perceptron object with m classes and n inputs.
     * It creates an array of m perceptrons, each with n inputs.
     *
     * @param m
     * @param n
     */
    public MultiPerceptron(int m, int n) {
        this.mm = m;
        this.nn = n;
        this.mp = new Perceptron[this.mm];
        for (int i = 0; i < this.mm; ++i)
            this.mp[i] = new Perceptron(this.nn);
    }

    /**
     * Returns the number of classes m.
     *
     * @return this.mm number of classes (int)
     */
    public int numberOfClasses() {
        return this.mm;
    }

    /**
     * Returns the number of inputs n (length of the feature vector).
     *
     * @return number of inputs [picture size] (int)
     */
    public int numberOfInputs() {
        return this.nn;
    }

    /**
     * Returns the predicted label (between 0 and m-1) for the given input.
     *
     * @param x the input picture (Array double[])
     * @return [0 - (m-1)] index of Perceptron with max weight return (int)
     */
    public int predictMulti(double[] x) {
        double maxPred = Double.NEGATIVE_INFINITY;
        double ws;
        int idx = -1;

        /*************************************************
         **** get the weighted sum of all perceptrons ****
         ******* and save the index of the highest *******
         *************************************************/

        for (int i = 0; i < this.mm; ++i) {
            ws = this.mp[i].weightedSum(x);
            if (ws > maxPred) {
                maxPred = ws;
                idx = i;
            }
        }
        return idx;
    }

    /**
     * Trains this multi-perceptron on the labeled (between 0 and m-1) input.
     *
     * @param x the input pic (Array double[])
     * @param label [0 - (m-1)] to be provided from dataset to match input pic
     */
    public void trainMulti(double[] x, int label) {
        /***********************
         **** write comment ****
         ***********************/

        for (int i = 0; i < numberOfClasses(); ++i) {
            if (label == i) {
                this.mp[i].train(x, 1);
                continue;
            }
            this.mp[i].train(x, -1);
        }
    }

    /**
     * Returns a String representation of this MultiPerceptron, with
     * the string representations of the perceptrons separated by commas
     * and enclosed in parentheses.
     * Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
     *
     * @return the string repr of weights in ((##, ...), ...) form (String)
     */
    public String toString() {
        String s = "";

        s += String.format("(%s", this.mp[0].toString());
        for (int i = 1; i < numberOfClasses(); ++i)
            s += String.format(", %s", this.mp[i].toString());
        s += ")";

        return s;
    }

    /**
     * Tests this class by directly calling all instance methods.
     *
     * @param args
     */
    public static void main(String[] args) {
        MultiPerceptron pp = new MultiPerceptron(3, 9);
        Picture pic = new Picture("image3-by-3.png");
        double[] arr = new double[pic.height() * pic.width()];

        // get pic to arr
        for (int i = 0; i < pic.height(); ++i)
            for (int j = 0; j < pic.width(); ++j) {
                // get any color; they are the same since pics in greyscale
                arr[i * pic.width() + j] = pic.get(i, j).getRed();
            }

        System.out.printf("numberOfInputs: %d\n", pp.numberOfInputs());
        System.out.printf("numberOfClasses: %d\n", pp.numberOfClasses());
        System.out.printf("predictMulti BEFORE: %d\n", pp.predictMulti(arr));
        System.out.printf("W: %s\n", pp.toString());
        pp.trainMulti(arr, 2);
        System.out.printf("predictMulti AFTER: %d\n", pp.predictMulti(arr));
        System.out.printf("W: %s\n", pp.toString());

    }
}
