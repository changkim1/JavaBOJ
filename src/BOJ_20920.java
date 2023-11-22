import java.io.*;
import java.util.*;

public class BOJ_20920 {
	// input
	static int n, m;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Map<String, Integer> hash = new HashMap<>();

	static class Word implements Comparable<Word> {
		String word;

		public Word(String word) {
			this.word = word;
		}

		@Override
		public int compareTo(Word o) {
			// 자주 나오는 단어일수록 앞에 배치한다.
			if (hash.get(this.word) < hash.get(o.word))
				return 1;
			else if (hash.get(this.word) > hash.get(o.word))
				return -1;
			else {
				if (this.word.length() < o.word.length())
					return 1;
				else if (this.word.length() > o.word.length())
					return -1;
				else {
					// this 가 앞
					if (this.word.compareTo(o.word) < 0)
						return -1;
					else if (this.word.compareTo(o.word) > 0)
						return 1;
					else
						return 0;
				}
			}
		}
	}

	public static void input() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++) {
			String word = br.readLine();
			if (word.length() < m)
				continue;
			hash.put(word, hash.getOrDefault(word, 0) + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		List<Word> list = new ArrayList<>();
		for (String word : hash.keySet()) {
			list.add(new Word(word));
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for (Word word : list) {
			sb.append(word.word).append('\n');
		}
		System.out.print(sb);
	}
}
