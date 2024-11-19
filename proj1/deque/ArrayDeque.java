package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int nextFirst;
    private int nextLast;//起始点和终止点我们不知道，因为可能在中间插入(它并不是完全从头插入从尾插入的，之前想错了)，所以说我们要自己限制这一段的头和尾
    private int size;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;//最后一个元素的后面
        nextLast = 4;//第一个元素的前面
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
       private int index;

       public ArrayDequeIterator() {
            index = FirstOnePosition(nextFirst);
      }

       public boolean hasNext() {
        return index != nextLast;
        }

        public T next () {
           T returnitem = items[index];
           index = FirstOnePosition(index);
           return returnitem;
        }
    }

    private int FirstOnePosition(int index){//return 的是数组第一个元素所在的下标(下标从0开始)
        return (index + 1 ) % items.length;
    }

    private int LastOnePosition(int index){//return 的是数组最后一个元素所在的下标(下标从0开始)
        return (index - 1 + items.length) % items.length;
    }

    private void resize(int capacity) {//下面print不断更新currentindex的方法好，直接开个for循环吧，arraycopy有点难用。
        T[] a = (T[]) new Object[capacity];
        int currentindex = FirstOnePosition(nextFirst);
       for(int i = 0 ;i < size;i++){
           a[i] = items[currentindex];
           currentindex = FirstOnePosition(currentindex);
       }
        items = a;//这里别忘了更新
        nextFirst = capacity -1;//我们默认前面站满了，也就是说first在最后一位
        nextLast = size;//zhuyi duiwei
    }

    public void addFirst(T x) {
        if(size == items.length )  resize(size * 2);
        items[nextFirst] = x;//zhuyishunxu
        nextFirst = LastOnePosition(nextFirst);//zhuyi
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length ) resize(size * 2);
        items[nextLast] = x;//下标从0开始
        nextLast = FirstOnePosition(nextLast);//zhuyi
        size += 1;
    }

    public int size(){
        return size;
    }

    public void printDeque() {
        int currentindex = FirstOnePosition(nextFirst);
        for(int i = 0;i < size; i++){
            System.out.print(items[currentindex]+" ");
            currentindex = FirstOnePosition(currentindex);//持续保持向右移动一位，进行循环输出
        }
        System.out.println();//一行用来美观的换行
    }

    public T removeFirst() {
        if(size == 0) return null;
        nextFirst = FirstOnePosition(nextFirst);//zhuyi
        T moveFirst = items[nextFirst];
        items[nextFirst] = null;
       size -= 1;
       if(size == 0){
           nextFirst = items.length - 1;
           nextLast = 0;
       }
       else if(size <= items.length/4 && items.length >= 8){
           resize(items.length/2);
       }
       return moveFirst;
    }

    public T removeLast() {
        if(size == 0) return null;
        nextLast = LastOnePosition(nextLast);//
        T moveLast = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if(size == 0){
            nextFirst = items.length - 1;
            nextLast = 0;
        }
        else if(size <= items.length/4 && items.length >= 8){
            resize(items.length/2);
        }
        return moveLast;
    }

    public T get(int index) {
        if(index<0 || index>= size) return null;//这里注意等号，index==size时也是超出了范围的，第一遍交的时候这里有问题
        int firstPosition = FirstOnePosition(nextFirst);
        return items[(index+firstPosition) % items.length];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {//比较两个对象是不是同一个类的实例,有助于保持 equals 方法的行为一致性和合理性。
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (size() != other.size()) {
            return false;
        }
        for (int i =0; i < size(); i++) {
            if (!get(i).equals(other.get(i))) {//如果都含有相同元素(无论顺序)，就返回true
                return false;
            }
        }
        return true;
    }

    private boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x) && items[i] != null) {//这里应该加个空值检查
                return true;
            }
        }
        return false;
    }
}
