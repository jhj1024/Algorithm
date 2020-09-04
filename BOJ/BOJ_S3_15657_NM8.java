import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S3_15657_N과M(8)
* @date 2020.09.04
* @link https://www.acmicpc.net/problem/15657
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 자연수 N과 M / N개의 숫자 정보
* [출력사항] N개의 자연수 중에서 M개를 고른 조합 (중복 허용)
* 
* 설명에는 순열이라고 하지만, 순열 내 요소가 오름차순이어야 하기 때문에 순서가 정해져있는 조합이다.
* 수열은 사전 순으로 증가하는 순서로 출력 -> 수열 만들기 전 정렬
*/

public class BOJ_S3_15657_NM8 {
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
        makeCobination(0, 0, new int[M]);
        System.out.println(output);

    }
    
    public static void makeCobination(int m, int start, int[] temp) {
        if(m == M) {            
            for(int t : temp) {
                output.append(t).append(" ");
            }
            output.append("\n");
            return;
        }
        
        for(int i = start; i < N; i++) {
            temp[m] = numbers[i];
            makeCobination(m+1, i, temp);
        }        
    }

}
