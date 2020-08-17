import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name SWEA_모의 SW 역량테스트_2382_미생물격리
* @date 2020.08.17
* @link https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl&categoryId=AV597vbqAH0DFAVl&categoryType=CODE
* @mem
* @time
* @caution 
* [고려사항] 
* [입력사항] 테스트 케이스의 개수 T / 구역의 한 변에 있는 셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K / 미생물 군집 K개의 정보(세로 위치, 가로 위치, 미생물 수, 이동 방향)
* [출력사항] M시간 후 남아 있는 미생물 수의 총 합
* 
* (상: 1, 하: 2, 좌: 3, 우: 4). 
* 살아남은 미생물 수 = 원래 미생물 수를 2로 나눈 후 소수점 이하를 버림 한 값
* 동시에 미생물이 같은 칸에 이동하여 동시에 미생물 군집이 합쳐진다.
* 합쳐 진 군집의 미생물 수는 군집들의 미생물 수의 합이며, 이동 방향은 군집들 중 미생물 수가 가장 많은 군집의 이동방향이 된다.
*/

public class SWEA_D0_2382_미생물격리_upgrade {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    static int T, N, M, K, Answer = 0;
    
    static List<Microbe> microbes, check;
    static int[][] cnt;
    
    static int[][] dirs = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws Exception {
        input = new BufferedReader(new StringReader(src));
        
        //테스트케이스 개수 입력
        T = Integer.parseInt(input.readLine());
        for(int tc = 1; tc <= T; tc++) {
            //데이터 입력
            tokens = new StringTokenizer(input.readLine()); 
            N = Integer.parseInt(tokens.nextToken()); //구역의 한 변에 있는 셀의 개수
            M = Integer.parseInt(tokens.nextToken()); //격리 시간
            K = Integer.parseInt(tokens.nextToken()); //미생물 군집의 개수
                      
            Answer = 0; //일단 미생물 수의 총 합을 구한 뒤, 약품으로 죽은 미생물(cnt/2 +1)을 빼나갈 예정.
            microbes = new ArrayList<>();
            check = new ArrayList<>();
            cnt = new int[N][N];
            int c, r, cnt, dir;
            for(int i = 0; i < K; i++) {
                tokens = new StringTokenizer(input.readLine()); 
                r = Integer.parseInt(tokens.nextToken()); //세로 위치   
                c = Integer.parseInt(tokens.nextToken()); //가로 위치
                cnt = Integer.parseInt(tokens.nextToken()); //미생물 수
                dir = Integer.parseInt(tokens.nextToken()); //이동 방향
                microbes.add(new Microbe(r, c, cnt, dir));
                Answer += cnt;
            }
                        
            //알고리즘    
            int hours = 0;
            while(hours != M) { //M시간이 될 때까지 반복
            	move(); //미생물 이동시키고 좌표, 미생물 수, 방향 조정
            	microbes.clear();
            	check(); //같은 좌표에 있는 미생물들은 합치고 다시 어레이리스트 만들기            	
            	check.clear();
            	hours++;
            }                        
            output.append("#").append(tc).append(" ").append(Answer).append("\n");
        }
        System.out.println(output);
    }
    
    static void move() {
    	Microbe m;
    	
        for(int i = 0; i < microbes.size(); i++) { 
        	m = microbes.get(i);
            m.r += dirs[m.dir][0];
            m.c += dirs[m.dir][1];
            
            if(m.r == 0 || m.r == N-1 || m.c == 0 || m.c == N-1) { //약품이 있는 위치면 절반 타노스하고 방향만 바꾸기
            	Answer -= (m.cnt - m.cnt/2); //죽은 녀석 빼기                	
            	m.cnt = m.cnt/2; //절반 죽고 살아난 녀석만 저장                    
                if(m.cnt == 0) { //미생물이 모두 죽으면
                    continue; //check에 안넣고 건너뛰기
                }
                switch(m.dir){//방향 바꾸기
                    case 1:
                        m.dir = 2;
                        break;
                    case 2:
                        m.dir = 1;
                        break;
                    case 3:
                        m.dir = 4;
                        break;
                    case 4:
                        m.dir = 3;
                        break;
                } 
            }   
            cnt[m.r][m.c]++; //해당 좌표에 있는 미생물집단 수 증가
            check.add(m); //업데이트 결과 저장
        }
    }
    
