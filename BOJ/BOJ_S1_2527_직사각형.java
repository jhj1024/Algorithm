import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_2527_직사각형
* @date 2020.09.24
* @link https://www.acmicpc.net/problem/2527
* 
* [입력사항] (두 직사각형의 각 왼쪽 아래 좌표, 오른쪽 위 좌표)*4
* [출력사항] (두 직사각형의 공통부분의 특성을 코드문자로 출력)*4
* 
* a: 두 직사각형의 겹치는 부분이 직사각형
* b: 선분
* c: 점
* d: 안만나
* 
* 직사각형이 가장 까다로운 조건이기 때문에 bcd를 체크하고 나머지를 a로 처리
* 
*/

public class BOJ_S1_2527_직사각형 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static Point a1, a2, b1, b2;
    static String Answer;
    
    public static void main(String[] args) throws Exception {
       for(int i = 0; i < 4; i++) {
           //입력
           tokens = new StringTokenizer(input.readLine());
           a1 = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
           a2 = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
           b1 = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
           b2 = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));

           //알고리즘
           if((a2.x == b1.x && a2.y == b1.y) || (a1.x == b2.x && a2.y == b1.y) || (a2.x == b1.x && a1.y == b2.y) || (a1.x == b2.x && a1.y == b2.y)){
               Answer = "c";
           }else if((a2.x == b1.x && a2.y != b1.y) || (a1.x == b2.x && a2.y != b1.y) || (a2.x != b1.x && a1.y == b2.y) || (a1.x != b2.x && a1.y == b2.y)) {
               Answer = "b";
           }else if((a2.x < b1.x) || (b2.x < a1.x) || (a2.y < b1.y) || (b2.y < a1.y)) {
               Answer = "d";
           }else {
               Answer = "a";
           }

           output.append(Answer).append("\n");
       }
       System.out.println(output);
    }

}
