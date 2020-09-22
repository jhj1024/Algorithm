import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S5_2578_빙고
* @date 2020.09.21
* @link https://www.acmicpc.net/problem/2578
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 5*5개의 빙고판 정보, 5*5개의 사회자가 부르는 수
* [출력사항] 사회자가 몇 번째 수를 부른 후 철수가 "빙고"를 외치게 되는지 출력
* 
* 빙고여부 체크할 때마다 해당 좌표가 포함된 열, 행에 빙고가 있는지 확인
* (n, n)과 (n, n-m)은 대각선 빙고도 있는지 확인
* 
* 사회자가 부른 번호를 빠르게 찾기 위해 map 사용
* 
*/

public class BOJ_S5_2578_빙고 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int bingo, Answer;
    static boolean[][] board;
    static int[] calling;
    static Map<Integer, Point> map;

    public static void main(String[] args) throws Exception {
        //빙고판 입력
        map = new HashMap<Integer, Point>();
        for (int i = 0; i < 5; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < 5; j++) {
                map.put(Integer.parseInt(tokens.nextToken()), new Point(i, j));
            }
        }

        //부르는 수 입력
        calling = new int[25]; //굳이 2차원을 쓸 필요가? 1차원으로 바꿔 사용
        for (int i = 0; i < 25; i++) {
            if (i % 5 == 0) {
                tokens = new StringTokenizer(input.readLine());
            }
            calling[i] = Integer.parseInt(tokens.nextToken());
        }


        //알고리즘
        bingo = 0;
        board = new boolean[5][5];
        for (int i = 0; i < 25; i++) {
            Point point = map.get(calling[i]);//부른 번호의 좌표 확인
            board[point.x][point.y] = true;//체크
            check(point); //빙고여부 확인            
            if (bingo >= 3) { //빙고가 3개 이상이 되면 끝
                Answer = i + 1; //사회자가 몇 번째 수를 불렀을때인지 저장
                break;
            }
        }

        //출력
        System.out.println(Answer);
    }

    public static void check(Point point) {
        int r = 0, c = 0;
        
        //세로
        for(r = 0; r < 5; r++) {
            if(board[r][point.y] != true) { //아직 체크안된 곳이면
                break; //빙고 안됨
            }
        }
        if(r == 5) { //모두 체크됐다면
            bingo++; //빙고추가
        }

        //가로
        for(c = 0; c < 5; c++) {
            if(board[point.x][c] != true) { //아직 체크안된 곳이면
                break; //빙고 안됨
            }
        }
        if(c == 5) { //모두 체크됐다면
            bingo++; //빙고추가
        }

        //필요한경우 대각선
        if(point.x == point.y) { //(0,0) - (4,4) 대각선
            for(r = 0, c = 0; r < 5; r++, c++) {
                if(board[r][c] != true) { //아직 체크안된 곳이면
                    break; //빙고 안됨
                }
            }
            if(r == 5) { //모두 체크됐다면
                bingo++; //빙고추가
            }
        }
        
        if(point.x + point.y == 4) { //(0,4) - (4,0) 대각선
            for(r = 0, c = 4; r < 5; r++, c--) {
                if(board[r][c] != true) { //아직 체크안된 곳이면
                    break; //빙고 안됨
                }
            }
            if(r == 5) { //모두 체크됐다면
                bingo++; //빙고추가
            }
        }
    }
}
