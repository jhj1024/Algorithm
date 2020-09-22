import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
* @author JUNG
* @name BOJ_B2_2309_일곱난쟁이
* @date 2020.09.21
* @link https://www.acmicpc.net/problem/2309
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 아홉난쟁이들의 키
* [출력사항] 일곱 난쟁이의 키를 오름차순으로 출력
* 
* 9개의 정보에서 7개를 뽑는 조합 생성. 
* 조합 생성 중 총 합이 100을 넘으면 취소
* 맨 처음에 정렬시켜두면 마지막에 정렬할 필요 없음
*/

public class BOJ_B2_2309_일곱난쟁이 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int[] dwarfs;
    static int[] Answer;
    
    public static void main(String[] args) throws Exception{
        //입력
        dwarfs = new int[9];      
        for(int i = 0; i < 9; i++) {
            dwarfs[i] = Integer.parseInt(input.readLine());
        }
        
        //알고리즘
        Arrays.sort(dwarfs); //먼저 오름차순 정렬
        makeCobination(0, 0, new int[7], 0); //일곱난쟁이를 뽑는 조합
        
        
        //출력
        for(int i = 0; i < 7; i++) {
            System.out.println(Answer[i]);
        }
    }
    
    public static void makeCobination(int n, int start, int[] temp, int sum) {
        if(sum > 100) { //당신들은 일곱난쟁이가 아니야
            return;
        }
        
        if(n == 7) { 
            if(sum == 100) { //진정한 일곱난쟁이
                Answer = Arrays.copyOf(temp, 7);
            }
            return;
        }
        
        for(int i = start; i < dwarfs.length; i++) {
            temp[n] = dwarfs[i];
            makeCobination(n+1, i+1, temp, sum+dwarfs[i]);
        }
    }

}
