public class NBody {
    /**
     * NBody - graphic simulation of the solar system
     *
     * @param: args (Array: String)
     * @return: void
     */
    public static void main(String[] args) {
        double G = 6.67e-11;            // gravitational constant
        double dt;                      // discrete time
        double t;                       // total time of life
        double time;                    // time passing
        double r;                       // radius of universe
        double f, fx, fy;               // force vector & in x, y directions
        double sumFx, sumFy;            // sum of forces in the x, y directions
        double drx, dry;                // difference in x, y coordinates
        double dr2;                     // distance between masses squared
        double dr;                      // distance between masses
        int n;                          // number of masses

        t = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);

        n = StdIn.readInt();
        r = StdIn.readDouble();

        double[] m = new double[n];   // masses

        double[] rx = new double[n];  // position x direction
        double[] ry = new double[n];  // position y direction

        double[] vx = new double[n];  // velocity x direction
        double[] vy = new double[n];  // velocity y direction

        double[] ax = new double[n];  // acceleration in x direction
        double[] ay = new double[n];  // acceleration in y direction

        String[] img = new String[n]; // images names of masses

        // read the input file
        for (int i = 0; i < n; ++i)
        {
            rx[i] = StdIn.readDouble();
            ry[i] = StdIn.readDouble();

            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();

            m[i] = StdIn.readDouble();
            img[i] = StdIn.readString();
        }

        StdDraw.setXscale(-r, r);        // set the dimensions of universe
        StdDraw.setYscale(-r, r);
        StdDraw.enableDoubleBuffering(); // to prevent flickiring

        // play funky sound
        StdAudio.playInBackground("2001.wav");

        // time starts (big bang boom)
        time = 0;
        while (time < t)
        {
            /*********************
             * CHANGE THE FORCES *
             *********************/

            // loop through each planet
            for (int i = 0; i < n; ++i)
            {
                    // initialize forces acting to be zero
                    sumFx = 0;
                    sumFy = 0;

                    // calculate the force by each planet
                    for (int j = 0; j < n; ++j)
                    {
                        // exclude the force by the planet on itself
                        if (j != i)
                        {
                            drx = rx[j] - rx[i]; // should be [j - i]
                            dry = ry[j] - ry[i]; // and not the vice versa
                            dr2 = Math.pow(drx, 2) + Math.pow(dry, 2); // distance^2
                            dr = Math.sqrt(dr2); // distance
                            f = (G * m[i] * m[j]) / dr2; // all forces
                            fx = f * drx / dr; // force in x direction
                            fy = f * dry / dr; // force in y direction
                            sumFx += fx; // sum of forces in the x direction
                            sumFy += fy; // sum of forces in the y direction
                        }
                    }

                    // updating accelerations
                    ax[i] = sumFx / m[i];
                    ay[i] = sumFy / m[i];
            }

            /*********************
             *** FORCES EFFECT ***
             *********************/

            for (int i = 0; i < n; ++i)
            {
                // updating velocities
                vx[i] += ax[i] * dt;
                vy[i] += ay[i] * dt;

                // updating positions
                rx[i] += vx[i] * dt;
                ry[i] += vy[i] * dt;
            }

            /*********************
             * DRAW THE UNIVERSE *
             *********************/

            /*
            Pause for 0 ms.
            Any value > 0 will make the graphics looks terrible;
            i.e. not smooth.
             */
            StdDraw.pause(0);

            // clear the old universe
            StdDraw.clear(StdDraw.BLUE);

            // backgroud appear
            StdDraw.picture(0, 0, "starfield.jpg");

            // put the planets in their positions
            for (int i = 0; i < n; ++i)
            {
                StdDraw.picture(rx[i], ry[i], img[i]);
            }

            // show the universe
            StdDraw.show();

            // time passing
            time += dt;
        }

        // print the final position of the universe
        StdOut.println(n); // how many planets
        StdOut.printf("%.2e\n", r); // radius of universe
        for (int i = 0; i < n; ++i)
        {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                           rx[i], ry[i], vx[i], vy[i], m[i], img[i]);
        }
    }
}
