import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D4_3289_서로소집합
* @date 2020.08.04
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항]
* [출력사항]
*/

public class SWEA_D4_3289_서로소집합 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, M; 
    static int[] repres; 
    static int[] rank; //각 노드의 등급
    
	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new StringReader(src));
		T = Integer.parseInt(input.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			tokens = new StringTokenizer(input.readLine(), " ");
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			makeSet();
			
			//입력을 하나씩 받으면서 연산 수행
			for(int m = 0; m < M; m++) {
				tokens = new StringTokenizer(input.readLine(), " ");
				//명령
				String oper = tokens.nextToken();
				//a
				int a = Integer.parseInt(tokens.nextToken());
				//b
				int b = Integer.parseInt(tokens.nextToken());
				
				switch(oper) {
				case "0": //결합 연산
					union(a, b);
					break;
					
				case "1": //찾기 연산
					int repreA = findSet(a);
					int repreB = findSet(b);
					output.append(repreA == repreB ? "1" : "0");
					break;
				}
			}
			output.append("\n");
		}
		System.out.println(output);
	}
	
	//서로소인 집합으로 만들기
	static void makeSet() {
		repres = new int[N+1]; //값이 0이면 그녀석은 짱
		rank = new int[N+1];
		for(int i = 0; i < repres.length; i++) {
			repres[i] = i;
			rank[i] = 1;
		}
	}
	
	static int findSet(int v) {
		if(repres[v] == v) { //얘가 짱일 때
			return v; //짱 반환
		} else {
			//path compression
			return repres[v] = findSet(repres[v]);
		}		
	}
	
	static void union(int a, int b) {
		//각 짱을 찾기
		a = findSet(a);
		b = findSet(b);
		
		if(a != b) {
			if(rank[a] == rank[b]) { //같으면 임의로 랭크를 1 높여줌
				rank[a]++;
			}
			
			//랭크 작은 녀석의 짱을 랭크가 큰 녀석으로 붙여줌
			if(rank[a] < rank[b])
				repres[b] = a; //b의 짱을 a로 = 통합	
			else
				repres[a] = b; //b의 짱을 a로 = 통합	
		}
	}
	
	static String src = "1\r\n" + 
			"7 8\r\n" + 
			"0 1 3\r\n" + 
			"1 1 7\r\n" + 
			"0 7 6\r\n" + 
			"1 7 1\r\n" + 
			"0 3 7\r\n" + 
			"0 4 2\r\n" + 
			"0 1 1\r\n" + 
			"1 1 1";
}
