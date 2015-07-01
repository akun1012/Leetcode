import java.io.IOException;
import java.util.Stack;


public class p1 {
	public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < words.length ; i++){
        	
        }
        return result.toString();
    }
	
/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * @param tokens
 * @return
 */
	public static int evalRPN(String[] tokens) {
		int returnValue = 0;
		Stack<String> stack = new Stack<String>();
		String operands = "+-*/";
		for(String token:tokens){
			if(!operands.contains(token)){
				stack.push(token);
			}else{
				int a = Integer.parseInt(stack.pop());
				int b = Integer.parseInt(stack.pop());
				
				int index = operands.indexOf(token);
				switch(index){
					case 0: 
						stack.push((b+a)+"");
						break;
					case 1:
						stack.push((b-a)+"");
						break;
					case 2:
						stack.push((b*a)+"");
						break;
					case 3:
						stack.push((b/a)+"");
				}
			}
		}
		
		returnValue = Integer.parseInt(stack.pop());
		
		return returnValue;
    }
	
	public static void main(String[] args) throws IOException{
		String[] tokens = new String[] { "2", "1", "+", "3", "*" };
		System.out.println(evalRPN(tokens));
	}
}
