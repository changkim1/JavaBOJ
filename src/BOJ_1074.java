import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Z - 분할정복
public class BOJ_1074 {
	/*
	가장 큰 범위부터 Z의 몇 사분면에 있는지 계속 파고들어가기.
	초기에 몇 번째 서클에 있는지 확인하기
	서클 -1 씩 해가면서 재귀타기.
	사분면 체크, 시작값, 기준값, 좌표기준값 갱신
	서클이 1일 경우에 시작값 출력.
	ex.
		(5, 2) 는 2^0~2^1-1 -> 2^1~2^2-1 -> 2^2~2^3-1 사이에 있음. 3번째니까 4^2 ~ 4^3-1 의 크기를 가짐

		시작 값 기준 값 : 0, 16
		좌표 기준값 4 (3서클)
		x가 2^2보다 크니까 2, 4사분면
		y가 2^2보다 작으니까 1, 2사분면 => 2사분면

		시작 값 기준 값 : 16, 4 initVal, refVal
		좌표 기준값 2 (2서클) posRef
		2사분면은 x 값을 기준 숫자만큼 빼고 다시 비교 : 5,2 -> 1,2
		x 가 좌표기준값 보다 작으니까 1, 3사분면
		y 가 좌표기준값보다 크니까 3, 4 사분면 => 3사분면 => 시작값 + 기준값 * (3 - 1) -> 다음 시작 값

		시작 값 기준 값 : 24, 1
		좌표 기준값 1 (1서클)

	 */
	static int n, r, c;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int initVal, ref;

	static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
	}

	static int getCircle(int x, int y) {
		int Max = Math.max(x, y);
		int tmp = 2;
		int circle = 1;
		while (Max >= tmp) {
			tmp = tmp * 2;
			circle++;
		}
		initVal = 0;
		getRef(circle);
		return circle;
	}

	static void getRef(int circle) {
		// 서클이 3이면 ref 는 16 : 4^(3-1)
		ref = (int) Math.pow(4, circle - 1);
	}

	static int getPosRef(int circle) {
		return (int) Math.pow(2, circle - 1);
	}

	static void solve(int circle) {
		if (circle == 0)
			return;
		int quad = getQuad(c, r, getPosRef(circle));
		initVal += ref * (quad - 1);
		setPos(quad, getPosRef(circle));
		circle--;
		getRef(circle);
		solve(circle);
	}

	static void setPos(int quad, int posRef) {
		if (quad == 1)
			return;
		else if (quad == 2)
			c -= posRef;
		else if (quad == 3)
			r -= posRef;
		else if (quad == 4) {
			c -= posRef;
			r -= posRef;
		}
	}

	static int getQuad(int x, int y, int posRef) {
		// y가 좌표 기준보다 크거나 같으면 3, 4사분면
		if (y >= posRef) {
			// x가 작으면 3사분면
			if (x < posRef)
				return 3;
			else
				return 4;
		}
		// y가 좌표 기준보다 작으면 1, 2사분면
		else {
			// x 가 작으면 1
			if (x < posRef)
				return 1;
			else
				return 2;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve(getCircle(c, r));
		System.out.println(initVal);
	}
}
