import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 리모컨 - 완전탐색
public class BOJ_1107 {
	static int n, m, ans; // 목표 숫자, 고장난 버튼 갯수, 가장 가까운 숫자
	static HashSet<Integer> nums = new HashSet<>(); // 안 고장난 숫자 저장
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static void init() throws IOException {
		ans = Math.abs(n - 100);
		if (m == 0)
			return;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			nums.add(num);
		}
	}
	
	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
	}

	static void solve() {
		for (int i = 0; i < 1000100; i++) {
			boolean flag = false;
			String s = String.valueOf(i);
			for (int num : nums) {
				if (s.contains(String.valueOf(num))) {
					flag = true;
					break;
				}
			}
			if (flag)
				continue;
			if (ans > Math.abs(n - i) + s.length()) {
				ans = Math.abs(n - i) + s.length();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		input();
		init();
		solve();
		System.out.println(ans);
	}
	
}
