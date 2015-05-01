/*******************************************************************
  Used in Testing of your classes.  You do not modify this.
 *******************************************************************/

public class HashSimulator 
{
    private int [] table;
    private int numEntries;
     
    public HashSimulator(int size) 
    {
        table = new int[size];
        numEntries = 0;
    }

    public int getHashTableSize()
    {
       return table.length;
    }

    public int getSize()
    {
       return numEntries;
    }

    public void addData(Object obj)
    {
        simulateHash(obj);
        numEntries++;
    }

    private void simulateHash(Object obj)
    {
        int hashVal;

        hashVal = obj.hashCode();
        hashVal = hashVal & Integer.MAX_VALUE;
        hashVal = hashVal % table.length;
        table[hashVal]++;
    }

    public void printStatistics(String description)
    {
         int maxLength = 0;
         int collisions = 0;
         for (int i = 0; i < table.length; i++) {
            if (table[i] > 0) {
               collisions += table[i] - 1; // first one isn't a collision
               if (table[i] > maxLength)
                  maxLength = table[i];
            }
         } 
        System.out.println ("\n" + description);
        System.out.println ("  Number of Items in the Table: " + numEntries); 
        System.out.println ("  TableSize: " + table.length); 
        System.out.println ("  Load Factor: " + numEntries * 100 / table.length
                                             + "%"); 
        System.out.println ("  Number of Collisions: " + collisions); 
        System.out.println ("  Largest List: " + maxLength);
        System.out.println ();
    }
}
