public class RandomWalkers {
    /**
     * RandomWalker - calculate the avg of squared distances of a point
     * that moves for n times in random directions from the origin for some trials
     *
     * @param: args (Array: String)
     *
     * @return: void
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        double tot = 0; // total distances
        double rand; // to store the randomized value
        int x; // x position vector
        int y; // y position vector

        for (int j = trials; j > 0; --j) {
            x = 0;
            y = 0;

            for (int i = n; i > 0; --i) {
                rand = Math.random();
                if (rand < 0.25)
                    x += 1;
                else if (rand < 0.5)
                    y -= 1;
                else if (rand < 0.75)
                    x -= 1;
                else
                    y += 1;
            }

            tot += Math.pow(x, 2) + Math.pow(y, 2);
        }
        System.out.println("mean squared distance = " + tot/trials);
    }
}
