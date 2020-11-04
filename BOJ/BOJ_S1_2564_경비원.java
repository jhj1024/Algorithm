import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_2564_경비원
* @date 2020.11.03
* @link https://www.acmicpc.net/problem/2564
* 
* [입력사항] 블록 크기 R, C / 상점의 개수 N / N개의 상점정보(상점이 위치한 방향, 블록 경계로부터의 거리) / 동근이의 위치(상점이 위치한 방향, 블록 경계로부터의 거리)
* [출력사항] 동근이의 위치와 각 상점 사이의 최단 거리의 합
* 
* 1은 블록의 북쪽, 2는 블록의 남쪽, 3은 블록의 서쪽, 4는 블록의 동쪽에 상점이 있음을 의미한다.
* 상점이 블록의 북쪽 또는 남쪽에 위치한 경우 블록의 왼쪽 경계로부터의 거리를 나타내고, 상점이 블록의 동쪽 또는 서쪽에 위치한 경우 블록의 위쪽 경계로부터의 거리를 나타낸다.
* 
* 
*/

public class BOJ_S1_2564_경비원 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, N, len, Answer;
    static Point guard;

    public static void main(String[] args) throws Exception {
        //입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        N = Integer.parseInt(input.readLine());
        Point[] stores = new Point[N];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            stores[i] = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
        }

        tokens = new StringTokenizer(input.readLine());
        guard = new Point(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));

        //알고리즘
        Answer = 0;
        len = 2 * (R + C);
        for (int i = 0; i < N; i++) {
            Answer += calc(stores[i]);
        }

        //출력
        System.out.println(Answer);

    }

    public static int calc(Point store) { //상점과 경비원 사이의 최단거리 계산
        int distance = 0;

        //방향 같음
        if (guard.dir == store.dir) {
            return Math.abs(guard.dist - store.dist);
        }

        //동서 또는 서동
        if ((guard.dir == 4 && store.dir == 3) || (guard.dir == 3 && store.dir == 4)) {
            return (Math.min(guard.dist + store.dist + R, len - (guard.dist + store.dist + R)));
        }

        //북남 또는 남북  
        if ((guard.dir == 1 && store.dir == 2) || (guard.dir == 2 && store.dir == 1)) {
            return (Math.min(guard.dist + store.dist + C, len - (guard.dist + store.dist + C)));
        }
        
        //서북 또는 북서
        if (guard.dir == 3 && store.dir == 1 || guard.dir == 1 && store.dir == 3) {
            return Math.min(guard.dist + store.dist, len -(guard.dist + store.dist));
        }

        //동남
        if (guard.dir == 4 && store.dir == 2) {
            return Math.min((C - guard.dist) + (R - store.dist), len - ((C - guard.dist) + (R - store.dist)));      
        }
        
        //남동
        if (guard.dir == 2 && store.dir == 4) {
            return Math.min((R - guard.dist) + (C - store.dist), len - ((R - guard.dist) + (C - store.dist)));  
        }

        //동북
        if (guard.dir == 4 && store.dir == 1) {
            return Math.min(guard.dist + (R-store.dist), len - (guard.dist + (R-store.dist)));
        }
        
        //북동
        if (guard.dir == 1 && store.dir == 4) {
            return Math.min((R-guard.dist) + store.dist, len - ((R-guard.dist) + store.dist));
        }
        

        //서남
        if (guard.dir == 3 && store.dir == 2) {
            return Math.min((C-guard.dist) + store.dist, len-((C-guard.dist) + store.dist));
        }
        
        //남서
        if(guard.dir == 2 && store.dir == 3) {
            return Math.min((C-store.dist) + guard.dist, len-((C-store.dist) + guard.dist));
        }

        return distance;
    }



    static class Point {
        int dir, dist;

        public Point(int dir, int dist) {
            super();
            this.dir = dir;
            this.dist = dist;
        }
    }
}
