import java.util.Stack;
//使用一个辅助栈保存最小值
public class Solution {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(Integer node) {
        stack1.push(node);
        if (stack2.empty()) stack2.push(node);
        else {
            if (node <= stack2.peek())
                stack2.push(node);
        }
    }

    public void pop() {
        if (stack1.peek() == stack2.peek())
            stack2.pop();
        stack1.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();

    }
}