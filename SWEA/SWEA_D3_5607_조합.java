import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D3_5607_조합
* @date 2020.09.28
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXGKdbqczEDFAUo
* 
* [입력사항] 테스트케이스의 개수 T, 정수 N, R
* [출력사항] N combination R을 1234567891로 나눈 나머지
* 
* >> 페르마의 소정리 이용
* >> pow 계산시 분할정복 이용 
* 
*/

public class SWEA_D3_5607_조합 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, R;
    static long up, down, Answer;
    static final int mod = 1234567891;
    static long[] fac;

    public static void main(String[] args) throws Exception {
        //모든 테스트케이스에 대한 팩토리얼을 미리 생성
        makeFactorial();
        
        //테스트케이스 입력
        T = Integer.parseInt(input.readLine());
        for (int t = 1; t <= T; t++) {
            //입력
            tokens = new StringTokenizer(input.readLine(), " ");
            N = Integer.parseInt(tokens.nextToken());
            R = Integer.parseInt(tokens.nextToken());
                       
            //알고리즘
            up = fac[N];
            down = pow(((fac[N-R]*fac[R])%mod), mod-2);
            Answer = (up*down)%mod;
            
            //출력
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }
        System.out.println(output);
    }

    public static long pow(long num, long remain) {
        if(remain == 0) {
            return 1;
        }      
        
        if(remain%2 == 0) {
            long temp = pow(num, remain/2);
            return (temp*temp)%mod;
        }else {
            long temp = pow(num, remain-1)%mod;
            return (temp*num)%mod;
        }       
    }    
    
    public static void makeFactorial() {
        fac = new long[1000001]; //1 ≤ N ≤ 1000000
        fac[0] = 1;
        for (int i = 1; i < fac.length; i++) {
            fac[i] = (fac[i - 1] * i) % mod;
        }
    }

}
