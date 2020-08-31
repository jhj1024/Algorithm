import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B1_18868_멀티버스1
* @date 2020.08.31
* @link https://www.acmicpc.net/problem/18868
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 우주의 개수 M과 각 우주에 있는 행성의 개수 N, M*N개의 행성 크기 정보
* [출력사항] 균등한 우주의 쌍의 개수
* 
* 
* 균등한 우주를 찾기위해 하나의 우주에 대한 행성 크기 조건을 문자열로 만들어서 맵에 저장했다.
* 균등한 우주가 k개일 때, 균등한 우주의 쌍은 kC2=(k*(k-1))/2)개이다.
* 
*/

public class BOJ_B1_18868_멀티버스1 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int M, N, Answer;
    static int[][] students;
    static Map<String, Integer> points;
    
    public static void main(String[] args) throws Exception {
        //입력
        tokens = new StringTokenizer(input.readLine(), " ");
        M = Integer.parseInt(tokens.nextToken()); //우주의 개수
        N = Integer.parseInt(tokens.nextToken()); //행성 수
        
        students = new int[M][N]; //M개의 우주 * N개의 행성
        for(int i = 0; i < M; i++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            for(int j = 0; j < N; j++) {
                students[i][j] = Integer.parseInt(tokens.nextToken()); //각 우주의 행성 크기 정보 저장
            }
        }
        
        //알고리즘
        points = new HashMap<String, Integer>(); //크기분포구성과 우주의 수를 저장할 맵 생성
        Answer = 0;                
        StringBuilder s = null;
        for(int m = 0; m < M; m++) { //각 우주마다 반복
            s = new StringBuilder();
            
            //i번째 행성과 j번째 행성의 크기 비교
            for(int i = 0; i < N-1; i++) {
                for(int j = i+1; j < N; j++) {
                    if(students[m][i] > students[m][j]) { //i번째가 크면
                        s.append("1");
                    }else if(students[m][i] == students[m][j]) { //같으면
                        s.append("2");
                    }else { //작으면 
                        s.append("3");
                    }
                }
            }           
            
            if(points.containsKey(s.toString())) { //이미 해당 크기분포를 가진 우주가 이미 있으면
                points.put(s.toString(), points.get(s.toString())+1); //우주 개수 증가시키고 업데이트
            }else { //해당 크기분포를 가진 우주가 없으면
                points.put(s.toString(), 1); //크기분포와 우주 개수 추가
            }           
        }
        
        for(String key : points.keySet()) {
            if(points.get(key) > 1) { //균등한 우주가 여러개 발견됐으면
                Answer += ((points.get(key)*(points.get(key)-1))/2); //그 우주들 중 2개를 고르는 조합의 수를 저장
            }
        }
        
        System.out.println(Answer); //정답 출력
    }

}
