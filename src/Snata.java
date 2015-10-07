
import static java.util.Arrays.deepToString;
import java.util.*;

class Solution {

  static class SecretSanta {
      public String[][] go(String[] s) {
  //       return new String[][] { {"Barney", "Wilma"}, {"Fred", "Pebbles"}, {"Wilma", "Bam Bam"}, 
  //         {"Bam Bam", "Fred"}, {"Pebbles", "Barney"} };
  //     }

      if(s == null || s.length == 0){
        return null;
      }

      String[][] result = new String[s.length][2];

      int[] giver = new int[s.length];
      int[] receiver = new int[s.length]; 
      int[] nums = new int[s.length];

      for(int i = 0; i < s.length; i++){
        nums[i] = i;
      }
      giver = nums;

      Collections.shuffle(Arrays.asList(giver));

      ArrayList<Integer> listForReceiver = new ArrayList<Integer>();
      Random rand = new Random();
      for(int i = 0; i < s.length; i++){
        listForReceiver = getListForReceiver(giver[i], receiver, i, nums);
        //System.out.print("listForReceiver" + listForReceiver);
        int p = rand.nextInt(listForReceiver.size());
        receiver[i] = listForReceiver.get(p);
      }

      for(int i = 0; i < s.length; i++){
        result[i][0] = s[giver[i]];
        result[i][1] = s[receiver[i]];
      }

      return result;
    }
                             
    private ArrayList<Integer> getListForReceiver(int giver, int[] receiver, int pointer, int[] nums){
      ArrayList<Integer> newNumList = new ArrayList<Integer>();

      for(int i = 0; i < nums.length; i++){
        if(nums[i] != giver){
          boolean valid = true;
          for(int j = 0; j < pointer ;j++){
            if(receiver[j] == nums[i]){
              valid = false;
            }
          }
          if(valid){
            //System.out.print(nums[i]);
            newNumList.add(nums[i]);
          }
        }
      }

      return newNumList;
    }
  
    
  }
  public static void main(String... args) {
      String[] players = new String[] {"Fred", "Wilma", "Barney", "Pebbles", "Bam Bam"};
      String[][] santas = new SecretSanta().go(players);

      System.out.println(deepToString(santas));
    }
}

// 2   1 3 4 5   4
// 3   1 2 5     2
// 1   3 5       5       
// 5   1 3       3
// 4   1         1