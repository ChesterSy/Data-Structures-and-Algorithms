class hors {
	public int[] shiftTable(char[] p) { //T O O T H		A B C D E F G H I J K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z  _
		int[] s = new int[500];			//0 1 2 3 4		0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
		int m = p.length; //5
		
		for (int i = 0; i < s.length; i++) {
			s[i] = m; //yung buong shift table value muna is yng pattern length
		}
		for (int j = 0; j <= m - 2; j++) {
			s[p[j]] = m - 1 - j; //ito na yung shifts ng mga letters, the rest is yng pattern length
		}
		return s;
	}
	
	public int Horspool(char[] p, char[] t) {
		hors hss = new hors();
		int[] shift = hss.shiftTable(p);
		int i = p.length - 1;
		int m = p.length;
		int k;
		
		while(i <= t.length - 1) {
			k = 0;
			while((k <= m - 1) && (p[m - 1 - k] == t[i - k])) {
				k = k + 1;
			}
			if (k == m) {
				return i - m + 1;
			} else {
				i = i + shift[t[i]];
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		hors hss = new hors();
		String pattern = "Cheese";
		String source = "Carbonara Chicken Alfredo Spaghetti Aglio Olio Tuna Pesto Lasagna Macaroni and Cheese Chow Mien Wanton Noodles Beef Noodles Dumpling Noodles Beef-Dumpling Shana Noodles Carbonara Chicken Alfredo Spaghetti Aglio Olio Tuna Pesto Lasagna Macaroni and Cheese Chow Mien Wanton Noodles  Beef Noodles Dumpling Noodles Beef-Dumpling Noodles Miso Ramen";
		char[] patt = pattern.toLowerCase().toCharArray();
		char[] src = source.toLowerCase().toCharArray();
		
		int pos = hss.Horspool(patt, src);
		System.out.print(pos);
	}
}