package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> c;
    public MaxArrayDeque(Comparator<T> c) {
        this.c = c;
    }

    public T max() {
        if(isEmpty()) return null;
        return max(c);
    }

    public T max(Comparator<T> c) {
        if(isEmpty()) return null;
        T max = get(0);
        for(int i = 1; i < size() ; i++) {
            T current = get(i);
            if(c.compare(current,max) > 0) max = current;
        }
      return max;
    }

}
