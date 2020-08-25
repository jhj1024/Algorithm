import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
* @author JUNG
* @name BOJ_S1_1992_쿼드트리
* @date 2020.08.25
* @link https://www.acmicpc.net/problem/1992
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class BOJ_S1_1992_쿼드트리 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static int N;
    static int[][] data;
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        N = Integer.parseInt(input.readLine());
        data = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            String line = input.readLine();
            for(int j = 0; j < N; j++) {
                data[i][j] = line.charAt(j) - '0';
            }
        }
        
        quadTree(0, 0, N);
        System.out.println(output);       
    }
    
    static void quadTree(int r, int c, int size) {
        int bit = data[r][c]; //모든 데이터가 bit와 같으면 갠잖
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                int x = r + i, y = c + j;
                if(data[x][y] != bit) { //틀린 순간 4등분                   
                    output.append("(");
                    quadTree(r, c, size/2);//1사분면
                    quadTree(r, c + size/2, size/2);//2사분면
                    quadTree(r + size/2, c, size/2);//3사분면
                    quadTree(r + size/2, c + size/2, size/2);//4사분면
                    output.append(")");
                    return;
                }
            }
        }
        output.append(bit);       
    }
    
    static String src = "8\r\n" + 
            "11110000\r\n" + 
            "11110000\r\n" + 
            "00011100\r\n" + 
            "00011100\r\n" + 
            "11110000\r\n" + 
            "11110000\r\n" + 
            "11110011\r\n" + 
            "11110011";
}

