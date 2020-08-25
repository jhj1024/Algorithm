import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
* @author JUNG
* @name BOJ_S1_1074_Z
* @date 2020.08.25
* @link https://www.acmicpc.net/problem/1074
* @mem
* @time
* @caution
* [고려사항] 분할 정복
* [입력사항]
* [출력사항]
*/

public class BOJ_S1_1074_Z {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, r, c, Answer = 0;
    
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        r = Integer.parseInt(tokens.nextToken()); 
        c = Integer.parseInt(tokens.nextToken());
        
        
        search(); //반복문, 시간 76ms
        Answer = 0;
        search(0, 0, 1 << N); //재귀, 시간 3652ms     
    }
    
    static void search() {
        int x = 0, y = 0, size = 1 << N;
        while(size > 0) {
            size /= 2;
 
            // 2사분면 (왼 위)
            if(r < x+size && c < y+size) {
                Answer += size * size * 0;
            }
            // 1사분면 (오른 위)
            else if(r < x+size) {
                Answer += size * size * 1;
                y += size;
            }
            // 3사분면 (왼 아래)
            else if(c < y+size) {
                Answer += size * size * 2;
                x += size;
            }
            // 4사분면 (오른 아래)
            else {
                Answer += size * size * 3;
                x += size;
                y += size;
            }
 
            if(size == 1) {
                System.out.println(Answer);
                break;
            }
        }
    }
    
    static void search(int x, int y, int size) {
        if(size == 1) {
            if(x == r && y == c) {
                System.out.println(Answer);
            }               
            Answer++;
            return;
        }
 
        int s = size/2;
 
        search(x, y, s);
        search(x, y+s, s);
        search(x+s, y, s);
        search(x+s, y+s, s);
    }

}
