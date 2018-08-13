package List;
/*
* 队列实现栈*/

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/*使用list实现stack*/
public class Stack<E> {
    private Queue<E> queue1 = new ArrayDeque<>();
    private Queue<E> queue2 = new ArrayDeque<>();

    public void push(E node) {
        queue1.add(node);
    }

    public E pop() {
        while (queue1.size() > 1)
            queue2.add(queue1.poll());
        E temp = queue1.poll();
        while (!queue2.isEmpty())
            queue1.add(queue2.poll());
        return temp;
    }
    @Test
    public void test(){
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < 10; i++){
            stack.push(i);
        }

        for (int i = 0; i < 10; i++){
            System.out.println(stack.pop());
        }
    }
}
