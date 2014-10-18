import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        long start = System.nanoTime();

        for(int i=0; i<n; i++){
            String[] words = scan.nextLine().split(" ");
            HashMap<Character, Integer> lowest = new HashMap<Character, Integer>();
            int maxLen = words[0].length();

            long min = 0;
            long max = 0;

            for(int j=0; j<words.length; j++){
                String word = words[j];
                if(j<words.length-1){
                    min += Math.pow(10, word.length()-1);
                    max += Math.pow(10,word.length())-1;
                }
                maxLen = Math.max(maxLen, word.length());
                lowest.put(word.charAt(0), 1);
                for(char c : word.toCharArray()){
                    if(!lowest.containsKey(c))
                        lowest.put(c, 0);
                }
            }

            long minAns = (long)Math.pow(10,words[words.length-1].length()-1);
            long maxAns = (long)Math.pow(10,words[words.length-1].length())-1;


            if(Math.max(min, minAns) > Math.min(max, maxAns)){
               System.out.println("no solution"); //out of range
                continue;
            }

            if(lowest.size() > 10){
                System.out.println("no solution"); //too many characters.
                continue;
            }



            String constraints = "";
            for(int j=0; j<maxLen; j++){
                for(String word : words){
                    if(j < word.length()){
                        constraints += word.charAt(word.length()-j-1);
                    }
                }
                constraints+=" ";
            }

            for(String word: words){
            }
            Map<Character,Integer> answer = getSolution(constraints, 0, lowest, new boolean[10], new HashMap<Character, Integer>(), 0);

            if(answer.size() > 0){
                String equation = "";
                for(int j=0; j<words.length; j++){
                    for(char c : words[j].toCharArray()){
                        equation += answer.get(c);
                    }
                    if(j < words.length-2){
                        equation+="+";
                    }
                    if(j == words.length-2){
                        equation+="=";
                    }
                }
                System.out.println(equation);
            } else {
                System.out.println("no solution");
            }

        }

        long end = System.nanoTime();

        System.out.println("Total time: " + (end-start));

    }

    //Since we know there is only 0 or 1 solutions, we can make some pretty nice optimizations here.
    public static Map<Character,Integer> getSolution(String constraints, int index, HashMap<Character,Integer> lowest, boolean[] used, HashMap<Character, Integer> assigned, int error){
        if(index >= constraints.length()){
            if(error == 0){
                return new HashMap<Character, Integer>(assigned);
            } else {
                return Collections.EMPTY_MAP;
            }
        }

        char c = constraints.charAt(index);

        if(constraints.charAt(index+1) == ' '){
            if(assigned.containsKey(c) && assigned.get(c) == error % 10){
                return getSolution(constraints, index+2, lowest, used, assigned, error/10);
            } else if(!assigned.containsKey(c) && !used[error%10] && error%10 >= lowest.get(c)){
                used[error%10] = true;
                assigned.put(c,error%10);
                Map<Character,Integer> ret = getSolution(constraints, index+2, lowest, used, assigned, error/10);
                assigned.remove(c);
                used[error%10] = false;
                if(ret.size() >0){
                    return ret;
                }
            }
        } else {
            if(assigned.containsKey(c)){
                return getSolution(constraints, index+1, lowest, used, assigned, error+assigned.get(c));
            } else {
                for(int i=lowest.get(c); i<10; i++){
                    if(!used[i]){
                        used[i] = true;
                        assigned.put(c, i);
                        Map<Character,Integer> ret = getSolution(constraints, index+1, lowest, used, assigned, error+i);
                        if(ret.size() > 0){
                            return ret;
                        }
                        assigned.remove(c);
                        used[i] = false;
                    }
                }
            }
        }

        return Collections.EMPTY_MAP;
    }
}