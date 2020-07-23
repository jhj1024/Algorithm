package com.ssafy.java.day3.ws;

//프로그래머스 모의고사
public class Programmers2 {
	
	public static int[] solution(int[] answers) {
	    int[] mathone = {1,2,3,4,5};
	    int[] mathtwo = {2,1,2,3,2,4,2,5};
	    int[] maththree = {3,3,1,1,2,2,4,4,5,5};
		
	    int[] result = new int[3];		
	    
		for(int i = 0; i < answers.length; i++) {
			if(answers[i] == mathone[i%mathone.length]) result[0]++;
			if(answers[i] == mathtwo[i%mathtwo.length]) result[1]++;
			if(answers[i] == maththree[i%maththree.length]) result[2]++;
		}
		
		
		int max = (result[0] > result[1]) ? result[0] : result[1];
		max = (max > result[2]) ? max : result[2];

		//최고점수를 획득한 사람 수 구하기
		int n = 0;
		for(int i = 0; i < result.length; i++) {
			if(result[i] == max) {
				n++;
			}
		}    
		
		//최고점수를 획득한 사람 수만큼 배열 할당 후 최고점수를 획득한 사람의 번호 저장하기
		int[] answer = new int[n];
		int r = 0;
		for(int i = 0; i < result.length; i++) {
			if(result[i] == max) {
				answer[r] = i+1;
				r++;
			}
		}  
		
        return answer;
    }

	public static void main(String[] args) {
		int[] answers = {1,3,2,4,2};
		solution(answers);
	}

}
