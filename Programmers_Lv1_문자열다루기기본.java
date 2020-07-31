package com.ssafy.algo.d0731;
/**
* @author JUNG
* @name Programmers_Lv1_문자열다루기기본
* @date 2020.07.31
* @link https://programmers.co.kr/learn/courses/30/lessons/12918
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 길이가 1~8인 문자열s
* [출력사항] 문자열 길이가 4 또는 6이고, 숫자로만 이루어져 있으면 true, 그 외는 모두  false
*/

public class Programmers_Lv1_문자열다루기기본 {

    static class Solution {
        public boolean solution(String s) {
            if(s.length() == 4 && s.length() == 6) {
                for(int i =0; i < s.length(); i++) {
                    if((s.charAt(i)-'0') < 0 || (s.charAt(i)-'0') > 9) {
                        return false;
                    }
                }
                
                return true;
            }

            return false;

        }
    }
    
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("a1234"));
        System.out.println(s.solution("a234"));
        System.out.println(s.solution("1234"));
        System.out.println(s.solution("10"));
        System.out.println(s.solution("-10"));
        System.out.println(s.solution("00"));

    }

}
