package com.ssafy.algo.d0731;

/**
* @author JUNG
* @name BOJ_B2_8958_OX퀴즈
* @date 2020.07.31
* @link https://www.acmicpc.net/problem/8958
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 테스트케이스, OX로 이루어진 문자열
* [출력사항] 각 테스트케이스의 점수 결과. X는 0점이며 O는 기본적으로 1점이지만 연속으로 O면 연속된 개수를 점수로 함.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class BOJ_B2_8958_OX퀴즈 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static int Answer = 0;
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        int T = Integer.parseInt(input.readLine());
        
        for(int tc = 0; tc < T; tc++) {
            String line = input.readLine();
            
            Answer = 0;
            int score = 0;       
            for(int i = 0; i < line.length(); i++) {
               if(line.charAt(i) == 'O') {
                   Answer += (++score);
               }else {
                   score = 0;
               }
            }
            
            System.out.println(Answer);
        }
    }
    static String src = "5\r\n" + 
            "OOXXOXXOOO\r\n" + 
            "OOXXOOXXOO\r\n" + 
            "OXOXOXOXOXOXOX\r\n" + 
            "OOOOOOOOOO\r\n" + 
            "OOOOXOOOOXOOOOX";
}
