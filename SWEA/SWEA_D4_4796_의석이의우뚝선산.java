import java.util.Scanner;

/**
* @author JUNG
* @name SWEA_D4_4796_의석이의우뚝선산
* @date 2020.09.06
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWS2h6AKBCoDFAVT&categoryId=AWS2h6AKBCoDFAVT&categoryType=CODE
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 테스트 케이스의 수 T, 산의 개수 N, N개의 산 높이 정보
* [출력사항] 우뚝 선 산이 될 수 있는 구간의 개수
* 
* bufferedReader를 사용하면 런타임 에러가 발생하기 때문에 Scanner 사용
* 
*/

public class SWEA_D4_4796_의석이의우뚝선산 {
    static StringBuilder output = new StringBuilder();
    static int T, N, Answer;
    static int[] heights;
    
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        
        //테스트케이스 수
        T = sc.nextInt();
        for(int t = 1; t <= T; t++) {
            //입력
            N = sc.nextInt();
            heights = new int[N];
            for(int i = 0; i < N; i++) {
                heights[i] = sc.nextInt();
            }
            
            //알고리즘
            Answer = 0;
            search();
            
            output.append("#").append(t).append(" ").append(Answer).append("\n");
        }
        System.out.println(output);
        sc.close();
    }
    
    public static void search() {
        //start, max, end 인덱스 구하기        
        int start = -1, max = -1, end = -1;
        
        for(int i = 1; i < N; i++) {
            if(heights[i-1] < heights[i]) {
                if(start == -1) { //오르막길 시작
                    start = i-1;
                }else if(max != -1){ //내리막길 끝
                    end = i-1;
                    cases(start, max, end);
                    start = i-1; max = -1; end = -1; //초기화
                }
            }
            else { //heights[i-1] > heights[i]
              if(start != -1 && max == -1) { //우뚝 선 곳
                  max = i-1;
              } 
            }
        }
        
        if(start != -1 && max != -1 && heights[N-2] > heights[N-1]) {
            end = N-1;
            cases(start, max, end);
        }  
    }
    
    public static void cases(int start, int max, int end) {
        //우뚝선 산이 될 수 있는 경우의 수 계산 -> start ~ max-1 * max+1~end
        Answer += ((max-start) * (end-max));
    }

//    static String src = "3\r\n" + 
//        "3\r\n" + 
//        "1 3 2\r\n" + 
//        "3\r\n" + 
//        "3 2 1\r\n" + 
//        "9\r\n" + 
//        "1 4 6 5 3 7 9 2 8";
}
