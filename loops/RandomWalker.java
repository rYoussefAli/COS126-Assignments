public class RandomWalker {
    /**
     * RandomWalker - print the squared distance of a point
     * that moves for n times in random directions from the origin
     *
     * @param: args (Array: String)
     *
     * @return: void
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int x = 0; // x position vector
        int y = 0; // y position vector
        double rand; // to store the randomized value

        System.out.println("(" + x + ", " + y + ")");
        for (int i = n; i > 0; --i)
        {
            rand = Math.random();
            if (rand < 0.25)
                x += 1;
            else if (rand < 0.5)
                y -= 1;
            else if (rand < 0.75)
                x -= 1;
            else
                y += 1;

            System.out.println("(" + x + ", " + y + ")");
        }

        System.out.println("squared distance = "
                + (int) (Math.pow(x, 2) + Math.pow(y, 2)));
    }
}
