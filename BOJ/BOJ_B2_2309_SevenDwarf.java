import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
* @author JUNG
* @name BOJ_B2_2309_일곱난쟁이
* @date 2020.07.29
* @link https://www.acmicpc.net/problem/2309
* @mem
* @time
* @caution 일곱난쟁이의 합이 100일때, 9명의 난쟁이에서 진짜 일곱난쟁이의 키를 오름차순으로 출력. 
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class BOJ_B2_2309_SevenDwarf {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int[] sevenDwarf = null;
    static StringBuilder output;
    
    public static void main(String[] args) throws Exception{
        int[] dwarfs = new int[9];
        for(int i = 0; i < dwarfs.length; i++) {
            dwarfs[i] = Integer.parseInt(input.readLine());
        }
        
        
        makeCombi(7, 0, dwarfs, new int[7]); //진짜 일곱난쟁이 찾기
        Arrays.sort(sevenDwarf); //정렬
        
        output = new StringBuilder();
        for(int data : sevenDwarf) {
            output.append(data).append('\n');
        }
        System.out.print(output);       
    }
    
  //9명에서 7명을 뽑는 조합에서 합이 100인 경우 찾기
    static void makeCombi(int r, int start, int[] dwarfs, int[] temp) {
        if(r == 0) {
            int sum = 0;
            for(int t = 0; t < temp.length; t++) {
                sum += temp[t];
            }
            if(sum == 100) { //합이 100이면 리스트 복사
                sevenDwarf = Arrays.copyOfRange(temp, 0, 7);
            }
            return;
        }
        for(int i = start; i < dwarfs.length; i++) {
            temp[r - 1] = dwarfs[i];
            makeCombi(r - 1, i + 1, dwarfs, temp);
        }
    }
}
