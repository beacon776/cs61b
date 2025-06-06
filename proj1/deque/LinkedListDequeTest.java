package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }


    }

    @Test
    public void printTestWithDifferentParameters(){
        LinkedListDeque<Integer> intDeque = new LinkedListDeque<>();
        LinkedListDeque<Double> doubleDeque = new LinkedListDeque<>();
        LinkedListDeque<String> stringDeque = new LinkedListDeque<>();
        LinkedListDeque<Boolean> booleanDeque = new LinkedListDeque<>();

        for (int i = 1; i <= 10; i++){
            intDeque.addFirst(i);
            intDeque.addLast(-i);
            double j = i + 0.114514;
            doubleDeque.addFirst(j);
            doubleDeque.addLast(-j);
        }
        String[] test = new String[]{"I", "am", "a", "happy", "man", "with", "CS61B"};
        for (String i: test){
            stringDeque.addLast(i);
            stringDeque.addFirst(i);
        }
        booleanDeque.addLast(true);
        booleanDeque.addFirst(false);

        intDeque.printDeque();
        doubleDeque.printDeque();
        stringDeque.printDeque();
        booleanDeque.printDeque();
    }

    @Test
    public void testGet(){
        LinkedListDeque<Integer> intDeque = new LinkedListDeque<>();

        for (int i = 1; i <= 10; i++){
            intDeque.addFirst(i);
            intDeque.addLast(-i);
        }

        int tmp0 = intDeque.get(0);
        int tmp2 = intDeque.get(2);
        int tmp19 = intDeque.get(19);
        intDeque.printDeque();
        String tmp = tmp0 + " " + tmp2 + " " + tmp19;
        System.out.println(tmp);
    }

    @Test
    public void testGetRecursive(){
        LinkedListDeque<Integer> intDeque = new LinkedListDeque<>();

        for (int i = 1; i <= 10; i++){
            intDeque.addFirst(i);
            intDeque.addLast(-i);
        }

        int tmp0 = intDeque.getRecursive(0);
        int tmp2 = intDeque.getRecursive(2);
        int tmp19 = intDeque.getRecursive(19);
        intDeque.printDeque();
        String tmp = tmp0 + " " + tmp2 + " " + tmp19;
        System.out.println(tmp);
    }

    @Test
    public void testGetNull(){
        LinkedListDeque<Integer> intDeque = new LinkedListDeque<>();

        for (int i = 1; i <= 10; i++){
            intDeque.addFirst(i);
            intDeque.addLast(-i);
        }

        int tmp0 = intDeque.getRecursive(0);
        assertNull(intDeque.get(1000));
        assertNull(intDeque.getRecursive(1000));
    }

    @Test
    public void testEquals(){
        LinkedListDeque<Integer> intDeque1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> intDeque2 = new LinkedListDeque<>();

        boolean flagEquals1 = intDeque1.equals(intDeque2);
        assertTrue(flagEquals1);

        intDeque1.addLast(1);
        boolean flagNotEquals1 = intDeque1.equals(intDeque2);
        assertFalse(flagNotEquals1);

        boolean flagNotEquals2 = intDeque1.equals(123);
        assertFalse(flagNotEquals2);

        intDeque2.addLast(1);
        intDeque1.addFirst(3);
        intDeque2.addFirst(3);
        boolean flagEquals2 = intDeque1.equals(intDeque2);
        assertTrue(flagEquals2);

        intDeque1.addFirst(123);
        boolean flagNotEquals3 = intDeque1.equals(intDeque2);
        assertFalse(flagNotEquals3);

        intDeque2.addFirst(234);
        boolean flagNotEquals4 = intDeque1.equals(intDeque2);
        assertFalse(flagNotEquals4);
    }
}