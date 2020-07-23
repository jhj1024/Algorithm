package com.ssafy.java.day3.ws;

import java.util.Arrays;

//프로그래머스) 같은 숫자는 싫어
public class Programmers7 {
    public static int[] solution(int []arr) {
        int[] answer = {};
        
        int prev = -1, idx = 0;
        int[] result = new int[arr.length];
        for(int data : arr) {
        	if(prev != data) {
        		result[idx++] = data;        		
        	}
        	prev = data;
        }
        
        answer = Arrays.copyOf(result, idx);
        System.out.println(Arrays.toString(answer));

        return answer;
    }
	public static void main(String[] args) {
		//int[] arr = {1,1,3,3,0,1,1};
		int[] arr = {4,4,4,3,3};
		solution(arr);

	}

}
