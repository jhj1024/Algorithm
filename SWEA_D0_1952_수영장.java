package com.ssafy.d1028;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JUNG
 * @name SWEA_D0_1952_수영장
 * @date 2020.10.30
 * @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&categoryId=AV5PpFQaAQMDFAUq&categoryType=CODE
 * 
 * [입력사항] 테스트 케이스의 개수 T / 각 이용권의 요금 / 1~12월까지 각 달의 이용 계획
 * [출력사항] 수영장을 이용하는 경우 중 가장 적게 지출하는 비용
 * 
 * 이용권 종류: 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권
 */

public class SWEA_D0_1952_수영장 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, Answer;
    static int[] ticket, months;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(input.readLine());

        for (int t = 1; t <= T; t++) {
            // 입력
            int days = 0;
            ticket = new int[4]; // 각 이용 요금
            tokens = new StringTokenizer(input.readLine());
            for (int i = 0; i < ticket.length; i++) {
                ticket[i] = Integer.parseInt(tokens.nextToken());
                days += ticket[i];
            }

            months = new int[12]; // 1월부터 12월
            tokens = new StringTokenizer(input.readLine());
            for (int i = 0; i < months.length; i++) {
                months[i] = Integer.parseInt(tokens.nextToken());
            }

            // 알고리즘
            Answer = Math.min(ticket[3], days * ticket[0]); // 일단 1년치 vs 모두 1일치
            calc();

            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }

        System.out.println(output);
    }

    public static void calc() {
        int[] dp1M = new int[12];

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            dp1M[i] = Math.min(months[i] * ticket[0], ticket[1]); //1일권과 한달권 비교
            sum += dp1M[i];
        }

        Answer = Math.min(Answer, sum);
        
        int[] dp3M = new int[12];
        for(int i = 0; i < 12; i++) {
            if(i == 11) {
                dp3M[i] = dp1M[i];
            }else if(i == 10) {
                dp3M[i] = dp1M[i] + dp1M[i+1];
            }else {
                dp3M[i] = dp1M[i] + dp1M[i+1] + dp1M[i+2];
            }
            
            dp3M[i] = Math.min(dp3M[i], ticket[2]); //3달권과 1일권or1달권 비교           
        }

        
        
        
        
        Answer = Math.min(Answer, sum);

    }

}


