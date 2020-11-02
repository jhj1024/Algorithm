import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S4_10828_스택
* @date 2020.11.03
* @link https://www.acmicpc.net/problem/10828
* 
* [입력사항] 명령의 수 N / N개의 명령
* [출력사항] 명령 결과
* 
* push X: 정수 X를 스택에 넣는 연산이다.
* pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
* size: 스택에 들어있는 정수의 개수를 출력한다.
* empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
* top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
*/

public class BOJ_S4_10828_스택 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int N;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(input.readLine());

        Stack<Integer> st = new Stack<Integer>();
        String cmd;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());

            cmd = tokens.nextToken();
            switch (cmd) {
                case "push":
                    st.push(Integer.parseInt(tokens.nextToken()));
                    break;

                case "pop":
                    if (!st.isEmpty()) {
                        output.append(st.pop()).append("\n");
                    } else {
                        output.append(-1).append("\n");
                    }
                    break;

                case "size":
                    output.append(st.size()).append("\n");
                    break;

                case "empty":
                    output.append(st.isEmpty() == true ? 1 : 0).append("\n");
                    break;

                case "top":
                    if (!st.isEmpty()) {
                        output.append(st.peek()).append("\n");
                    } else {
                        output.append(-1).append("\n");
                    }
                    break;
            }
        }

        System.out.println(output);

    }

}
