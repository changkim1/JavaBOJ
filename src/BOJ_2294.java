import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 동전 2 - DP
public class BOJ_2294 {
	/*
	dp 값에 n 개를 더한 dp 값을 계속해서 갱신해나가기
	 */

	static int n, k, ans;
	static int[] dp = new int[10001];
	static HashSet<Integer> coins = new HashSet<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Integer> q = new LinkedList<>();

	static void input() throws IOException {
		Arrays.fill(dp, 210000000);
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num > k)
				continue;
			coins.add(num);
			dp[num] = 1;
		}
	}

	static void solve() {
		for (int i = 1; i <= k; i++) {
			for (int coin : coins) {
				if (i - coin >= 0 && dp[i - coin] != 210000000) {
					dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
				}
			}
		}
	}



	public static void main(String[] args) throws IOException {
		input();
		solve();
		if (dp[k] == 210000000) {
			System.out.println("-1");
		}
		else
			System.out.println(dp[k]);
	}
}
