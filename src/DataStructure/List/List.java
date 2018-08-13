package List;

import org.junit.Test;

import java.util.Stack;

/*使用stack实现list*/
public class List {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    /*list先进先出:
     * 1.)把stack1的元素依次取出到stack2中*
     * 2.)stack2首元素出栈,即list.pop()元素
     * 3.)还原stack1剩余元素,依次取出stack2首元素到stack1中*/
    public int pop() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int temp = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return temp;
    }

    @Test
    public void test() {
        List list = new List();
        for (int i = 0; i < 10; i++) {
            list.push(i * 2);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(list.pop());

        }


    }
}