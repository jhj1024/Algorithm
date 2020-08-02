package com.ssafy.algo.d0731;

/**
* @author JUNG
* @name BOJ_S1_1149_RGB거리
* @date 2020.07.31
* @link https://www.acmicpc.net/problem/1149
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항]
* [출력사항]
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ_S1_1149_RGB거리 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N = 0, Answer = Integer.MAX_VALUE;
    static int[][] RGB;
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        N = Integer.parseInt(input.readLine());        
        RGB = new int[N][3]; //N개의 건물 * RGB칠하는 가격
        
        for(int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j = 0; j < 3; j++) {
                RGB[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
                 
        selectRGB(0, 0, 0); //첫번째 집 빨간색 선택
        selectRGB(0, 1, 0); //첫번째 집 초록색 선택
        selectRGB(0, 2, 0); //첫번째 집 파랑색 선택
        
        System.out.println(Answer);
    }
    
    public static void selectRGB(int n, int select, int sum) {       
        if(n == N) {
            Answer = Answer > sum ? sum : Answer;
            return;
        }
        
        switch(select) {
            case 0:
                selectRGB(n+1, 1, sum+RGB[n][select]);
                selectRGB(n+1, 2, sum+RGB[n][select]);
                break;
            case 1:
                selectRGB(n+1, 0, sum+RGB[n][select]);
                selectRGB(n+1, 2, sum+RGB[n][select]);
                break;
            case 2:
                selectRGB(n+1, 0, sum+RGB[n][select]);
                selectRGB(n+1, 1, sum+RGB[n][select]);
                break;
        }
        
    }
    
    static String src = "3\r\n" + 
            "26 40 83\r\n" + 
            "49 60 57\r\n" + 
            "13 89 99";

}
