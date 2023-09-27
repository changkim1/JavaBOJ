import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2839 {
	static int n;
	static int five_cnt, thr_cnt, mod;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		String s = br.readLine();
		n = Integer.parseInt(s);

		five_cnt = n / 5;
		while (five_cnt >= 0) {
			mod = n - (5 * five_cnt);
			if (mod % 3 == 0)
				break;
			five_cnt--;
		}
		if (five_cnt < 0){
			System.out.println("-1");
			return;
		}
		System.out.println(five_cnt + mod / 3);
	}
}
