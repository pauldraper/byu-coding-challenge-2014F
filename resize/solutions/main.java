package resize;

import java.util.*;

public class Main {

    static class IndexedDouble {
        int index;
        double value;
        public IndexedDouble(int i, double d){
            index = i;
            value = d;
        }
    }

    static Comparator<IndexedDouble> byIndex = new Comparator<IndexedDouble>() {
        @Override
        public int compare(IndexedDouble a, IndexedDouble b) {
            return a.index - b.index;
        }
    };

    static Comparator<IndexedDouble> byValue = new Comparator<IndexedDouble>() {
        @Override
        public int compare(IndexedDouble a, IndexedDouble b) {
            if(a.value < b.value) return -1;
            if(a.value > b.value) return 1;
            return 0;
        }
    };

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        for(int t=scan.nextInt(); t-->0;){
            int rows = scan.nextInt();
            int cols = scan.nextInt();
            List<IndexedDouble> rowHeights = new ArrayList<IndexedDouble>();
            List<IndexedDouble> colWidths = new ArrayList<IndexedDouble>();

            for(int i=0; i<rows; i++) rowHeights.add(new IndexedDouble(i, scan.nextDouble()));
            for(int i=0; i<cols; i++) colWidths.add(new IndexedDouble(i, scan.nextDouble()));

            double minRow = scan.nextDouble();
            double minCol = scan.nextDouble();
            double height = scan.nextDouble();
            double width = scan.nextDouble();

            resizeList(rowHeights, minRow, height);
            printList(rowHeights);
            resizeList(colWidths, minCol, width);
            printList(colWidths);
        }
    }

    public static void printList(List<IndexedDouble> list){
        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i).value + " ");
        }
        System.out.println();
    }

    public static double getSize(List<IndexedDouble> list){
        double sum = 0;
        for(IndexedDouble d : list)
            sum += d.value;
        return sum;
    }

    public static void resizeList(List<IndexedDouble> list, double minVal, double desiredSize){
        Collections.sort(list,byValue);
        double size = getSize(list);

        for(IndexedDouble d : list){
            double cur = d.value;
            d.value = Math.max(minVal, desiredSize/size*d.value);
            size -= cur;
            desiredSize -= d.value;
        }
        Collections.sort(list,byIndex);
    }
}