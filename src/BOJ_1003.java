import java.io.*;

// 피보나치 함수 - dp
public class BOJ_1003 {
	static int[][] cnt = new int[41][2]; // [2][0] = 2의 0cnt, [1] = 1 cnt
	static int t, n;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static void init() throws IOException{
		t = Integer.parseInt(br.readLine());

		// dp 완성시켜두기
		cnt[0][0] = 1; // 0일 때 0의 갯수 1개
		cnt[0][1] = 0; // 0일 때 1의 갯수 0개
		cnt[1][0] = 0;
		cnt[1][1] = 1;
	}

	static void solve() throws IOException {
		for (int i = 2; i < 41; i++) {
			cnt[i][0] = cnt[i - 2][0] + cnt[i - 1][0];
			cnt[i][1] = cnt[i - 2][1] + cnt[i - 1][1];
		}

		for (int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			bw.write(cnt[n][0] + " " + cnt[n][1] + "\n");
			bw.flush();
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		solve();
	}
}
