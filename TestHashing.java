import java.util.Scanner;
import java.util.Random;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/*******************************************************************
   Test File:  You should not have to modify this.
 ******************************************************************/

public class TestHashing
{

   private static final int QUIT = 4;
   private Scanner cin;
   private Random rand;

   public TestHashing()
   {
        cin = new Scanner(System.in);
        rand = new Random();
   }


  public void test()
  {
   int choice;
   boolean error;

    choice = menu();
    while (choice != QUIT) 
    {
       error = false;
       switch (choice)
       {
          case 1:  error = testStringEquals();
                   if (!error)
                       testStringHashCode();
                   break;
          case 2:  error = testFractionEquals();
                   if (!error)
                      testFractionHashCode();
                   break;
          case 3:  error = testEightEquals();
                   if (!error)
                       testEightHashCode();
                   break;
          default: 
              System.out.println( "Invalid choice: Select again\n");
       } 
       choice = menu(); 
    }
  }

    /*
      *********************************************************************
      *+                     menu 
      *  Returns: the choice of which algorithm to run or a choice 
                  to change the data in the list. 
      *********************************************************************
      */

public int menu()
{
    int choice = 0;

    System.out.println
          ("**************************************************");
    System.out.println("           hashCode testing  ");
    System.out.println
        ("**************************************************");
    System.out.println("       1.  MyString HashCode Test");
    System.out.println("       2.  Fraction HashCode Test");
    System.out.println("       3.  EightState HashCode Test");
    System.out.println("       4.  Quit");
    System.out.println
       ("**************************************************");
    return  getInt("\n\nEnter your choice: ", 0, 1, 4);
}

  public boolean testStringEquals()
  {
     boolean error = false;

     MyString string1 = new MyString("dog");
     MyString string2 = new MyString("cat");
     MyString string3 = new MyString("dog");
     MyString string4 = new MyString("do");

     error = string1.equals(string2) || string1.equals(string4);
     if (error)
        System.out.println ("Error in MyString .equals method.");
     error = error || !string1.equals(string3);
     if (error = string1.hashCode() != string3.hashCode())
        System.out.println ("Error: equal strings should have equal hashcodes.");
      
     return error;
  }

  public void testStringHashCode() 
  {
       String filename = null;
       Scanner inputFile = null;
       String str;
       MyString wrappedStr = new MyString();
       HashSimulator yourHashTable;
       HashSimulator theirHashTable;
       boolean opened = false;
       int size;

       System.out.print ("What is the name of the String file: ");
       filename = cin.next();
       try {
          inputFile = new Scanner(new FileReader(filename));
          opened = true;
       } catch (IOException e) {
            opened = false;
            System.out.println ("Couldn't open the String file.");
       }

       if (!opened)
          return;
       size = getInt( "How big should the hash table be: ", 27007, 10,
                 100000);       
       theirHashTable = new HashSimulator(size);
       yourHashTable = new HashSimulator(size);

       while (inputFile.hasNext()) {
          str = inputFile.next();
          wrappedStr.setString(str);
          theirHashTable.addData(str);
          yourHashTable.addData(wrappedStr);
       }
      inputFile.close();
      theirHashTable.printStatistics("String Class Hash Function Statistics");
      yourHashTable.printStatistics("MyString Hash Function Statistics");
   }

  private int getInt(String prompt, int defaultValue, int lo, int hi)
  {
      int response = lo-1;
      System.out.print(prompt);
      if (cin.hasNextInt())
         response = cin.nextInt();
      else
         cin.nextLine(); // throw away non-integer input
      if (response >= lo && response <= hi)
          return response;
      System.out.println ("Tough luck.  You get " + defaultValue);
      return defaultValue;
   }
     

  public boolean testFractionEquals()
  {
      boolean error = false;
      Fraction f1 = new Fraction(1,2);
      Fraction f2 = new Fraction(1,2);
      Fraction f3 = new Fraction(1,0);
      Fraction f4 = new Fraction(3,0);
      Fraction f5 = new Fraction(2,1);

      error = f1.equals(f5) || !(f2.equals(f1));
      if (error)
        System.out.println ("Error in Fraction Equals Method");
      if (f1.hashCode() != f2.hashCode()) {
         System.out.println ("Error: equal fractions should have equal hashcodes");
         return true;
      }
      if (!(f3.equals(f4) && f3.hashCode() == f4.hashCode())) {
         System.out.print ("Error: undefined fractions should be equal ");
         System.out.println ("and have equal hashCodes.");
         return true;
      }
      return error;
   }

  public void testFractionHashCode() 
  {
     int tablesize, numEntries;
     int numerator;
     int denominator;
     Fraction fract;
     Fraction fract2 = new Fraction();

     numEntries = getInt("How many fractions do you want: ", 750, 10,
                          10000000);

     tablesize = getInt("How big do you want your table: ", 1000, 1,
                          10000000);
     HashSimulator hashTable = new HashSimulator(tablesize);
     HashSet<Fraction> fractSet = new HashSet<Fraction>(numEntries);

     int count = 0;
     while (count < numEntries) {
         numerator = rand.nextInt(numEntries/2) + 1;
         denominator = rand.nextInt(numEntries/2) + 1;
         fract2.resetFraction(numerator, denominator);
         if (!fractSet.contains(fract2)) {
            fractSet.add(new Fraction(numerator,denominator));
            count++;
            hashTable.addData(fract2);
         } 
     }    
     hashTable.printStatistics("Your Fraction Hash Function Statistics");
  }

  public boolean testEightEquals()
  {
      boolean error = false;
      int [] arr = new int[9];
      EightState e1 = new EightState();
      EightState e2 = new EightState();
      EightState e3 = new EightState();
      fillRandomly(arr);
      e1.setValues(arr);
      e2.setValues(arr);
      fillRandomly(arr);
      e3.setValues(arr);
      error = !(e1.equals(e2)) ||  e2.equals(e3);
      if (error) {
         System.out.println ("Error in equals method.");
         return true;
      }
      if (e1.hashCode() != e2.hashCode()) {
         System.out.println ("Error: equal eights should have the same hashCode.");
         return true;
      }
      return false;
  }      
      
  public void testEightHashCode() 
  {
       int tablesize, numEntries;
       HashSimulator hashTester;
       int [] arr = new int[9];
       EightState game = new EightState();


       numEntries = getInt("How many Games of 8: ", 750, 10,
                          100000);
       tablesize = getInt("How big of a hashtable: ", 1000, 10,
                          100000);
       hashTester = new HashSimulator(tablesize);

       for (int i = 0; i < numEntries; i++) {
          fillRandomly(arr);
          game.setValues(arr); 
          hashTester.addData(game);
       }
       hashTester.printStatistics("Your Eight Game Hash Function Statistics");
  }
 
  public void fillRandomly(int [] arr)
  {
       int temp, index;
       for (int i = 0; i < 9; i++)
           arr[i] = i;
       for (int i = 8; i > 0; i--) {
           index = rand.nextInt(i+1); 
           temp = arr[index];
           arr[index] = arr[i];
           arr[i] = temp;
       }
   }

   public static void main(String [] args)
   {
        TestHashing tester = new TestHashing();
        tester.test();
   } 
}
