/**
* @author JUNG
* @name Programmers_Lv1_수박수박수박수박수박수?
* @date 2020.07.31
* @link https://programmers.co.kr/learn/courses/30/lessons/12922
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 문자열 길이
* [출력사항] "수박수박수박수박수...."문자열에서 문자열 길이만큼 출력
*/

public class Programmers_Lv1_수박수박수 {

    static class Solution {
        public String solution(int n) {
            StringBuffer answer = new StringBuffer();
            
            if(n%2 == 0) { //짝수
                for(int i = 0; i < n/2; i++) {
                    answer.append("수박");
                }
            }else { //홀수
                for(int i = 0; i < n/2; i++) {
                    answer.append("수박");
                }
                answer.append("수");
            }           
            return answer.toString();
        }
    }
    
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(4));
        System.out.println(s.solution(3));
    }

}
