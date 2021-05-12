import java.util.Stack;

public class MinStackWithStk extends Stack<Integer> {
    Stack<Integer> stk;

    public MinStackWithStk() {
        stk = new Stack<Integer>();
    }

    public void push(int value){
        if (value <= min()) {
            stk.push(value);
        }
        super.push(value);
    }

    public Integer pop() {
        int value = super.pop();
        if (value == min()) {
            stk.pop();
        }
        return value;
    }

    public int min() {
        if (stk.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return stk.peek();
        }
    }

    public static void main(String[] args) {
        MinStackWithStk m= new MinStackWithStk();
        m.push(4);
        System.out.println("New min is " + m.peek() + ", " + m.min());

        m.push(2);
        System.out.println("New min is " + m.peek() + ", " + m.min());

        m.push(5);
        m.push(1);
        System.out.println("New min is " + m.peek() + ", " + m.min());

        m.pop();
        System.out.println("New min is " + m.peek() + ", " + m.min());

    }
}
