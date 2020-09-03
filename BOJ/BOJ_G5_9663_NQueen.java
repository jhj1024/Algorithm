package com.ssafy.algo.d0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
* @author JUNG
* @name BOJ_G5_9663_NQueen
* @date 2020.09.03
* @link https://www.acmicpc.net/problem/9663
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 퀸 N개
* [출력사항] 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수
* 
* 백트래킹의 대표 문제.
* 
*/

public class BOJ_G5_9663_NQueen {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int[] col; //각 행의 위치 저장
    static int N, Answer = 0;
    
    public static void main(String[] args) throws Exception{
       N = Integer.parseInt(input.readLine());
       col = new int[N+1]; //1행부터 시작

       setQueens(1);
       System.out.println(Answer);
    }
    
    //가능한 선택지 1~N열
    public static void setQueens(int rowNo) {
        if(rowNo > N) {
            Answer++;
            return;
        }
        
        for(int j = 1; j <= N; j++) {
            col[rowNo] = j;
            if(checking(rowNo)) {
                setQueens(rowNo+1);              
            }     
        }
    }

    //rowNo행의 퀸을 놓는게 가능한지 체크
    private static boolean checking(int rowNo) {
        for(int i = 1; i < rowNo; i++) {
            if(col[rowNo] == col[i] || Math.abs(col[rowNo] - col[i]) == rowNo-i) { //이전 행의 퀸과 같은 열에 있거나, 대각선 선상에 있다면
                return false;
            }
        }
        return true;
    }
}
