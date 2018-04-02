package Tree;


import java.util.ArrayList;

public class TestBST {
    public static void main(String[] args){
        BST<Integer> tree = new BST<>();
        tree.insert(8);
        tree.insert(6);
        tree.insert(7);
        tree.insert(9);
        tree.insert(10);

        //遍历
        System.out.print("Inorder(sorted): ");
        tree.inorder();

        System.out.print("\nPostorder: ");
        tree.postorder();

        System.out.print("\nPreorder: ");
        tree.preorder();

        System.out.println("\nThe number of nodes is " + tree.getSize());

        //Search
        System.out.println("Is 9 in the tree? " + tree.search(9));

        //Path
        ArrayList<BST.TreeNode<Integer>> path = tree.path(6);
        for(int i = 0; path != null && i < path.size(); i++)
            System.out.print(path.get(i).element + " ");

        Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
        BST<Integer> intTree = new BST<>(numbers);
        System.out.print("\nInorder(sorted): ");
        intTree.inorder();

    }
}

//中序遍历相等于递增排序