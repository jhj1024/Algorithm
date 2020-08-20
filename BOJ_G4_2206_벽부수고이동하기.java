package com.ssafy.good;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_2206_벽부수고이동하기 
* @date 
* @link https://www.acmicpc.net/problem/2206
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class BOJ_G4_2206_벽부수고이동하기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, Answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] dirs = {{-1, 0},{1, 0},{0, -1},{0, 1}};
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        tokens = new StringTokenizer(input.readLine());        
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        map = new int[R][C];
        for(int i = 0; i < R; i++) {
            String line = input.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        
        bfs();       
        System.out.println(Answer);
    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];   
        
        Node node = new Node(0, 0, 1, 0, visited);
        queue.offer(node);

        while(!queue.isEmpty()) {
            if(node.r == R && node.c == C) {
                Answer = Math.min(Answer, node.cnt);
            }
            
            
      
        }
        
        if(Answer == Integer.MAX_VALUE) { //도착한 녀석이 없었다면
            Answer = -1;
        }
            
    }
    
    static class Node{
        int r, c, cnt, crush;
        boolean[][] visited;
        
        public Node(int r, int c, int cnt, int crush, boolean[][] visited) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.crush = crush;
            this.visited = visited;
        }
    }
    
    static String src = "4 4\r\n" + 
            "0000\r\n" + 
            "0110\r\n" + 
            "1000\r\n" + 
            "0010";
}
