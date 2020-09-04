import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_16236_아기상어
* @date 2020.09.04
* @link https://www.acmicpc.net/problem/16236
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 공간의 크기 N, N*N 크기의 공간 정보
* [출력사항] 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간
* 
* 초기 아기 상어의 크기: 2
* 1초에 상하좌우로 인접한 한 칸씩 이동
* 자신의 크기보다 같거나 작은 칸만 지나갈 수 있다. 
* 자신의 크기보다 작은 물고기만 먹을 수 있다. 
* 
* 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다. 
* 거리가 가장 가까운 물고기 -> 가장 위에 있는 물고기 -> 가장 왼쪽에 있는 물고기 순으로 먹는다.
* 거리 = 지나야하는 칸의 개수의 최솟값.
* 
* 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다. 
* 
* 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있다.
* 0: 빈 칸
* 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
* 9: 아기 상어의 위치
* 
* bfs로 level마다 상하좌우 탐색
* 해당 level에 물고기 찾으면 = 찾은 물고기 수를 보고 조건에 따라 먹을 물고기 선택
* 먹을때마다 cnt++해주고, 아기상어 크기와 같아지면 size++ 후 cnt는 0으로 초기화.
* 
*/

public class BOJ_G5_16236_아기상어 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int N, M, size, cnt, Answer;
    static int[][] map, visited;
    static int[] fishNum;
    static Point babyShark;

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));

        //입력
        N = Integer.parseInt(input.readLine());
        map = new int[N][N];
        fishNum = new int[7];
        M = 0;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == 9) {
                    babyShark = new Point(i, j); //아기상어 위치 저장
                } else if (map[i][j] > 0) {
                    fishNum[map[i][j]]++; //각 크기별 물고기 수 카운트
                    M++; //전체 물고기 수 카운트
                }
            }
        }

        //알고리즘
        cnt = 0;
        size = 2;
        Answer = 0;
        if (M > 0) {
            bfs();
        }

        System.out.println(Answer);
    }

    public static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        PriorityQueue<Point> fishes = new PriorityQueue<Point>();
        copyMap(); //visited 배열 초기화
        int qsize, level = 0, fcnt = 0;

        queue.offer(babyShark);
        visited[babyShark.r][babyShark.c] = -1; //방문처리

        outer: while (!queue.isEmpty()) {
            qsize = queue.size();
            while (--qsize >= 0) {
                Point p = queue.poll();
                for (int d = 0; d < dirs.length; d++) {
                    int dr = p.r + dirs[d][0];
                    int dc = p.c + dirs[d][1];

                    if (isIn(dr, dc) && visited[dr][dc] != -1) {
                        if (visited[dr][dc] == 0 || visited[dr][dc] == size) { //빈 공간이거나 크기가 같으면 queue에 넣고
                            queue.offer(new Point(dr, dc));
                            visited[dr][dc] = -1;
                        } else if (visited[dr][dc] < size) { //크기보다 작은 물고기면 fishes에 넣기
                            fishes.offer(new Point(dr, dc));
                            visited[dr][dc] = -1;
                        }
                    }
                }
            }

            if (queue.size() == 0 && fishes.size() == 0) { //이동할 수 없을 때
                break outer;
            }

            level++; //1초가 지남

            if (fishes.size() > 0) { //물고기 찾음    
                fishNum[map[fishes.peek().r][fishes.peek().c]]--; //물고기 수 줄이기

                if (++cnt == size) { //크기를 키울 때가 되면
                    size++; //물고기 크기 키우기
                    cnt = 0; //먹은 물고기 수 초기화
                }

                Answer += level; //해당 물고기 까지 가는데 걸린 시간 더함

                fcnt = 0;
                for (int i = 1; i < fishNum.length; i++) {
                    if (i == size) {
                        break;
                    }
                    fcnt += fishNum[i]; //먹을 수 있는 물고기 카운트
                }
                if (fcnt == 0) { //먹을 수 있는 물고기가 없으면
                    break outer;
                }

                map[babyShark.r][babyShark.c] = 0; //아기상어 이동할 예정
                babyShark = fishes.poll(); //아기상어 위치는 먹을 물고기 위치로 이동
                map[babyShark.r][babyShark.c] = 9; //아기상어 이동

                queue = new LinkedList<>(); //큐 비우기
                fishes = new PriorityQueue<Point>(); //물고기큐도 비우기
                copyMap(); //visited 배열 초기화  
                queue.offer(babyShark); //다시 아기상어 위치 넣기 
                visited[babyShark.r][babyShark.c] = -1; //방문처리
                level = 0;
            }

        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < N);
    }

    public static void copyMap() {
        visited = new int[N][];
        for (int i = 0; i < N; i++) {
            visited[i] = Arrays.copyOf(map[i], N);
        }
    }

    static class Point implements Comparable<Point> {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Point o) {
            if (this.r == o.r) {
                return (Integer.compare(this.c, o.c)); //행이 같으면 열 오름차순
            } else {
                return (Integer.compare(this.r, o.r)); //행 오름차순
            }

        }
    }

    static String src =           
            "3\r\n" +
                "9 2 2\r\n" +
                "2 2 3\r\n" +
                "1 3 1"; //2   
    
