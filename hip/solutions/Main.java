import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        for(int n=scan.nextInt(); n-->0;){
            int players = scan.nextInt();
            int size = scan.nextInt();
            String b = scan.next();
            int[][] board = new int[size][size];

            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    board[i][j] = b.charAt(i*size+j);
                }
            }

            int empty = '-';

            Map<Integer,Integer> scores = new HashMap<Integer,Integer>();

            for(int i=0; i<players; i++){
                scores.put('a'+i,0);
            }

            for(int x1=0; x1<size; x1++){
                for(int y1=0; y1<size; y1++){
                    if(board[y1][x1] != empty){
                        for(int x2 = y1==size-1 ? x1+1:x1; x2<size; x2++){
                            for(int y2 = x2>x1 ? 0 : y1+1; y2<size; y2++){
                                if(board[y2][x2] == board[y1][x1]){
                                    int dx = x1 - x2;
                                    int dy = y1 - y2;
                                    int x3 = x1+dy,
                                        y3 = y1-dx,
                                        x4 = x2+dy,
                                        y4 = y2-dx;
                                    if(x3 < 0 || x3 >= size || y3 < 0 || y3 >= size || x4 < 0 || x4 >= size || y4 < 0 || y4 >= size){
                                        continue;
                                    }
                                    if(board[y3][x3]==board[y1][x1] && board[y4][x4]==board[y1][x1]){
                                        scores.put(board[y1][x1], scores.get(board[y1][x1])+1);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            List<Map.Entry<Integer,Integer>> entries = new ArrayList<Map.Entry<Integer, Integer>>(scores.entrySet());

            Collections.sort(entries,new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                    if(a.getValue() - b.getValue() != 0){
                        return b.getValue()-a.getValue();
                    }
                    return a.getKey() - b.getKey();
                }
            });

            for(Map.Entry<Integer, Integer> entry : entries){
                System.out.println((char)entry.getKey().intValue() + " " + entry.getValue()/2);
            }
        }
    }
}