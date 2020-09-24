import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_9205_맥주마시면서걸어가기
* @date 2020.09.24
* @link https://www.acmicpc.net/problem/9205
* 
* [입력사항] 테스트 케이스의 개수 t / 맥주를 파는 편의점의 개수 n / 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표
* [출력사항] 상근이와 친구들이 행복하게 페스티벌에 갈 수 있으면 "happy", 중간에 맥주가 바닥나면 "sad"를 출력
* 
* 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이
* 50미터에 한 병씩 + 박스에 들어있는 맥주는 20병을 넘을 수 없다. >> 두 정점간의 거리가 1000m 이내이면 연결
* 
*/

public class BOJ_S1_9205_맥주마시면서걸어가기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N;
    static Point[] points;
    static int[][] distances;
    static final int INF = 9999999;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());
        for (int t = 0; t < T; t++) {
            //입력
            N = Integer.parseInt(input.readLine());
            points = new Point[N + 2];
            for (int i = 0; i < N + 2; i++) {
                tokens = new StringTokenizer(input.readLine());
                points[i] = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
            }

            //알고리즘
            if (isHappy()) {
                output.append("happy").append("\n");
            } else {
                output.append("sad").append("\n");
            }
        }
        System.out.println(output);
    }

    public static boolean isHappy() {
        //1. 인접 여부 확인
        distances = new int[N + 2][N + 2];
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < N + 2; j++) {
                if (i == j) {
                    distances[i][j] = INF;
                    continue;
                }

                if (getDistance(points[i], points[j]) <= 1000) {
                    distances[i][j] = 1;
                } else {
                    distances[i][j] = INF;
                }
            }
        }

        //2. 플로이드 와샬로 최단 거리 계산
        for (int k = 0; k < N + 2; k++) {
            for (int i = 0; i < N + 2; i++) {
                for (int j = 0; j < N + 2; j++) {
                    if (distances[i][j] > distances[i][k] + distances[k][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }


        //3. 0번 정점(상근이 집)에서 N+1 정점(락 페스티발)이 연결되어있으면 true
        if (distances[0][N + 1] < INF) {
            return true;
        } else {
            return false;
        }

    }

    public static int getDistance(Point start, Point end) {
        return (Math.abs(start.x - end.x) + Math.abs(start.y - end.y));
    }

}
