import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S4_2960_에라토스테네스의체
* @date 2020.10.22
* @link https://www.acmicpc.net/problem/2960
* 
* [입력사항] 정수 N과 K (1 ≤ K < N, max(2, K) < N ≤ 1000)
* [출력사항] 에라토스테네스의 체에서 K번째로 지워진 수
* 
* 에라토스테네스의 체
* 2부터 N까지 모든 정수를 적는다.
* 아직 지우지 않은 수 중 가장 작은 수를 찾는다. 이것을 P라고 하고, 이 수는 소수이다.
* P를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
* 아직 모든 수를 지우지 않았다면, 다시 2번 단계로 간다.
* 
*/

public class BOJ_S4_2960_에라토스테네스의체 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, K, cnt, Answer;

    public static void main(String[] args) throws Exception {
        //입력
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        //알고리즘
        cnt = 0;
        boolean[] sieve = new boolean[N + 1];
        outer: for (int i = 2; i <= N; i++) {
            if (sieve[i] == true) {
                continue; //이미 걸러진 수
            }

            for (int j = 1; i * j <= N; j++) {
                if (sieve[i * j] == false) {
                    sieve[i * j] = true;
                    if (++cnt == K) {
                        Answer = i * j;
                        break outer;
                    }
                }
            }
        }

        //출력
        System.out.println(Answer);
    }

}
