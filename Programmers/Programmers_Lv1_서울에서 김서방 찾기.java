//프로그래머스:) 서울에서 김서방 찾기 
public class Programmers_Lv1_서울에서 김서방 찾기 {
    public static String solution(String[] seoul) {
    	String answer = "";
    	for(int i = 0; i < seoul.length; i++){
            if(seoul[i].equals("Kim")) {
            	answer = "김서방은 " + i + "에 있다";   
            	break;
            }
        }   	
        return answer;
    }
	public static void main(String[] args) {
		String[] seoul = {"Jane", "Hello", "Kim", "KimKim"};
		solution(seoul);
	}

}
