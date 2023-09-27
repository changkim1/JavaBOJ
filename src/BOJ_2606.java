import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2606 {
		static int n, m, cnt, s, d;
		static int[] vis;
		static ArrayList<Integer>[] map;
		static Queue<Integer> q = new LinkedList<>();

	public static void bfs() {
		q.add(1);
		vis[1] = 1;
		while (!q.isEmpty()){
			int now = q.poll();
			cnt++;
			for (int i = 0; i < map[now].size(); i++) {
				int to = map[now].get(i);
				if (vis[to] == 0) {
					q.add(to);
					vis[to] = 1;
				}

			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		map = new ArrayList[n + 1];
		vis = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			map[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			s = sc.nextInt();
			d = sc.nextInt();
			map[s].add(d);
			map[d].add(s);
		}
		bfs();
		System.out.println(cnt - 1);
	}
}
