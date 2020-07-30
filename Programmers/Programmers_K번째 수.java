package com.ssafy.java.day3.ws;

import java.util.Arrays;
//프로그래머스:) K번째 수
public class Programmers3 {
    public static int[] solution(int[] array, int[][] commands) {
        int N = commands.length;
    	int[] answer = new int[N];
        
        for(int i = 0; i < N; i++) {
        	int cpArray[] = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]); //부분배열 추출
        	Arrays.sort(cpArray); //정렬
        	answer[i] = cpArray[commands[i][2]-1]; //k번째 수 저장
        }
        
        return answer;
    }

	public static void main(String[] args) {
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		solution(array, commands);
		
	}

}
