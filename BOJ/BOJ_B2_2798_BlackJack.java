import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B2_2798_블랙잭
* @date 2020.07.29
* @link https://www.acmicpc.net/problem/2798
* @mem
* @time
* @caution 
* [고려사항] N장의 카드에서 3장을 골라 M보다 같거나 작은 수 중 최대값 만들기(조합)
* [입력사항]
* [출력사항]
*/

public class BOJ_B2_2798_BlackJack {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static List<Integer> list = new ArrayList<>(); // 점수합을 저장하는 리스트
    static int Answer;
    
    public static void main(String[] args) throws Exception {        
        StringTokenizer tokens = new StringTokenizer(input.readLine(), " ");
        N = Integer.parseInt(tokens.nextToken()); //카드의 개수
        M = Integer.parseInt(tokens.nextToken()); //최대한 가깝게 만들어야 하는 수
        
        //숫자 입력
        int[] nums = new int[N];
        tokens = new StringTokenizer(input.readLine(), " ");
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(tokens.nextToken()); 
        }
        
        /* --------------------------- 알고리즘 --------------------------- */
        Answer = 0;
        makeCombination(3, 0, nums, new int[3]); //3개의 수를 고르는 조합   
        System.out.println(Answer);
    }
    
  //3장을 뽑는 조합 만들기. 단, 뽑은 3개의 숫자를 저장하는 것이 아닌 뽑은 3개의 숫자의 합을 생각한다.
    static void makeCombination(int r, int start, int[] nums, int[] temp) {
    	if (r == 0) {
            int sum = temp[0] + temp[1] + temp[2];
            if(sum <= M && sum > Answer) { //수의 합이 M보다 작거나 같지만 Answer보다 크면 Answer에 저장
            	Answer = sum;
            }
            return;
        }
        for (int i = start; i < nums.length; i++) {
            temp[r - 1] = nums[i];
            makeCombination(r - 1, i + 1, nums, temp);
        }
    }

}
