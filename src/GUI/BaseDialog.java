/**
 * @author Beyza Salyador
 * Class créée afin d'avoir un design de base pour toutes les interfaces avec JDialog
 * Il y a deja une class BaseInterface qui fait ca, mais il faut aussi une identique heritant de JDialog pour interdire 
 * de passer à une autre fenetre sans fermer celle en cours
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.BaseInterface.Select;

public class BaseDialog extends JDialog {

	/**
	 * Panel du bas contenant les boutons retour, accueil et fermeture
	 */
	protected JPanel sud = new JPanel();

	/**
	 * Panel du haut contenant l'heure et le label "Swisscom"
	 */
	private JPanel nord = new JPanel();

	/**
	 * Panel de gauche
	 */	
	protected JPanel ouest = new JPanel();
	
	/**
	 * Panel de droite
	 */
	protected JPanel est = new JPanel();

	/**
	 * Bouton retour
	 */
	private JButton retour = new JButton();

	/**
	 * Bouton accueil
	 */
	private JButton accueil = new JButton();

	/**
	 * Bouton fermeture
	 */
	private JButton fermeture = new JButton();

	/**
	 * ImageIcon pour le bouton retour
	 */
	private ImageIcon retourImage = new ImageIcon ("./src/img/retour.png");

	/**
	 * ImageIcon pour le bouton accueil
	 */
	private ImageIcon accueilImage = new ImageIcon ("./src/img/home.png");

	/**
	 * ImageIcon pour le bouton fermeture
	 */
	private ImageIcon fermetureImage = new ImageIcon ("./src/img/delete.png");

	/**
	 * Variable contenant la date d'aujourdhui
	 */
	private Date aujourdhui;

	/**
	 * Label contenant l'heure actuelle
	 */
	protected JLabel heure = new JLabel(getTime());

	/**
	 * Timer
	 */
	private Timer timer = new Timer();

	/**
	 * Label Swisscom
	 */
	private JLabel swisscom = new JLabel ("Swisscom");

	/**
	 * Police d'ecrtiure du texte
	 */
	private Font taille = new Font ("Arial", Font.BOLD, 16);

	/**
	 * BaseInterface pour permettre d'avoir qu'une seule fenetre ouverte a la fois
	 */
	BaseInterface interfacePrinci ;

	public BaseDialog(InterfacePrincipale interfacePrinci) {

		this.interfacePrinci = interfacePrinci;  

		//Enleve les decorations
		setUndecorated(true);

		//Couleur d'arriere-plan des panels
		sud.setBackground(new Color(128,128,128));
		ouest.setBackground(new Color(128,128,128));
		est.setBackground(new Color(128,128,128));

		setLocationByPlatform(true);
		//Specifie la location de la fenetre
		setLocation(800, 100);
		boolean flag = isLocationByPlatform();

		//Taille de l'écran smartphone
		setSize(504, 840);

		//Ajout du panel sud et nord
		add(sud, BorderLayout.SOUTH);
		add(nord, BorderLayout.NORTH);
		add(ouest, BorderLayout.WEST);
		add(est, BorderLayout.EAST);

		//Ajout d'ActionListener aux boutons
		retour.addActionListener(new Select());
		fermeture.addActionListener(new Select());
		accueil.addActionListener(new Select());

		//Incorpore les images aux boutons
		accueil.setMargin(new Insets(0, 0, 0, 0)); // Enleve les marges
		//Ajout image représentant le home dans le bouton home
		accueil.setIcon(accueilImage); 
		accueil.setPreferredSize(new Dimension(145, 45));
		accueil.setBackground(Color.WHITE);

		fermeture.setMargin(new Insets( 0, 0, 0, 0));
		fermeture.setIcon(fermetureImage);
		fermeture.setPreferredSize(new Dimension(145, 45));
		fermeture.setBackground(Color.WHITE);

		retour.setMargin(new Insets( 0, 0, 0, 0));
		retour.setIcon(retourImage);
		retour.setPreferredSize(new Dimension(145, 45));
		retour.setBackground(Color.WHITE);

		//Ajout des boutons au panel sud
		sud.add(fermeture);
		sud.add(accueil);
		sud.add(retour);

		//Heure
		setTimer();

		//Ajout de l'heure et du label Swisscom au nord
		nord.setLayout(new BorderLayout());
		nord.add(swisscom, BorderLayout.WEST);
		nord.add(heure, BorderLayout.EAST);

		//Gestion des couleurs des JPANEL + écritures
		nord.setBackground(Color.darkGray);
		sud.setBackground(Color.WHITE);
		swisscom.setForeground(Color.white);
		heure.setForeground(Color.white);

		swisscom.setFont(taille);
		heure.setFont(taille);

		//Le panel de centre est ajouté separement dans les autres inferfaces afin de pouvoir ajouter ce que l'on veut au centre.	
	}

	/**
	 * Recupere l'heure et la rafaichit
	 */
	private String getTime(){
		aujourdhui = new Date();
		DateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(aujourdhui);
	}

	private void setTimer(){
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				heure.setText(BaseDialog.this.getTime());
			}
		}, 1000, 1000);
	}

	/**
	 * Indique l'action des boutons
	 */
	class Select implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			//Revient a la fenetre precedemment ouverte
			if(e.getSource() == retour){
				setVisible(false);
				if (interfacePrinci != null) { 
					interfacePrinci.setVisible(true);
				}
			}

			//Ferme toutes les fenetres
			if(e.getSource() == fermeture){
				System.exit(0);
			}

			//Revient sur l'interface principale
			if(e.getSource() == accueil){ 
				InterfacePrincipale ip = new InterfacePrincipale();
				ip.setVisible(true);
				setVisible(false);
			}
		}
	}
}