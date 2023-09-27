public class BOJ_4673 {
	public static void main(String[] args) {
		int[] dat = new int[10001];
		int ans = 0;
		for (int i = 1; i <= 10000; i++) {
			int tmp = calc(i);
			if (tmp <= 10000)
				dat[tmp]++;
		}
		for (int i = 1; i <= 10000; i++) {
			if (dat[i] == 0)
				System.out.println(i);
		}
	}

	public static int calc(int numb) {
		int ret = numb;
		while (numb > 0){
			ret += numb % 10;
			numb /= 10;
		}
		return ret;
	}
}
