/*********************************************************
   MyString is a wrapper class for a String, written so
   you can practice writing a hashCode() method.

   your job is to implement an equals method and a hashCode method.
 
 *********************************************************/

public class MyString
{
     private String myString;

     
     public MyString ()
     {
            myString = "";
     }

     public MyString (String str)
     {
            myString = str;
     }
 
     public void setString (String str)
     {
          myString = str;
     }

     public String getString()
     {
         return myString;
     }
     public boolean equals(Object obj) {
         if (obj instanceof MyString) {
             MyString str = (MyString) obj;
             return str.myString.equals(myString);
         }
         return false;
     }

     /*
      * I have a lot going on in this method. almost all of it is useless. 
      * See the numbers in regards to the attempts.
      * method number 10 was the one i used.
      */
     public int hashCode() {
         /*1)
          *Try 1. got me nowhere.
          *
          */
         byte[] ascii = new byte[myString.length()];      
         int count = 0;
         for (int i = 0; i < myString.length(); i++) {
            ascii[i] = (byte)myString.charAt(i);
            count += ascii[i];
         }

         /*2)
          *try two. got me almost nowhere but better than last one.
          */
        for (int i  = 0; i < ascii.length; i += 2) {
            count += i;
        }
        count = (count * 31) - count;
   //     return count;
         /*3)
          *thus far this has proved the best. it works on size 40000 but performs poorly on the rest.
          * This was my best attempt, other than some bit shifting lower in the method. It still didnt 
          * beat the java hashcode.
          * I will probably be by after class tomorrow or friday to ask you exactly how one would
          * beat the java code. im sure there were people that did it but this was a particularly challenging
          * project for me.
          */
         int hash = 5;
         for (int i = 0; i < myString.length(); i++) {
             hash = ((hash * 31)+ myString.charAt(i)) - (hash % 7);
         }
   //      return hash; 
        /*4)
         *this failed miserably im not sure what my goal even was.
         */
        int h = 0;
        for (int i  = 0; i < myString.length(); i++) {
            h = (i + ((myString.charAt(i) << 5) * 31)  + ascii[i]);
        }
      //  return h;
   // 5)  return (hash * ((hash << 3) + (count * hash))) - count;
    //6)    return (((((hash + count) * (hash - count)) * (hash * count)) * (hash << 5)) * (count << 5));
        /*7)
         * this never actually worked at all.
         */
        int ex = 0;
        for (int i = 100000; i > 10; i = i / 100) {
            for (int j = 0; j < ascii.length; j++) {
            ex += i + ascii[j];
            }
        }
    //    return ex;

    /*8) 
     *the goal here was to reverse the string and add the two hashcodes together. it also didnt work.
     */
    StringBuilder str = new StringBuilder(myString);
    StringBuilder str1 = str.reverse();
    int upper = myString.hashCode() << 32;
    int lower = str1.hashCode() - (Integer.MIN_VALUE);
    int ha = upper + lower;
    //return upper;
    /*9)
     *here i just added the reversed hashcode to the original hashcode. it doesnt work because 
     they arent equal.
     */
    int one = myString.hashCode();
    int two = str1.hashCode();
    //return one + two;
    
    /*10)
     * here i kinda got into bit shifting but i think i built almost exactly what javas method does.
     * it actually did the best, so it is what i returned.
     */
    int hashed = myString.hashCode();
    hashed ^= ( hashed >>> 18) ^ (hashed >>> 22);
    //return hashed ^ (hashed >>> 7 ) ^ (hashed >>> 4);
    /*11)
     *tried something simple hoping it would work. it didnt.
     */
    int hashing = myString.hashCode() * 31;
    //return hashing;
   /*12) 
    * some more bit shifting, a little xoring. still not getting it. about to run out of time on assignment.
    */ 
    hashing = ((hashing ^ one) >>> 18);
    //return hashing + 5;
    //
       return hashed; 
      }
}
