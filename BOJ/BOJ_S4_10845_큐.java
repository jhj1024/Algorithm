import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S4_10845_큐
* @date 2020.11.03
* @link https://www.acmicpc.net/problem/10845
* 
* [입력사항] 명령의 수 N / N개의 명령
* [출력사항] 명령 결과
* 
* push X: 정수 X를 큐에 넣는 연산이다.
* pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
* size: 큐에 들어있는 정수의 개수를 출력한다.
* empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
* front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
* back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
*/

public class BOJ_S4_10845_큐 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());

        Deque<Integer> q = new LinkedList<Integer>();
        String cmd;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            cmd = tokens.nextToken();

            switch (cmd) {
                case "push":
                    q.offer(Integer.parseInt(tokens.nextToken()));
                    break;

                case "pop":
                    if (!q.isEmpty()) {
                        output.append(q.poll()).append("\n");
                    } else {
                        output.append(-1).append("\n");
                    }
                    break;

                case "size":
                    output.append(q.size()).append("\n");
                    break;

                case "empty":
                    output.append(q.isEmpty() == true ? 1 : 0).append("\n");
                    break;

                case "front":
                    if (!q.isEmpty()) {
                        output.append(q.peekFirst()).append("\n");
                    } else {
                        output.append(-1).append("\n");
                    }
                    break;

                case "back":
                    if (!q.isEmpty()) {
                        output.append(q.peekLast()).append("\n");
                    } else {
                        output.append(-1).append("\n");
                    }
                    break;
            }
        }

        System.out.println(output);
    }
}
