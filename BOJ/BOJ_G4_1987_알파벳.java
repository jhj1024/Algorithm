import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_1987_알파벳
* @date 2020.08.27
* @link https://www.acmicpc.net/problem/1987
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 보드의 크기 R, C /  R * C 크기의 보드 정보
* [출력사항] 알파벳이 중복되지 않고 지날 수 있는 최대의 칸 수
*/

public class BOJ_G4_1987_알파벳 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, Answer;
    static int[][] map;
    static boolean[] aVisited;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};    

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        
        map = new int[R][C];
        for(int i = 0; i < R; i++) {
            String line = input.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j) - 'A'; //A~Z를 0~25로 변환하여 저장
            }
        }
        
        //알고리즘
        Answer = Integer.MIN_VALUE;
        aVisited = new boolean[26]; //0~25니까 26개
        
        aVisited[map[0][0]] = true; //시작점 알파벳 방문처리
        dfs(0, 0, 1); //시작점도 카운트에 포함
        System.out.println(Answer);
    }

    private static void dfs(int r, int c, int cnt) {
        for(int d = 0; d < dirs.length; d++) {
            int dr = r + dirs[d][0];
            int dc = c + dirs[d][1];
                       
            if(isIn(dr, dc) && !aVisited[map[dr][dc]]) {          
                aVisited[map[dr][dc]] = true; //알파벳 방문처리
                dfs(dr, dc, cnt+1);
                aVisited[map[dr][dc]] = false; //이 알파벳을 지나는 루트는 끝났으니 다시 방문 해제
            }            
        }   
       Answer = Math.max(Answer, cnt);
    }
    
    private static boolean isIn(int r, int c) {
        return (0 <= r && r < R && 0 <= c && c < C);
    }
    
    static String src =
            "5 5\r\n" + 
            "IEFCJ\r\n" + 
            "FHFKC\r\n" + 
            "FFALF\r\n" + 
            "HFGCF\r\n" + 
            "HMCHH"; //10
    
//            "20 20\r\n" + 
//            "AYXWVUTSRQPONMLKJIHG\r\n" + 
//            "YXWVUTSRQPONMLKJIHGF\r\n" + 
//            "XWVUTSRQPONMLKJIHGFE\r\n" + 
//            "WVUTSRQPONMLKJIHGFED\r\n" + 
//            "VUTSRQPONMLKJIHGFEDC\r\n" + 
//            "UTSRQPONMLKJIHGFEDCB\r\n" + 
//            "TSRQPONMLKJIHGFEDCBA\r\n" + 
//            "SRQPONMLKJIHGFEDCBAA\r\n" + 
//            "RQPONMLKJIHGFEDCBAAA\r\n" + 
//            "QPONMLKJIHGFEDCBAAAA\r\n" + 
//            "PONMLKJIHGFEDCBAAAAA\r\n" + 
//            "ONMLKJIHGFEDCBAAAAAA\r\n" + 
//            "NMLKJIHGFEDCBAAAAAAA\r\n" + 
//            "MLKJIHGFEDCBAAAAAAAA\r\n" + 
//            "LKJIHGFEDCBAAAAAAAAA\r\n" + 
//            "KJIHGFEDCBAAAAAAAAAA\r\n" + 
//            "JIHGFEDCBAAAAAAAAAAA\r\n" + 
//            "IHGFEDCBAAAAAAAAAAAA\r\n" + 
//            "HGFEDCBAAAAAAAAAAAAA\r\n" + 
//            "GFEDCBAAAAAAAAAAAAAZ"; //25
            
//            "2 3\r\n" + 
//            "ABC\r\n" + 
//            "BAA\r\n"; //3

//            "3 2\r\n" + 
//            "AB\r\n" + 
//            "BA\r\n" + 
//            "CA\r\n"; //3

//            "2 3\r\n" + 
//            "ADE\r\n" + 
//            "BCA"; //5
            
//            "3 4\r\n" + 
//            "ABCD\r\n" + 
//            "BCDA\r\n" + 
//            "CDEF"; //6
            
//            "20 20\r\n" + 
//            "ABCDEFGHIJKLMNOPQRST\r\n" + 
//            "BCDEFGHIJKLMNOPQRSTU\r\n" + 
//            "CDEFGHIJKLMNOPQRSTUV\r\n" + 
//            "DEFGHIJKLMNOPQRSTUVW\r\n" + 
//            "EFGHIJKLMNOPQRSTUVWX\r\n" + 
//            "FGHIJKLMNOPQRSTUVWXY\r\n" + 
//            "GHIJKLMNOPQRSTUVWXYA\r\n" + 
//            "HIJKLMNOPQRSTUVWXYAA\r\n" + 
//            "IJKLMNOPQRSTUVWXYAAA\r\n" + 
//            "JKLMNOPQRSTUVWXYAAAA\r\n" + 
//            "KLMNOPQRSTUVWXYAAAAA\r\n" + 
//            "LMNOPQRSTUVWXYAAAAAA\r\n" + 
//            "MNOPQRSTUVWXYAAAAAAA\r\n" + 
//            "NOPQRSTUVWXYAAAAAAAA\r\n" + 
//            "OPQRSTUVWXYAAAAAAAAA\r\n" + 
//            "PQRSTUVWXYAAAAAAAAAA\r\n" + 
//            "QRSTUVWXYAAAAAAAAAAA\r\n" + 
//            "RSTUVWXYAAAAAAAAAAAA\r\n" + 
//            "STUVWXYAAAAAAAAAAAAA\r\n" + 
//            "TUVWXYZAAAAAAAAAAAAA"; //26
}
