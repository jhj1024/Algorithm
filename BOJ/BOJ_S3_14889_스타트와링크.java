import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S3_14889_스타트와링크
* @date 2020.09.01
* @link https://www.acmicpc.net/problem/14889
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 축구를 하기 위해 모인 사람 수 N, N*N의  i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치
* [출력사항] N/2명씩 두개의 팀을 만들 때,두 팀의 능력치의 차이의 최솟값
* 
* N개에서 N/2개를 뽑는 조합 생성
* 조합의 짝을 찾아 각 조합의 능력치 합의 차이의 최소값 찾기
* 
* 
*/

public class BOJ_S3_14889_스타트와링크 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, Answer;
    static int[][] stat;
    static List<int[]> teams;
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        N = Integer.parseInt(input.readLine());
        stat = new int[N][N];
        for(int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j = 0; j < N; j++) {
                stat[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        
        
        //알고리즘
        Answer = Integer.MAX_VALUE;
        teams = new ArrayList<int[]>();
        makeCombination(N/2, 0, new int[N/2]); //N/2개를 뽑는 조합 생성
               
        //데칼코마니처럼 팀을 짝지어서 능력치 최소값 구하기
        for(int i = 0; i < teams.size()/2; i++) {
            Answer = Math.min(Answer, Math.abs(calc(teams.get(i)) - calc(teams.get(teams.size()-i-1))));
        }
        
        System.out.println(Answer);
    }
    
    public static void makeCombination(int n, int start, int[] temp) {
        if(n == 0) {
            teams.add(Arrays.copyOf(temp, N/2)); //copyOf를 해줘야 copy by value로 제대로 들어감
            return;
        }
        
        for(int i = start; i < N; i++) {
            temp[n-1] = i;
            makeCombination(n-1, i+1, temp);
        }      
    }
    
    public static int calc(int[] team) {
        int sum = 0;
        
        for(int i = 0; i < N/2-1; i++) {
            for(int j = i+1; j < N/2; j++) {
                sum += (stat[team[i]][team[j]] + stat[team[j]][team[i]]);
            }
        }
        
        return sum;
    }

    static String src = 
            "4\r\n" + 
            "0 1 2 3\r\n" + 
            "4 0 5 6\r\n" + 
            "7 1 0 2\r\n" + 
            "3 4 5 0"; //0
    
//            "6\r\n" + 
//            "0 1 2 3 4 5\r\n" + 
//            "1 0 2 3 4 5\r\n" + 
//            "1 2 0 3 4 5\r\n" + 
//            "1 2 3 0 4 5\r\n" + 
//            "1 2 3 4 0 5\r\n" + 
//            "1 2 3 4 5 0"; //2
            
//            "8\r\n" + 
//            "0 5 4 5 4 5 4 5\r\n" + 
//            "4 0 5 1 2 3 4 5\r\n" + 
//            "9 8 0 1 2 3 1 2\r\n" + 
//            "9 9 9 0 9 9 9 9\r\n" + 
//            "1 1 1 1 0 1 1 1\r\n" + 
//            "8 7 6 5 4 0 3 2\r\n" + 
//            "9 1 9 1 9 1 0 9\r\n" + 
//            "6 5 4 3 2 1 9 0"; //1
            
    

}
