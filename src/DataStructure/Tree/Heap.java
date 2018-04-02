package Tree;
/*
 *用线性表实现堆
 */
import java.util.ArrayList;

public class Heap<E extends Comparable<E> >{
    private ArrayList<E> list = new ArrayList<>();

    /*Create a heap*/
    public Heap(E[] objects){   //create heap from Array
        for(int i = 0; i < objects.length; i++)
            add(objects[i]);    //Append elements of ArrayList into heap
    }

    public Heap() {

    }

    /*Add a new node into the heap:
     * add a new object into the arrayList
     * if the object is larger than its parent node,swap them
     * repeat the action to where the new object becomes a new root node
     * or the new object is less than its current parent node
     */
    public void add(E newObject){    //Add to arrayList and sort for heap
        list.add(newObject);
        int currentIndex = list.size() - 1; //The index of the last node
        while(currentIndex > 0){
            int parentIndex = (currentIndex - 1) / 2;
            //Swap if the current object is greater than its parent
            if(list.get(currentIndex).compareTo(list.get(parentIndex)) > 0){
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            }
            else
                break;  //The tree has been a heap now

            currentIndex = parentIndex;
        }
    }

    /*Remove the root form the heap and return it:
     * move the last node to root after record the previous root node
     * if the new root node is less than the maximum of its two child nodes,swap them
     * repeat the action to where the current node becomes a leaf node（no child nodes)
     * of not less than its child nodes
     * */
    public E remove(){
        if(list.size() == 0)
            return null;
        E root = list.get(0);
        list.set(0, list.get(list.size() - 1));   //把最后一个元素移到数顶，然后排序为堆
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()){
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            //Find the maximum between two children
            if(leftChildIndex >= list.size()) break; //The tree is a heap now，当前节点的左子节点下标越界，说明已遍历到深度最大层
            int maxIndex = leftChildIndex;
            if(rightChildIndex < list.size()){
                if(list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0){
                    maxIndex = rightChildIndex;
                }
            }

            //Swap if the current node is less than the maximum
            if(list.get(currentIndex).compareTo(list.get(maxIndex)) < 0){
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            }
            else
                break; // The tree is a heap

        }
        return root;
    }

    /*Get the numbers of nodes in the tree*/
    public int getSize(){
        return list.size();
    }
}


