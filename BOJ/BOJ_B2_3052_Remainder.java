import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_B2_3052_나머지
* @date 2020.07.29 
* @link https://www.acmicpc.net/problem/3052
* @mem
* @time
* @caution 
* [고려사항] 입력받은 10개의 숫자를 각각 42로 나눈 후, 그 나머지가 서로 다른 개수 구하기
* [입력사항]
* [출력사항]
*/

public class BOJ_B2_3052_Remainder {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int Answer;
    
    public static void main(String[] args) throws Exception{
        int[] nums = new int[10];
        boolean[] remainders = new boolean[42];
        
        for(int i = 0; i < 10; i++) {
            nums[i] = Integer.parseInt(input.readLine()); //수 입력
        }
        
        Answer = 0;
        for(int i = 0; i < 10; i++) {
            if(remainders[nums[i]%42] == false) { //해당 나머지는 나온적이 없다면
                remainders[nums[i]%42] = true; //나왔다고 표시
                Answer++; //서로 다른 나머지 개수 추가
            }
        }
        
        System.out.println(Answer);
    }

}
