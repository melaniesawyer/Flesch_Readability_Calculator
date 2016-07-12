//Melanie Sawyer
//Lab 4.1
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class FleschReadabilityIndexCalculator
{
    // instance variables
    private Scanner fileReader; // a Scanner object which can be used in this program to read a file

    /**
     * Constructor for objects of class FleschReadabilityIndexCalculator
     * takes care of the File opening, Scanner initializing, and some error catching.
     */
    public FleschReadabilityIndexCalculator(String fileName)
    {
        // initialise instance variables - in this case open the file
        try{
            fileReader = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found.  Terminating ...");
            System.exit(-1);
        }
    }

    public boolean isVowel(char A){//run a while loop here with the array
        if (A=='a'||A=='e'||A=='i'||A=='u'||A=='o'||A=='y'||A=='A'
        ||A=='E'||A=='I'||A=='O'||A=='U'||A=='Y'){
            return true;}
        else{
            return false;}
    }

    public boolean isPunctuationLast(String word){//make an array of punctuation here and run it like a while loop here as well
        int length= word.length();
        if ((word.charAt(length-1)=='.')||(word.charAt(length-1)=='?')||word.charAt(length-1)=='!'
        ||(word.charAt(length-1)==':')){
            return true;
        }
        else{
            return false;
        }
    }

    public int countSyllables(String nextWord){
        int length=nextWord.length();
        int syllables=0;
        for (int i=0; i<length;i=i+1){
            if (isVowel((nextWord.charAt(i)))){
                if (i ==0){
                syllables ++;
            }
                else if (isVowel(nextWord.charAt(i-1))){
                    syllables+=0;
                    
                }
                else if ((i==length-1&&(nextWord.charAt(i)=='e')))
                  {
                      syllables+=0;
                    }
                 else if (i==length-2&&(nextWord.charAt(i)=='e'&&isPunctuationLast(nextWord))){
                     syllables+=0;
                    }
                else
                syllables++;
            }
        }
        if (syllables==0){
            syllables=syllables+1;
        }
        return syllables;

    }

    public int countSentences(String nextWord){
        int sentences=0;
        if ((nextWord.charAt(nextWord.length()-1)=='.')||(nextWord.charAt(nextWord.length()-1)=='?')||(nextWord.charAt(nextWord.length()-1)=='!')
        ||(nextWord.charAt(nextWord.length()-1)==':')){

            sentences++;}
        return sentences;
    }

    public int countWords(String word)
    {

        int words=0;
        words=words+1;
        return words;
    }

    public void computeIndex()
    {
        String nextWord;
        double totalSyllables=0;
        double totalSentences=0;
        double totalPunctuation=0;
        double totalWords=0;
        double index=0;
        int index1=0;
        do{
            nextWord = fileReader.next();
            totalSyllables+=countSyllables(nextWord);
            totalSentences=totalSentences+countSentences(nextWord);
            totalPunctuation=totalPunctuation+countPunctuation(nextWord);
            totalWords=totalWords+countWords(nextWord);
            index=206.835-84.6*(totalSyllables/totalWords)-1.015*(totalWords/totalSentences);
        
            if (index-(int)(index)>0.5){
                index=(int)(index)+1;
                index1= (int)index;
                
            }else{
                index1= (int)index;
                
            }
        }while (fileReader.hasNext());
        
        
        
        
        if (index1>=91&&index1<=100){
            System.out.println("This document is at a 5th grade education level.");
        }
        else if (index1>=81&&index1<=90){
            System.out.println("This document is at a 6th grade education level.");
        }
        else if (index1>=71&&index1<=80){
            System.out.println("This document is at a 7th grade education level.");
        }
        else if (index1>=61&&index1<=70){
            System.out.println("This document is at a 9th grade education level.");
        }
        else if (index1>=51&&index1<=60){
            System.out.println("This document is at a high school level.");
        }
        else if (index1>=31&&index1<=50){
            System.out.println("This document is at a college student level.");
        }
        else if (index1>=0&&index1<=30){
            System.out.println("This document is at a college graduate level.");
        }
        else{
            System.out.println("This document is at a graduate degree level.");
        }
        System.out.println("Readability Index = "+index1);
            
    }

    public int countPunctuation(String word)
    {
        int numPunctuation = 0;
        for (int i=0; i<word.length(); i++)
        {
            if (word.charAt(i) == '.' || word.charAt(i) == '?' || word.charAt(i) == '!' || 
            word.charAt(i) == ',' || word.charAt(i) == ':' || word.charAt(i) == ';')
            {
                numPunctuation++;
            }
        }
        return numPunctuation;
    }
    
    /**
     * An example of a main method
     * 
     * @param  args   - a list of parameters that you can send to the main function
     * 
     * The keyword static means you do NOT need to construct an object to use this method.
     * Usually you construct an object within this method instead of using BlueJ to construct the object.
     * The main method should not be used for alorithms or substantial code
     * The main method is for getting things running and/or for testing code, and/or possibly for
     * initial input and final output
     */
      public static void main (String[] args)
    {
        // here we construct the object and run some methods so we don't have to do it in BlueJ 
        FleschReadabilityIndexCalculator fileCalculator;
        if (args.length == 0)
            fileCalculator = new FleschReadabilityIndexCalculator("coemail.txt");  
        else
            fileCalculator = new FleschReadabilityIndexCalculator(args[0]);
   
        fileCalculator.computeIndex();
    }
}


