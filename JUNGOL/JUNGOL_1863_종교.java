import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name JUNGOL_1863_종교
* @date 2020.08.04
* @link http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1136&sca=99&sfl=wr_hit&stx=1863
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 정수 n , m 이 주어진다. 다음 m 라인은 두 정수 i , j 가 주어진다.
*			i, j 는 i번 학생과 j번 학생이 같은 종교를 가진 학생의 쌍이다(1≤i, j≤n).
* [출력사항]
*/

public class JUNGOL_1863_종교 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int N, M, Answer = 0;
    static int[] repres; //짱 찾기
    static int[] rank; //각 노드의 등급
    
	public static void main(String[] args) throws IOException {
		input = new BufferedReader(new StringReader(src));
		tokens = new StringTokenizer(input.readLine(), " ");
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		
		makeSet();
		
		for(int m = 0; m < M; m++) {
			tokens = new StringTokenizer(input.readLine(), " ");
			int i = Integer.parseInt(tokens.nextToken());
			int j = Integer.parseInt(tokens.nextToken());
			
			union(i, j);	
		}	
		
		//종교의 수
        for(int i = 1; i<=N; i++) {
            if(i == repres[i]) {
            	Answer++;
            }
        }
        System.out.println(Answer);	
	}

	//서로소인 집합으로 만들기
	static void makeSet() {
		repres = new int[N+1]; //값이 0이면 그녀석은 짱
		rank = new int[N+1];
		for(int i = 1; i <= N; i++) {
			repres[i] = i;
		}
	}
	
	//짱 찾아서 반환
	static int findSet(int v) {
		if(repres[v] == v) { //얘가 짱일 때
			return v; //짱 반환
		} 
		//path compression
		return repres[v] = findSet(repres[v]);	
	}
	
	//병합
    public static void union(int a, int b) {
        int pa = findSet(a);
        int pb = findSet(b);

        if(rank[pa]<rank[pb]) {  // 트리의 깊이를 서로 비교해서 작은것을 큰것아래에 붙인다.(트리의 깊이 최소화)
        	repres[pa] = pb;  
        }
        else {
        	repres[pb] = pa;
            if(rank[pa] == rank[pb]) {
                rank[pa]++;
            }
        }
    }	


	static String src = "10 9\r\n" + 
			"1 2\r\n" + 
			"1 3\r\n" + 
			"1 4\r\n" + 
			"1 5\r\n" + 
			"1 6\r\n" + 
			"1 7\r\n" + 
			"1 8\r\n" + 
			"1 9\r\n" + 
			"1 10";

}
