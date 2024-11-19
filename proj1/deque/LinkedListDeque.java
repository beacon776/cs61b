package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
 private class Node {
      public T value;
      public Node front;
      public Node next;

      private Node(T i, Node a, Node b) {
          value = i;
          front = a;
          next = b;
      }

 }
     private int size;
     private Node sentinel;

      public LinkedListDeque() {
       size = 0;
       sentinel = new Node(null,null,null);
       sentinel.next = sentinel;//
       sentinel.front = sentinel;//
      }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node current;

        public LinkedListDequeIterator() {
            current = sentinel.next;
        }

        public boolean hasNext() {
            return current != sentinel;
        }

        public T next () {
            T returnitem = current.value;
            current = current.next;
            return returnitem;
        }
    }

      public void addFirst(T value) {
          size += 1;
          Node oldNode = sentinel.next;
          Node newNode = new Node(value,sentinel,oldNode);
          sentinel.next = newNode;
          oldNode.front = newNode;
      }

      public void addLast(T value) {
        size += 1;
        Node oldNode = sentinel.front;
        Node newNode = new Node(value,oldNode,sentinel);
        sentinel.front = newNode;
        oldNode.next = newNode;
     }

     private boolean isEmpty() {
            if(size == 0) return true;
            else return false;
     }

     public void printDeque() {
        for(int i = 0;i < size;i++) {
            System.out.print(get(i)+" ");
        }
     }

      public T removeFirst() {
          if(!isEmpty()) {//
        size-=1;
        Node oldNode = sentinel.next;
        Node newNode = oldNode.next;
        sentinel.next = newNode;
        newNode.front = sentinel;
          return oldNode.value;//buhui
          }
          return null;
      }

      public T removeLast() {
          if(!isEmpty()){//
       size-=1;
       Node oldNode = sentinel.front;
       Node newNode = oldNode.front;
       sentinel.front = newNode;
       newNode.next = sentinel;
          return oldNode.value;//
          }
          return null;
      }

      public T get(int index) {//
         if(index<0||index>=size) return null;
        Node current = sentinel.next;
        for(int i=0;i<index;i++){
            current = current.next;
        }
        return current.value;
      }

      public int size(){
        return size;
      }

     public T getRecursive(int index) {
          if(index < 0 || index >= size) return null;
          return getRecursiveHelper(sentinel.next,index);
     }

     private T getRecursiveHelper(Node node,int index) {
          if(index == 0) return node.value;//这里控制成index == 0，别写成size哈，要不就无限死循环了。
          return getRecursiveHelper(node.next,index-1);
     }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if (o == null || !(o instanceof Deque)) {//比较两个对象是不是同一个Deque的实例,有助于保持 equals 方法的行为一致性和合理性。
            return false;
        }
        Deque<T> it = (Deque<T>) o;;//在deque接口中是存在size这个东西的，而linklistdeque的size是private的
        if (it.size() != this.size()) {
            return false;
        }
        for(int i = 0;i < size ;i++) {
            if(!this.get(i).equals(it.get(i))){
                return false;
            }
        }
        return true;
    }


}
