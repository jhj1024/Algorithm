import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name 상호의 배틀필드
 * @date 2020.07.29 
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc&categoryId=AV5LyE7KD2ADFAXc&categoryType=CODE
 * @mem
 * @time
 * @caution 시뮬레이션 형태의 문제
 * [고려사항]
 * [입력사항]
 * [출력사항]
 */

/*
	.	평지(전차가 들어갈 수 있다.)
	*	벽돌로 만들어진 벽 (포탄을 맞으면 평지가 된다.)
	#	강철로 만들어진 벽
	-	물(전차는 들어갈 수 없다.)
	^	위쪽을 바라보는 전차(아래는 평지이다.)
	v	아래쪽을 바라보는 전차(아래는 평지이다.)
	<	왼쪽을 바라보는 전차(아래는 평지이다.)
	>	오른쪽을 바라보는 전차(아래는 평지이다.)
	
	U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
	D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
	L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
	R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
	S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 */


public class SWEA_D3_1873_BattleField {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output;

    public static void main(String[] args) throws Exception {
        //input = new BufferedReader(new StringReader(src));
        int T, H, W, N;
        T = Integer.parseInt(input.readLine());

        StringTokenizer tokens = null;
        for (int test_case = 1; test_case <= T; test_case++) {
            // H, W 입력
            tokens = new StringTokenizer(input.readLine(), " ", false);
            H = Integer.parseInt(tokens.nextToken());
            W = Integer.parseInt(tokens.nextToken());

            // map 입력 (입력받으면서 전차의 위치 파악해야함)
            int r = 0, c = 0; // 전차의 좌표
            char[][] map = new char[H][W];
            for (int i = 0; i < H; i++) {
                String row = input.readLine(); // 한 줄을 읽어온 후
                for (int j = 0; j < W; j++) {
                    map[i][j] = row.charAt(j); // 한글자씩 char배열에 저장
                    if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
                        r = i;
                        c = j; // 전차 위치 저장
                    }
                }
            }

            // 명령어 개수 및 명령어 입력
            N = Integer.parseInt(input.readLine()); // 사용자 명령어 개수
            char[] cmdArr = new char[N];
            cmdArr = input.readLine().toCharArray(); // 사용자 명령어 입력받아 한글자씩 char배열에 저장

            /*----------------------알고리즘----------------------*/
            // 전차 위치(r,c)에서부터 명령어에 따라 이동
            for (char cmd : cmdArr) {
                switch (cmd) {
                    case 'U':
                        if (r - 1 >= 0 && map[r - 1][c] == '.') {
                            map[r][c] = '.'; // 전차가 지나간 자리는 당연히 평지
                            map[--r][c] = '^'; // 전차 이동 및 방향 표시
                        } else {
                            map[r][c] = '^'; // 이동할 수 없다면 방향만 표시
                        }
                        break;
                    case 'D':
                        if (r + 1 < H && map[r + 1][c] == '.') {
                            map[r][c] = '.'; // 전차가 지나간 자리는 당연히 평지
                            map[++r][c] = 'v'; // 전차 이동 및 방향 표시
                        } else {
                            map[r][c] = 'v'; // 이동할 수 없다면 방향만 표시
                        }
                        break;
                    case 'L':
                        if (c - 1 >= 0 && map[r][c - 1] == '.') {
                            map[r][c] = '.'; // 전차가 지나간 자리는 당연히 평지
                            map[r][--c] = '<'; // 전차 이동 및 방향 표시
                        } else {
                            map[r][c] = '<'; // 이동할 수 없다면 방향만 표시
                        }
                        break;
                    case 'R':
                        if (c + 1 < W && map[r][c + 1] == '.') {
                            map[r][c] = '.'; // 전차가 지나간 자리는 당연히 평지
                            map[r][++c] = '>'; // 전차 이동 및 방향 표시
                        } else {
                            map[r][c] = '>'; // 이동할 수 없다면 방향만 표시
                        }
                        break;

                    case 'S':
                        switch (map[r][c]) {// 전차의 현재 방향 확인 후 포탄 쏘기
                            case '^':
                                for (int i = r; i >= 0; i--) {
                                    if (map[i][c] == '#')
                                        break; // 강철을 만나면 포탄 소멸
                                    else if (map[i][c] == '*') { // 벽을 만나면 평지로 만들고 포탄 소멸
                                        map[i][c] = '.';
                                        break;
                                    }
                                }
                                break;

                            case 'v':
                                for (int i = r; i < H; i++) {
                                    if (map[i][c] == '#')
                                        break; // 강철을 만나면 포탄 소멸
                                    else if (map[i][c] == '*') { // 벽을 만나면 평지로 만들고 포탄 소멸
                                        map[i][c] = '.';
                                        break;
                                    }
                                }
                                break;

                            case '<':
                                for (int j = c; j >= 0; j--) {
                                    if (map[r][j] == '#')
                                        break; // 강철을 만나면 포탄 소멸
                                    else if (map[r][j] == '*') { // 벽을 만나면 평지로 만들고 포탄 소멸
                                        map[r][j] = '.';
                                        break;
                                    }
                                }
                                break;

                            case '>':
                                for (int j = c; j < W; j++) {
                                    if (map[r][j] == '#')
                                        break; // 강철을 만나면 포탄 소멸
                                    else if (map[r][j] == '*') { // 벽을 만나면 평지로 만들고 포탄 소멸
                                        map[r][j] = '.';
                                        break;
                                    }
                                }
                                break;


                        }
                        break;
                }
            }

            // 맵 출력
            output = new StringBuilder();
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    output.append(map[i][j]);
                }
                output.append('\n');
            }

            System.out.print("#" + test_case + " " + output);
        }
    }
}