    static void check() {
    	Microbe m;   	
    	Collections.sort(check); //행 오름차순-열 오름차순-미생물 수 오름차순 정렬  
    	
    	for(int i = 0; i < check.size(); i++) {
    		m = check.get(i);
    		if(cnt[m.r][m.c] > 1) { //미생물 집단 수가 1개 이상일 때 = 하나로 합치기
    			for(int j = 1; j < cnt[m.r][m.c]; j++) {
    				i++;	
    				m.cnt += check.get(i).cnt;
    				m.dir = check.get(i).dir;
    			}
    		}
    		microbes.add(m); //결과 microbes에 넣기
    		cnt[m.r][m.c] = 0; //다시 집단 수 초기화해주기
    	} 	
    }
    
    static class Microbe implements Comparable<Microbe>{ //미생물 정보를 저장하는 객체
        //세로 위치, 가로 위치, 미생물 수, 이동 방향
        int r, c, cnt, dir;
        public Microbe(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
        
        @Override
        public int compareTo(Microbe o) {
            //행 오름차순, 열 오름차순, 미생물 수 오름차순
            if(this.r == o.r) {
                if(this.c == o.c) {
                    return Integer.compare(this.cnt, o.cnt);
                }else {
                    return Integer.compare(this.c, o.c);
                }
            }else {
                return Integer.compare(this.r, o.r);
            }            
        }
        
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("(");
            builder.append(r);
            builder.append(", ");
            builder.append(c);
            builder.append(")");
            builder.append(", cnt=");
            builder.append(cnt);
            builder.append(", dir=");
            switch(dir) { //(상: 1, 하: 2, 좌: 3, 우: 4)
                case 1:
                    builder.append("상");
                    break;
                case 2:
                    builder.append("하");
                    break;
                case 3:
                    builder.append("좌");
                    break;
                case 4:
                    builder.append("우");
                    break;
            }
            return builder.toString();
        }     
    }

