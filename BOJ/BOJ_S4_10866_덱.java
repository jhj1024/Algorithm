import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S4_10866_덱
* @date 2020.11.03
* @link https://www.acmicpc.net/problem/10866
* 
* [입력사항] 명령의 수 N / N개의 명령
* [출력사항] 명령 결과
* 
* push_front X: 정수 X를 덱의 앞에 넣는다.
* push_back X: 정수 X를 덱의 뒤에 넣는다.
* pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
* pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
* size: 덱에 들어있는 정수의 개수를 출력한다.
* empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
* front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
* back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
*/

public class BOJ_S4_10866_덱 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());

        Deque<Integer> dq = new LinkedList<Integer>();
        String cmd;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            cmd = tokens.nextToken();

            switch (cmd) {
                case "push_front":
                    dq.offerFirst(Integer.parseInt(tokens.nextToken()));
                    break;

                case "push_back":
                    dq.offerLast(Integer.parseInt(tokens.nextToken()));
                    break;

                case "pop_front":
                    if (!dq.isEmpty()) {
                        output.append(dq.pollFirst()).append("\n");
                    } else {
                        output.append(-1).append("\n");
                    }

                    break;

                case "pop_back":
                    if (!dq.isEmpty()) {
                        output.append(dq.pollLast()).append("\n");
                    } else {
                        output.append(-1).append("\n");
                    }
                    break;

                case "size":
                    output.append(dq.size()).append("\n");
                    break;

                case "empty":
                    output.append(dq.isEmpty() == true ? 1 : 0).append("\n");
                    break;

                case "front":
                    if (!dq.isEmpty()) {
                        output.append(dq.peekFirst()).append("\n");
                    } else {
                        output.append(-1).append("\n");
                    }
                    break;

                case "back":
                    if (!dq.isEmpty()) {
                        output.append(dq.peekLast()).append("\n");
                    } else {
                        output.append(-1).append("\n");
                    }
                    break;
            }
        }
        System.out.println(output);
    }

}
