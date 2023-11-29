import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 하노이 탑 이동 순서 - 재귀
public class BOJ_11729 {
	/*

	 */
	static int n, ans;
	static int[][] vis = new int[21][21];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
	}

	static void solve(int from, int via, int to, int cnt) {
		ans++;
		if (cnt == 1)
			sb.append(from).append(" ").append(to).append("\n");
		else {
			solve(from, to, via, cnt - 1);
			sb.append(from).append(" " ).append(to).append("\n");
			solve(via, from, to, cnt - 1);
		}

	}

	public static void main(String[] args) throws IOException {
		input();
		solve(1, 2, 3, n);
		sb.insert(0, ans + "\n");
		System.out.println(sb);
	}
}
