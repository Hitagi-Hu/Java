package List;


import java.util.Iterator;

public class MyLinkedList<E> extends MyAbstractList<E> {
    private Node<E> head, tail;
    /**Create a default list */
    public MyLinkedList(){}

    public MyLinkedList(E[] objects){
        super(objects);
    }
    /** Return the head element in the list*/
    public E getFirst(){
        if(size == 0)
            return null;
        else
            return head.element;
    }
    /** Return the last element in the list*/
    public E getLast(){
        if(size == 0)
            return null;
        else
            return tail.element;

    }
    /** Add an element to the beginning of the list*/
    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;

        if(tail == null)
            tail = head;


     }
     /** Add an element to the end of the list*/
     public void addLast(E e){
         Node<E> newNode = new Node<>(e);
         if(tail == null)
             head = tail = newNode;
         else {
             tail.next = newNode;
             tail = newNode;  //或者tail = tail.next;
         }

         size++;

     }
     /** Insert an element between index - 1 and index */
     public void add(int index, E e){  //example： index == 4，插入到3和4之间，当前节点从0移到3需要移到（index-1）步
         if(index == 0) addFirst(e);
         else if(index >= size) addLast(e);
         else {
             Node<E> current = head;
             for(int i = 1; i < index; i++)  //移到（index - 1）步
                 current = current.next;
             Node<E> temp = current.next;  //保存当前节点的下一个节点
             current.next = new Node<>(e); //当前节点指向待插入节点
             (current.next).next = temp;   //待插入节点指向已保存的下一节点，把链表连接上
             size++;
         }
     }
     /** Remove first element and return its element*/
     public E removeFirst(){
         if (size == 0)
             return null;
         else {
             Node<E> temp = head;          //返回语句必须在最后面，所有先保存节点
             head = head.next;
             size--;
             if(head == null) tail = null; //链表为空
             return temp.element;
         }
     }
     /** Remove last element and return its element*/
     public E removeLast(){
         if(size == 0) return null;
         else if(size == 1){
             Node<E> temp = head;
             head = tail = null; //链表空
             size--;
             return temp.element;
         }
         else {
             Node<E> current = head;
             for(int i = 0; i < size - 2; i++)  //移到（size - 2）步到倒数第二个节点
                 current = current.next;
             Node<E> temp = tail;
             tail = current;
             tail.next = null;
             size--;
             return temp.element;
         }

     }
     /** Remove element by specified index*/
     @Override
     public E remove(int index){
         if(index < 0 || index >= size) return null;
         else if(index == 0) return removeFirst();
         else if(index == size - 1) return removeLast();
         else {
             Node<E> previous = head;
             for(int i = 1; i < index; i++)   //(index - 1)步
                 previous = previous.next;

             Node<E> current = previous.next;
             previous.next = current.next;
             size--;
             return current.element;
         }
     }

     @Override
     public String toString(){
         StringBuilder result = new StringBuilder("[");

         Node<E> current = head;

         /*for(int i = 0; i < size - 1; i++){
             result.append(current.element + ", ");
             current = current.next;
         }*/
         while(current.element != null){
             result.append(current.element);
             if(current.next != null) result.append(", ");
             current = current.next;
         }
         result.append("]");
         return result.toString();      //线性表空时抛出异常
     }

     @Override
     public void clear(){
         size = 0;
         head = tail = null;
     }
     @Override
     public boolean contains(E e){
         if(size == 0) return false;
         else {
             Node<E> current = head;
             for(int i = 1; i < size; i++){     //这个类中有两种计算移动步数，一种是用index，一种是用size，这里从head移动到tail，需要（size-1）步
                 if(current.element.equals(e)) return true;
                 current = current.next;
             }
             return false;
         }
     }

     @Override
     public E get(int index){
         if(index < 0 || index >= size) return null;
         else if(index == 0) return getFirst();
         else if(index == size - 1) return getLast();
         else {
             Node<E> current = head;
             for(int i = 0; i < index; i++)
                 current = current.next;
             return current.element;
         }
     }

     @Override
     public int indexOf(E e){
         if(size == 0) return -1;
         else {
             Node<E> current = head;
             for(int i = 0; i <= size - 1; i++){
                 if (current.element.equals(e)) return i;
                 current = current.next;
             }
             return -1;

         }
     }

     @Override
     public int lastIndexOf(E e){
         for(int i = size - 1; i >= 0; i--){
             if(get(i).equals(e)) return i;
         }
         return -1;
     }
    /** Set value of element by index*/
     @Override
     public E set(int index, E e){
         if(index < 0 || index >= size) return null;
         else if(index == 0) {
             E temp = head.element;
             head.element = e;
             return temp;
         }
         else if(index == size - 1) {
             E temp = tail.element;
             tail.element = e;
             return temp;
         }
         else {
             Node<E> current = head;
             for(int i = 0; i < index; i++)
                 current = current.next;
             E temp = current.element;
             current.element = e;
             return temp;
         }

     }
     public Iterator<E> iterator(){
         return new LinkedListIterator();
     }

     private class LinkedListIterator implements Iterator<E>{
         private Node<E> current = head;
         @Override
         public boolean hasNext(){
             return (current != null);
         }
         @Override
         public E next(){
             E e = current.element;
             current = current.next;
             return  e;
         }
         @Override
         public void remove(){
             MyLinkedList.this.remove(current.element);
         }
     }
     private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element){
            this.element = element;
        }
     }
     public static void main(String[] args){
         MyLinkedList<String> list = new MyLinkedList<>();
         System.out.println(list);

         list.add("SK Gaming");
         System.out.println("(1)" + list);
         list.add(0, "Faze Clan");
         System.out.println("(2)" + list);

         list.add(1, "Astrails");
         System.out.println("(3)" + list);

         list.addLast("Faze Clan");
         System.out.println("(4)" + list);
         /*System.out.println(list.remove(1));
         System.out.println(list);*/

         /*list.removeFirst();
         System.out.println(list);*/


         /*list.set() is ok
         * System.out.println(list.set(3,'b'));        //set（index, (E)e） index >= size,return null
         * list.set(2,'b');
         * System.out.println(list);*/

         System.out.println("lastIndexOf: " + list.lastIndexOf("Faze Clan"));
         System.out.println("IndexOf: " + list.indexOf("Faze Clan"));
         Iterator it = list.iterator();
         while (it.hasNext()){
             System.out.print(it.next() + ", ");

         }




     }
}

/*每个方法还需要测试*/
//error: 线性表为空时打印抛出异常