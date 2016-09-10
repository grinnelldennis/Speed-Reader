//Speed
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.awt.*;

/* 
 * https://www.mkyong.com/java/java-convert-string-to-int/
 */

public class SpeedReader {
	
	public static void demonstratePanelCenter(WordGenerator w, int width, int height, int fontSize, int wpm) throws InterruptedException {
	    DrawingPanel panel = new DrawingPanel(width, height);
	    Font f = new Font("Courier", Font.BOLD, fontSize);
	    Graphics g = panel.getGraphics();
	    FontMetrics fm = g.getFontMetrics(new Font("Courier", Font.BOLD, fontSize));

	    g.setFont(f);
	    
	    while (w.hasNext()){
	    	
	    	String s = w.next();
	    	int sW = fm.stringWidth(s);
	    	
	    	g.setColor(Color.black);
	    	g.drawString(s, (width/2)-(sW/2), height/2-20);	
	    	
	    	Thread.sleep(60000/wpm   *wpm/60);
	    	
	    	g.setColor(Color.white);
	    	g.fillRect(0, 0, width, height);
	    }
	}

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		// TODO Auto-generated method stub

		if (args.length != 5 ){
			System.out.print("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");
		} else {
			String filename = args[0];
			int width = Integer.parseInt(args[1]);
			int height = Integer.parseInt(args[2]);
			int fontSize = Integer.parseInt(args[3]);
			int wpm = Integer.parseInt(args[4]);
			
			WordGenerator w = new WordGenerator (filename);

			demonstratePanelCenter (w, width, height, fontSize, wpm);
			
			System.out.println ("Word Count.     " + w.getWordCount());
			System.out.println ("Sentence Count. " + w.getSentenceCount());
		}
	}
}

