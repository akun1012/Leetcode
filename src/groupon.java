import java.io.*; 
import java.util.*; 
class Groupon { 
    public static void main(String [] args) throws Exception{ 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
        
        String str = in.readLine();
        String num = in.readLine();
        
        String[] strs = str.split(",");
        String[] nums = num.split(",");
        int size = strs.length;
        
        int max = 0;
        
        if(size <= 0){
        	return;
        }if(size == 1){
        	System.out.println(nums[0]);
        }
        
        List<List<Integer>> comboList = new ArrayList<List<Integer>>();
    	List<Integer> list = new ArrayList<Integer>();
    	
    	int[] numhelper = new int[size];
    	for(int i = 0; i < size; i++){
    		numhelper[i] = i;
    	}
    	
    	helper(comboList, list, size, numhelper);
    	
    	for(List<Integer> l : comboList){
    		StringBuffer sb = new StringBuffer();
    		for(Integer i : l){
    			sb.append(strs[i]);
    		}
    		if(isPalindrome(sb.toString())){
    			int current = 0;
    			for(Integer i : l){
        			current += Integer.parseInt(nums[i]);
        		}
    			if(current > max){
    				max = current;
    			}
    		}

    	}
    	System.out.println(max);
    } 
    
    public static void helper(List<List<Integer>> comboList, List<Integer> list,int size, int[] numhelper){
    	if(list.size() == size){
    		comboList.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i = 0; i < size; i++){
            if(list.contains(numhelper[i])){
                continue;
            }
            list.add(numhelper[i]);
            helper(comboList,list, size,numhelper);
            list.remove(list.size()-1);
        }
    }
    
    public static boolean isPalindrome(String s) {
        if(s == null || s.length() == 0 || s.length() == 1){
            return true;
        }
        
        char[] chars = s.toCharArray();
        
        int start = 0;
        int end = chars.length-1;
        
        while(start < end){
            
            if(start < end && Character.toLowerCase(chars[start]) != Character.toLowerCase(chars[end])){
                return false;
            }
            start++;
            end--;
        }
        
        return true;
    }
    
    
}