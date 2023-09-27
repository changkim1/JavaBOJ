import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, m, Max, rip_cnt, wall_cnt;
	static int[][] map = new int[1001][1001];
	static int[][] vis = new int[1001][1001];

	static Queue<Pos> q = new LinkedList<>();
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		input();
		solve();
		if (rip_cnt + wall_cnt == m * n) {
			if (Max == 0)
				System.out.println(Max);
			else
				System.out.println(Max - 1);
		}
		else {
			System.out.println("-1");
		}
	}

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1)
					wall_cnt++;
			}
		}
	}

	static Boolean isValid(int x, int y) {
		return (x >= 0 && x < m && y >= 0 && y < n);
	}

	static void solve() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && vis[i][j] == 0) {
					q.add(new Pos(j, i));
					vis[i][j] = 1;
				}
			}
		}
		bfs();
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Pos now = q.poll();
			rip_cnt++;
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isValid(nx, ny) && map[ny][nx] != -1 && vis[ny][nx] == 0) {
					q.add(new Pos(nx, ny));
					vis[ny][nx] = vis[now.y][now.x] + 1;
					Max = vis[ny][nx];
				}
			}
		}
	}
}
