/**
 * Created by SSLGhost on 6/1/17.
 */
public class ThreadsCounting implements Runnable {

    private long start;    // count starting number
    private long end;      // count ending number
    private double time;   // elapsed time
    private int thread;    // thread number (order created)

    /* Constructor */
    ThreadsCounting(long start, long end, int thread) {
        this.start = start;
        this.end = end;
        this.thread = thread;
    }

    /**
     * Function thread is running that is being timed
     * This function counts from start to end
     */
    public void run() {
        long time1 = System.nanoTime();

        long i=start;
        while (i <= end) {
            i++;
        }

        long time2 = System.nanoTime();
        this.time  = (double)((time2 - time1)/1000000000.0);
    } // end run

    // setters and getters
    public double getTime(){
        return this.time;
    }
    public int getThread(){
        return thread;
    }
}
