package uk.ac.cam.jsc89.prejava.ex4;

public class FibonacciCache {

   // Uninitialised array
   public static long[] fib = null;

   public static void store() throws Exception {
      //TODO: throw an Exception with a suitable message if fib is null
      if (fib == null) {
      	throw new Exception("fib is null");
      } else {
      	fib[0] = 1;
      	fib[1] = 1;
      	for (int n = 2;n<fib.length; n++) {
      		fib[n] = fib[n-1] + fib[n-2];
      	}
      }
   }

   public static void reset(int cachesize) {
   		fib = new long[cachesize];
   		for (int n = 0; n < fib.length; n++) {
   			fib[n] = 0;
   		}
   }
 
   public static long get(int i) throws Exception {
      if (fib == null) {
      	throw new Exception("fib is null");
      }
      if (i < 0 | i > fib.length-1) {
      	throw new Exception("value requested is out of bounds of array");
      }
      return fib[i];

   }

   public static void main(String[] args) {
      try {
      	reset(20);
      	store();
      	int i = Integer.decode(args[0]);
      	System.out.println(get(i));
      } catch (Exception e) {
      	System.out.println(e.getMessage());
      }
   }
  
}