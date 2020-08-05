import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
* @author JUNG
* @name BOJ_S3_1168_요세푸스문제2
* @date 2020.08.05
* @link https://www.acmicpc.net/problem/1168
* @mem
* @time
* @caution 
* [고려사항] 입력이 많으므로 큐(링크드)로 풀면 택도 없음. 어레이리스트로 풀어야함
* [입력사항] 
* [출력사항]
*/

public class BOJ_S3_1168_요세푸스문제2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens = null;
    
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine(), " ");
        int N = Integer.parseInt(tokens.nextToken());
        int K = Integer.parseInt(tokens.nextToken());
        
        ArrayList<Integer> circle = new ArrayList<Integer>();
        for(int i = 1; i <= N; i++) {
            circle.add(i);
        }
        
        output.append("<");
        int idx = -1, max = N;
        for(int i = 0; i < N; i++) {
            idx = (idx+K)%max;
            output.append(circle.get(idx)).append(", ");
            circle.remove(idx);
            idx--; max--;
        }
        
        output.replace(output.length()-2, output.length(), ">");

        System.out.println(output);    

    }

}
