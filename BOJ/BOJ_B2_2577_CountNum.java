import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
* @author JUNG
* @name BOJ_B2_2577_숫자의 개수
* @date 2020.07.29
* @link https://www.acmicpc.net/problem/2577
* @mem
* @time
* @caution 
* [고려사항] 세 개의 수를 곱합 결과에 0~9까지 숫자가 각각 몇번 쓰였는지 출력하기 
* [입력사항]
* [출력사항]
*/

public class BOJ_B2_2577_CountNum {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output;
    
    public static void main(String[] args) throws Exception{
        input = new BufferedReader(new StringReader(src));
        int A, B, C;
        long result;
        int[] nums = new int[10];
        
        //세 개의 수 입력받기
        A = Integer.parseInt(input.readLine());
        B = Integer.parseInt(input.readLine());
        C = Integer.parseInt(input.readLine());
        
        //곱한 결과
        result = A*B*C;
        
        //10으로 나눈 나머지를 통해 0~9사이의 수를 만들어 개수 더하기
        while(result > 0) {
            nums[(int) (result%10)]++;
            result /= 10;
        }
        
        //결과 출력
        for(int data : nums) {
            System.out.println(data);
        }
    }
    static String src = "150\r\n" + 
            "266\r\n" + 
            "427";
}
