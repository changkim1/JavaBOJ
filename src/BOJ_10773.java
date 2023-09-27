import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BOJ_10773 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int k, inp, sum = 0;
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        for (int i = 0; i < k; i++){
            inp = sc.nextInt();
            if (inp == 0)
                stack.pop();
            else
                stack.push(inp);
        }
        while (!stack.isEmpty()){
            sum += stack.peek();
            stack.pop();
        }
        System.out.println(sum);
    }
}