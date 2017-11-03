import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
//Extra feature for show users about developers.
public class AboutListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "This Game was developed as a CSE 215 Course project of North South University By..\nMd. Badiuzzaman Pranto \nEmail: prantoamt@gmail.com\nPhone: +8801734261822."
					+ "\n\t\t\t\t\t&\nMubina Rahaman Jerin\nEmail: mubinarahamanjerin@gmail.com");
			
		}
	}
