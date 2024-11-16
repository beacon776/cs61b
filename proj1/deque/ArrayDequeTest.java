package deque;

import org.junit.Test;
import static org.junit.Assert.*;


public class ArrayDequeTest {
    @Test
   public void testRemoveFirst() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(7);
        deque.addLast(6);
        deque.addLast(5);

        assertEquals(7, deque.removeFirst().intValue());
        assertEquals(6, deque.removeFirst().intValue());
        assertEquals(5, deque.removeFirst().intValue());
        assertNull(deque.removeFirst());
    }


}
