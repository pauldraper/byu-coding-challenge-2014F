import java.util.*;
import java.util.Map.Entry;

class Main2 {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        for(int t=scan.nextInt(); t-->0;){
            Map<Integer,Double> rowHeights = new TreeMap<Integer,Double>();
            Map<Integer,Double> colWidths = new TreeMap<Integer,Double>();

            int rows = scan.nextInt();
            int cols = scan.nextInt();
            for(int i=0; i<rows; i++) {
                rowHeights.put(i, scan.nextDouble());
            }
            for(int i=0; i<cols; i++) {
                colWidths.put(i, scan.nextDouble());
            }

            double minRow = scan.nextDouble();
            double minCol = scan.nextDouble();
            double height = scan.nextDouble();
            double width = scan.nextDouble();

            resize(rowHeights, minRow, height);
            resize(colWidths, minCol, width);
        }
    }

    private static void resize(Map<Integer,Double> sizes, double minVal, double desiredSize){
        double total = 0;
        for(double size : sizes.values()) {
            total += size;
        }

        List<Entry<Integer,Double>> list = new ArrayList<Entry<Integer,Double>>(sizes.entrySet());
        Collections.sort(list, new Comparator<Entry<Integer,Double>>() {
            public int compare(Entry<Integer,Double> a, Entry<Integer,Double> b) {
                return a.getValue().compareTo(b.getValue());
            }
        });

        for(Entry<Integer,Double> entry : list) {
            double cur = entry.getValue();
            entry.setValue(Math.max(minVal, desiredSize / total * entry.getValue()));
            total -= cur;
            desiredSize -= entry.getValue();
        }

        for(double size : sizes.values()) {
            System.out.print(size + " ");
        }
        System.out.println();
    }
}
