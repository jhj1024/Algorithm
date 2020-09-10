import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class solution1 {
    
    public static int[] solution(int[] numbers) {
        Set<Integer> result = new TreeSet<Integer>();
        
        Arrays.sort(numbers);        
        
        int len = numbers.length;
        for(int i = 0; i < len; i++) {
            for(int j = i+1; j < len; j++) {
                result.add(numbers[i]+numbers[j]);
            }
        }
        
        int[] answer = new int[result.size()];
        
        int idx = 0;
        Iterator<Integer> iter = result.iterator();
        while(iter.hasNext()) {
            answer[idx++] = iter.next();
        }
        
        return answer;
    }
    
    
    public static void main(String[] args) {
        int[] numbers = {5,0,2,7};
        System.out.println(Arrays.toString(solution(numbers)));
    }
}
