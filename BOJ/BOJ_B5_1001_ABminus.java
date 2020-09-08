package com.ssafy.algo.d0908;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_B5_1000_A+B
* @date 2020.09.08
* @link https://www.acmicpc.net/problem/1000
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 두 정수 A와 B
* [출력사항] A+B
*/

public class BOJ_B5_1001_ABminus {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int A, B, Answer;
    public static void main(String[] args) throws Exception {
        tokens = new StringTokenizer(input.readLine());
        A = Integer.parseInt(tokens.nextToken());
        B = Integer.parseInt(tokens.nextToken());
        Answer = A + B;
        System.out.println(Answer);
    }

}
