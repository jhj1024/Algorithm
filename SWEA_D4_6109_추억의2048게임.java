package com.ssafy.d1028;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D4_6109_추억의2048게임
 * @date 2020.10.28
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWbrg9uabZsDFAWQ&categoryId=AWbrg9uabZsDFAWQ&categoryType=CODE
 * 
 * [입력사항] 테스트게이스 개수 T, 맵의 크기 N, 명령어 S
 * [출력사항] 명령어 S를 수행한 결과
 * 
 * 땡길때 스택 사용
 * 
 */


public class SWEA_D4_6109_추억의2048게임 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N;
    static String S;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        // 테스트케이스
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력
            tokens = new StringTokenizer(input.readLine(), " ");
            N = Integer.parseInt(tokens.nextToken());
            S = tokens.nextToken();

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(input.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            // 알고리즘
            switch (S) {
                case "up":
                    up();
                    break;
                case "down":
                    down();
                    break;
                case "left":
                    left();
                    break;
                case "right":
                    right();
                    break;
            }

            // 결과 저장
            output.append("#").append(t).append("\n");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    output.append(map[i][j]).append(" ");
                }
                output.append("\n");
            }
        }

        // 출력
        System.out.println(output);
    }

    public static void up() {
        Stack<Integer> st = new Stack<Integer>();

        int idx;
        for (int j = 0; j < N; j++) { // 열
            idx = 0;
            for (int i = 0; i < N; i++) { // 위로 밀기 시작
                if (map[i][j] == 0) {
                    continue; // 0이면 생략
                }

                if (st.isEmpty()) {
                    st.push(map[i][j]); // 스택 비어있으면 채우기
                } else {
                    if (map[i][j] == st.peek()) {
                        map[idx++][j] = st.pop() + map[i][j]; // 수가 같으면 더해서 맵에넣기
                    } else {
                        map[idx++][j] = st.pop(); // 다르면 얘는 맵에 넣고
                        st.push(map[i][j]); // 이친구는 다음 비교를 위해 스택에 넣기
                    }
                }
            }

            while (!st.isEmpty()) { // 스택이 남아있으면 비우기
                map[idx++][j] = st.pop();
            }

            while (idx != N) { // 나머지 자리에는 0 넣기
                map[idx++][j] = 0;
            }
        }
    }

    public static void down() {
        Stack<Integer> st = new Stack<Integer>();

        int idx;
        for (int j = 0; j < N; j++) { // 열
            idx = N - 1;
            for (int i = N - 1; i >= 0; i--) { // 아래로 밀기 시작
                if (map[i][j] == 0) {
                    continue; // 0이면 생략
                }

                if (st.isEmpty()) {
                    st.push(map[i][j]); // 스택 비어있으면 채우기
                } else {
                    if (map[i][j] == st.peek()) {
                        map[idx--][j] = st.pop() + map[i][j]; // 수가 같으면 더해서 맵에넣기
                    } else {
                        map[idx--][j] = st.pop(); // 다르면 얘는 맵에 넣고
                        st.push(map[i][j]); // 이친구는 다음 비교를 위해 스택에 넣기
                    }
                }
            }

            while (!st.isEmpty()) { // 스택이 남아있으면 비우기
                map[idx--][j] = st.pop();
            }

            while (idx >= 0) { // 나머지 자리에는 0 넣기
                map[idx--][j] = 0;
            }
        }
    }

    public static void left() {
        Stack<Integer> st = new Stack<Integer>();

        int idx;
        for (int i = 0; i < N; i++) { // 행
            idx = 0;
            for (int j = 0; j < N; j++) { // 왼쪽밀기 시작
                if (map[i][j] == 0) {
                    continue; // 0이면 생략
                }

                if (st.isEmpty()) {
                    st.push(map[i][j]); // 스택 비어있으면 채우기
                } else {
                    if (map[i][j] == st.peek()) {
                        map[i][idx++] = st.pop() + map[i][j]; // 수가 같으면 더해서 맵에넣기
                    } else {
                        map[i][idx++] = st.pop(); // 다르면 얘는 맵에 넣고
                        st.push(map[i][j]); // 이친구는 다음 비교를 위해 스택에 넣기
                    }
                }
            }

            while (!st.isEmpty()) { // 스택이 남아있으면 비우기
                map[i][idx++] = st.pop();
            }

            while (idx != N) { // 나머지 자리에는 0 넣기
                map[i][idx++] = 0;
            }
        }
    }

    public static void right() {
        Stack<Integer> st = new Stack<Integer>();

        int idx;
        for (int i = 0; i < N; i++) { // 행
            idx = N - 1;
            for (int j = N - 1; j >= 0; j--) { // 오른쪽 밀기 시작
                if (map[i][j] == 0) {
                    continue; // 0이면 생략
                }

                if (st.isEmpty()) {
                    st.push(map[i][j]); // 스택 비어있으면 채우기
                } else {
                    if (map[i][j] == st.peek()) {
                        map[i][idx--] = st.pop() + map[i][j]; // 수가 같으면 더해서 맵에넣기
                    } else {
                        map[i][idx--] = st.pop(); // 다르면 얘는 맵에 넣고
                        st.push(map[i][j]); // 이친구는 다음 비교를 위해 스택에 넣기
                    }
                }
            }

            while (!st.isEmpty()) { // 스택이 남아있으면 비우기
                map[i][idx--] = st.pop();
            }

            while (idx >= 0) { // 나머지 자리에는 0 넣기
                map[i][idx--] = 0;
            }
        }
    }

}
