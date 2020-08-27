import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D0_2115_벌꿀채취
 * @date 2020.08.27
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu&categoryId=AV5V4A46AdIDFAWu&categoryType=CODE
 * @mem
 * @time
 * @caution
 * [고려사항]
 * [입력사항] 테스트 케이스의 개수 T, 벌통들의 크기 N, 선택할 수 있는 벌통의 개수 M, 꿀을 채취할 수 있는 최대 양 C, N*N개의 벌통 정보
 * [출력사항] 두 일꾼이 꿀을 채취하여 얻을 수 있는 최대 수익
 * 
 * 두 명의 일꾼은 각각 가로로 연속되도록 M개의 벌통을 선택하고, 꿀을 채취한다. (벌통은 서로 겹치면 안된다.)
 * 두 일꾼이 채취할 수 있는 꿀의 최대 양은 C이며, 하나의 벌통에서 꿀을 채취할 때, 일부분만 채취할 수 없고 벌통에 있는 모든 꿀을 한번에 채취해야 한다.
 * 각 용기에 있는 꿀의 양의 제곱만큼의 수익이 발생한다.
 * 
 */

public class SWEA_D0_2115_벌꿀채취 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens = null;
    static int T, N, M, C, Answer;
    static int[][] map;
    static List<Beehive> Beehives;

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new FileReader("벌꿀채취_input.txt"));

        //테스트케이스 수
        T = Integer.parseInt(input.readLine());
        for (int t = 1; t <= T; t++) {
            //입력
            tokens = new StringTokenizer(input.readLine());
            N = Integer.parseInt(tokens.nextToken());
            M = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            //알고리즘         
            Beehives = new ArrayList<>();
            Answer = Integer.MIN_VALUE;
            select(); //경우의 수 생성 
            check(); //가장 큰 수익을 얻는 경우 확인

            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }
        System.out.println(output);
    }

    public static void select() { //N*N 배열에서 연속으로 M개씩 뽑는 경우의 수를 만들어서 어레이에 저장
        int[] cols, honeys;
        int r = 0, honey = 0, profit = 0; //꿀양의 합이 C를 넘는지 안넘는지 확인하기위해 honey 사용

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - M + 1; j++) { //윈도우 한칸씩 오른쪽으로 이동
                cols = new int[M];
                honeys = new int[M];
                honey = 0;
                profit = 0;
                for (int k = j; k < j + M; k++) { //M개의 좌표 저장
                    r = i;
                    cols[k - j] = k;
                    honeys[k - j] = map[i][k];
                    honey += honeys[k - j];
                    profit += honeys[k - j] * honeys[k - j];
                }
                //넘치면 뭘 골라서 넣을지 선택하고 수익 저장하기
                if (honey > C && (profit = select(honeys)) != Integer.MIN_VALUE) {
                    Beehives.add(new Beehive(cols, r, profit)); //모든 부분집합의 수익을 구하고, C를 안넘으면서 수익이 가장큰 놈 선택                       
                } else {
                    Beehives.add(new Beehive(cols, r, profit));
                }
            }
        }
    }

    private static int select(int[] honeys) { //M개의 벌통에서 총합 C 이하를 만족하는 벌통들 선택하기
        int max = Integer.MIN_VALUE;
        //부분집합 구하기    
        int honey = 0, profit = 0;
        outer: for (int i = 1; i < (1 << M) - 1; i++) {
            honey = 0;
            profit = 0;
            for (int j = 0; j < M; j++) {
                if ((i & (1 << j)) <= 0) {
                    honey += honeys[j];
                    if (honey > C) {
                        continue outer; //해당 부분집합은 max와 비교도 하지 않고 넘어감
                    }
                    profit += honeys[j] * honeys[j];
                }
            }
            max = Math.max(max, profit);
        }
        return max;
    }

    private static void check() { //가장 큰 두개를 꺼내 겹치지 않는지 확인(행이 다르면 패스, 같으면 열이 겹치지 않는지 확인)
        Collections.sort(Beehives); //수익 내림차순 정렬
        
        for(int i = 0; i < Beehives.size()-1; i++) {
            for(int j = 1; j < Beehives.size(); j++) {
                Beehive bh1 = Beehives.get(i);
                Beehive bh2 = Beehives.get(j);
                
                if(bh1.r != bh2.r) { //행이 다르면 안겹침
                    Answer = Math.max(Answer, bh1.profit + bh2.profit);
                }else { //열이 겹치지 않는지 확인 
                    if(bh1.cols[M-1] <  bh2.cols[0] || bh2.cols[M-1] <  bh1.cols[0]) {
                        Answer = Math.max(Answer, bh1.profit + bh2.profit);
                    }
                }   
            }
        }
    }

    static class Beehive implements Comparable<Beehive> {
        int[] cols; //가로로 연속된 벌통이기 때문에 열 정보는 M개 저장
        int r, profit; //벌통의 행 정보, 수익 

        public Beehive(int[] cols, int r, int profit) {
            this.cols = cols;
            this.r = r;
            this.profit = profit;
        }
        
        @Override
        public int compareTo(Beehive o) {
            return -(Integer.compare(this.profit, o.profit)); //수익 내림차순
        }
    }
}
