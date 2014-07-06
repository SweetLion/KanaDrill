package com.jorgecastillo.kanadrill;

import java.util.Arrays;

public class CommonCode {
	public static void orderLinear(int upto, int order[]){
		
		for (int i = 0; i < upto; i++){
			order[i] = i;
		}
		
	}
	
	private static boolean intExists(int[] vector, int element, int length){

		for(int i = 0; i < length; i++){
		   if (vector[i] == element){
		       return true;
		   }
		}
			
		return false;	
		  
	}
	
	public static void orderRandom(int upto, int order[]){
		
		Arrays.fill(order, -1);
		
		for (int i = 0; i < upto;){
			int val = randomInt(upto);
			if (!intExists(order, val, upto)){
			    order[i] = val;
			    i++;
			}
		}
		
	}
	
	public static int setUpto(int option){
		
		int upto = 0;
		
		switch(option){
		  case 1:
			  upto = 46;
			  break;
		  case 2:
			  upto = 71;
			  break;
		  case 3:
			  upto = 92;
			  break;
		  case 4:
			  upto = 107;
			  break;
		  default:
			  break;
		}
		
		return upto;
		
	}
	
	public static int randomInt(int upto){
		
	    int number;
	    
	    number = (int) Math.floor(Math.random() * upto);
		
	    return number;
	}
}
