import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S3_15656_N과M(7)
* @date 2020.09.04
* @link https://www.acmicpc.net/problem/15656
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 자연수 N과 M / N개의 숫자 정보
* [출력사항] N개의 자연수 중에서 M개를 고른 조합 (중복 허용)
* 
* 수열은 사전 순으로 증가하는 순서로 출력 -> 수열 만들기 전 정렬
*/

public class BOJ_S3_15656_NM7 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = null;
    static int N, M;
    static int[] numbers;
    
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine(), " ");
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        
        numbers = new int[N];
        tokens = new StringTokenizer(input.readLine(), " ");
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(tokens.nextToken());
        }
        
        output = new StringBuilder();
        Arrays.sort(numbers); //사전 순 증가를 위해 미리 오름차순 정렬
        makePermutation(0, new int[M]);
        System.out.println(output);

    }

    public static void makePermutation(int m, int[] temp) {
        if(m == M) {            
            for(int t : temp) {
                output.append(t).append(" ");
            }
            output.append("\n");
            return;
        }
        
        for(int i = 0; i < N; i++) {
            temp[m] = numbers[i];
            makePermutation(m+1, temp);
        }
        
    }
}
