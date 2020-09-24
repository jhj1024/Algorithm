import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
* @author JUNG
* @name BOJ_S5_2635_수이어가기
* @date 2020.09.24
* @link https://www.acmicpc.net/problem/2635
* 
* [입력사항] 첫 번째 수(30,000 보다 같거나 작은 양의 정수)
* [출력사항] 입력된 첫 번째 수로 시작하여 위의 규칙에 따라 만들 수 있는 수들의 최대 개수 / 만들어진 수
*            
* 1. 첫 번째 수로 양의 정수가 주어진다.
* 2. 두 번째 수는 양의 정수 중에서 하나를 선택한다.
* 3. 세 번째부터 이후에 나오는 모든 수는 앞의 앞의 수에서 앞의 수를 빼서 만든다.
* 4. 음의 정수가 만들어지면, 이 음의 정수를 버리고 더 이상 수를 만들지 않는다.        
*            
* 증가하던 answer가 줄어들면 중단                  
*/

public class BOJ_S5_2635_수이어가기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static int N, Answer;
    static List<Integer> result, nums;
    
    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(input.readLine());
        
        result = new ArrayList<Integer>();
        for(int i = 1; i <= N; i++) {
            int cnt = count(i); //두번째 수가 i일때 만들 수 있는 수들의 최대 개수 저장
            
            if(cnt < Answer) { //최대 개수가 Answer보다 작으면 중단
                break;
            }else if(cnt > Answer){
                Answer = cnt; //만들수 있는 수들의 최대 개수 업데이트
                result = nums; //숫자리스트 업데이트
            }           
        }
        
        System.out.println(Answer); //최대 개수
        for(int n : result) { //생성된 수 출력
            output.append(n).append(" ");
        }        
        System.out.println(output);
        
    }
    
    public static int count(int n) {
        nums = new ArrayList<Integer>();
        nums.add(N); //첫번째 수
        nums.add(n); //두번째 수
        
        int i = 2; //세번째 수부터 계산
        while(true) {
            int a = nums.get(i-2), b = nums.get(i-1);
            
            if(a >= b) { //계산해도 양수일때
                nums.add(a-b); //저장
            }else { //음수일 때 중단
                break;
            }
            i++;
        }        
        
        return nums.size(); //만든 숫자 배열의 길이
    }    
}
