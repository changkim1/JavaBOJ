import java.io.*;
import java.util.*;

// 유기농 배추 - BFS
public class BOJ_1012 {
	static int t, n, m, k, cnt;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static Queue<Pos> q = new LinkedList<>();
	static List<List<Integer>> Map = new ArrayList<>();
	static List<List<Integer>> vis = new ArrayList<>();
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		Map.clear();
		vis.clear();

		for (int i = 0; i < n; i++) {
			Map.add(new ArrayList<>(Collections.nCopies(m, 0)));
			vis.add(new ArrayList<>(Collections.nCopies(m, 0)));
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Map.get(y).set(x, 1);
		}

		cnt = 0;
	}

	static void solve() throws IOException {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (Map.get(i).get(j) == 1 && vis.get(i).get(j) == 0) {
					q.add(new Pos(j, i));
					vis.get(i).set(j, 1);
					bfs();
					cnt++;
				}
			}
		}
		bw.write(cnt + "\n");
		bw.flush();
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Pos now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isValid(nx, ny) && Map.get(ny).get(nx) == 1 && vis.get(ny).get(nx) == 0) {
					q.add(new Pos(nx, ny));
					vis.get(ny).set(nx, 1);
				}
			}
		}
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && x < m && y >=0 && y < n;
	}

	public static void main(String[] args) throws IOException {
		t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			init();
			solve();
		}
	}
}
