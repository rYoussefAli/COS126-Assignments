public class Transform2D {
    /**
     * Returns a new array object that is an exact copy of the given array.
     * The given array is not mutated.
     * @param array: array given
     * @return arr: copy of array
     */
    public static double[] copy(double[] array) {
        int n = array.length;
        double[] arr = new double[n];

        for (int i = 0; i < n; ++i)
            arr[i] = array[i];

        return arr;
    }

    /**
     * Scales the polygon by the factor alpha
     * @param x: x corrs
     * @param y: y corrs
     * @param alpha: scale factor
     */
    public static void scale(double[] x, double[] y, double alpha) {
        int n = x.length;

        for (int i = 0; i < n; ++i) {
            x[i] *= alpha;
            y[i] *= alpha;
        }
    }

    /**
     * Translates the polygon by (dx, dy)
     * @param x: x corrs
     * @param y: y corrs
     * @param dx: x translation factor
     * @param dy: y translation factor
     */
    public static void translate(double[] x, double[] y, double dx, double dy) {
        int n = x.length;

        for (int i = 0; i < n; ++i) {
            x[i] += dx;
            y[i] += dy;
        }
    }

    /**
     * Rotates the polygon theta degrees counterclockwise, about the origin
     * @param x: x corrs
     * @param y: y corrs
     * @param theta: rotation angle
     */
    public static void rotate(double[] x, double[] y, double theta) {
        int n = x.length;
        double tempx, tempy;

        theta = Math.toRadians(theta);

        for (int i = 0; i < n; ++i) {
            tempx = x[i] * Math.cos(theta) - y[i] * Math.sin(theta);
            tempy = y[i] * Math.cos(theta) + x[i] * Math.sin(theta);

            x[i] = tempx;
            y[i] = tempy;
        }
    }

    /**
     * Two-dimensional transformation library
     * @param args (if any)
     */
    public static void main(String[] args) {
        // Set the x- and y-scale
        StdDraw.setScale(-5.0, +5.0);

        // Create polygon
        double[] x = { 1, 2, 2, 1 };
        double[] y = { 1, 1, 3, 2 };
        double[] t;

        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.polygon(x, y);

        rotate(x, y, 90.0);
        t = copy(x);
        scale(t, y, 0.5);
        translate(t, y, 0, 0);

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.polygon(t, y);

    }
}
