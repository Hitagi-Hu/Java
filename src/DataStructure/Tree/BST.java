package Tree;
/**二叉查找树*/
import java.util.ArrayList;
import java.util.Iterator;

public class BST<E extends Comparable<E>> extends AbstractTree<E> {
    private TreeNode<E> root;
    private int size = 0;

    /**Default construct method*/
    public BST(){}

    /**Cnstruct from an array*/
    public BST(E[] objects){
        for (E object : objects) insert(object);
    }

    @Override
    public boolean search(E e){
        TreeNode<E> current = root;
        while (current != null){
            if(e.compareTo(current.element) < 0){
                current = current.left;
            }
            else if(e.compareTo(current.element) > 0){
                current = current.right;
            }
            else
                return true;
        }
        return false;
    }

    public boolean insert(E e){
        if(root == null)  //可以尝试 size == 0 or size <= 0
            root = new TreeNode<>(e);
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null){
                if(e.compareTo(current.element) < 0){
                    parent = current;
                    current = current.left;
                }
                else if(e.compareTo(current.element) > 0){
                    parent = current;
                    current = current.right;
                }
                else
                    return false;    //相同element的节点不能插入
            }

            //Insert
            if(e.compareTo(parent.element) < 0)
                parent.left = new TreeNode<>(e);
            else
                parent.right = new TreeNode<>(e);
        }

        size++;
        return true;
    }

    /**递归中序遍历：左 + 中 + 右*/
    public void inorder(){
        inorder(root);
    }
    private void inorder(TreeNode<E> root){    //对于每一个当前节点，先遍历左子树，再输出当前节点，再遍历右子树。
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**后序遍历:左 + 右 + 中*/
    public void postorder(){
        postorder(root);
    }
    private void postorder(TreeNode<E> root){
        if(root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**前序遍历：中 + 左 + 右*/
    public void preorder(){
        preorder(root);
    }
    private void preorder(TreeNode<E> root){
        if(root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**Inner class TreeNode is static,because it does not access nay instance members defined in its outer class*/
    static class TreeNode<E extends Comparable<E>> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        TreeNode(E e){
            element = e;
        }
    }

    public int getSize(){
        return size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    //返回从root到e的路径,返回的路径保存在数组线性表中
    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null){
            list.add(current);
            if(e.compareTo(current.element) < 0)
                current = current.left;
            else  if(e.compareTo(current.element) > 0)
                current = current.right;
            else
                break;
        }
        return list;
    }
    /**
     * */
    public boolean delete(E e){
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null){//遍历直到当前节点为空
            if(e.compareTo(current.element) < 0){
                parent = current;
                current = current.left;
            }
            else if(e.compareTo(current.element) > 0){
                parent = current;
                current = current.right;
            }
            else
                break;    //Element is in the tree pointed at by current
        }

        if(current == null)
            return false;  //Element is not in the tree

        //Case 1 : current has no left child
        if(current.left == null){
            //Connect the parent with right of child of the current node
            if(parent == null)  //parent定义时初始化为nul,意味着在根节点就找到了
                root = current.right;    //要删除根节点且左子树为空，那么直接只剩右子树，右子节点即为删除后的根节点
            else {
                if(e.compareTo(parent.element) < 0)    //e == current.element,判断要删除的current节点是parent的左子节点还是右子节点
                    parent.left = current.right;       //是parent的左子节点，那就把current的右子树接到parent的左子树
                else
                    parent.right = current.right;      //反之
            }
        }
        else{
            //Case 2 : The current has a left child
            TreeNode<E> parentOfRightMost = current;//这里current == root 也包含在内，所以不算特殊情况
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; //Keep going to the right
            }
            current.element = rightMost.element;
            //理解: 如果有左子数，则找到左子树里的最大值（在左子树中向右遍历到其右边最深处），赋给当前节点

            if(parentOfRightMost.right == rightMost)   //至少往下遍历了一步
                parentOfRightMost.right = rightMost.left;
            //等同于rightMost = rightMost.left,把找出的最大值节点rightMost用其左子节点或null（遍历到最深层rightMost只有左子节点或者没有子节点)替换掉
            else
                //Special case : parentOfRightMost == current（包括==root），第一步就停止，即current的左子节点没有右子节点，直接将其连接起来
                parentOfRightMost.left = rightMost.left;
        }
        size--;
        return true;

    }

    public Iterator<E> iterator(){
        return  new InorderIterator();
    }
    private class InorderIterator implements Iterator<E> {
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        public InorderIterator() {
            inorder();
        }
        private void inorder(){
            inorder(root);
        }
        private void inorder(TreeNode<E> root){
            if(root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        public boolean hasNext(){
            if(current < list.size())
                return true;
            return false;
        }

        public E next(){
            return list.get(current++);
        }

        public void remove(){
            delete(list.get(current));
            //删除线性表并重新遍历
            list.clear();
            inorder();
        }
    }
    public void clear(){
        root = null;
        size = 0;
    }
}
