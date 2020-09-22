import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name public class BOJ_B2_2605_줄세우기 {
* @date 2020.09.21
* @link https://www.acmicpc.net/problem/2605
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 학생의 수 N / N명의 학생이 차례대로 뽑은 번호
* [출력사항] 학생들이 최종적으로 줄을 선 순서
* 
* 0이면 그대로, 1이면 한칸 앞...자기번호-1까지 뽑을 수 있음
* 
*/

public class BOJ_B2_2605_줄세우기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int N;
    static int[] students;
    static List<Integer> order;
    
    public static void main(String[] args) throws Exception{
        //입력
        N = Integer.parseInt(input.readLine());
        
        students = new int[N];
        tokens = new StringTokenizer(input.readLine(), " ");
        for(int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(tokens.nextToken());
        }
        
        //알고리즘
        order = new LinkedList<Integer>();
        for(int i = 0; i < N; i++) {
            int n = students[i]; //학생이 뽑은 번호
            
            if(n == 0){//0이면 뒤에 붙이기
                order.add(i);
            }else {//그 외의 숫자면 i-n에 붙이기
                order.add(i-n, i);
            }           
        } 
        
        //출력(학생번호가 1부터 시작하므로 1씩 더해주기 
        for(int i = 0; i < N; i++) {
            output.append(order.get(i)+1).append(" ");
        }        
        
        System.out.println(output);

    }
}
