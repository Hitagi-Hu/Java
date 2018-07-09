package NOWCODER;
/*
* 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
*/
/*算法：两个递归，一个使用递归b遍历判断是否是子结构，另一个递归遍历判断是否包含子结构*/
public class HasSubtree {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        //递归结束条件
        if (root1 == null || root2 == null)
            return false;
        else {
            return IsSubtree(root1,root2) || HasSubtree(root1.left,root2) || HasSubtree(root1.right,root2);//是子结构返回true，不是则往后递归遍历
        }

    }
    boolean IsSubtree(TreeNode root1,TreeNode root2){
        //递归结束条件
        if (root2 == null) return true; //树2遍历结束则为子结构
        if (root1 == null) return false;//树1遍历结束则不是子结构
        if (root1.val == root2.val)
            return IsSubtree(root1.left,root2.left) && IsSubtree(root1.right,root2.right);
        else
            return false;
    }
}
