/**
 * Created by SSLGhost on 6/1/17.
 */
import java.text.DecimalFormat;

public class Main {

    public static void main(String[] args) {
        int i;
        final long value = 1000000L; // value split up into segments to count using threads
        final int cores = Runtime.getRuntime().availableProcessors(); //obtains thread count for CPU
        //final int cores = 2;
        long[][] frags = getFragments(cores, value);        // splits the problem up into fragments for each thread to run
        Thread[] arr = new Thread[cores];                   // array of threads
        ThreadsCounting[] obj = new ThreadsCounting[cores]; // array of threading objects

		/* Creating ThreadsCounting objects */
        for(i=0;i<cores;i++){
            obj[i] = new ThreadsCounting(frags[i][0], frags[i][1], i);
        }
		/* Creating threads and starting them */
        for(i=0;i<cores;i++){
            arr[i] = new Thread(obj[i]);
            arr[i].start();
        }
        /* Joining threads (main doesn't continue executing until all threads finish running */
        for(i=0;i<cores;i++){
            try {
                arr[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		/* printing results */
        printTime(obj, cores);
    }

    /**
     *
     * @param cores Amount of available threads on CPU
     * @param value Number to split up into segments for threads to count to
     * @return returns 2d array holding the threads starting and ending values for counting
     */
    public static long[][] getFragments(int cores, long value) {
        DecimalFormat df = new DecimalFormat("0");
        long[][] frags = new long[cores][2];
        long temp = 0L;

        for(double i = 1.0D; i < (double)(cores + 1); ++i) {
            long x = Long.parseLong(df.format((double)value * (i / (double)cores)));
            frags[(int)i - 1][0] = temp;
            frags[(int)i - 1][1] = x;
            temp = x + 1L;
        }

        return frags;
    }

    /**
     *
     * @param obj Threading objects for information
     * @param cores Thread count on current CPU
     */
    public static void printTime(ThreadsCounting[]obj, int cores){
        double sum = 0L;
        for(int i=0;i<obj.length;i++) {
            System.out.println("Thread " + i + " ran in " + obj[i].getTime() + "seconds.");
        }
    }
}
