package deque;

public class LinkedListDeque<Blorp> {
 public class Node{
      public Blorp value;
      public Node front;
      public Node next;

      public Node(Blorp i, Node a,Node b){
          value = i;
          front = a;
          next = b;
      }

 }
     private int size;
     private Node sentinel;

      public LinkedListDeque(){
       size = 0;
       sentinel = new Node(null,null,null);
       sentinel.next = sentinel;//
       sentinel.front = sentinel;//
      }


      public void addFirst(Blorp value){
          size += 1;
          Node oldNode = sentinel.next;
          Node newNode = new Node(value,sentinel,oldNode);
          sentinel.next = newNode;
          oldNode.front = newNode;
      }

      public void addLast(Blorp value){
        size += 1;
        Node oldNode = sentinel.front;
        Node newNode = new Node(value,oldNode,sentinel);
        sentinel.front = newNode;
        oldNode.next = newNode;
     }

     public boolean isEmpty(){
            if(size==0) return true;
            else return false;
     }

     public void printDeque(){
        for(int i=0;i<size;i++){
            System.out.print(get(i)+" ");
        }
     }

      public Blorp removeFirst(){
          if(!isEmpty()){//
        size-=1;
        Node oldNode = sentinel.next;
        Node newNode = oldNode.next;
        sentinel.next = newNode;
        newNode.front = sentinel;
          return oldNode.value;//buhui
          }
          return null;
      }

      public Blorp removeLast(){
          if(!isEmpty()){//
       size-=1;
       Node oldNode = sentinel.front;
       Node newNode = oldNode.front;
       sentinel.front = newNode;
       newNode.next = sentinel;
          return oldNode.value;//buhui
          }
          return null;
      }

      public Blorp get(int index){//
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

     public Blorp getRecursive(int index){
          if(index<0||index>=size) return null;
          return getRecursiveHelper(sentinel.next,index);
     }
     public Blorp getRecursiveHelper(Node node,int index){
          if(size==0) return node.value;
          return getRecursiveHelper(node.next,index-1);
     }

}