//                "2\r\n" + 
//                "9 3\r\n" + 
//                "3 1"; //0
//                
//                "4\r\n" + 
//                "1 0 0 0\r\n" + 
//                "0 9 0 0\r\n" + 
//                "0 0 1 0\r\n" + 
//                "0 0 0 1"; //8
//                
//                "6\r\n" + 
//                "1 0 0 0 0 0\r\n" + 
//                "6 6 6 6 6 0\r\n" + 
//                "1 0 6 0 9 0\r\n" + 
//                "2 0 6 0 6 6\r\n" + 
//                "6 0 0 0 6 6\r\n" + 
//                "6 6 6 6 6 6"; //25
//                
//                "20\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 2 3 3 4 4\r\n" + 
//                "0 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
//                "6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 0\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
//                "0 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
//                "6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 0\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
//                "9 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
//                "6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 0\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
//                "0 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
//                "6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 0\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\r\n" + 
//                "0 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6\r\n" + 
//                "0 0 0 0 0 0 0 0 0 0 0 0 0 1 2 2 3 3 4 4\r\n" + 
//                "6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 6 4"; //913
//                
//                "3\r\n" + 
//                "0 0 0\r\n" + 
//                "0 0 0\r\n" + 
//                "0 9 0"; //0
//                
//                "3\r\n" +
//                "0 0 1\r\n" +
//                "0 0 0\r\n" +
//                "0 9 0"; //3
//                
//                "4\r\n" +
//                "4 3 2 1\r\n" +
//                "0 0 0 0\r\n" +
//                "0 0 9 0\r\n" +
//                "1 2 3 4"; //14
//                
//                "6\r\n" + 
//                "5 4 3 2 3 4\r\n" + 
//                "4 3 2 3 4 5\r\n" + 
//                "3 2 9 5 6 6\r\n" + 
//                "2 1 2 3 4 5\r\n" + 
//                "3 2 1 6 5 4\r\n" + 
//                "6 6 6 6 6 6"; // 60
//                
//                "6\r\n" + 
//                "6 0 6 0 6 1\r\n" + 
//                "0 0 0 0 0 2\r\n" + 
//                "2 3 4 5 6 6\r\n" + 
//                "0 0 0 0 0 2\r\n" + 
//                "0 2 0 0 0 0\r\n" + 
//                "3 9 3 0 0 1"; //48
//                
//                "6\r\n" + 
//                "1 1 1 1 1 1\r\n" + 
//                "2 2 6 2 2 3\r\n" + 
//                "2 2 5 2 2 3\r\n" + 
//                "2 2 2 4 6 3\r\n" + 
//                "0 0 0 0 0 6\r\n" + 
//                "0 0 0 0 0 9"; //39

}
