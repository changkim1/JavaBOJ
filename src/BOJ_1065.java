import java.util.Scanner;

public class BOJ_1065 {
	public static void main(String[] args) {
		int n, cnt = 0;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for (int i = 1; i <= n; i++) {
			if (solve(i) == 1)
				cnt++;
		}
		System.out.println(cnt);
	}

	public static int solve(int num) {
		if (num < 100)
			return 1;
		int interval = (num % 10) - ((num / 10) % 10);
		while (num > 9) {
			if ((num % 10) - ((num / 10) % 10) != interval)
				return 0;
			num /= 10;
		}
		return 1;
	}
}
