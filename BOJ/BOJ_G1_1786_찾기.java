import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_G1_1786_찾기
* @date 2020.09.01
* @link https://www.acmicpc.net/problem/1786
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 타겟 문자열 T, 패턴 문자열 P
* [출력사항] T 중간에 P가 몇 번 나타나는지를 나타내는 음이 아닌 정수, P가 나타나는 위치를 차례대로 출력
* 
* 문자열 내에 공백이 포함되어 있을 수도 있음에 유의한다. 물론 공백도 하나의 문자로 인정된다.
* KMP 알고리즘 사용
* 
*/

public class BOJ_G1_1786_찾기 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = null;
    static String pattern, target;
    static List<Integer> answers;
    
    public static void main(String[] args) throws Exception {
        //입력
        target = input.readLine();
        pattern = input.readLine();
        
        //알고리즘
        answers = new ArrayList<Integer>();
        kmp();
        
        //출력        
        System.out.println(answers.size()); //패턴과 일치하는 구간 수
        output = new StringBuilder();
        for(int idx : answers) {
            output.append(idx).append(" "); //패턴과 일치하는 시작점
        }
        System.out.println(output);

    }
    
    static void kmp() {
        int patternLen = pattern.length();
        int targetLen = target.length();
        int[] pi = getPi();

        int begin = 0, matched = 0;
        while (begin + patternLen <= targetLen) {
            if (matched < patternLen && pattern.charAt(matched) == target.charAt(begin + matched)) {
                matched++;

                if (matched == patternLen) {
                    answers.add(begin+1);
                }
            }
            else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin = begin + matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }

        }
    }

    static int[] getPi() {
        int patternLen = pattern.length();
        int[] pi = new int[patternLen];

        int begin = 1, matched = 0;
        while (begin + matched < patternLen) {
            if (pattern.charAt(matched) == pattern.charAt(begin + matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            }
            else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin = begin + matched - pi[matched - 1]; 
                    matched = pi[matched - 1];
                }
            }
        }

        return pi;
    }

}
