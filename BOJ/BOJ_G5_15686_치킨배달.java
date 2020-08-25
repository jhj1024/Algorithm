import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G5_15686_치킨배달
* @date 2020.08.25
* @link https://www.acmicpc.net/problem/15686
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 도시 크기 N, 도시에 남을 치킨집 최대 수 M, N*N크기의 도시 정보
* [출력사항] 폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값
* 
* 무조건 M개를 고르는 것이 아니라, 최대 M개....
* 집과 치킨집의 좌표를 별도로 저장하고 최대 M개를 고르는 경우의 수로 치킨집을 뽑아 그때마다의 도시의 치킨 거리 구하기
* 
*/

public class BOJ_G5_15686_치킨배달 {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens = null;
	static int N, M, Answer = Integer.MAX_VALUE;
	static List<Map> houses;
	static List<Map> chickens;
	
	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new StringReader(src));
		
		//입력
		tokens = new StringTokenizer(input.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
			
		houses = new ArrayList<Map>();
		chickens = new ArrayList<Map>();
		for(int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(input.readLine());
			for(int j = 1; j <= N; j++) {
				int info = Integer.parseInt(tokens.nextToken());
				if(info == 1) {
					houses.add(new Map(i, j));
				}else if(info == 2) {
					chickens.add(new Map(i, j));
				}
			}
		}
		
		//알고리즘 (1~M개의 치킨집을 고르는 조합을 만들고 그때마다 치킨 거리 구하기)
		for(int n = 1; n <= M; n++) {
			MakeCombination(n, 0, new Map[n]);
		}
		
		System.out.println(Answer);
	}
	
	static void MakeCombination(int n, int start, Map[] temp) {
		if(n == 0) {
			//System.out.println(Arrays.toString(temp));
			chickenDistance(temp);
			return;
		}
		
		for(int i = start; i < chickens.size(); i++) {
			temp[n-1] = chickens.get(i);
			MakeCombination(n-1, i+1, temp);
		}
	}
	
	static void chickenDistance(Map[] selected) {
		int[] distance = new int[houses.size()];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		for(int i = 0; i < selected.length; i++) {
			for(int j = 0; j < houses.size(); j++) {
				int dtc = Math.abs(selected[i].i - houses.get(j).i) + Math.abs(selected[i].j - houses.get(j).j);
				distance[j] = Math.min(distance[j], dtc);
			}
		}
		
		int sum = 0;
		for(int d : distance) {
			sum += d;
		}
		
		Answer = Math.min(Answer, sum);		
	}

	static class Map{
		int i, j;

		public Map(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("(");
			builder.append(i);
			builder.append(", ");
			builder.append(j);
			builder.append(")");
			return builder.toString();
		}
		
	}
	
	
	static String src = "5 1\r\n" + 
			"1 2 0 2 1\r\n" + 
			"1 2 0 2 1\r\n" + 
			"1 2 0 2 1\r\n" + 
			"1 2 0 2 1\r\n" + 
			"1 2 0 2 1";
}
