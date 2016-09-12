import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordGenerator {

	private Scanner scan;
	private File file;
	private int wordCount;
	private int sentenceCount;

/**
 *  Constructs a new generator that processes text from the given file.
 * @param filename, has to be a full & valid path
 * @throws FileNotFoundException
 */
	public WordGenerator (String filename) throws FileNotFoundException {
		this.file = new File (filename); 
		this.scan = new Scanner(file);
		this.wordCount = 0;
	}
	
/**
 * @return true iff the underlying Scanner of this WordGenerator has text left to process.
 */
	public boolean hasNext(){
		if ( this.scan.hasNext() ){
			return true;
		} else {
			return false;
		}
	}
	
/**
 * @return the next word of the underlying Scanner. If the Scanner does not have words left, 
 *    then the behavior of next() is undefined (i.e., you don’t have to check or handle this case).
 */
	public String next() {
		if ( this.hasNext() ) {
			
			String s = scan.next();
			char c = s.charAt(s.length() - 1);
			this.wordCount ++;
			
			if ( c == '.' || c == '!' || c == '?'){	sentenceCount++; }
			
			return s;
		} else {
			return "\0";
		}
	}
	
/**
 * @return number of words produced by the WordGenerator so far.
 */
	public int getWordCount(){
		return this.wordCount;
	}
	
/**
 * @return number of sentences produced by the WordGenerator so far. Define a sentence 
 *   to be the number of occurrences a sentence-ending punctuation mark appears at the end of 
 *   a word—'.', '!', or '?'.
 */
	public int getSentenceCount(){	
		return this.sentenceCount;
	}
	
	
}
