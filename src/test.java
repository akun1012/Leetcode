import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test { 
	
		
		
		
		public static void main (String args[]) {
			int len;
			String s = "";
			int times = 0;
			List<String> rotation = new ArrayList<String>();
			
			//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			int count = 0;
			while(in.hasNext()){
				if(count == 0){
					len = Integer.parseInt(in.nextLine());
				}else if(count == 1){
					s = in.nextLine();
				}else if(count == 2){
					times = Integer.parseInt(in.nextLine());
				}else{
					rotation.add(in.nextLine());
					if(count >= times+2){
						break;
					}
				}
				count++;
			}	
			//char[] chars = s.toCharArray();
			s = helper(s, rotation);
			
			System.out.println(s);
		}
		
		public static String helper(String s, List<String> rotation){
			if(s == null || s.length() < 2 || rotation == null || rotation.size() == 0){
				return s;
			}
			
			for(String r : rotation){
				String[] temp = r.split(" ");
				int start = Integer.parseInt(temp[0]);
				int end = Integer.parseInt(temp[1]);
				int t = Integer.parseInt(temp[2]);
				s = doRotate(s, start-1, end-1, t);
			}
			
			return s;
		}
		
		public static String doRotate(String s, int start, int end, int t){
			char[] chars = s.toCharArray();
			
			while(t > 0){
				char last = chars[end];
				for(int i = start; i <= end; i++){
					char temp = chars[i];
					chars[i] = last;
					last = temp;
				}
				t--;
			}
			
			return new String(chars);
			
		}
		
}