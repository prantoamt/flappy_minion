import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class frame extends JFrame {
	
	public final int HEIGHT = 750, WIDTH = 900;
	
	
	public frame()
	{
		FlappyBird flappybird = new FlappyBird();
		JFrame frame = new JFrame();
		frame.add(flappybird);
		//frame.setIconImage(arg0);
		
		
		JMenuBar menubar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		frame.setJMenuBar(menubar);
		menubar.add(file);
		JMenuItem about = new JMenuItem("About..");
		file.add(about);
		about.addActionListener(new AboutListener());
		
		frame.setTitle("Flappy Minion");
		frame.setResizable(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}
	
}
