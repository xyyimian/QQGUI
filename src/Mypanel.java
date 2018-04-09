import java.awt.*;
import java.util.Random;


public class Mypanel extends Panel{
	public void paint (Graphics g){
		int height = 50;
		int width = 90;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width-1, height-1);
		Random random = new Random();
		
		//set disturbing point 
		for(int i = 0;i < 100; ++i){
			int x = random.nextInt(width) - 1;
			int y = random.nextInt(height) - 1;
			g.drawOval(x, y, 2, 2);		//ramdom draw oval
		}
		
		g.setFont(new Font("黑体", Font.BOLD, 20));
		g.setColor(Color.RED);
		
		char[] temp = ("1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM").toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < 4; ++i){
			int pos = random.nextInt(temp.length);
			char c = temp[pos];
			stringBuilder.append(c + " ");	//stringbuilder线程不安全但是速度很快
		}
		g.drawString(stringBuilder.toString(), 10, 20);
	}
}
