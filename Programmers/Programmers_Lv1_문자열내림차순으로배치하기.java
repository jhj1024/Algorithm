import java.util.Arrays;

/**
* @author JUNG
* @name Programmers_Lv1_문자열내림차순으로배치하기
* @date 2020.08.05
* @link https://programmers.co.kr/learn/courses/30/lessons/12917
* @mem
* @time
* @caution 
* [고려사항] 대문자는 소문자보다 작은 것으로 간주 = 즉 내림차순(아스키 기준 대문자가 소문자보다 작음)
* [입력사항] 문자열 s에 나타나는 문자를 큰것부터 작은 순으로 정렬해 새로운 문자열을 리턴
* [출력사항]
*/


public class Programmers_Lv1_문자열내림차순으로배치하기 {
    public static String solution(String s) {
        char[] arr = s.toCharArray();
        
        Arrays.sort(arr); //오름차순 정렬
        
        StringBuilder answer = new StringBuilder(new String(arr)).reverse(); //거꾸로
        
        return answer.toString();     
    }
    
    public static void main(String[] args) {
        String s = "Zbcdefg";
        System.out.println(solution(s));

    }

}
