import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Interval> airplanes = new ArrayList<Interval>();
        airplanes.add(new Interval(1, 4));
        airplanes.add(new Interval(1, 4));
        airplanes.add(new Interval(1, 4));
        System.out.println(countOfAirplanes(airplanes));
    }

    public static class Interval {
         int start, end;
         Interval(int start, int end) {
             this.start = start;
             this.end = end;
         }
    }

    public static int countOfAirplanes(List<Interval> airplanes) {
        int[] timeline = new int[10000000];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(Interval term : airplanes) {
            timeline[term.start] += 1;
            timeline[term.end] += -1;
            if(term.start < min) {
                min = term.start;
            }
            if(term.end > max) {
                max = term.end;
            }
        }
        int count = 0;
        int maxCount = 0;
        for(int i = min; i <= max; i++) {
            count += timeline[i];
            if(count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }
}
