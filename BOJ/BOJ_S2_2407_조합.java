package com.ssafy.algo.d1007;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S2_2407_조합
* @date 2020.10.15
* @link https://www.acmicpc.net/problem/2407
* 
* [입력사항] 정수 n과 m
* [출력사항] n개에서 m개를 뽑는 조합의 경우의 수
*/

public class BOJ_S2_2407_조합 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int N, R;
    static BigInteger Answer;

    public static void main(String[] args) throws Exception {
        //입력
        tokens = new StringTokenizer(input.readLine(), " ");
        N = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());
                   
        //알고리즘
        BigInteger up = BigInteger.ONE;
        BigInteger down = BigInteger.ONE;
        
        for(int i = 0; i < R; i++) {
            up = up.multiply(new BigInteger(String.valueOf(N-i)));
            down = down.multiply(new BigInteger(String.valueOf(i+1)));
        }
        
        Answer = up.divide(down);
        
        //출력
        System.out.println(Answer);
    }
}