    static String src = "10\r\n" + 
            "7 2 9\r\n" + 
            "1 1 7 1\r\n" + 
            "2 1 7 1\r\n" + 
            "5 1 5 4\r\n" + 
            "3 2 8 4\r\n" + 
            "4 3 14 1\r\n" + 
            "3 4 3 3\r\n" + 
            "1 5 8 2\r\n" + 
            "3 5 100 1\r\n" + 
            "5 5 1 1\r\n" + 
            "10 17 46\r\n" + 
            "7 5 724 2\r\n" + 
            "7 7 464 3\r\n" + 
            "2 2 827 2\r\n" + 
            "2 4 942 4\r\n" + 
            "4 5 604 4\r\n" + 
            "7 2 382 1\r\n" + 
            "6 5 895 3\r\n" + 
            "8 7 538 4\r\n" + 
            "6 1 299 4\r\n" + 
            "4 7 811 4\r\n" + 
            "3 6 664 2\r\n" + 
            "6 8 868 2\r\n" + 
            "7 6 859 2\r\n" + 
            "4 6 778 2\r\n" + 
            "5 4 842 3\r\n" + 
            "1 3 942 1\r\n" + 
            "1 1 805 3\r\n" + 
            "3 2 350 3\r\n" + 
            "2 5 623 2\r\n" + 
            "5 3 840 1\r\n" + 
            "7 1 308 4\r\n" + 
            "1 8 323 3\r\n" + 
            "2 3 82 3\r\n" + 
            "2 6 115 2\r\n" + 
            "8 3 930 1\r\n" + 
            "6 2 72 1\r\n" + 
            "2 1 290 3\r\n" + 
            "4 8 574 4\r\n" + 
            "8 5 150 3\r\n" + 
            "8 2 287 2\r\n" + 
            "2 8 909 2\r\n" + 
            "2 7 588 2\r\n" + 
            "7 3 30 3\r\n" + 
            "5 8 655 3\r\n" + 
            "3 8 537 1\r\n" + 
            "4 2 350 3\r\n" + 
            "5 6 199 1\r\n" + 
            "5 5 734 2\r\n" + 
            "3 3 788 1\r\n" + 
            "8 4 893 1\r\n" + 
            "1 4 421 4\r\n" + 
            "6 3 616 2\r\n" + 
            "1 2 556 4\r\n" + 
            "7 8 8 1\r\n" + 
            "5 2 702 2\r\n" + 
            "4 4 503 3\r\n" + 
            "10 5 28\r\n" + 
            "3 3 796 1\r\n" + 
            "7 2 798 2\r\n" + 
            "2 6 622 1\r\n" + 
            "3 5 179 3\r\n" + 
            "7 8 888 4\r\n" + 
            "5 8 634 3\r\n" + 
            "1 8 646 1\r\n" + 
            "3 7 433 4\r\n" + 
            "6 7 416 1\r\n" + 
            "2 7 651 3\r\n" + 
            "6 4 476 2\r\n" + 
            "5 6 712 4\r\n" + 
            "1 7 869 4\r\n" + 
            "6 1 789 2\r\n" + 
            "8 8 585 3\r\n" + 
            "7 6 426 1\r\n" + 
            "1 5 154 2\r\n" + 
            "1 2 692 1\r\n" + 
            "2 4 549 3\r\n" + 
            "2 1 60 2\r\n" + 
            "4 8 996 4\r\n" + 
            "8 2 437 2\r\n" + 
            "3 6 195 2\r\n" + 
            "1 3 734 4\r\n" + 
            "3 8 355 2\r\n" + 
            "1 1 945 1\r\n" + 
            "2 5 558 2\r\n" + 
            "7 7 144 2\r\n" + 
            "10 22 26\r\n" + 
            "2 2 450 4\r\n" + 
            "6 3 659 1\r\n" + 
            "5 8 24 2\r\n" + 
            "3 7 649 2\r\n" + 
            "3 2 22 3\r\n" + 
            "1 3 905 4\r\n" + 
            "7 8 625 3\r\n" + 
            "6 7 824 3\r\n" + 
            "7 3 159 1\r\n" + 
            "2 7 297 4\r\n" + 
            "7 2 270 2\r\n" + 
            "4 5 985 1\r\n" + 
            "7 1 627 2\r\n" + 
            "3 4 625 4\r\n" + 
            "8 5 972 4\r\n" + 
            "6 6 432 4\r\n" + 
            "6 8 142 1\r\n" + 
            "7 7 900 1\r\n" + 
            "4 1 974 2\r\n" + 
            "4 2 760 4\r\n" + 
            "1 4 550 2\r\n" + 
            "5 7 624 4\r\n" + 
            "4 6 694 1\r\n" + 
            "4 3 593 3\r\n" + 
            "3 1 152 4\r\n" + 
            "1 8 926 1\r\n" + 
            "10 7 15\r\n" + 
            "3 4 227 1\r\n" + 
            "4 7 109 1\r\n" + 
            "3 7 487 2\r\n" + 
            "2 3 627 2\r\n" + 
            "6 1 520 4\r\n" + 
            "7 3 596 4\r\n" + 
            "2 6 525 4\r\n" + 
            "1 5 116 3\r\n" + 
            "7 7 771 4\r\n" + 
            "4 4 520 2\r\n" + 
            "7 5 763 1\r\n" + 
            "5 4 829 3\r\n" + 
            "5 2 578 3\r\n" + 
            "6 8 200 2\r\n" + 
            "3 8 760 4\r\n" + 
            "10 24 12\r\n" + 
            "6 5 887 2\r\n" + 
            "2 3 428 1\r\n" + 
            "2 1 540 2\r\n" + 
            "8 1 356 4\r\n" + 
            "1 7 485 4\r\n" + 
            "5 1 357 3\r\n" + 
            "7 6 271 2\r\n" + 
            "6 2 22 1\r\n" + 
            "6 1 41 2\r\n" + 
            "8 2 565 2\r\n" + 
            "8 5 855 1\r\n" + 
            "6 3 734 1\r\n" + 
            "10 22 44\r\n" + 
            "2 2 963 1\r\n" + 
            "8 4 635 4\r\n" + 
            "4 1 938 4\r\n" + 
            "8 7 511 3\r\n" + 
            "6 8 825 4\r\n" + 
            "6 7 934 3\r\n" + 
            "3 7 701 4\r\n" + 
            "2 7 534 2\r\n" + 
            "5 2 705 1\r\n" + 
            "3 5 300 2\r\n" + 
            "6 2 855 4\r\n" + 
            "7 7 877 4\r\n" + 
            "1 7 443 1\r\n" + 
            "1 2 313 1\r\n" + 
            "3 3 932 2\r\n" + 
            "1 8 831 2\r\n" + 
            "1 1 90 2\r\n" + 
            "2 6 145 3\r\n" + 
            "2 3 740 4\r\n" + 
            "5 3 759 4\r\n" + 
            "1 6 181 1\r\n" + 
            "8 6 608 4\r\n" + 
            "5 6 556 2\r\n" + 
            "2 4 541 4\r\n" + 
            "2 1 174 2\r\n" + 
            "6 1 601 1\r\n" + 
            "7 5 84 4\r\n" + 
            "4 3 970 3\r\n" + 
            "8 8 503 1\r\n" + 
            "3 4 171 3\r\n" + 
            "5 7 913 4\r\n" + 
            "8 1 232 3\r\n" + 
            "7 6 539 4\r\n" + 
            "3 8 648 1\r\n" + 
            "8 2 944 2\r\n" + 
            "2 5 508 2\r\n" + 
            "5 1 87 1\r\n" + 
            "5 8 88 4\r\n" + 
            "2 8 681 2\r\n" + 
            "1 5 758 2\r\n" + 
            "3 1 690 3\r\n" + 
            "6 4 620 3\r\n" + 
            "5 4 783 1\r\n" + 
            "6 6 748 1\r\n" + 
            "10 9 38\r\n" + 
            "2 7 955 1\r\n" + 
            "7 7 25 4\r\n" + 
            "4 2 496 2\r\n" + 
            "1 4 342 1\r\n" + 
            "7 5 72 1\r\n" + 
            "3 7 429 2\r\n" + 
            "5 2 812 3\r\n" + 
            "8 6 36 2\r\n" + 
            "1 6 994 3\r\n" + 
            "1 5 838 1\r\n" + 
            "3 4 131 4\r\n" + 
            "7 2 11 2\r\n" + 
            "6 3 650 3\r\n" + 
            "7 3 353 2\r\n" + 
            "1 7 454 2\r\n" + 
            "8 3 256 4\r\n" + 
            "5 5 213 2\r\n" + 
            "6 5 80 1\r\n" + 
            "2 1 676 4\r\n" + 
            "4 6 561 3\r\n" + 
            "2 5 653 3\r\n" + 
            "3 5 923 3\r\n" + 
            "8 2 259 3\r\n" + 
            "4 4 781 2\r\n" + 
            "1 1 313 2\r\n" + 
            "3 6 938 3\r\n" + 
            "2 6 700 3\r\n" + 
            "4 1 215 2\r\n" + 
            "4 8 39 3\r\n" + 
            "5 1 954 3\r\n" + 
            "6 7 774 1\r\n" + 
            "5 8 541 4\r\n" + 
            "3 1 885 4\r\n" + 
            "7 8 867 2\r\n" + 
            "2 8 825 1\r\n" + 
            "5 6 598 3\r\n" + 
            "6 6 80 3\r\n" + 
            "8 1 405 2\r\n" + 
            "10 16 11\r\n" + 
            "5 7 87 3\r\n" + 
            "2 5 686 1\r\n" + 
            "6 7 64 2\r\n" + 
            "6 8 873 3\r\n" + 
            "5 6 762 2\r\n" + 
            "8 4 268 3\r\n" + 
            "7 3 307 4\r\n" + 
            "1 7 809 3\r\n" + 
            "5 5 293 3\r\n" + 
            "5 1 345 3\r\n" + 
            "4 1 114 4\r\n" + 
            "10 8 19\r\n" + 
            "3 1 52 4\r\n" + 
            "6 8 423 3\r\n" + 
            "7 3 498 4\r\n" + 
            "7 5 633 3\r\n" + 
            "7 7 392 3\r\n" + 
            "6 6 458 4\r\n" + 
            "3 8 830 3\r\n" + 
            "5 1 799 3\r\n" + 
            "1 1 540 3\r\n" + 
            "4 8 567 3\r\n" + 
            "1 6 897 3\r\n" + 
            "5 4 230 1\r\n" + 
            "2 6 229 3\r\n" + 
            "1 5 147 1\r\n" + 
            "4 1 754 2\r\n" + 
            "3 3 569 1\r\n" + 
            "7 8 515 4\r\n" + 
            "2 4 528 4\r\n" + 
            "2 1 962 2\r\n" + 
            "";
}
