import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SWEA_D3_3499_퍼펙트셔플 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder output;
    
    public static void main(String[] args) throws Exception{
        input = new BufferedReader(new StringReader(src));
        StringTokenizer tokens = null;
        
        int T = Integer.parseInt(input.readLine());
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int i = 0, j = 0;
            int N = Integer.parseInt(input.readLine());
            String[] cards = new String[N];
            
            tokens = new StringTokenizer(input.readLine(), " ");
            for(int k = 0; k < N; k++) {
                cards[k] = tokens.nextToken();
            }          
            
            if(N%2 == 1) { //홀수면
                j = (N/2)+1;
            }else { //짝수면
                j = (N/2);
            }
            
            output = new StringBuilder();
            output.append("#").append(test_case).append(" ");
            while(j != N) {
                    output.append(cards[i++]).append(" ");
                    output.append(cards[j++]).append(" ");
            }if(N%2 == 1) {
                output.append(cards[i]).append(" ");
            }
            
            System.out.println(output);
        }
        
    }
    
    static String src = "3\r\n" + 
            "6\r\n" + 
            "A B C D E F\r\n" + 
            "4\r\n" + 
            "JACK QUEEN KING ACE\r\n" + 
            "5\r\n" + 
            "ALAKIR ALEXSTRASZA DR-BOOM LORD-JARAXXUS AVIANA";

}
