import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 동전 0 - DP, 그리디
public class BOJ_11047 {
	static int n, k, cnt;
	static List<Integer> coins = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num > k)
				continue;
			coins.add(num);
		}
	}

	static void solve() {
		int idx = coins.size() - 1;
		while (k != 0) {
			if (k < coins.get(idx)) {
				idx--;
			}
			else {
				cnt += k / coins.get(idx);
				k %= coins.get(idx);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(cnt);
	}
}
