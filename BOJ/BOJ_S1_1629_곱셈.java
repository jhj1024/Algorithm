import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_1629_곱셈
* @date 2020.09.29
* @link https://www.acmicpc.net/problem/1629
* 
* [입력사항] 정수 A, B, C
* [출력사항] A를 B번 곱한 수를 C로 나눈 나머지
* 
* >> 분할 정복을 이용한 거듭제곱 사용
* 
*/

public class BOJ_S1_1629_곱셈 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int A, B, C;
    static long Answer;
    
    public static void main(String[] args) throws Exception{
        tokens = new StringTokenizer(input.readLine());
        A = Integer.parseInt(tokens.nextToken());
        B = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        
        Answer = pow(A, B, C);
        System.out.println(Answer);        
    }
    
    public static long pow(int num, int remain, int mod) {
        if(remain == 0) {
            return 1;
        }      
        
        if(remain%2 == 0) {
            long temp = pow(num, remain/2, mod)%mod;
            return (temp*temp)%mod;
        }else {
            long temp = pow(num, remain-1, mod)%mod;
            return (temp*num)%mod;
        }       
    }  

}
