package gh2;

import deque.Deque;
import deque.ArrayDeque;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
     private ArrayDeque<Double> buffer;
     private int capacity;
    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity;i += 1){
            buffer.addFirst(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        capacity = Math.round(capacity);
        for (int i = 0;i < capacity;i += 1){
            double r = Math.random() - 0.5;
            buffer.removeFirst();
            buffer.addLast(r);//如何保证不重复？其实这一部分还是没有做到
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double first =  buffer.get(0);
        double second = buffer.get(1);
        buffer.removeFirst();
        double newNumber = 0.5 * (first + second) * DECAY;
        buffer.addLast(newNumber);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
