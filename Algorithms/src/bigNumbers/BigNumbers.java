package bigNumbers;

// Given two numbers as arrays, compute their sum

public class BigNumbers {
	 
	  public int[] sum(int[] op1, int[] op2) {
		  
		  
		  int [] opMax = op1.length >= op2.length  ? op1 : op2;
		  int [] opMin;
		  if ( opMax == op1 ) {
			  opMin = op2;
		  } else {
			  opMin = op1;
		  }
		  
		  int opMaxLength = opMax.length;
		  int opMinLength = opMin.length;
		  
		  int [] res = new int[opMaxLength + 1];
		  int carry = 0;
		  
		  for ( int i = opMaxLength; i > 0; i-- ) {
			  if ( opMinLength > 0 ) {
				  res[i] = opMax[i - 1] + opMin[opMinLength - 1] + carry;
			  } else {
				  res[i] = opMax[i - 1] + carry;
			  }
			  
			  opMinLength --;
			  
			  if ( res[i] >= 10 && i > 1) {
				  res[i] = res[i] % 10;
				  carry = 1;
			  } else if ( res[i] >= 10 && i == 1 ) {
				  res[i] = res[i] % 10;
				  res[0] = 1;
			  } else {
				  carry = 0;
			  }
		  }
		  
		  if ( res[0] == 0 ) {
			  int [] newRes = new int[opMaxLength];
			  for ( int j = 1; j < res.length; j++ ) {
				  newRes[j - 1] = res[j];
			  }
			  return newRes;
		  }
	
		  return res;
	  }
	 
	  public static void main(String[] args) {
		  int noTests = 6;
		  
		    int[][] op1 = {{0}, {9, 9}, {9}, {2, 1, 7, 8}, 
		                   {5, 0, 5, 0, 5}, {1, 0, 8, 6, 7, 8, 9, 4, 2, 3, 4}};
		    int[][] op2 = {{1, 0, 1}, {1}, {9, 9, 9, 9}, {5, 9, 6}, 
		                   {5, 5, 0, 5, 0}, {3, 4, 2, 8, 9, 7, 9, 2, 3, 4, 9}};
		    int[][] correct = {{1, 0, 1}, {1, 0, 0}, {1, 0, 0, 0, 8}, {2, 7, 7, 4}, 
		                   {1, 0, 5, 5, 5, 5}, {4, 5, 1, 5, 7, 6, 8, 6, 5, 8, 3}};
		 
		    int total = 0;
		    for (int i = 0; i < noTests; i++) {
		      System.out.println("Test " + (i+1) + ":");
		      String op1S = noToString(op1[i]);
		      String op2S = noToString(op2[i]);
		 
		      BigNumbers bn = new BigNumbers();
		      int[] rez = bn.sum(op1[i], op2[i]);
		 
		      String rezS = noToString(rez);
		      String correctS = noToString(correct[i]);
		      System.out.println(op1S + " + " + op2S + " = " + rezS + " C: " + correctS + 
		                         "......" + (rezS.equals(correctS) ? "OK" : "WRONG"));
		      System.out.println();
		 
		    }
	  }
	 
	  private static String noToString(int[] no) {
	    StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < no.length; i++) {
	      sb.append(no[i]);
	    }
	    return sb.toString();
	  }
}