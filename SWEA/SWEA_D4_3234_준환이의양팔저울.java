import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_D4_3234_준환이의양팔저울
* @date 2020.08.28
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWAe7XSKfUUDFAUw&categoryId=AWAe7XSKfUUDFAUw&categoryType=CODE
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 테스트 케이스의 수 T, 추의 개수 N, N개의 추 무게 정보
* [출력사항] 무게추를 올리는 과정에서 오른쪽 위에 올라가있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 커지지 않는 경우의 수
* 
* 모든 무게 추를 양팔저울 위에 올리는 순서는 총 N!가지
* 각 추를 양팔저울의 왼쪽에 올릴 것인지 오른쪽에 올릴 것인지를 정해야 해서 총 2N * N!가지의 경우
* 
* 1. 무게에 대한 가지치기: 왼쪽 < 오른쪽 -> 중지
* 2. 왼쪽에 올린 추의합 > 오른쪽 추의 합 + 나머지 추의 합: 더 올릴 필요 없이 가능 --> 나머지 추를 배치하는 경우의 수만 계산
* 
* 
*/

public class SWEA_D4_3234_준환이의양팔저울 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output = new StringBuilder();
    static StringTokenizer tokens = null;
    static int T, N, total, Answer;
    static int[] pow, fac, nums;
    
    public static void main(String[] args) throws Exception {
       input = new BufferedReader(new StringReader(src));
       
       //전처리
       preProcess();
       
       //테스트케이스 수
       T = Integer.parseInt(input.readLine());
       for(int t = 1; t <= T; t++) {
           //입력
           N = Integer.parseInt(input.readLine());                      
           tokens = new StringTokenizer(input.readLine(), " ");
           nums = new int[N];
           total = 0;
           for(int i = 0; i < N; i++) {
               nums[i] = Integer.parseInt(tokens.nextToken());
               total += nums[i];
           }
           
           //알고리즘
           Answer = 0;
           makePermutation(N, new boolean[N], 0, 0, total);
       
           //결과 저장
           output.append("#").append(t).append(" ").append(Answer).append("\n");
       }
       System.out.println(output); //출력
    }
    
    public static void makePermutation(int n, boolean[] visited, int left, int right, int remain){
        if(left < right) { //왼쪽보다 오른쪽이 커서 실패
            return;
        }
        
        if(left >= right + remain) { //무조건 왼쪽이 크니까 더 살펴볼 필욘 없고 뻔한 성공개수만 추가
            Answer += pow[n] * fac[n];
            return;
        }
        
        if(n == 0) { //성공
            Answer++;
            return;
        }
        
        //다음 노드 탐색
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                makePermutation(n-1, visited, left + nums[i], right, remain - nums[i]); //왼쪽에 올리는 경우
                makePermutation(n-1, visited, left, right+ nums[i], remain - nums[i]); //오른쪽에 올리는 경우
                visited[i] = false;
            }
        }        
    }        

    
    //전처리: 미리 2의 n승과 팩토리얼 구해 놓기
    static void preProcess() {
        pow = new int[10];
        fac = new int[10];
        
        pow[0] = fac[0] = 1;
        for(int i = 1; i < pow.length; i++) {
            pow[i] = pow[i-1]*2;
            fac[i] = fac[i-1]*i;
        }
    }
    
    static String src = "3\r\n" + 
            "3\r\n" + 
            "1 2 4\r\n" + 
            "3\r\n" + 
            "1 2 3\r\n" + 
            "9\r\n" + 
            "1 2 3 5 6 4 7 8 9";
}
