package uk.ac.cam.jsc89.prejava.ex2; 
        
public class PackedLong {
            
   /*
    * Unpack and return the nth bit from the packed number at index position;
    * position counts from zero (representing the least significant bit)
    * up to 63 (representing the most significant bit).
    */
   public static boolean get(long packed, int position) {
   // set "check" to equal 1 if the "position" bit in "packed" is set to 1
   // you should use bitwise operators (not % or similar)
   long check = 1 & (packed>>position);

   return (check == 1);
   }

  
   public static long set(long packed, int position, boolean value) {
      if (value) {
       
        packed = (packed | 1L<<position);
      }
      else {
       
        packed = ~(~packed | 1L<<position);
      }
      return packed;
   }
}
