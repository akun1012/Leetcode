import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Indeed_Ball { 
	
		public static void main (String args[]) {
			int N = 0;
			int K = 0;
			int[] numbers = {1,2,3,4,5,6,7,8};
			List<String> rotation = new ArrayList<String>();
			String result = "";
			
			//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			int count = 0;
			while(in.hasNext()){
				if(count == 0){
					String firstInput = in.nextLine();
					String[] temp = firstInput.split(" ");
					N = Integer.parseInt(temp[0]);
					K = Integer.parseInt(temp[1]);
				}else{
					rotation.add(in.nextLine());
					if(count >= N){
						break;
					}
				}
				count++;
			}	
			//char[] chars = s.toCharArray();
			while(K > 0){				
				numbers = helper(numbers, rotation);
				K--;
			}
			
			for(int n : numbers){
				result += n;
				result += " ";
			}
			result = result.substring(0,result.length()-1);
			System.out.println(result);
			
		}
		
		public static int[] helper(int[] numbers, List<String> rotation){
			if(rotation == null || rotation.size() == 0){
				return numbers;
			}
			
			for(String r : rotation){
				String[] temp = r.split(" ");
				int a = Integer.parseInt(temp[0]);
				int b = Integer.parseInt(temp[1]);
				numbers = doRotate(numbers, a-1, b-1);
			}
			
			return numbers;
		}
		
		public static int[] doRotate(int[] numbers, int a, int b){
			
			int temp = numbers[b];
			numbers[b] = numbers[a];
			numbers[a] = temp;
			
			
			return numbers;
			
		}
		
}