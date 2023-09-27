import java.util.*;

public class BOJ_4963 {
	static class node {
		int x, y;
		public node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int n, m, ans;
	static int[][] map = new int[51][51], vis = new int[51][51];
	static int[] dx = {0, 1, 0, -1, -1, 1, 1, -1};
	static int[] dy = {1, 0, -1, 0, 1, 1, -1, -1};
	static Queue<node> q = new LinkedList<>();
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		while (true){
			init();
			input();
			if (n == 0 && m == 0)
				break;
			solve();
			System.out.println(ans);
		}
	}

	static void input() {
		m = sc.nextInt();
		n = sc.nextInt();
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
	}

	static void init() {
		for (int i = 0; i < n; i++) {
			Arrays.fill(vis[i], 0);
			Arrays.fill(map[i], 0);
		}
		ans = 0;
	}

	static boolean isValid(int x, int y) {
		return (x >= 0 && x < m && y >= 0 && y < n);
	}

	static void solve(){
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && vis[i][j] == 0){
					node start = new node(j, i);
					q.add(start);
					vis[i][j] = 1;
					ans++;
					bfs();
				}
			}
		}
	}

	static void bfs() {
		while (!q.isEmpty()) {
			node now = q.poll();
			for (int i = 0; i < 8; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isValid(nx, ny) && map[ny][nx] == 1 && vis[ny][nx] == 0) {
					node to = new node(nx, ny);
					q.add(to);
					vis[ny][nx] = 1;
				}
			}
		}
	}
}
