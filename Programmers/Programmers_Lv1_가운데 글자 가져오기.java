//프로그래머스:) 가운데 글자 가져오기 
public class Programmers_Lv1_가운데 글자 가져오기 {

    public static String solution(String s) {
        String answer = "";
        
        if(s.length()%2 == 0) {
        	//짝수: 가운데 두글자
        	answer = s.substring(s.length()/2-1, s.length()/2+1);
        }
        else {
        	//홀수: 가운데 한글자
        	answer = s.substring(s.length()/2, s.length()/2+1);
        }
        
        System.out.println(answer);
        return answer;
    }
    
	public static void main(String[] args) {
		solution("abcde");
		solution("qwer");

	}

}
