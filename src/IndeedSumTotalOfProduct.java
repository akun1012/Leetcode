import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class IndeedSumTotalOfProduct { 
	
		public static void main (String args[]) {
			int N = 0;
			List<String> numLoc = new ArrayList<String>();
			int max = Integer.MIN_VALUE;
			
			//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			@SuppressWarnings("resource")
			Scanner in = new Scanner(System.in);
			int count = 0;
			while(in.hasNext()){
				if(count == 0){
					N = Integer.parseInt(in.nextLine());
				}else{
					numLoc.add(in.nextLine());
					if(count >= N){
						break;
					}
				}
				count++;
			}	
			//char[] chars = s.toCharArray();
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			int[] num = new int[N];
			helper(numLoc, num, map);
			List<List<Integer>> res = new ArrayList<List<Integer>>();
	        ArrayList<Integer> list = new ArrayList<Integer>();
	        doit(res, list, num, map);
			
	        for(List<Integer> l : res){
	        	int sumP = 0;
	        	if(l != null && l.size() > 0){
//	        		boolean valid = true;
//	        		
//		        	if(!map.isEmpty()){
//		        		for(int key : map.keySet()){
//		        			if(l.get(key) != map.get(key)){
//		        				valid = false;
//		        				break;
//		        			}
//		        		}
//		        	}
//		        	if(valid == false){
//		        		continue;
//		        	}
		        	
		        	int last = l.get(0);
		        	for(int i = 1; i < l.size(); i++){
		        		sumP += last * l.get(i);
		        		last = l.get(i);
		        	}
	        	}
	        	if(l.size() == 1){
	        		sumP = l.get(0);
	        	}
	        	
	        	max = Math.max(max, sumP);
	       
	        	
	        }
	        
			System.out.println(max);
			
		}

		public static void helper(List<String> numLoc, int[] num, HashMap<Integer, Integer> map){
			if(numLoc == null || numLoc.size() == 0){
				return;
			}
			
			for(int i = 0; i < numLoc.size(); i++){
				String[] temp = numLoc.get(i).split(" ");
				int a = Integer.parseInt(temp[0]);
				int b = Integer.parseInt(temp[1]);
				num[i] = a;
				if(b != -1){
					map.put(b-1, a);
				}
			}
			
			return;
		}
		
	    
	    public static void doit(List<List<Integer>> res, ArrayList<Integer> list, int[] num, HashMap<Integer, Integer> map){
	        if(list.size() == num.length){
	            res.add(new ArrayList(list));
	            return;
	        }
	        
	        for(int i = 0; i < num.length; i++){
	            if(list.contains(num[i])){
	                continue;
	            }
	            if(map.containsKey(list.size()+1)){
	            	if(num[i] != map.get(list.size()+1)){
	            		continue;
	            	}
	            }
	            list.add(num[i]);
	            
	            doit(res,list,num, map);
	            list.remove(list.size()-1);
	        }
	    }
		
		
		public static int[] doRotate(int[] numbers, int a, int b){
			
			int temp = numbers[b];
			numbers[b] = numbers[a];
			numbers[a] = temp;
			
			
			return numbers;
			
		}
		
}