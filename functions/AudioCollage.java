public class AudioCollage {
    /**
     * amplify - Returns a new array that rescales a[] by a factor of alpha.
     * @param a - list type double (Sound array)
     * @param alpha - amplification factor
     * @return arr - new array double[] type
     */
    public static double[] amplify(double[] a, double alpha) {
        double[] arr = new double[a.length];
        for (int i = 0; i < a.length; ++i)
            arr[i] = a[i] * alpha;
        return arr;

    }

    /**
     * reverse - Returns a new array that is the reverse of a[].
     * @param a
     * @return arr - new array double[] type
     */
    public static double[] reverse(double[] a) {
        double[] arr = new double[a.length];
        for (int i = 0; i < a.length; ++i)
            arr[i] = a[a.length-i-1];
        return arr;
    }

    /**
     * merge - Returns a new array that is the concatenation of a[] and b[].
     * @param a
     * @param b
     * @return arr - new array double[] type
     */
    public static double[] merge(double[] a, double[] b) {
        int newlen = a.length + b.length;
        double[] arr = new double[newlen];
        int i;
        for (i = 0; i < a.length; ++i)
            arr[i] = a[i];
        for (int j = 0; j < b.length; ++i, ++j)
            arr[i] = b[j];
        return arr;
    }

    /**
     * mix - Returns a new array that is the sum of a[] and b[],
     * padding the shorter array with trailing 0s if necessary.
     * @param a
     * @param b
     * @return arr - new array double[] type
     */
    public static double[] mix(double[] a, double[] b) {
        int maxlen = Math.max(a.length, b.length);
        double[] arr = new double[maxlen];
        for (int i = 0; i < maxlen; ++i) {
            if (i < a.length)
                arr[i] += a[i];
            if (i < b.length)
                arr[i] += b[i];
        }
        return arr;
    }

    /**
     * changeSpeed - Returns a new array that changes
     * the speed of a[] by a factor of alpha.
     * @param a - list type double (Sound array)
     * @param alpha - speed factor
     * @return
     */
    public static double[] changeSpeed(double[] a, double alpha) {
        int n = (int) (a.length/alpha);
        double[] arr = new double[n];
        for (int i = 0; i < arr.length; ++i)
            arr[i] = a[(int) (i * alpha)];
        return arr;
    }

    /**
     * clamp - replace all samples greater than +1.0 with 1.0;
     * and all samples less than -1.0 with -1.0
     * @param samples - list type double (Sound array)
     * @return
     */
    private static double[] clamp(double[] samples) {
        double[] arr = new double[samples.length];
        for (int i = 0; i < samples.length; ++i)
        {
            if (samples[i] > 1)
            {
                arr[i] = 1;
                continue;
            }
            if (samples[i] < -1)
            {
                arr[i] = -1;
                continue;
            }
            arr[i] = samples[i];
        }
        return arr;
    }


    /**
     * main - Creates an audio collage and plays it on standard audio.
     * @param args
     */
    public static void main(String[] args) {
        double[] mus1 = StdAudio.read("beatbox.wav");
        double[] mus2 = StdAudio.read("afrobeat.wav");
        double[] mus3 = StdAudio.read("chimes.wav");
        double[] mus4 = StdAudio.read("piano.wav");
        double[] mus5 = StdAudio.read("stomp.wav");
        double[] rev;

        /********************************
         ****** DO NOT POST PLEASE  *****
         ** IT IS A RANDOM COMBINATION **
         * JUST FOR ASSIGNMENT PORPUSES *
         ********************************/

        mus1 = changeSpeed(mus1, 1);
        mus2 = reverse(mus2);
        rev = merge(mus3, mus2);
        rev = merge(rev, rev);
        rev = mix(rev, mus5);
        rev = merge(mus4, rev);
        rev = merge(rev, mus1);
        rev = amplify(rev, 2);
        StdAudio.play(clamp(rev));
    }
}
