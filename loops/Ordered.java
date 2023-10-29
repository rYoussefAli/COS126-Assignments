public class Ordered {
    /**
     * Ordered - prints whether the three inputted numbers are in order
     * (ascending or descending)
     *
     * @param: args (Array: String)
     *
     * @return: void
     */
    public static void main(String[] args) {
        int arg1 = Integer.parseInt(args[0]);
        int arg2 = Integer.parseInt(args[1]);
        int arg3 = Integer.parseInt(args[2]);

        // ascending
        boolean asc = (arg1 < arg2) && (arg2 < arg3);
        // descending
        boolean desc = (arg1 > arg2) && (arg2 > arg3);

        boolean state =  asc || desc;
        System.out.println(state);
    }
}
