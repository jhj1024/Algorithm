import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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
* [고려사항] N장의 카드에서 3장을 골라 M보다 같거나 작으면서 최대한 가까운 수 만들기(조합)
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
        makeCombination(3, 0, nums, new int[3]);
        
        int min = Integer.MAX_VALUE;
        Collections.sort(list); //정렬
        for(int i = 0; i < list.size(); i++) {
            if(M >= list.get(i) && ((M - list.get(i)) < min)) {
                Answer = list.get(i);
                min = M - list.get(i); //(M - 총합)이 가장 작은 수 구하기
            }
        }
        System.out.println(Answer);
    }
    
  //3장을 뽑는 조합 만들기. 단, 뽑은 3개의 숫자를 저장하는 것이 아닌 뽑은 3개의 숫자의 합을 반환한다.
    static void makeCombination(int r, int start, int[] nums, int[] temp) {
        if (r == 0) {
            int sum = temp[0] + temp[1] + temp[2];
            list.add(sum); //합 저장
            return;
        }
        for (int i = start; i < nums.length; i++) {
            temp[r - 1] = nums[i];
            makeCombination(r - 1, i + 1, nums, temp);
        }
    }

}
