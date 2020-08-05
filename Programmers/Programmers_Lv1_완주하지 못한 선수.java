import java.util.HashMap;
//프로그래머스:) 완주하지 못한 선수 
public class Programmers_Lv1_완주하지 못한 선수 {
	
    public static String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        for(String p : participant) {
        	if(!map.containsKey(p))
        		map.put(p, 1);
        	else
        		map.put(p, map.get(p) + 1); //해당 이름에 대한 value 1 증가
        }
        
        System.out.println(map.toString());
        
        
        
        for(String c : completion) {
        	map.put(c, map.get(c) - 1); //해당 이름에 대한 value 1 감소
        }
        
        for(String key : map.keySet()) {
        	if(map.get(key) > 0)
        		answer = key.toString();
        }
        
        System.out.println(map.toString());
        
        return answer;
    }
		
	public static void main(String[] args) {
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};
		String answer = solution(participant, completion);
		System.out.println(answer);
	}

}
