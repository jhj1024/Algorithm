/**
* @author JUNG
* @name Programmers_Lv1_핸드폰번호가리기
* @date 2020.08.05
* @link https://programmers.co.kr/learn/courses/30/lessons/12948
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class Programmers_Lv1_핸드폰번호가리기 {

    public static String solution(String phone_number) {
        StringBuilder output = new StringBuilder(phone_number);
        
        int len = phone_number.length();
        
        //0부터 len-4부분은 *로 마스킹하기
        for(int i = 0; i < len-4; i++) {
            output.setCharAt(i, '*');
        }
        
        return output.toString();
    }
    
    public static void main(String[] args) {
        String src = "01033334444";
        System.out.println(solution(src));
        
        src = "027778888";
        System.out.println(solution(src));
    }

}
