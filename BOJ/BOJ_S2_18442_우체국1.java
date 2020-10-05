import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_18442_우체국1
* @date 2020.10.05
* @link https://www.acmicpc.net/problem/18442
* 
* [입력사항] 마을의 수 V, 지으려고 하는 우체국의 수 P, L / 각 마을의 위치를 나타내는 V개의 정수 좌표
* [출력사항] 각 마을과 거기서 가장 가까운 우체국 사이 거리들의 합의 최소값 / 우체국을 지을 P개의 마을 좌표
* 
* v개에서 p개를 뽑는 조합 생성
* 각 조합마다 거리를 구해서 거리 합의 최솟값 구하기
* 
*/

public class BOJ_S2_18442_우체국1 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int V, P;
    static long L, Answer;
    static long[] town;
    static int[] result;

    public static void main(String[] args) throws Exception {

        tokens = new StringTokenizer(input.readLine());
        V = Integer.parseInt(tokens.nextToken()); //마을의 개수
        P = Integer.parseInt(tokens.nextToken()); //지으려고하는 경찰서의 수
        L = Long.parseLong(tokens.nextToken()); //큰길의 둘레

        //마을의 위치 정보 저장
        town = new long[V];
        tokens = new StringTokenizer(input.readLine());
        for (int i = 0; i < V; i++) {
            town[i] = Long.parseLong(tokens.nextToken());
        }

        // 알고리즘
        Answer = Long.MAX_VALUE; // 최소값을 저장해야하므로 일단 정수의 최댓값으로 초기화
        makeCom(0, 0, new int[P]); //V개의 위치에서 P개를 고르는 조합 실행
        
        //출력
        output.append(Answer).append("\n");        
        for(int r : result) {
           output.append(town[r]).append(" ");
        }
        System.out.println(output);
    }

    public static void makeCom(int n, int start, int[] temp) { //조합 생성
        if (n == P) {
            calc(Arrays.copyOf(temp, temp.length)); //해당 경찰서위치 조합으로 거리 계산
            return;
        }
        for (int i = start; i < V; i++) {
            temp[n] = i;
            makeCom(n + 1, i + 1, temp);
        }
    }

    public static void calc(int[] polices) { //거리 계산
        long min = 0, d = 0, sum = 0;
        for (int i = 0; i < V; i++) {
            min = Long.MAX_VALUE;
            for (int j = 0; j < P; j++) {
                long police = town[polices[j]]; // 거리를 비교할 경찰서의 위치 저장
                d = Long.min(Math.abs(town[i] - police), L - Math.abs(town[i] - police)); //해당 마을과 해당 경찰서의 거리 계산
                min = Long.min(min, d); //해당 마을과 가장 가까운 경찰서의 거리 저장
            }
            sum += min; //각 마을과 가장 가까운 경찰서 거리를 더해줌
        }

        if (Answer > sum) { //전체 조합 중 가장 경찰서 거리의 합이 작은 결과를 저장
            Answer = sum;
            result = Arrays.copyOf(polices, polices.length);
        }
    }
}
