import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
* @author JUNG
* @name Programmers_Lv1_실패율
* @date 2020.09.09
* @link https://programmers.co.kr/learn/courses/30/lessons/42889?language=java
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 전체 스테이지의 개수 N, 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages
* [출력사항] 실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열
* 
* 
* 실패율: 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
* N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
* 만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다
* 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
* 
*/

public class Programmers_Lv1_실패율 {

    public static int[] solution(int N, int[] stages) {
        int[] answer = null;

        int[] count = new int[N + 1];
        for (int i = 0; i < stages.length; i++) {
            if (stages[i] == N + 1) { //이미 모든 스테이지를 통과한 유저는 생략
                continue;
            }

            count[stages[i]]++; //해당 스테이지에 머물러있는 사람 카운트
        }

        List<Stage> result = new ArrayList<>();
        int total = stages.length;
        for (int i = 1; i <= N; i++) {
            if (count[i] == 0) {
                result.add(new Stage(i, 0)); //해당스테이지에 머물러있는 사람이 0명이면 실패율은 0 -> 따로 처리 안해주면 틀렸습니다 나옴
            } else {
                result.add(new Stage(i, ((double) count[i] / (double) total))); //실패율 저장(분자, 분모를 double 처리)
                total -= count[i];//다음스테이지 카운트를 위해 현재스테이지에 머무른 사람 제외시킴   
            }
        }

        Collections.sort(result); //실패율 내림차순 - 스테이지 오름차순으로 정렬

        answer = new int[N];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i).stage; //정렬된 스테이지 순서대로 answer에 저장
        }

        return answer;
    }

    static class Stage implements Comparable<Stage> {
        int stage; //스테이지
        double fail; //실패율

        public Stage(int stage, double fail) {
            this.stage = stage;
            this.fail = fail;
        }

        @Override
        public int compareTo(Stage o) {
            if (this.fail == o.fail) {
                return Integer.compare(this.stage, o.stage); //실패율이 같으면 스테이지 오름차순
            } else {
                return -(Double.compare(this.fail, o.fail)); //실패율 내림차순
            }
        }
    }

    public static void main(String[] args) {
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] answer = solution(5, stages);
        System.out.println(Arrays.toString(answer));

        int[] stages1 = {4, 4, 4, 4, 4};
        int[] answer1 = solution(4, stages1);
        System.out.println(Arrays.toString(answer1));

    }

}
