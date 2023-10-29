public class Sierpinski {
    /**
     * Get height of an equilateral triangle with the specified side length
     * @param length: side length
     * @return h: the height value
     */
    public static double height(double length) {
        return length * Math.sqrt(3) / 2;
    }

    /**
     * Draws a filled equilateral triangle with the specified side length
     * whose bottom vertex is (x, y)
     * @param x
     * @param y
     * @param length
     */
    public static void filledTriangle(double x, double y, double length) {
        double[] xx = new double[3];
        double[] yy = new double[3];

        xx[0] = x;
        xx[1] = x - length/2;
        xx[2] = x + length/2;

        yy[0] = y;
        yy[1] = y + height(length);
        yy[2] = y + height(length);

        StdDraw.filledPolygon(xx, yy);

    }

    /**
     * Draws a Sierpinski triangle of order n, such that the largest filled
     * triangle has the specified side length and bottom vertex (x, y)
     * @param n:
     * @param x
     * @param y
     * @param length
     */
    public static void sierpinski(int n, double x, double y, double length) {
        if (n == 0)
            return;

        filledTriangle(x, y, length);

        sierpinski(n - 1, x + length/2, y, length/2);
        sierpinski(n - 1, x, y + height(length), length/2);
        sierpinski(n - 1, x - length/2, y, length/2);

    }

    /**
     * draws the outline of an upwards equilateral triangle of length 1
     * whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0);
     * and draws a Sierpinski triangle of order n that fits inside the outline.
     * @param args: order of triangle
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double[] xx = new double[3];
        double[] yy = new double[3];

        /************************
         * THE OUTLINE TRIANGLE *
         ************************/
        xx[0] = 0;
        xx[1] = 1;
        xx[2] = 1./2;

        yy[0] = 0;
        yy[1] = 0;
        yy[2] = height(1);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.polygon(xx, yy);
        /**********************************/

        sierpinski(n, 0.5, 0, 0.5);
        StdDraw.show();

    }

}
