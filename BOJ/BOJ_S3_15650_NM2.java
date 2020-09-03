package com.ssafy.algo.d0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S3_15650_N과M(2)
* @date 2020.09.03
* @link https://www.acmicpc.net/problem/15650
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 자연수 N과 M
* [출력사항] 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 조합
* 
* 설명에는 중복 없는 순열이라고 하지만, 순열 내 요소가 오름차순이어야 하기 때문에 순서가 정해져있는 조합이다.
* 수열은 사전 순으로 증가하는 순서로 출력
*/

public class BOJ_S3_15650_NM2 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = null;
    static int N, M;
    static int[] numbers;
    
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine(), " ");
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        
        numbers = new int[N];
        for(int i = 0; i < N; i++) {
            numbers[i] = i+1;
        }
        
        output = new StringBuilder();
        makeCobination(0, 0, new int[M]);
        System.out.println(output);
    }

    public static void makeCobination(int m, int start, int[] temp) {
        if(m == M) {            
            for(int t : temp) {
                output.append(t).append(" ");
            }
            output.append("\n");
            return;
        }
        
        for(int i = start; i < N; i++) {
            temp[m] = numbers[i];
            makeCobination(m+1, i+1, temp);
        }        
    }
}
