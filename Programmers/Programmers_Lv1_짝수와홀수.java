package com.ssafy.algo.d0731;
/**
* @author JUNG
* @name Programmers_Lv1_짝수와홀수
* @date 2020.07.31
* @link https://programmers.co.kr/learn/courses/30/lessons/12937
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 정수
* [출력사항] 짝수면  Even, 홀수면 Odd
*/

public class Programmers_Lv1_짝수와홀수 {
    static class Solution {
        public String solution(int num) {
            if(num%2 == 0) return "Even";
            else return "Odd";
        }
    }
    
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(3));
        System.out.println(s.solution(4));
    }

}
