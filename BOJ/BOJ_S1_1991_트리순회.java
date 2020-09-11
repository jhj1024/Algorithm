import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
* @author JUNG
* @name BOJ_S1_1991_트리순회
* @date 
* @link https://www.acmicpc.net/problem/1991
* @mem
* @time
* @caution
* [고려사항] 
* [입력사항] 이진 트리의 노드의 개수 N, 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드
* [출력사항] 전위 순회, 중위 순회, 후위 순회한 결과
* 
* 노드의 이름은 A부터 차례대로 영문자 대문자로 매겨지며, 항상 A가 루트 노드가 된다. 자식 노드가 없는 경우에는 .으로 표현된다.
*/

public class BOJ_S1_1991_트리순회 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens = null;
    static StringBuilder output = new StringBuilder();
    
    static int N;
    static Node[] nodes;
    static String Answer;
    
    public static void main(String[] args) throws Exception{
        input = new BufferedReader(new StringReader(src));
        
        //입력
        N = Integer.parseInt(input.readLine());
        nodes = new Node[N];
        int idx, left, right;
        String temp;
        for(int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            
            idx = tokens.nextToken().charAt(0)-'A'; //부모=인덱스
            
            if((temp=tokens.nextToken()).equals(".")) { //왼쪽자식
               left = -1;
            }else {
                left = temp.charAt(0)-'A';
            }
            
            if((temp=tokens.nextToken()).equals(".")) { //오른쪽자식
                right = -1;
             }else {
                 right = temp.charAt(0)-'A';
             }

            nodes[idx] = new Node(left, right);
        }
                
        preOrder(0);
        output.append("\n");
        inOrder(0);
        output.append("\n");
        postOrder(0);
        System.out.println(output);       
    }
    
    public static void preOrder(int idx) {
        output.append((char)('A'+idx));
        if(nodes[idx].left != -1) {
            preOrder(nodes[idx].left);
        }
        if(nodes[idx].right != -1) {
            preOrder(nodes[idx].right);
        }
    }

    public static void inOrder(int idx) {
        if(nodes[idx].left != -1) {
            inOrder(nodes[idx].left);
        }
        output.append((char)('A'+idx));
        if(nodes[idx].right != -1) {
            inOrder(nodes[idx].right);
        }
    }
    
    public static void postOrder(int idx) {        
        if(nodes[idx].left != -1) {
            postOrder(nodes[idx].left);
        }
        if(nodes[idx].right != -1) {
            postOrder(nodes[idx].right);
        }
        output.append((char)('A'+idx));
    }
    
    static class Node{
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Node [left=");
            builder.append(left);
            builder.append(", right=");
            builder.append(right);
            builder.append("]");
            return builder.toString();
        }       
        
        
    }
    
    static String src = 
            "7\r\n" + 
            "A B C\r\n" + 
            "B D .\r\n" + 
            "C E F\r\n" + 
            "E . .\r\n" + 
            "F . G\r\n" + 
            "D . .\r\n" + 
            "G . .";
}
