package GoogleChallenge1till2;


public class GoogleChallenge1Code1
{
  public static void main(String[] args)
  {
  
    System.out.println(answer("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!"));
    
  }
  
  public static String answer(String s) { 

         String outputString = "";
        char[] inputArray = s.toCharArray();
        char[] outputArray = new char[s.length()];
        // bruteforce approach
        char[] convortor = {'z','y','x','w','v','u','t','s','r','q','p','o','n','m','l','k','j','i','h','g','f','e','d','c','b','a'};
        //traversing input and generating output
        for( int i=0;i<inputArray.length;i++ )
        {
            if(inputArray[i] >= 97 && inputArray[i]<=122)
            {
                outputArray[i] = convortor[inputArray[i] - 97];
            }
            else
            {
                outputArray[i] = inputArray[i];
            }
        }
        outputString = new String(outputArray);
        return outputString;
    } 
}