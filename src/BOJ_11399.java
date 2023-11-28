import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// ATM - 정렬
public class BOJ_11399 {
	static int n, ans;
	static List<Integer> p = new ArrayList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static void input() throws IOException {
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			p.add(Integer.parseInt(st.nextToken()));
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		Collections.sort(p);
		int tmp = 0;
		for (int pi : p) {
			tmp += pi;
			ans += tmp;
		}
		System.out.println(ans);
	}
}
