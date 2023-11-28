import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 케빈 베이컨의 6단계 법칙 - BFS
public class BOJ_1389 {
	static int n, m, min = 21000000, ans;
	static int[] cnt = new int[101]; // 각 사람의 케빈 베이컨 수
	static int[] vis = new int[101];
	static List<HashSet<Integer>> inputMap = new ArrayList<>();
	static List<List<Integer>> map = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Integer> q = new LinkedList<>();

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= n; i++) {
			inputMap.add(new HashSet<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			inputMap.get(x).add(y);
			inputMap.get(y).add(x);
		}

		for (int i = 0; i <= n; i++) {
			map.add(new ArrayList<>(inputMap.get(i)));
		}
	}

	static void solve() {
		for (int i = 1; i <= n; i++) {
			if (i != 1)
				init();
			q.add(i);
//			System.out.println("start = " + i + " first = " + q.peek());
			vis[i] = 1;
			bfs(i);
			if (min > cnt[i]) {
				min = cnt[i];
				ans = i;
			}
//			System.out.println();
		}
		System.out.println(ans);
	}

	static void init() {
		for (int i = 1; i <= n; i++) {
			vis[i] = 0;
		}
	}

	static void bfs(int start) {
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 0; i < map.get(now).size(); i++) {
				int to = map.get(now).get(i);
				if (vis[to] == 0) {
					q.add(to);
					vis[to] = vis[now] + 1;
					cnt[start] += vis[to] - 1;
//					System.out.println("start = " + (start+1) + " to = " + (to+1) + " cnt = " + (vis[to] - 1));

				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
	}
}
