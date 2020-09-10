import java.util.Arrays;

public class solution2 {
    static int[] answer;

    public static int[] solution(int n) {
        int len = (n * (n + 1)) / 2; //등차수열의 합 공식 = 배열의 길이
        answer = new int[len]; //1부터 시작

        int start = 0, cnt = -1, num = 0;
        while (n > 0) {
            start = start + (++cnt * 4);            
            make(start, n, num, cnt);
            num += (n+(n-1)+(n-2));
            n -= 3;
        }

        return answer;
    }

    public static void make(int start, int n, int num, int cnt) {
        int idx = start-(cnt*2);

        //왼변 (n번)
        for (int i = 1; i <= n; i++) {
            idx = idx + (cnt*2);
            idx = idx + i - 1;
            answer[idx] = ++num;            
        }

        if(n-1 > 0) {
            //밑변 (n-1번)
            for (int i = 1; i <= n-1; i++) {
                idx = idx + 1;
                answer[idx] = ++num;
            }
        }
                
        if(n-2 > 0) {
            //오른변 (n-2번)
            for (int i = 0; i < n-2; i++) {
                idx = idx - (cnt*2);
                idx = idx - (n-i);
                answer[idx] = ++num;
            }
        }
        System.out.println(Arrays.toString(answer));
    }


    public static void main(String[] args) {
        int number = 7;
        System.out.println(Arrays.toString(solution(number)));
    }
}
