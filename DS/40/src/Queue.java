import java.util.Stack;

public class Queue {

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.push(1);
        System.out.println(queue.pop());
        queue.push(2);
        queue.push(3);
        System.out.println(queue.top());
        System.out.println(queue.pop());
    }

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public Queue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int element) {
        stack1.push(element);
    }

    public int pop() {
        int result;
        if(!stack2.isEmpty()) {
            result = stack2.pop();
        } else {
            while(!stack1.isEmpty()) {
                Integer x = stack1.pop();
                stack2.push(x);
            }
            result = stack2.pop();
        }
        return result;
    }

    public int top() {
        int result;
        if(!stack2.isEmpty()) {
            result = stack2.lastElement();
        } else {
            while(!stack1.isEmpty()) {
                Integer x = stack1.pop();
                stack2.push(x);
            }
            result = stack2.lastElement();
        }
        return result;
    }
}
