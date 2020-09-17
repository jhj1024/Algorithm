package com.ssafy.algo.d0916;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name 
* @date 
* @link 
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 4x4크기의 물고기 정보(물고기 번호, 방향)
* [출력사항] 상어가 먹을 수 있는 물고기 번호의 합의 최댓값
* 
* 
* 청소년 상어는 (0, 0)에 있는 물고기를 먹고, 방향은 (0, 0)에 있던 물고기의 방향과 같다.
* 물고기는 번호가 작은 물고기부터 순서대로 이동.
* 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전
* 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동
* 
* 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다. 
* 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다.
* 
* 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
* 
*/

public class BOJ_G3_19236_청소년상어 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int Answer;
    static Fish[][] map;
    static Point shark;
    static Point[] moveQ;
    static int[][] dirs = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));

        //입력
        moveQ = new Point[17]; //16+1;
        map = new Fish[4][4];
        for (int i = 0; i < 4; i++) {
            tokens = new StringTokenizer(input.readLine());
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(tokens.nextToken());
                int dir = Integer.parseInt(tokens.nextToken())-1;
                map[i][j] = new Fish(num, dir);
                moveQ[num] = new Point(i, j);
            }
        }

        //알고리즘
        Answer = map[0][0].num;
        shark = new Point(0, 0);
        moveQ[map[0][0].num] = null; //잡아먹힌 물고기는 이동리스트에서 삭제
        map[0][0] = null; //잡아먹힌 물고기는 맵에서 삭제

        youthShark();


        //출력
        System.out.println(Answer);
    }

    public static void youthShark() {
        // TODO Auto-generated method stub
        
    }

    public static boolean moveShark() {
        return false;
        
    }

    public static void moveFish() {
        for (Point p : moveQ) {           
            if (p != null) {
                int num = map[p.r][p.c].num;
                int dir = map[p.r][p.c].dir;

                for (int i = 0; i < dirs.length; i++) {
                    int d = i + dir;
                    if (d >= dirs.length) {
                        d -= dirs.length;
                    }

                    int dr = p.r + dirs[d][0];
                    int dc = p.c + dirs[d][1];
                    
                    if(isIn(dr, dc) && map[dr][dc] != null) { //이동 가능하면 map에서 서로 swap하고 moveQ에서의 물고기 좌표 업데이트 후 break;
                        moveQ[num] = new Point(dr, dc);
                        moveQ[map[dr][dc].num] = new Point(p.r, p.c);
                        
                        map[p.r][p.c] = new Fish(map[dr][dc].num, map[dr][dc].dir);
                        map[dr][dc] = new Fish(num, dir);
                        
                        break;
                    }
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return (0 <= r && r < 4 && 0 <= c && c < 4);
    }

    static class Point implements Comparable<Point> {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(map[this.r][this.c].num, map[o.r][o.c].num);
        }
    }


    static class Fish implements Comparable<Fish> {
        int num, dir;

        public Fish(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("(");
            builder.append(num);
            builder.append(", ");
            builder.append(dir);
            builder.append(")");
            return builder.toString();
        }

        @Override
        public int compareTo(Fish o) {
            return Integer.compare(this.num, o.num);
        }
    }

    static String src =
            "7 6 2 3 15 6 9 8\r\n" +
                "3 1 1 8 14 7 10 1\r\n" +
                "6 1 13 6 4 3 11 4\r\n" +
                "16 1 8 7 5 2 12 2";
}


