import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_17406_배열돌리기4
* @date 2020.08.26
* @link https://www.acmicpc.net/problem/17406
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 배열 크기 R*S, 회전 연산의 수 K, R*S 크기의 배열 정보, 회전축 좌표(r,c), 회전 칸 수 s
* [출력사항] 배열 A의 값의 최솟값(= 각 행에 있는 모든 수의 합 중 최솟값)
* 
* 회전 연산이 두 개 이상이면, 연산을 수행한 순서에 따라 최종 배열이 다르다.
* 순열로 회전 경우의 수 만들고 그 순서대로 회전한 결과에서 최소값 찾기
*/

public class BOJ_G4_17406_배열돌리기4 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, K, r, c, s, Answer = Integer.MAX_VALUE; //배열 크기 R*S, 회전 연산의 수 K, 회전축 좌표(r,c), 회전 칸 수 s 
    static int[][] original, map;
    static int[] rtIndexs;
    static RtInfo[] rtInfos;
         
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력       
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());
        
        original = new int[R][C]; //배열 원본 저장
        for(int i = 0; i < R; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j = 0; j < C; j++) {
                original[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        
        rtIndexs = new int[K]; //회전연산정보에 접근할 인덱스(0~K) 저장
        for(int i = 0; i < K; i++) {
            rtIndexs[i] = i;
        }
        
        rtInfos = new RtInfo[K]; //회전 연산 정보 저장
        for(int i = 0; i < K; i++) {
            tokens = new StringTokenizer(input.readLine());
            r = Integer.parseInt(tokens.nextToken())-1;
            c = Integer.parseInt(tokens.nextToken())-1;
            s = Integer.parseInt(tokens.nextToken());
            rtInfos[i] = new RtInfo(r, c, s);
        }        
        
        //알고리즘 (순열 생성 -> 순열 순서대로 회전 -> 각 행의 수를 합한 뒤, 최소값 저장)
        do {
            rotate(); //rtInfos 사용
            check(); //map 사용
        }while(makePermutation(rtIndexs)); //rtInfos 사용
        
        //출력
        System.out.println(Answer);
    }
    
    static boolean makePermutation(int[] numbers) { //순열 생성
        //1. 꼭대기 찾기
        int i = K-1;
        while(i > 0 && numbers[i-1] >= numbers[i]) { //i-1보다 i가 크면 탈출
            --i;
        }
        
        if(i == 0) { //더이상 다음 순열을 못만듬 = 마지막 순열 = 종료
            return false;
        }
        
        //2. i-1위치와 교환할 다음으로 큰 수를 뒤에서부터 찾기
        int j = K-1;
        while(numbers[i-1] >= numbers[j]) { //i-1보다 j가 크면 탈출
            --j;
        }
        
        //3. i-1 위치와 j위치 교환
        swap(numbers, i-1, j);
        
        //4. i위치부터 끝까지 오름차순 정렬
        int k = K-1;
        while(i < k) {
            swap(numbers, i++, k--);
        }
        return true;
    }

    static void rotate() { //회전을 위한 전처리
        map = new int[R][C]; //이차원 배열은 deepCopy해줘야 원본에 영향 안줌
        for(int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, map[i], 0, original[i].length);
        } 
               
        //해당 인덱스의 RtInfo를 꺼내 회전
        for(int i = 0; i < rtIndexs.length; i++) {
            RtInfo rt = rtInfos[rtIndexs[i]];
            rotate(rt);
        }        
    }
    
    static void rotate(RtInfo rotate) { //회전
        //시작
        int rs = rotate.r - rotate.s; 
        int cs = rotate.c - rotate.s;
        
        //끝
        int re = rotate.r + rotate.s;
        int ce = rotate.c + rotate.s;

        // 맨 바깥부터 안쪽으로 들어가면서 처리
        for (int i = 0; i < rotate.s; i++) {
            // 모서리 자료 저장
            int saved = map[rs][cs];
            
            // 왼쪽 아래 -> 위
            for (int r = rs + 1; r <= re; r++) {
                map[r - 1][cs] = map[r][cs];
            }
            // 맨아래를 왼쪽으로
            for (int c = cs + 1; c <= ce; c++) {
                map[re][c - 1] = map[re][c];
            }
            // 맨 오른쪽 아래로 이동
            for (int r = re - 1; r >= rs; r--) {
                map[r + 1][ce] = map[r][ce];
            }
            // 맨 처음 줄 오른쪽으로
            for (int c = ce - 1; c >= cs; c--) {
                map[rs][c + 1] = map[rs][c];
            }
            
            //마지막 부분 값 저장
            map[rs][cs + 1] = saved;

            //안쪽 회전으로 이동
            rs++;
            re--;
            cs++;
            ce--;
        }
    }
    
    static void check() { //각 행에 있는 모든 수의 합 계산 후, 최소값을 answer에 저장
        int cnt;
        for(int[] row : map) { //각 행
            cnt = 0;
            for(int data : row) { //행에 있는 각 요소
                //System.out.print(data + " ");
                cnt += data;
            }
            //System.out.println();
            Answer  = Math.min(Answer, cnt);
        }
    }
    
    public static void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;       
    }
    
    static class RtInfo{
        int r, c, s;

        public RtInfo(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }

        @Override
        public String toString() {            
            StringBuilder builder = new StringBuilder();
            builder.append("(");
            builder.append(r);
            builder.append(", ");
            builder.append(c);
            builder.append(": ");
            builder.append(s);
            return builder.toString();
        }      
    }    
    
    static String src = "5 6 2\r\n" + 
            "1 2 3 2 5 6\r\n" + 
            "3 8 7 2 1 3\r\n" + 
            "8 2 3 1 4 5\r\n" + 
            "3 4 5 1 1 1\r\n" + 
            "9 3 2 1 4 3\r\n" + 
            "3 4 2\r\n" + 
            "4 2 1";
}
