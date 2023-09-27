import java.util.Scanner;

public class BJ_2908 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String n1, n2, ans = "";
		n1 = sc.next();
		n2 = sc.next();
		for (int i = n1.length() - 1; i >= 0; i--) {
			if (n1.charAt(i) != n2.charAt(i))
			{
				ans = n1.charAt(i) > n2.charAt(i) ? n1 : n2;
				break ;
			}
			else
				continue ;
		}
		for (int i = ans.length()-1; i >= 0; i--)
		{
			System.out.print(ans.charAt(i));
		}
	}
}
