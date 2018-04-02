package List;


import java.util.Iterator;

public class MyArrayList<E> extends MyAbstractList<E>{
    private static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    /** Create a default list */
    public MyArrayList(){}

    /** Create a list from an array of objects*/
    public MyArrayList(E[] objects){
        for (E object : objects) {
            add(object);  //不要使用便利抽象类MyAbstractList的构造方法——super（objects）
        }
    }

    @Override
    /** Add a new element at the specified index */
    public void add(int index, E e){
        ensureCapacity();

        // Move the element to the right after the specified index
        for(int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

            //Insert new element to data[index]
            data[index] = e;

            //size + 1
            size++;
    }

    /** Create a new larger array, double the current size + 1 */
    private void ensureCapacity(){
        if(size >= data.length){
            E[] newData = (E[]) new Object[size * 2 + 1];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    @Override
    public void clear(){
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean contains(E e){
        for (int i = 0; i < size; i++)
            if(e.equals(data[i]))
                return true;
        return false;
    }

    @Override
    public E get(int index){
        checkIndex(index);
        return data[index];
    }

    private void checkIndex(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index " + index + " out of bounds");
    }

    @Override
    public int indexOf(E e){
        for(int i = 0; i < size; i++)
            if(e.equals(data[i])) return i;
        return -1;
    }

    @Override
    public int lastIndexOf(E e){
        for(int i = size - 1; i >=0; i--)
            if(e.equals(data[i])) return i;
        return -1;
    }

    @Override
    public E remove(int index){
        checkIndex(index);
        E e = data[index];

        //Shift data to the left
        for(int j = index; j < size - 1; j++){
            data[j] = data[j + 1];
        }

        data[size - 1] = null;

        size--;

        return e;
    }

    @Override
    public E set(int index, E e){
        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder("[");
        for(int i = 0; i < size; i++){
            result.append(data[i]);
            if(i < size - 1) result.append(", ");
        }
        result.append("]");
        return result.toString();
    }

    public void trimToSize(){
        if(size != data.length){
            E[] newData = (E[]) new Object[size];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }

    @Override
    public Iterator<E> iterator(){
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {
        private int current = 0;
        @Override
        public boolean hasNext(){
            return (current < size);
        }

        @Override
        public E next(){
            return data[current++];
        }

        @Override
        public void remove(){
            MyArrayList.this.remove(current);
        }
    }

    /**Test method*/
    public static void main(String[] args){
        //create a list
        MyList<String> list = new MyArrayList<>();
        System.out.println(list);

        //add elements to the list
        list.add("SK Gaming");
        System.out.println("(1)" + list);

        list.add(0, "Faze Clan");
        System.out.println("(2)" + list);

        list.add("Gambit Gaming");
        System.out.println("(3)" + list);

        //remove element
        list.remove("Gambit Gaming");
        System.out.println("(4)" + list);

        Iterator<String> it = list.iterator();
        /*while (it.hasNext()){
            System.out.print(it.next() + ", ");

        }*/
        it.remove();
        System.out.println(list);
    }

}
