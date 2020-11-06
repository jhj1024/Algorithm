import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name BOJ_G2_17143_낚시왕
 * @date 2020.11.06
 * @link https://www.acmicpc.net/problem/17143
 * 
 * [입력사항] 격자판의 크기 R, C와 상어의 수 M / M개의 상어 정보(위치 r.c, 속력 s, 방향 d, 크기 z)
 * [출력사항] 낚시왕이 잡은 상어 크기의 합
 * 
 * 낚시왕은 처음에 (0, 0)에 있다. 낚시왕은 (R, C)에 이동하면 이동을 멈춘다. (map 크기를 R+1 * C*1로)
 * 
 * 1초동안 다음과 같은 일이 진행된다.
 * 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
 * 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
 * 3. 상어가 이동한다.
 * 4. 상어가 이동하려고 하는 칸이 격자판의 경계를 넘는 경우에는 방향을 반대로 바꿔서 속력을 유지한채로 이동한다.
 * 
 * 상어는 입력으로 주어진 속도로 이동하고, 속도의 단위는 칸/초이다.
 * 두 상어가 같은 크기를 갖는 경우는 없고, 하나의 칸에 둘 이상의 상어가 있는 경우는 없다.
 * 상어가 이동을 마친 후에 한 칸에 상어가 두 마리 이상 있을 수 있다. 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
 */

public class BOJ_G2_17143_낚시왕 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, N, Answer;
    static int[][] map;
    static Shark[] sharks;

    public static void main(String[] args) throws Exception {
        // 입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());

        int r, c, s, d, z;
        map = new int[R + 1][C + 1];
        sharks = new Shark[N + 1];
        for (int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(input.readLine());
            r = Integer.parseInt(tokens.nextToken());
            c = Integer.parseInt(tokens.nextToken());
            s = Integer.parseInt(tokens.nextToken());
            d = Integer.parseInt(tokens.nextToken());
            z = Integer.parseInt(tokens.nextToken());

            sharks[i] = new Shark(r, c, s, d, z);
            map[r][c] = i;
        }

        // 알고리즘
        Answer = 0;
        for (int i = 1; i <= C; i++) {
            check(i);
            move();
        }

        System.out.println(Answer);
    }

    // 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어 찾기
    public static void check(int c) {
        for (int i = 1; i <= R; i++) {
            if (map[i][c] > 0) {
                Answer += sharks[map[i][c]].z; // 낚시꾼이 잡은 상어의 크기 합 증가

                // 상어 삭제
                sharks[map[i][c]] = null;
                map[i][c] = 0;

                break;
            }
        }
    }

    // 상어 움직이기
    public static void move() {
        int[][] dirs = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        for (int i = 1; i <= N; i++) { // 상어 정보 업데이트
            if (sharks[i] == null) {
                continue; // 잡아먹힌 상어
            }

            Shark shark = sharks[i];

            map[shark.r][shark.c] = 0; //원래 위치 삭제

            int s = shark.s;
            while(true) { //맵을 벗어나지 않고 이동하는 날이 올 때까지
                if (!isIn(shark.r + dirs[shark.d][0]*s, shark.c + dirs[shark.d][1]*s)) { //이렇게 가면 범위를 벗어나요                   
                    switch (shark.d) {
                        case 1:
                            s -= (shark.r - 1); //범위를 벗어나기 직전까지 이동
                            shark.r = 1; //그러면 (1, shark.c)에 위치하게됨
                            shark.d++; //방향 전환
                            break;
                        case 2:
                            s -= (R-shark.r); //범위를 벗어나기 직전까지 이동
                            shark.r = R; //그러면 (R, shark.c)에 위치하게됨
                            shark.d--; //방향 전환
                            break;
                        case 3:
                            s -= (C-shark.c); //범위를 벗어나기 직전까지 이동
                            shark.c = C; //그러면 (shark.r, C)에 위치하게됨
                            shark.d++; //방향 전환
                            break;
                        case 4:
                            s -= (shark.c - 1); //범위를 벗어나기 직전까지 이동
                            shark.c = 1; //그러면 (shark.r, 1)에 위치하게됨
                            shark.d--; //방향 전환
                            break;
                    }
                }else { //드디어 범위를 벗어나지 않고 이동했어요
                    shark.r += dirs[shark.d][0]*s;
                    shark.c += dirs[shark.d][1]*s;
                    break;
                }
            }

            sharks[i] = new Shark(shark.r, shark.c, shark.s, shark.d, shark.z); // 상어 업데이트
        }

        // 한 칸에 여러 상어가 있으면 제일 큰 상어가 나머지 잡아먹기
        for (int cur = 1; cur <= N; cur++) {
            if (sharks[cur] == null) {
                continue; // 잡아먹힌 상어
            }

            if (map[sharks[cur].r][sharks[cur].c] != 0) { // 이미 다른상어가 있다면
                int cmp = map[sharks[cur].r][sharks[cur].c];

                if (sharks[cmp].z < sharks[cur].z) { // 들어오려는 상어가 더 크면
                    // 잡아먹기
                    sharks[cmp] = null;
                    map[sharks[cur].r][sharks[cur].c] = cur;
                } else { // 작으면
                    sharks[cur] = null; //상어 삭제
                }
            } else {
                map[sharks[cur].r][sharks[cur].c] = cur;
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return (1 <= r && r <= R && 1 <= c && c <= C);
    }

    static class Shark {
        int r, c, s, d, z;

        public Shark(int r, int c, int s, int d, int z) {
            super();
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}


