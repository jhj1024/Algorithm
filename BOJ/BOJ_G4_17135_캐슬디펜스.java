import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G4_17135_캐슬디펜스
* @date 2020.08.07
* @link https://www.acmicpc.net/problem/17135
* @mem
* @time
* @caution 이 게임은 궁수의 처음 배치가 중요하다
* [고려사항] 격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|로 계산
* [입력사항] 행 길이, 열 길이, 궁수의 최대 공격 거리
* [출력사항] 궁수 3명의 공격으로 제거할 수 있는 적의 최대 수
* 
* R행에 궁수 3명을 배치: 조합 + 공격 및 적군 이동: 시뮬레이션
* 
*/

public class BOJ_G4_17135_캐슬디펜스 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, D, deadMan = 0, Answer = Integer.MIN_VALUE;
    static List<Enemy> positions = new ArrayList<Enemy>();
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        D = Integer.parseInt(tokens.nextToken());

        for(int i = 0; i < R; i++) {
            tokens = new StringTokenizer(input.readLine());
            for(int j = 0; j < C; j++) {           
                if(Integer.parseInt(tokens.nextToken()) == 1){ //적군이면
                    positions.add(new Enemy(i, j)); //좌표 저장
                }
            }
        }
        
        //궁수 배치하기 = 순서 상관없이 3명 배치이므로 조합.
        placeArcher(3, 0, new int[3]);

        //결과 출력
        System.out.println(Answer);
        
    }
    
    //궁수 세 명 배치하는 조합
    static void placeArcher(int n, int start, int[] temp) { 
        if (n == 0) {
            check(temp); //3명배치 완료. 공격하러 가기
            return;
        }

        for (int i = start; i < C; i++) {
            temp[n - 1] = i;
            placeArcher(n - 1, i + 1, temp);
        } 
    }
    
    
    //궁수를 이용하여 적군을 공격하고 적군을 이동시키는 시뮬레이션
    static void check(int[] archers) { 
        //적군 위치정보 리스트 복사
        List<Enemy> enemies = new ArrayList<Enemy>();
        for(int i = 0; i < positions.size(); i++) {
            enemies.add(new Enemy(positions.get(i)));
        }
        
        deadMan = 0; //죽은 병사 수 초기화

        while(!enemies.isEmpty()) { //모든 적군이 사라질 때까지 반복
            attack(archers, enemies); //궁수가 공격할 타겟 찾기
            move(enemies); //타겟을 공격하고, 성에 도착한 적군을 없애고, 나머지 적군은 한 칸 전진시키기
        } 

        Answer = Math.max(Answer, deadMan); //죽인 적군의 수를 최대값을 업데이트    
    }

  //궁수가 공격할 적 찾기
    static void attack(int[] archers, List<Enemy> enemies) {       
        for(int i = 0; i < archers.length; i++) {
            List<Enemy> target = new ArrayList<Enemy>();
            int archer = archers[i];
            
            //해당 궁수가 적군을 쏠 수 있는 거리(D)에 있는지 확인
            for(int e = 0; e < enemies.size(); e++) {
                Enemy enemy = enemies.get(e); //적군 하나 가져와서
                enemy.d = Math.abs(archer - enemy.c) + Math.abs(R - enemy.r); //궁수와 적군 사이의 거리 계산
                if(enemy.d <= D) { //궁수가 공격 가능한 거리 안에 위치해있으면
                    target.add(enemy); //바로 죽이지(리스트에서 삭제하지)않고 target리스트에 저장
                }
            }
            
            //타겟이 있다면 정렬해서 가장 우선순위가 높은 적군 한명을 공격대상으로 설정
            if(!target.isEmpty()) {
                Collections.sort(target); //가장 왼쪽의 녀석을 먼저 죽여야하므로 정렬
                target.get(0).isTargeted = true; //한 놈만 공격 대상으로 지정
            }
        }
    }
    
    //타겟을 공격하고, 성에 도착한 적군을 없애고, 나머지 적군은 한 칸 전진시키기
    static void move(List<Enemy> enemies) {       
        for(int e = 0; e < enemies.size();) {
            Enemy enemy = enemies.get(e);
            if(enemy.isTargeted) {
                enemies.remove(enemy); //궁수가 공격하여 적군 사라짐
                deadMan++;   
            }else if(enemy.r == R-1) {
                enemies.remove(enemy); //성에 도착하여 적군 사라짐
            }else {
                enemy.r++; //살아남은 적군은 한 칸 전진
                e++;
            }
        }  
    }
      
    static class Enemy implements Comparable<Enemy>{ //적의 좌표와 궁수와의 거리를 저장하는 객체
        int r, c, d; //d: 궁수와의 거리
        boolean isTargeted; //궁수의 공격대상인지 확인

        public Enemy(int r, int c)  { //처음에는 거리정보를 모르니까 거리정보 빼고 좌표정보만 저장
            this.r = r;
            this.c = c;
        }
        
        public Enemy(Enemy e)  { //copy by value를 위한 생성자
            this.r = e.r;
            this.c = e.c;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(r);
            builder.append(", ");
            builder.append(c);
            builder.append(" d= ");
            builder.append(d);
            return builder.toString();
        }

        @Override
        public int compareTo(Enemy o) {
            if(this.d == o.d) {
                return Integer.compare(this.c, o.c); //거리가 같으면 왼쪽에 있는게 더 우선
            }else {
                return Integer.compare(this.d, o.d); //거리 오름차순
            }
        }
    }
    
    static String src = "6 5 2\r\n" + 
            "1 0 1 0 1\r\n" + 
            "0 1 0 1 0\r\n" + 
            "1 1 0 0 0\r\n" + 
            "0 0 0 1 1\r\n" + 
            "1 1 0 1 1\r\n" + 
            "0 0 1 0 0";
}
