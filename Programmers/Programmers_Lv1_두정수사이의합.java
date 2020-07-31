package com.ssafy.algo.d0731;

/**
* @author JUNG
* @name Programmers_Lv1_두정수사이의합
* @date 2020.07.31
* @link https://programmers.co.kr/learn/courses/30/lessons/12912
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 두 개의 정수 a,b (음수 포함. 대소관계 안정해져 있음)
* [출력사항] a와 b사이의 수들의 합
*/

public class Programmers_Lv1_두정수사이의합 {
    public static long solution(int a, int b) {
        long answer = 0;
        
        //a와 b 대소관계 정리
        if(a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        //알고리즘 생각 안하고 걍 순차적으로 더하기
        for(int i = a; i <= b; i++) {
            answer += i;
        }      
        
        return answer;
    }
    
    public static void main(String[] args) {
        System.out.println(solution(3,5));
    }
}
