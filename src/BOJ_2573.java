import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2573 {
//	녹기 전에 map , 몇 개가 녹는지 저장할 map 필요
//	녹고 난 후에 몇 개로 분리되어 있는지 확인 필요
	static class Coo {
		int x, y;
		public Coo(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[][] map = new int[301][301];
	static int[][] melt = new int[301][301];
	static int[][] vis = new int[301][301];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static Queue<Coo> q = new LinkedList<>();
	static int n, m, ans = 0;

	static boolean isValid(int x, int y) {
		return (x >= 0 && x < m && y >= 0 && y < n);
	}

	static void melting() {
		// melt 배열 초기화
		for (int i = 0; i < n; i++)
			Arrays.fill(melt[i], 0);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// 아직 남아있는 빙산들은 사방 체크
				if (map[i][j] != 0) {
					for (int k = 0; k < 4; k++) {
						if (map[i + dy[k]][j + dx[k]] == 0)
							melt[i][j]++;
					}
				}
			}
		}
		// 사방 체크 완료 이제 녹이면 됨
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] -= melt[i][j];
				if (map[i][j] < 0)
					map[i][j] = 0;
			}
		}
	}

	static void bfs() {
		while (!q.isEmpty()) {
			Coo now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isValid(nx, ny) && map[ny][nx] != 0 && vis[ny][nx] == 0) {
					Coo new_coo = new Coo(nx, ny);
					q.add(new_coo);
					vis[ny][nx] = 1;
				}
			}
		}
	}

	static void solve() {
		boolean ansFlag;
		boolean endFlag;
		int time = 0;
		do {
			time++;
			melting();
			ansFlag = false;
			endFlag = false;
			for (int i = 0; i < n; i++) {
				Arrays.fill(vis[i], 0);
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] != 0 && vis[i][j] == 0) {
						// 하나라도 돌 수 있으면 아직 안 끝남
						endFlag = true;
						// bfs 를 두 번 돌게 되면 답
//						System.out.println(i);
//						System.out.println(j);
						if (!ansFlag)
							ansFlag = true;
						else{
							ans = time;
							return ;
						}
						Coo start = new Coo(j, i);
						q.add(start);
						bfs();
					}
				}
			}
		} while (endFlag);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		solve();
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		System.out.println(ans);
	}
}
