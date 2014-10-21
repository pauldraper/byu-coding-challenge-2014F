import java.util.*;

public class Main {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		for(int i=scan.nextInt(); i-->0;){
			String board = scan.next();
			int missing = findMissing(board);
			if(missing != -1 && isValid(board.replace('x',(char)('0'+missing)))){
				System.out.println(missing);
			} else {
				System.out.println("invalid");
			}
		}
	}

	public static int findMissing(String board){
		int col = board.indexOf('x') % 9;
		int missing = 45;
		for(int i=0; i<9; i++){
			char c = board.charAt(col+9*i);
			if(c != 'x') missing -= (c - '0');
		}
		if(missing > 0 && missing < 10) return missing;
		return -1;
	}

	public static boolean isValid(String board){
		int[] checks = new int[27];
		for(int i=0; i<81; i++){
			int row = i/9;
			int col = i%9;
			int box = (row/3)*3 + col/3;
			int n = 1 << (board.charAt(i) - '0' - 1);
			checks[row] |= n;
			checks[9+col] |= n;
			checks[18+box] |= n;
		}
		int valid = (1 << 9) - 1;
		for(int check : checks){
			if(check != valid) return false;
		}
		return true;
	}
}