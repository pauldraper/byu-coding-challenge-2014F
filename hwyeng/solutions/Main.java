import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String output = "";
        scan.nextLine();
        while(scan.hasNextLine()){
            output = scan.nextLine() + "\n" + output;
        }
        System.out.println(output);
    }
}