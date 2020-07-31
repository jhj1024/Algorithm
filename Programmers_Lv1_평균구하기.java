package com.ssafy.algo.d0731;

/**
* @author JUNG
* @name Programmers_Lv1_평균구하기
* @date 2020.07.31
* @link https://programmers.co.kr/learn/courses/30/lessons/12944
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 정수 배열 arr
* [출력사항] 배열 arr 요소들의 평균값
*/

public class Programmers_Lv1_평균구하기 {
    static class Solution {
        public double solution(int[] arr) {
            double answer = 0;
            
            for(int data : arr) {
                answer += data;
            }
          
            return answer/arr.length;
        }
    }
    
    public static void main(String[] args) {
        Solution s = new Solution();
        
        int[] arr = {1,2,3,4};
        System.out.println(s.solution(arr));
        
        int[] arr1 = {5,5};
        System.out.println(s.solution(arr1));
    }

}
