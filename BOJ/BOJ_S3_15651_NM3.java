package com.ssafy.algo.d0903;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S3_15651_N과M(3)
* @date 2020.09.03
* @link https://www.acmicpc.net/problem/15651
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 자연수 N과 M
* [출력사항] 1부터 N까지 자연수 중에서 M개를 고른 수열 (중복 허용)
* 
* 수열은 사전 순으로 증가하는 순서로 출력
*/

public class BOJ_S3_15651_NM3 {
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
        for (int i = 0; i < N; i++) {
            numbers[i] = i + 1;
        }

        output = new StringBuilder();
        makePermutation(0, new int[M]);
        System.out.println(output);

    }

    public static void makePermutation(int m, int[] temp) {
        if (m == M) {
            for (int t : temp) {
                output.append(t).append(" ");
            }
            output.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            temp[m] = numbers[i];
            makePermutation(m + 1, temp);
        }
    }
}
