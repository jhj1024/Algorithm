import java.util.Arrays;

/**
* @author JUNG
* @name Programmers_Lv1_비밀지도
* @date 2020.09.09
* @link https://programmers.co.kr/learn/courses/30/lessons/17681
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 지도의 한 변 크기 n 과 2개의 정수 배열 arr1, arr2
* [출력사항] 원래의 비밀지도를 해독하여 '#', 공백으로 구성된 문자열 배열로 출력
* 
* arr1, arr2는 길이 n인 정수 배열로 주어진다.
* 정수 배열의 각 원소 x를 이진수로 변환했을 때의 길이는 n 이하이다.
* 
*/

public class Programmers_Lv1_비밀지도 {
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        char[] s1 = null, s2 = null;
        for(int i = 0; i < n; i++) {
            //2진수 배열 두 개만들기
            s1 = makeBinary(n, arr1[i]);
            s2 = makeBinary(n, arr2[i]);
            
            //2진수 배열 두 개 합치기(둘 다 0이면 공백, 둘 중 하나라도 1이면 #)
            StringBuilder line = new StringBuilder();
            for(int j = 0; j < n; j++) {
                if(s1[j] == s2[j] && s1[j] == '0') {
                    line.append(" ");
                }else {
                    line.append("#");
                }
            }
            answer[i] = line.toString(); //string으로 만들어서 저장          
        }        

        return answer;
    }
    
    public static char[] makeBinary(int n, int num) { //10진수 -> 2진수 변환
        char[] binary = new char[n];
        
        for(int i = n-1; i >=0; i--) {
            binary[i] = (char)((num%2)+'0');
            num /= 2;
        }        
        
        return Arrays.copyOf(binary, n);
    }
    
    public static void main(String[] args) {
        int n1 = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};
        String[] answer = solution(n1, arr1, arr2);
        System.out.println(Arrays.toString(answer));
        
        int n2 = 6;
        int[] arr3 = {46, 33, 33 ,22, 31, 50};
        int[] arr4 = {27 ,56, 19, 14, 14, 10};
        answer = solution(n2, arr3, arr4);
        System.out.println(Arrays.toString(answer));
    }
}
