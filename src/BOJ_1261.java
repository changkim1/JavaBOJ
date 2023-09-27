import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class BOJ_1261 {
	static int n, m;
	static int[][] map = new int[101][101];
	static int[][] vis = new int[101][101];
	static int[][] wall = new int[101][101]; // 각 칸까지 가기위해 부숴야하는 벽의 최솟값

	static class Pos {
		int x, y, cost;

		public Pos(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	static PriorityQueue<Pos> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	static boolean isValid(int x, int y) {
		return x >= 0 && x < m && y >= 0 && y < n;
	}



	static void input() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			s = bf.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
	}

	static void solve() {
		pq.add(new Pos(0, 0, 0));
		pq.add(new Pos(0, 1, 1));
		wall[0][0] = 0;
		bfs();
	}

	static void bfs() {
		while (!pq.isEmpty()) {
			Pos now = pq.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isValid(nx, ny) && vis[ny][nx] == 0) {
					vis[ny][nx] = 1;
					wall[ny][nx] = wall[now.y][now.x] + map[ny][nx];
					pq.add(new Pos(nx, ny, wall[ny][nx]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(wall[n - 1][m - 1]);
	}
}
