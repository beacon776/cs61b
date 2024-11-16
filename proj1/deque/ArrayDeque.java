package deque;

public class ArrayDeque<Blorp> {

    public int nextFirst;
    public int nextLast;//起始点和终止点我们不知道，因为可能在中间插入(它并不是完全从头插入从尾插入的，之前想错了)，所以说我们要自己限制这一段的头和尾
    private int size;
    private Blorp[] items;

    public ArrayDeque(){
        items = (Blorp[]) new Object[8];
        size = 0;
        nextFirst = 1;//最后一个元素的后面
        nextLast = 2;//第一个元素的前面
    }

    private int FirstOnePosition(int index){//return 的是数组第一个元素所在的下标(下标从0开始)
        return (index +1 ) % items.length;
    }

    private int LastOnePosition(int index){//return 的是数组最后一个元素所在的下标(下标从0开始)
        return (index -1 + items.length) % items.length;
    }

    private void resize(int capacity) {//下面print不断更新currentindex的方法好，直接开个for循环吧，arraycopy有点难用。
        Blorp[] a = (Blorp[]) new Object[capacity];
        int currentindex = FirstOnePosition(nextFirst);
       for(int i = 0 ;i < size;i++){
           a[i] = items[currentindex];
           currentindex = FirstOnePosition(currentindex);
       }
       items = a;
        //这里别忘了更新
        nextFirst = capacity -1;//我们默认前面站满了，也就是说first在最后一位
        nextLast = size;//队尾
    }

    public void addFirst(Blorp x){
        if(size == items.length )  resize(size * 2);
        nextFirst = LastOnePosition(nextFirst);//zhuyi
        items[nextFirst] = x;
        size += 1;
    }

    public void addLast(Blorp x){
        if (size == items.length  ) resize(size * 2);
        items[nextLast] = x;//下标从0开始
        nextLast = FirstOnePosition(nextLast);//zhuyi
        size += 1;
    }

    public boolean isEmpty(){
        if(size == 0) return true;
        else return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int currentindex = FirstOnePosition(nextFirst);
        for(int i = 0;i < size; i++){
            System.out.print(items[currentindex]+" ");
            currentindex = FirstOnePosition(currentindex);//持续保持向右移动一位，进行循环输出
        }
        System.out.println();//一行用来美观的换行
    }

    public Blorp removeFirst(){
        if(size == 0) return null;
        nextFirst = FirstOnePosition(nextFirst);
        Blorp moveFirst = items[nextFirst];
        items[nextFirst] = null;
       size -= 1;
       if(size <= items.length/4 && items.length >= 8){
           resize(items.length/2);
       }
       return moveFirst;
    }

    public Blorp removeLast(){
        if(size == 0) return null;
        nextLast = LastOnePosition(nextLast);
        Blorp moveLast = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if(size <= items.length/4 && items.length >= 8){
            resize(items.length/2);
        }
        return moveLast;
    }

    public Blorp get(int index){
        if(index<0 || index> size) return null;
        int firstPosition = FirstOnePosition(nextFirst);
        return items[(index+firstPosition) % items.length];
    }

}
