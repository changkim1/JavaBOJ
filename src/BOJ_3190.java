// 뱀이 어디로 향하고 있는지, 뱀의 몸이 어디 어디에 위치해있는지(덱)
// 시간 정보 -> 정렬해서 시간이 빠른거부터
// 덱에 좌표가 들어있는지 확인해서 있으면 끝

import java.util.*;

public class BOJ_3190 {
	static class Position {
		private int x, y;

		public Position() {
			this(0, 0);
		}

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static class TimeInfo {
		private int t;
		private char dir;

		public TimeInfo(int t, char dir) {
			this.t = t;
			this.dir = dir;
		}
	}

	static Scanner sc = new Scanner(System.in);
	static Deque<Position> snakePos = new ArrayDeque<>(); // 뱀의 몸통들을 저장해둔 덱
	static ArrayList<TimeInfo> timeInfos = new ArrayList<>();
	static int dir = 1;
	static int[] nx = {0, 1, 0, -1};
	static int[] ny = {-1, 0, 1, 0};
	static int[][] map = new int[101][101];
	static int n, k, now = 1;
	static boolean endFlag = false;

	static void input() {
		n = sc.nextInt();
		k = sc.nextInt();
		for (int i = 0; i < k; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			map[y - 1][x - 1] = 1; // 사과는 맵에 1로 표시
		}
		int l = sc.nextInt();
		for (int i = 0; i < l; i++) {
			int x = sc.nextInt();
			char c = sc.next().charAt(0);
			timeInfos.add(new TimeInfo(x, c));
		}
	}

	static void headToNextPos() {
		Position nowHead = snakePos.getFirst();
		Position next = new Position(nowHead.x + nx[dir], nowHead.y + ny[dir]);
		snakePos.addFirst(next);
	}

	static boolean isValid(int x, int y) {
		return (x >= 0 && x < n && y >= 0 && y < n);
	}

	static void endCheck() {
		Position head = snakePos.pollFirst();
		if (!isValid(head.x, head.y)) {
			// 벽이랑 만난거임
			endFlag = true;
			return ;
		}
		snakePos.forEach(position -> {
			if (position.x == head.x && position.y == head.y) {
				// 몸통이랑 만난거임
				endFlag = true;
				return ;
			}
		});
		snakePos.addFirst(head);
	}

	static void getApple() {
		Position nowPos = snakePos.getFirst();
		// 사과가 없다면
		if (map[nowPos.y][nowPos.x] != 1) {
			snakePos.removeLast();
		}
		else {
			// 있다면 꼬리 안 없어지고 사과는 없어짐
			map[nowPos.y][nowPos.x] = 0;
		}
	}

	static void setDir() {
		if (timeInfos.isEmpty())
			return ;
		TimeInfo tif = timeInfos.get(0);
		if (now == tif.t) {
			if (tif.dir == 'L')
				dir = (dir + 3) % 4;
			else
				dir = (dir + 1) % 4;
			timeInfos.remove(0);
		}
	}

	static void solve() {
		Position first = new Position();
		snakePos.addFirst(first);
		// 계속 도는데, endFlag == true 되면 끝
		while(true) {
			headToNextPos();
			endCheck();
			if (endFlag) {
				System.out.println(now);
				break;
			}
			getApple();
			setDir();
			now++;
		}
	}

	public static void main(String[] args) {
		input();
		solve();
	}


}
