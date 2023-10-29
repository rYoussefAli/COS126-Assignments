public class Art {
    /**
     * get a color to draw with based on `n` order
     *
     * @param idx the `n` order
     */
    private static void picker(int idx) {
        if (idx % 6 == 0)
            StdDraw.setPenColor(StdDraw.BOOK_RED);
        else if (idx % 6 == 1)
            StdDraw.setPenColor(StdDraw.BLUE);
        else if (idx % 6 == 2)
            StdDraw.setPenColor(StdDraw.PINK);
        else if (idx % 6 == 3)
            StdDraw.setPenColor(StdDraw.GREEN);
        else if (idx % 6 == 4)
            StdDraw.setPenColor(StdDraw.YELLOW);
        else
            StdDraw.setPenColor(StdDraw.BLUE);
    }

    /**
     * Draws a fractal tree with branch angle = angle
     * x, y are starting position of the branch
     *
     * @param n
     * @param x
     * @param y
     * @param angle
     */
    private static void doArt(int n, double x, double y, double angle) {
        if (n == 0)
            return;

        double x1 = x + Math.cos(Math.toRadians(angle)) * 0.2/n;
        double y1 = y + Math.sin(Math.toRadians(angle)) * 0.2/n;

        StdDraw.line(x, y, x1, y1);
        picker(n);
        doArt(n-1, x1, y1, angle-10);
        picker(n);
        doArt(n-1, x1, y1, angle+10);
    }

    /**
     * Art Art Art.
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        StdDraw.setPenColor(StdDraw.CYAN);
        double x = 0.5;
        double y = 0;
        double startingAngle = 90;
        doArt(n, x, y, startingAngle);

    }
}
