import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
* @author JUNG
* @name SWEA_D3_1289_원재의 메모리 복구하기
* @date 2020.07.29 
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV19AcoKI9sCFAZN&categoryId=AV19AcoKI9sCFAZN&categoryType=CODE
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class SWEA_D3_1289_Memory {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static int Answer = 0;
	
	public static void main(String[] args) throws Exception{
		//input = new BufferedReader(new StringReader(src));
		int T = Integer.parseInt(input.readLine());
		for(int tc = 1; tc <= T; tc++) {
			//입력
			char[] mem = input.readLine().toCharArray();
						
			/*-------------------------- 알고리즘 --------------------------*/
			Answer = 0;
			char prev = mem[0];
			for(int i = 1; i < mem.length; i++) {
				if(prev != mem[i]) { //앞과 뒤의 비트가 다르면 뒤집기
					for(int j = i; j < mem.length; j++) {
						if(mem[j] == '0') mem[j] = '1';
						else mem[j] = '0';
					}
					Answer++;
				}
				prev = mem[i];
			}
			
			if(mem[mem.length-1] == '1') //마지막이 1이면 +1 해주기
				Answer++;
						
			System.out.printf("#%d %d\n", tc, Answer);			
		}

	}

}

