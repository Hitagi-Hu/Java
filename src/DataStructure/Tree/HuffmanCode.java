package Tree;
public class HuffmanCode {
    /**定义霍夫曼树model*/
    public static class Tree implements Comparable<Tree> {
        Node root;
        /**从两个子树生成新树*/
        public Tree(Tree t1, Tree t2){
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }
        /***/
        public Tree(int weight, char element){
            root = new Node(weight, element);
        }
        @Override
        public int compareTo(Tree t){
            if(root.weight < t.root.weight)
                return 1;
            else if(root.weight > t.root.weight)
                return -1;
            else
                return 0;
        }
        /**inner class Node*/
        class Node{
            char element;
            int weight;  //权重
            String code = "";
            Node left;
            Node right;

            Node(){}

            Node(int w, char e){
                this.element = e;
                this.weight = w;
            }
        }
    }
    /**从给定的数生产Huffman编码表*/
    public static String[] getCode(Tree.Node root){   //外部接口写成静态可以直接调用，但其他实现方法也必须为静态方法
        if (root == null)
            return null;
        String[] codes = new String[2 * 128];
        assignCode(root, codes);
        return codes;
    }

    /**递归获取编码*/
    private static void assignCode(Tree.Node root, String[] codes){
        if (root.left != null){
            root.left.code = root.code + "0";
            assignCode(root.left, codes);

            root.right.code = root.code + "1";
            assignCode(root.right, codes);
        }
        else
            codes[(int)root.element] = root.code; //codes[ascii(char)]
    }

    /**从编码获得Huffman树*/
    public static Tree getHuffmanTree(int[] counts){
        Heap<Tree> heap = new Heap<>();
        for (int i = 0; i < counts.length; i++){
            if (counts[i] > 0)
                heap.add(new Tree(counts[i], (char)i));
        }

        while (heap.getSize() > 1){
            Tree t1 = heap.remove();
            Tree t2 = heap.remove();
            heap.add(new Tree(t1, t2));
        }
        return  heap.remove();
    }
    /**计算字符出现频率*/
    public static int[] getCharacterFrequency(String text){
        int[] counts = new int[256];
        for(int i = 0; i < text.length(); i++)
            counts[(int)text.charAt(i)]++; //计算文本里的字符数

        return  counts;
    }
}
