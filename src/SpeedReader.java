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
	    	
	    	Thread.sleep(60000/wpm);
	    	
	    	g.setColor(Color.white);
	    	g.fillRect(0, 0, width, height);
	    }
	}

	
	public static void demonstratePanelOffset(WordGenerator w, int width, int height, int fontSize, int wpm) throws InterruptedException {
	    DrawingPanel panel = new DrawingPanel(width, height);
	    Font f = new Font("Courier", Font.BOLD, fontSize);
	    Graphics g = panel.getGraphics();
	    FontMetrics fm = g.getFontMetrics(new Font("Courier", Font.BOLD, fontSize));

	    g.setFont(f);
	    
	    while (w.hasNext()){
	    	String s = w.next();
	    	int sW = fm.stringWidth(s);
	    	
	    	focusLetter(g, s, width, height, wpm, sW);
	    }
	}
	
	public static void focusLetter (Graphics g, String s, int width, int height, int wpm, int sW) throws InterruptedException {
		int stringLength = s.length();
		int charWidth = sW / stringLength; 
		char c = s.charAt( stringLength-1 );
		
		if ( stringLength < 2 ){
	    	g.setColor(Color.red); 
	    	g.drawString(s.substring(0), (width/2)-(charWidth/2), height/2-20);	
	    	
		} else if ( stringLength < 6 ){
			g.setColor(Color.black);
	    	g.drawString(s.substring(0, 1), (width/2)-charWidth, height/2-20);
	    	g.setColor(Color.red);
	    	g.drawString(s.substring(1, 2), (width/2), height/2-20);
	    	
	    	if (stringLength > 2){
	    		g.setColor(Color.black);
		    	g.drawString(s.substring(2), (width/2)+charWidth, height/2-20);	
	    	}
		} else if ( stringLength < 10 ){
			g.setColor(Color.black);
	    	g.drawString(s.substring(0, 2), (width/2)-charWidth*2, height/2-20);	
	    	g.setColor(Color.red);
	    	g.drawString(s.substring(2, 3), (width/2), height/2-20);
	    	g.setColor(Color.black);
	    	g.drawString(s.substring(3), (width/2)+charWidth, height/2-20);	
	    	
		} else if ( stringLength < 13 ){
			g.setColor(Color.black);
	    	g.drawString(s.substring(0, 3), (width/2)-charWidth*3, height/2-20);	
	    	g.setColor(Color.red);
	    	g.drawString(s.substring(3, 4), (width/2), height/2-20);
	    	g.setColor(Color.black);
	    	g.drawString(s.substring(4), (width/2)+charWidth, height/2-20);	
	    	
		} else {
			g.setColor(Color.black);
	    	g.drawString(s.substring(0, 4), (width/2)-charWidth*4, height/2-20);	
	    	g.setColor(Color.red);
	    	g.drawString(s.substring(4, 5), (width/2), height/2-20);
	    	g.setColor(Color.black);
	    	g.drawString(s.substring(5), (width/2)+charWidth, height/2-20);	
	    	
		}
		
		if ( c == '.' || c == '!' || c == '?' || c == ','){
			Thread.sleep((long) (60000/wpm*1.75));
		}
		else {
			Thread.sleep(60000/wpm);
		}
		
		g.setColor(Color.white);
    	g.fillRect(0, 0, width, height);
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

			demonstratePanelOffset (w, width, height, fontSize, wpm);
			
			System.out.println ("Word Count.     " + w.getWordCount());
			System.out.println ("Sentence Count. " + w.getSentenceCount());
		}
	}
}

