import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D5_1247_최적경로
 * @date 2020.08.27
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD&categoryId=AV15OZ4qAPICFAYD&categoryType=CODE
 * @mem
 * @time
 * @caution
 * [고려사항]
 * [입력사항] 고객의 수 N, 회사의 좌표,집의 좌표, N명의 고객의 좌표
 * [출력사항] 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것
 * 
 * 가장 짧은 경로를 ‘효율적으로’ 찾는 것이 목적이 아니다. 여러분은 모든 가능한 경로를 살펴서 해를 찾아도 좋다
 * -> 순열 쓰자
 */

public class SWEA_D5_1247_최적경로 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, Answer;
    static Point[] points;
    static Point departure, destination;
    static int[] indexs;

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));

        //테스트케이스 수
        T = Integer.parseInt(input.readLine());
        for (int t = 1; t <= T; t++) {
            //입력
            N = Integer.parseInt(input.readLine());
            points = new Point[N];
            indexs = new int[N];
            tokens = new StringTokenizer(input.readLine());
            departure = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())); //시작점 좌표
            destination = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())); //도착점 좌표
            for (int i = 0; i < N; i++) {
                points[i] = new Point(Integer.parseInt(tokens.nextToken()),  Integer.parseInt(tokens.nextToken())); //고객 좌표
                indexs[i] = i; //points배열의 인덱스 저장
            }

            //알고리즘
            Answer = Integer.MAX_VALUE;
            makePermutation(N, new int[N], new boolean[N], 0); //순열만들기
            
            //결과 저장
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }
        //출력
        System.out.println(output);
    }

    public static void makePermutation(int n, int[] temp, boolean[] visit, int sum) { //배열 인덱스 기반으로 순열 생성
        if(sum > Answer) { //최소값과 거리가 멀면 더이상 순열을 만들 가치가 없음
            return;
        }
        
        if (n == 0) { //순열만들기 완료
            sum += (Math.abs(departure.r - points[temp[n]].r) + Math.abs(departure.c - points[temp[n]].c)); //시작점과 거리 계산
            if(sum < Answer) {               
                Answer = sum; //최소값 저장
            }
            return;
        }

        int cal = 0;
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                temp[n - 1] = indexs[i];
                if (n == N) {
                    cal = (Math.abs(destination.r - points[temp[n - 1]].r) + Math.abs(destination.c - points[temp[n - 1]].c)); //도착점과 거리 계산                                     
                } else {
                    cal = (Math.abs(points[temp[n]].r - points[temp[n - 1]].r) + Math.abs(points[temp[n]].c - points[temp[n - 1]].c)); //직전에 넣은 순열요소와 거리 계산
                }
                makePermutation(n - 1, temp, visit, sum + cal); //거리계산의 합을 넣어 재귀
                visit[i] = false;
            }
        }
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static String src = "10\r\n" +
                        "5\r\n" +
                        "0 0 100 100 70 40 30 10 10 5 90 70 50 20\r\n" +
                        "6\r\n" +
                        "88 81 85 80 19 22 31 15 27 29 30 10 20 26 5 14\r\n" +
                        "7\r\n" +
                        "22 47 72 42 61 93 8 31 72 54 0 64 26 71 93 87 84 83\r\n" +
                        "8\r\n" +
                        "30 20 43 14 58 5 91 51 55 87 40 91 14 55 28 80 75 24 74 63\r\n" +
                        "9\r\n" +
                        "3 9 100 100 16 52 18 19 35 67 42 29 47 68 59 38 68 81 80 37 94 92\r\n" +
                        "10\r\n" +
                        "39 9 97 61 35 93 62 64 96 39 36 36 9 59 59 96 61 7 64 43 43 58 1 36\r\n" +
                        "10\r\n" +
                        "26 100 72 2 71 100 29 48 74 51 27 0 58 0 35 2 43 47 50 49 44 100 66 96\r\n"
                        +
                        "10\r\n" +
                        "46 25 16 6 48 82 80 21 49 34 60 25 93 90 26 96 12 100 44 69 28 15 57 63\r\n"
                        +
                        "10\r\n" +
                        "94 83 72 42 43 36 59 44 52 57 34 49 65 79 14 20 41 9 0 39 100 94 53 3\r\n"
                        +
                        "10\r\n" +
                        "32 79 0 0 69 58 100 31 67 67 58 66 83 22 44 24 68 3 76 85 63 87 7 86\r\n" +
                        "";
}

