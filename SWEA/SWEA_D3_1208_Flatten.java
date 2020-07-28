package com.ssafy.algo.d0728.ws;

import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//algo 단축어 만들기
/**
 * @author JUNG
 * @name SWEA D3 1208 Flatten
 * @data 2020.07.28
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh
 * @mem
 * @time
 * @caution
 * [고려사항] 가장 높은 곳에 있는 박스를 가장 낮은 곳으로 이동시키기 -> 반복문 내에서 정렬 처리 후 이동
 * [입력사항]
 * [출력사항]
 */

public class Flatten{	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); //입력스트림
	static int Answer;
	static int T=10;
		
	public static void main(String args[]) throws Exception{	
		//input = new BufferedReader(new StringReader(src)); //입력방향 설정, 제출 시 주석 처리
		for(int test_case = 1; test_case <= T; test_case++){
			//data 입력
			int[] boxes = new int[100]; //상자 100개
			int dump = Integer.parseInt(input.readLine()); //입력된 String > integer 변환
			
			StringTokenizer tokens = new StringTokenizer(input.readLine(), " "); //한줄 읽어와서 " "로 자르기
			for(int i = 0; i < boxes.length; i++) {
				boxes[i] = Integer.parseInt(tokens.nextToken()); //" "로 자른 100개의 문자열을 정수로 변환하여 저장
			}
			
			//알고리즘
			Answer = 0; //정답 초기화
			int min = 0, max = 99;
			while(dump != 0) {	
				Arrays.sort(boxes); //정렬
				if(boxes[max] - boxes[min] <= 1) break;//1이나 0차이밖에 안나면 탈출					
				boxes[max]--;
				boxes[min]++;
				dump--;
			}			
			
			Arrays.sort(boxes); //정렬
			Answer = boxes[max] - boxes[min];
			System.out.printf("#%d %d\n", test_case, Answer);				
		}
	}
	
	String src = "";
}

