package com.jorgecastillo.kanadrill;

import android.content.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.Arrays;

public class CommonCode {

  public static int theme_list;

  public static void orderLinear(int upto, int order[]) {

    for (int i = 0; i < upto; i++) {
      order[i] = i;
    }

  }
	
  private static boolean intExists(int[] vector, int element, int length) {

    for(int i = 0; i < length; i++) {
      if (vector[i] == element) {
        return true;
		  }
		}

    return false;	

  }

  public static void orderRandom(int upto, int order[]) {

    Arrays.fill(order, -1);

    for (int i = 0; i < upto;) {
      int val = randomInt(upto);
      if (!intExists(order, val, upto)) {
        order[i] = val;
        i++;
      }
    }

  }

  public static int setUpto(int option) {

    int upto = 0;

    switch(option) {
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

  public static int randomInt(int upto) {

    int number;

    number = (int) Math.floor(Math.random() * upto);

    return number;
  }

  public static void intArrayToFile(Context myContext, String filename, int[] array){
    File root = myContext.getFilesDir();
    File current = new File(root, filename);
    current.delete();
    FileOutputStream outputStream;
    try {
      outputStream = myContext.openFileOutput(filename, Context.MODE_APPEND);
      for (int i : array) {
        String s = "" + i;
        outputStream.write(s.getBytes());
        outputStream.write("\n".getBytes());
      }
      outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public static int[] fileToIntArray(Context myContext, String filename, int size){

    int[] array = new int[size];
    int i = 0;
    FileInputStream inputStream;
    try {
      int c;
      inputStream = myContext.openFileInput(filename);
      StringWriter writer = new StringWriter();
      while((c = inputStream.read()) != -1 ){
        writer.append((char) c);
      }
      String ints[] = writer.toString().split("\n");
      for (String s : ints){
        array[i++] = Integer.parseInt(s);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return array;
  }

}
