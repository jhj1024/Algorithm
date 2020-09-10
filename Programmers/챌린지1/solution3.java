import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class solution3 {
    static List<Integer> nums;
    static Set<Integer> lastOne;
    static int answer;
    
    public static int solution(int[] a) {
        nums = new LinkedList<Integer>();
        for(int i = 0; i < a.length; i++) {
            nums.add(a[i]);
        }
        lastOne = new HashSet<>();
        answer = 0;
        
        for(int i = 0; i < a.length - 1; i++) {
            if(a[i] > a[i+1]) {
                nums.remove(i);//i를 삭제
                dfs(false);
                nums.add(i, a[i]);//다시 넣기
                
                nums.remove(i+1);//i를 삭제
                dfs(true);
                nums.add(i+1, a[i+1]);//다시 넣기                    
            }else {
                nums.remove(i);//i를 삭제
                dfs(true);
                nums.add(i, a[i]);//다시 넣기
                
                nums.remove(i+1);//i를 삭제
                dfs(false);
                nums.add(i+1, a[i+1]);//다시 넣기
            }
        }
        
        answer = lastOne.size();
        
        return answer;
    }
    
    public static boolean dfs(boolean min) { 
        if(nums.size() == 1) {
            lastOne.add(nums.get(0));
            return true;
        }
        
        for(int i = 0; i < nums.size() - 1; i++) {
            int left = nums.get(i);
            int right = nums.get(i+1);
            
            if(nums.get(i) > nums.get(i+1)) {
                nums.remove(i);
                dfs(min);
                nums.add(i, left);//다시 넣기
                
                if(min != true) {
                    min = true;
                    nums.remove(i+1);                   
                    dfs(min);
                    nums.add(i+1, right);//다시 넣기
                    min = false;                    
                }
            }else {
                nums.remove(i+1);
                dfs(min);
                nums.add(i+1, right);//다시 넣기
                
                if(min != true) {
                    min = true;
                    nums.remove(i);
                    dfs(min);
                    nums.add(i, left);//다시 넣기
                    min = false;
                }
            }
        }
        
        System.out.println(nums);
        return false;
    }
    
    public static void main(String[] args) {
        int[] numbers = {-16,27,65,-2,58,-92,-71,-68,-61,-33};
        System.out.println(solution(numbers));
    }

}
