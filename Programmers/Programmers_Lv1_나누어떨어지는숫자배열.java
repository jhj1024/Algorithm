package com.ssafy.algo.d0731;

import java.util.Arrays;

/**
* @author JUNG
* @name Programmers_Lv1_나누어떨어지는숫자배열
* @date 2020.07.31
* @link https://programmers.co.kr/learn/courses/30/lessons/12910
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] int 배열과 정수 divisor
* [출력사항] array의 각 element 중 divisor로 나누어 떨어지는 값을 오름차순으로 정렬한 배열, 하나도 없으면 -1
*/

public class Programmers_Lv1_나누어떨어지는숫자배열 {
    public static int[] solution(int[] arr, int divisor) {
        int[] answer = new int[arr.length];
        
        int idx = 0;
        for(int data : arr) {
            if(data%divisor == 0) //나누어 떨어지면
                answer[idx++] = data; //저장
        }
        
        if(idx == 0) //하나도 없으면 -1 저장
            answer[idx++] = -1;
        
        answer = Arrays.copyOfRange(answer, 0, idx); //크기 조정
        Arrays.sort(answer); //오름차순 정렬
        return answer;
    }
    
    public static void main(String[] args) {
        int[] arr = {5,9,7,10};
        int divisor = 5;
        
        System.out.println(Arrays.toString(solution(arr, divisor)));
        return;
    }

}
