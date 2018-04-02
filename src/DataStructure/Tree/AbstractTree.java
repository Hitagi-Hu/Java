package Tree;

public abstract class AbstractTree<E> implements Tree<E> {
    @Override
    public void inorder(){}

    public void postorder(){}

    public void preorder(){}

    public boolean isEmpty(){
        return getSize() == 0;
    }
}
