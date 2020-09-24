import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S5_2628_종이자르기
* @date 2020.09.24
* @link https://www.acmicpc.net/problem/2628
* 
* [입력사항] 종이의 가로와 세로의 길이, 칼로 잘라야하는 점선의 개수, 가로/세로 여부와 점선 번호
* [출력사항] 가장 큰 종이 조각의 넓이를 출력
* 
* 가로: 0, 세로: 1
* 문제에는 정확하게 나오진 않지만, 가로로 자르는 것이 없거나, 세로로 자르는 것이 없는 경우 있음
* 
*/

public class BOJ_S5_2628_종이자르기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static int R, C, N;
    static List<Integer> vtcs, hrzs;

    public static void main(String[] args) throws Exception {
        //입력
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken()); //종이의 가로길이
        C = Integer.parseInt(tokens.nextToken()); //세로길이
        N = Integer.parseInt(input.readLine()); //잘라야하는 점선의 개수

        hrzs = new ArrayList<Integer>(); //가로로 자를 점선
        vtcs = new ArrayList<Integer>(); //세로로 자를 점선

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            int dir = Integer.parseInt(tokens.nextToken());
            int num = Integer.parseInt(tokens.nextToken());

            if (dir == 0) { //가로
                hrzs.add(num);
            } else { //세로
                vtcs.add(num);
            }
        }

        //알고리즘
        int maxh, maxv;

        //가로(실제로는 열 나누기)
        if(hrzs.size() > 0) {
            Collections.sort(hrzs); //정렬
            
            maxh = hrzs.get(0);
            for (int i = 1; i < hrzs.size(); i++) {
                maxh = Math.max(maxh, hrzs.get(i) - hrzs.get(i - 1)); //잘랐을 때 최대 길이 저장
            }
            maxh = Math.max(maxh, C - hrzs.get(hrzs.size()-1));
            
        }else {
            maxh = C;
        }

        
        //세로(실제로는 행 나누기)
        if(vtcs.size() > 0) {
            Collections.sort(vtcs); //정렬

            maxv = vtcs.get(0);
            for (int j = 1; j < vtcs.size(); j++) {
                maxv = Math.max(maxv, vtcs.get(j) - vtcs.get(j - 1)); //잘랐을 때 최대 길이 저장
            }                        
            maxv = Math.max(maxv, R - vtcs.get(vtcs.size()-1));
        }else {
            maxv = R;
        }

        //출력
        
        System.out.println(maxh*maxv);
    }
}
