/**
 * @author Beyza Salyador & Marco Almeida
 * Class qui est l'interface principale du smartphone. Elle est la sous-classe de BaseInterface a laquelle
 * on ajoute le JPanel center
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import GUI.Contact;

public class InterfacePrincipale extends BaseInterface {

	/**
	 * Panel de centre
	 */
	private JPanel center = new JPanel();
	
	/**
	 * Bouton representant l'application calculette
	 */
	private JButton calculatrice = new JButton();
	
	/**
	 * Bouton representant l'application de photos
	 */
	private JButton photo = new JButton();
	
	/**
	 * Bouton representant l'application de contacts
	 */
	private JButton contact = new JButton();
	
	/**
	 * ImageIcon de la calculette qui va etre insere dans le bouton calculette
	 */
	private ImageIcon calculetteImage = new ImageIcon ("./src/img/calculette.png");
	
	/**
	 * ImageIcon de la galerie de contacts qui va etre inseree dans le bouton de la galerie de contacts
	 */
	private ImageIcon contactImage = new ImageIcon ("./src/img/contact.png");
	
	/**
	 * ImageIcon de la galerie d'images qui va etre inseree dans le bouton de la galerie d'images
	 */
	private ImageIcon photoImage = new ImageIcon("./src/img/photo.png");
	
	/**
	 * Police d'ecriture
	 */
	private Font Theure = new Font ("Arial", Font.PLAIN, 100);
	
	public InterfacePrincipale (){
		super(null);
		
		setTitle("Interface principale");
		
		sud.setBackground(new Color(128,128,128));//gray

		center.setLayout(new BorderLayout());
		setUndecorated(true);

		contact.setMargin(new Insets(0, 0, 0, 0));	
		contact.setIcon(contactImage);
		contact.setBackground(new Color(0,0,0,0));
		contact.setOpaque(false);
		contact.setBorder(null);
		contact.setContentAreaFilled(false);

		calculatrice.setMargin(new Insets(0, 0, 0, 0));	
		calculatrice.setIcon(calculetteImage);
		calculatrice.setBackground(new Color(0,0,0,0));
		calculatrice.setOpaque(false);
		calculatrice.setBorder(null);
		calculatrice.setContentAreaFilled(false);
		
		photo.setMargin(new Insets( 0, 0, 0, 0));
		photo.setIcon(photoImage);
		photo.setBackground(new Color(0,0,0,0));
		photo.setOpaque(false);
		photo.setBorder(null);
		photo.setContentAreaFilled(false);

		//Ajout d'action aux boutons representant les applications
		contact.addActionListener(new Select());
		calculatrice.addActionListener(new Select());
		photo.addActionListener(new Select());

		//Panel créé afin de pouvoir tout mettre ensemble sinon problème avec l'affichage de l'heure + les boutons
		JPanel apps = new JPanel() ;

		apps.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 200));
		apps.add(contact);
		apps.add(calculatrice);
		apps.add(photo);

		//Centre l'heure
		heure.setHorizontalAlignment(SwingConstants.CENTER);

		center.add(heure, BorderLayout.NORTH);
		center.add(apps, BorderLayout.CENTER);
		add(center, BorderLayout.CENTER);

		//Mise en page
		apps.setBackground(Color.black);
		heure.setFont(Theure);

		center.setBackground(Color.black);	
	}

	/**
	 * Class qui definit les actions des boutons
	 */
	class Select implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			//Ouvre la galerie de contacts
			if(e.getSource() == contact){
				Contact c;
				try {
					c = new Contact(InterfacePrincipale.this);
					c.setVisible(true);
					setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			//Ouvre la calculette
			if(e.getSource() == calculatrice){
				Calculette cal = new Calculette(InterfacePrincipale.this);
				cal.setVisible(true);
				setVisible(false);
			}			

			//Ouvre la galerie d'images
			if(e.getSource() == photo){
				Pictures p = new Pictures(InterfacePrincipale.this);
				p.setVisible(true);
				setVisible(false);
			}			
		}
	}
}
