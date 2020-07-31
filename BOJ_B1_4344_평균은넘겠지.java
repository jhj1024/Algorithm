package com.ssafy.algo.d0731;

/**
* @author JUNG
* @name BOJ_B1_4344_평균은넘겠지
* @date 2020.07.31
* @link https://www.acmicpc.net/problem/4344
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 테스트 케이스 개수 T, 학생의 수 N, N명의 점수
* [출력사항] 각 케이스마다 평균을 넘는 학생들의 비율을 반올림하여 소수점 셋째자리까지 출력
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ_B1_4344_평균은넘겠지 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output;

    public static void main(String[] args) throws Exception{
        input = new BufferedReader(new StringReader(src));
        StringTokenizer tokens = null;
        
        int T = Integer.parseInt(input.readLine());       
        for(int tc = 0; tc < T; tc++) {
            tokens = new StringTokenizer(input.readLine(), " ");
            int N = Integer.parseInt(tokens.nextToken());
            
            int avg = 0;
            int[] scores = new int[N];
            for(int i = 0; i < N; i++) {
                scores[i] = Integer.parseInt(tokens.nextToken()); //배열에 점수 저장
                avg += scores[i]; //점수 합산
            }
            avg /= N; //평균만들기
            
            int cnt = 0;
            for(int i = 0; i < N; i++) {
                if(scores[i] > avg) cnt++; //평균을 넘는 학생 수 계산
            }
            
            output = new StringBuilder();
            output.append(String.format("%.3f", (float)(cnt*100)/N)).append("%"); //평균을 넘는 학생의 비율을 소수점 세자리 출력 포맷으로 표현
            System.out.println(output);           
        }

    }
    static String src = "5\r\n" + 
            "5 50 50 70 80 100\r\n" + 
            "7 100 95 90 80 70 60 50\r\n" + 
            "3 70 90 80\r\n" + 
            "3 70 90 81\r\n" + 
            "9 100 99 98 97 96 95 94 93 91";

}
