public class ImageClassifier {
    /**
     * Creates a feature vector (1D array) from the given picture
     *
     * @param picture the input picture
     * @return the picture converted to Array double[]
     */
    public static double[] extractFeatures(Picture picture) {
        int w = picture.width();
        int h = picture.height();
        double[] arr = new double[h * w];

        for (int i = 0; i < h; ++i)
            for (int j = 0; j < w; ++j)
                // get any color; they are the same since pics in greyscale
                arr[i * w + j] = picture.get(j, i).getRed();

        return arr;
    }

    /**
     * Magic starts.
     *
     * @param args
     */
    public static void main(String[] args) {
        Picture pic;
        String picName;
        double[] arr;
        int label;

        /***********************
         **** write comment ****
         ***********************/
        In dfTrain = new In(args[0]);
        In dfTest = new In(args[1]);

        int m = dfTrain.readInt(); // get number of features
        int w = dfTrain.readInt(); // get width of pics
        int h = dfTrain.readInt(); // get height of pics

        MultiPerceptron mp = new MultiPerceptron(m, w * h);

        /***********************
         **** write comment ****
         ***********************/

        while (!dfTrain.isEmpty()) {
            picName = dfTrain.readString();
            pic = new Picture(picName);
            arr = extractFeatures(pic);
            label = dfTrain.readInt();

            mp.trainMulti(arr, label);
        }

        int mm = dfTest.readInt(); // get number of features
        int ww = dfTest.readInt(); // get width of pics
        int hh = dfTest.readInt(); // get height of pics

        if ((mm != m) || (ww != w) || (hh != h)) {
            System.out.println("testing dataframe not compatible with training");
            return;
        }

        /***********************
         **** write comment ****
         ***********************/

        int pred, err = 0, tot = 0;

        while (!dfTest.isEmpty()) {
            picName = dfTest.readString();
            pic = new Picture(picName);
            arr = extractFeatures(pic);
            label = dfTest.readInt();

            pred = mp.predictMulti(arr);

            // print out the incorrected labeled pics
            if (pred != label) {
                System.out.printf("%s, label = %d, predict = %d\n",
                                   picName, label, pred);
                // increase the error rate
                err += 1;
            }
            tot += 1;
        }
        System.out.println("test error rate = " + (err / (double) tot));
    }
}
