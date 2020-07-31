/**
* @author JUNG
* @name Programmers_Lv1_문자열 내 p와 y의 개수
* @date 2020.07.31
* @link https://programmers.co.kr/learn/courses/30/lessons/12916
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 대소문자가 섞여있는 문자열
* [출력사항] 문자열에서 p와 y의 개수가 같으면 true, 다르면 false
*/

public class Programmers_Lv1_p와y의개수 {
    static class Solution {
        boolean solution(String s) {
            int sump = 0, sumy = 0;
            boolean answer = true;
            
            s = s.toLowerCase(); //다 소문자로 바꿈
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == 'p') sump++; //p개수 증가
                else if(s.charAt(i) == 'y') sumy++; //y개수 증가
            }

            answer = sump == sumy ? true : false; //같으면 true 다르면 false
            return answer;
        }
    }
    
    public static void main(String[] args) {
        Solution s = new Solution();
        
        System.out.println(s.solution("pPoooyY"));
        System.out.println(s.solution("Pyy"));
        return;
    }

}
