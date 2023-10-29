public class RGBtoCMYK {
    /**
     * RGBtoCMYK - convert RGB color scheme to CMYK
     *
     * @param: args (Array: String)
     * @return: void
     */
    public static void main(String[] args) {
        int r = Integer.parseInt(args[0]); // red
        int g = Integer.parseInt(args[1]); // green
        int b = Integer.parseInt(args[2]); // blue
        double c = 0, m = 0, y = 0, k = 1;

        double nr = r / 255.; // normalized red
        double ng = g / 255.; // normalized green
        double nb = b / 255.; // normalized blue
        double w = Math.max(nr, Math.max(ng, nb)); // white

        // if the color is not black
        if (w > 0)
        {
            k = 1 - w;
            c = (w - nr) / w;
            m = (w - ng) / w;
            y = (w - nb) / w;
        }

        System.out.println("red     = " + r);
        System.out.println("green   = " + g);
        System.out.println("blue    = " + b);
        System.out.println("cyan    = " + c);
        System.out.println("magenta = " + m);
        System.out.println("yellow  = " + y);
        System.out.println("black   = " + k);
    }
}
