import java.lang.reflect.Array;
import java.util.*;

public class BOJ_5430 {
/*	앞, 뒤 인덱스를 계속 움직이면서 계산하기
*   앞에서 시작 D 나오면 하나 뒤로, R 나오면 앞 -> 뒤, 뒤에서 D 나오면 하나 앞으로
*   만약 D 나왔는데 앞 >= 뒤면 error 출력하고 종료
* */
	static boolean direction = false; // false 면 앞, true 면 뒤
	static String query = "", inp = "";
	static int l = 0, r = 0, t, n;
	static Scanner sc = new Scanner(System.in);
	static StringBuilder sb = new StringBuilder();
	static String[] arr;
	static Deque<String> dq = new LinkedList<>();
	public static void main(String[] args) {
		t = sc.nextInt();
		for (int z = 0; z < t; z++) {
			input(z);
			solve();
		}
		System.out.println(sb);
	}

	static void input(int z) {
		if (z != 0)
			Arrays.fill(arr, "");
		query = sc.next();
		n = sc.nextInt();
		inp = sc.next();
		arr = inp.substring(1, inp.length() - 1).split(",");
		if (!Objects.equals(arr[0], ""))
			dq.addAll(Arrays.asList(arr));
		l = 0;
		r = arr.length - 1;
		direction = false;
	}

	static void solve() {
		int i = 0;
//		System.out.println("size = "+dq.size());
		while (i < query.length()) {
			char now = query.charAt(i);
			if (now == 'R')
				direction = !direction;
			if (now == 'D') {
				if (dq.isEmpty()) {
					sb.append("error\n");
					return ;
				}
				if (!direction) { // 앞
					dq.removeFirst();
				}
				else
					dq.removeLast();
			}
			i++;
		}
		int size = dq.size();
		sb.append("[");
		for (int a = 0; a < size; a++) {
			if (!direction)
				sb.append(dq.pollFirst());
			else
				sb.append(dq.pollLast());
			if (dq.size() != 0)
				sb.append(",");
		}
		sb.append("]\n");
	}
}
