//프로그래머스:) 2016년
public class Programmers_2016년 {
    public static String solution(int a, int b) {
    	String[] days = {"FRI","SAT","SUN","MON","TUE","WED","THU"}; //1월1일이 금요일이니까 FRI부터 시작
    	int[] months = {0,31,29,31,30,31,30,31,31,30,31,30,31};
    	
    	int time = 0;
    	for(int m = 0; m < a; m++) {
    		time += months[m];
    	}
    	time += (b - 1); //1월1일부터 세는것이므로 1 빼기
    	
        String answer = days[time%7];
        System.out.println(answer);
        return answer;
    }
    
	public static void main(String[] args) {
		solution(2, 1);

	}

}
