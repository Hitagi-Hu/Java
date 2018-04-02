package Tree;

public interface Tree<E> extends Iterable<E> {
    public boolean search(E e);

    public boolean insert(E e);

    public boolean delete(E e);

    /**中序遍历*/
    public void inorder();

    /**后序遍历*/
    public void postorder();

    /**前序遍历*/
    public void preorder();

    public int getSize();

    public boolean isEmpty();
}
