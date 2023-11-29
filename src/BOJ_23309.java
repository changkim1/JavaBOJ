import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 철도 공사 - Linked List
public class BOJ_23309 {
	/*
		BN i j : 고유번호가 i인 역의 다음역 고유번호 출력, 그 사이에 j를 고유번호로 가진 역 설립
		BP i j : 고유번호 i 이전 / 그 사이에 j 설립
		CN i : 다음역 폐쇄, 그 역의 고유번호 출력
		CP i : 이전역 폐쇄, 그 역의 고유번호 출력

		list[i] -> i 가 가진 prev, next 를 저장. (HashSet)
		BN i j -> list.add(new Node(list[i], list[i].next)) / list[i].next.prev = j; / list[i].next = j; /
		BP i j -> list[i].prev.next = j; / list[i].prev = j;
	 */

	static int n, m;
	static int[] nextList = new int[1000001];
	static int[] prevList = new int[1000001];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> inputList = new ArrayList<>();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			inputList.add(num);
		}
		for (int i = 0; i < n; i++) {
			nextList[inputList.get(i)] = inputList.get((i + 1) % n);
			prevList[inputList.get(i)] = inputList.get((i + n - 1) % n);
		}
	}

	static void BN(int i, int j) {
		sb.append(nextList[i]).append("\n");
		prevList[j] = i;
		nextList[j] = nextList[i];
		prevList[nextList[i]] = j;
		nextList[i] = j;
	}

	static void BP(int i, int j) {
		sb.append(prevList[i]).append("\n");
		prevList[j] = prevList[i];
		nextList[j] = i;
		nextList[prevList[i]] = j;
		prevList[i] = j;
	}

	static void CN(int i) {
		int ref = nextList[i];
		sb.append(ref).append("\n");
		prevList[nextList[ref]] = i;
		nextList[i] = nextList[ref];
	}

	static void CP(int i) {
		int ref = prevList[i];
		sb.append(ref).append("\n");
		nextList[prevList[ref]] = i;
		prevList[i] = prevList[ref];
	}

	static void solve() throws IOException {
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int x = Integer.parseInt(st.nextToken());
			int y = 0;
			if (st.hasMoreTokens())
				y = Integer.parseInt(st.nextToken());
			switch (command) {
				case "BN" -> BN(x, y);
				case "BP" -> BP(x, y);
				case "CN" -> CN(x);
				case "CP" -> CP(x);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(sb);
	}
}
