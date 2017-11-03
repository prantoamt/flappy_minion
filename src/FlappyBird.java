import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;




public class FlappyBird extends JPanel implements ActionListener, KeyListener{

	public final int HEIGHT = 750;
	public final int WIDTH = 900;

	public BufferedImage bird;
	public boolean start, game_over;
	public ArrayList<Rectangle> columns;
	public int x = WIDTH/2-10, y = HEIGHT/2-10; 
	public int ticks, y_motion, score, high_score = 0;
	public Rectangle rec_bird;
	public Timer timer ;
	public Random rand;
	int count = 0;
	//ScoreLoader score_loader = new ScoreLoader();

	
	FlappyBird() 
	{			
		init();
	}
	
	private void init()
	{

		loadImage("Images/bird2.png");//bird's pic
		rec_bird = new Rectangle(x, y, 40, 45);//rectangle will cover the bird.
			
		timer = new Timer(15,this);
		rand = new Random();
		
		this.addKeyListener(this);// adds keyListener
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		
		columns = new ArrayList<Rectangle>();
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
	
		
		timer.start();
	}
	
	public void loadImage(String path)//Loads Bird Image from resource Folder
	{
		try {
			bird = ImageIO.read(FlappyBird.class.getResource(path));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Bird Image not found!!");
		}
	}
	

	public void addColumn(boolean start)
	{
		int space = 330;
		int width = 100;
		int height = 50 + rand.nextInt(300);
		if(start)
		{
			columns.add(new Rectangle(WIDTH+width + columns.size()*300,HEIGHT-height-120, width, height));
			columns.add(new Rectangle(WIDTH+width+(columns.size()-1)*300, 0, width, HEIGHT - height - space));
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size()-1).x+600, HEIGHT-height-120, width, height));
			columns.add(new Rectangle(columns.get(columns.size()-1).x, 0, width, HEIGHT - height - space));
		}
		
	}
	
	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.green.darker());
		g.fill3DRect(column.x, column.y, column.width, column.height,true);
	}
	
	
	public void moveColumn()
	{
		int speed = 10;//speed of column moving
		
		if(start)
		{
		for(int i = 0; i < columns.size(); i++)
		{
			Rectangle column = columns.get(i);
			column.x  -= speed;
		}
		
		}
	}
	
	public void reArrangeColumns()
	{
		for(int i = 0; i < columns.size(); i++)
		{
			Rectangle column = columns.get(i);
			 
			if(column.x + column.width < 0)
			{
				columns.remove(column);
				
				if(column.y==0)
				{
					addColumn(false);
				}
			}
			
		}
	}
	
	public void birdGravity() // Controls Bird's gravity
	{
		if(ticks%2==0 && y_motion<15)
		{
			y_motion += 1; 
		}
		
		rec_bird.y += y_motion;
		y = rec_bird.y;
		
	}
	
	
	public void birdFunction() //controls bird's score & other functions
	{
		for(Rectangle column: columns)
		{
			if(column.y == 0 && rec_bird.x + (rec_bird.width/2) == column.x + (column.width / 2) - 10)
			{
				
				score+= 1;
				SoundPanel.pointSound();
								
				if(score > high_score)
				{
					high_score = score;		
				}
				
				
			}
			if(column.intersects(rec_bird))
			{
				SoundPanel.hitSound();
				game_over = true;
				//rec_bird.x = column.x - rec_bird.width;
				//x = rec_bird.x;
				
				
				
			}
			
		}
		if(rec_bird.y > HEIGHT - 140 || rec_bird.y < 0)
		{
			SoundPanel.hitSound();
			game_over = true;
			//rec_bird.y = HEIGHT-140 - rec_bird.height;
			//y = rec_bird.y;
			
			
		}
		
		if(rec_bird.y + y_motion  >= HEIGHT - 140)
		{
			SoundPanel.hitSound();
			game_over = true;
			//rec_bird.y = HEIGHT - 140 - rec_bird.height;
			//y = rec_bird.y;
		
			
		}
		
		
   }
	
	public void jump()
	{
		if(game_over)
		{
			score = 0;
			x = WIDTH/2-10; 
			y = HEIGHT/2-10;
			rec_bird = new Rectangle(x, y, 40, 45);
			loadImage("Images/bird2.png");
			columns.clear();
			
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
			
			game_over = false;
		}
		if(!start)
		{
			start = true;
		}
		else if(!game_over)
		{
			if(y_motion>0)
			{
				y_motion = 0;
			}
				y_motion -= 10;
			
		}
	}
	
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.cyan);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.orange);
		g.fillRect(0, HEIGHT-120, WIDTH, 120);
		
		g.setColor(Color.green);
		g.fillRect(0, HEIGHT-120, WIDTH, 20);
		
		
		g.setColor(Color.cyan);
		g.drawRect(rec_bird.x, rec_bird.y, rec_bird.width, rec_bird.height);
		
		g.drawImage(bird, rec_bird.x, rec_bird.y, null, this);
		
		for(Rectangle column : columns)
		{
			paintColumn(g, column);
		}
		
		
		if(start)
		{
			g.setFont(new Font("Arial",1,30));
			g.setColor(Color.white);
			g.drawString(String.valueOf(score), WIDTH/2, 50);
			
			g.setFont(new Font("Arial",1,18));
			g.setColor(Color.red.darker().darker().darker());
			g.drawString("High Score : " + high_score, 5, 18);
		}
		if(!start)
		{
			g.setFont(new Font("Arial",1,25));
			g.setColor(Color.white);
			g.drawString("Press Space to start", WIDTH/2-100, HEIGHT/2-70);
		}
		if(game_over)
		{
			g.setFont(new Font("Arial",1,45));
			g.setColor(Color.red);
			g.drawString("Game Over !", WIDTH/2-100, HEIGHT/2-70);
		}
		
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ticks++;
		
		if(start && !game_over)
		{

			moveColumn();
			reArrangeColumns();
			birdGravity();
			birdFunction();
			
		}
		
		
		

		repaint();
		
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			jump();
			//SoundPanel.flapSound();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	

	
}
