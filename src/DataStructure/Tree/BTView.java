package Tree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;


public class BTView extends Pane {
    private BST<Integer> tree = new BST<>();
    private double radius = 15; //圆形节点半径
    private double vGap = 50;   //节点间隔

    BTView(BST<Integer> tree){
        this.tree = tree;
        setStatus("Tree is empty");
    }

    /**show the status by text*/
    public void setStatus(String msg){
        getChildren().add(new Text(20, 20, msg));
    }

    /**递归显示树*/
    public void displayTree(){
        this.getChildren().clear();
        if(tree.getRoot() != null){
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4);
        }
    }

    /**显示位于坐标（x, y) 处的子树*/
    private void displayTree(BST.TreeNode<Integer> root, double x, double y, double hGap){
        if(root.left != null){
            //画出左子树
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null){
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }

}
