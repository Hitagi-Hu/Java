package List;

public abstract class MyAbstractList<E> implements MyList<E> {
    protected int size = 0;

    /** Create a default list */
    protected MyAbstractList(){}

    /** Create a list form an array of objects */
    protected MyAbstractList(E[] objects){
        for(int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    @Override
    /** Add a new element at the end of the list */
    public void add(E e){
        add(size, e);
    }

    @Override
    /** Return if the list doesn't contain any elements */
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean remove(E e){
        if(indexOf(e) >= 0){
            remove(indexOf(e));
            return true;
        }
        else
            return false;
    }
}
