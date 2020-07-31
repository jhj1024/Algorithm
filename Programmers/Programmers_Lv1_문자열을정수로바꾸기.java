/**
* @author JUNG
* @name Programmers_Lv1_문자열을 정수로 바꾸기
* @date 2020.07.31
* @link https://programmers.co.kr/learn/courses/30/lessons/12925
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 문자열
* [출력사항] 문자열을 숫자로 변환. 맨 앞에 +- 부호가 올 수도 있음.
*/

public class Programmers_Lv1_문자열을정수로바꾸기 {

    static class Solution {
        public int solution(String s) {
            int answer = 0;
            
            int isMinus = 1;
            if(s.charAt(0) == '-' || s.charAt(0) == '+') { //부호가 존재하는 경우
                if(s.charAt(0) == '-')
                    isMinus = -1; //마이너스 부호면 -1을 저장
                s = s.substring(1, s.length()); //부호를 뺀 숫자 문자열만 저장
            }
            
            answer = Integer.parseInt(s) * isMinus; //문자열을 정수로 바꾸고, 부호에 따라 1 또는 -1 곱함
          
            return answer;
        }
    }
    
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("1234"));
        System.out.println(s.solution("-1234"));
    }

}
