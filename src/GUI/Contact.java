/**
 * @author Beyza Salyador
 * Class qui affiche la liste des contacts et qui permet l'affichage, l'ajout, la modification et la suppression d'un contact.
 * Il est egalement possible d'associer une image a un contact.
 */

package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.*;

import Contact.InfoContact;
import Contact.ListeContact;

public class Contact extends BaseInterface implements Serializable {
	
	/**
	 * Liste de contacts
	 */	
	private ListeContact liste;
	
	/**
	 * Panel central
	 */	
	private JPanel centre = new JPanel();
	
	/**
	 * Panel qui va contenir le scrollpane
	 */	
	private JPanel pNord = new JPanel();
	
	/**
	 * Panel qui va contenir les boutons permettant d'afficher, d'ajouter, de modifier, de supprimer un contact
	 */	
	private JPanel pSud = new JPanel();
	
	/**
	 * Table qui va contenir la liste de contacts
	 */
	private JTable tableau;
	
	/**
	 * Permet de scroller la table
	 */
	private JScrollPane scroll;
	
	/**
	 * Champs prevu pour remplir le nom du contact
	 */
	private JTextField nom = new JTextField(30);
	
	/**
	 * Champs prevu pour remplir le prenom du contact
	 */
	private JTextField prenom = new JTextField(30);
	
	/**
	 * Champs prevu pour remplir le numero de telephone du contact
	 */
	private JTextField tel = new JTextField(30);
	
	/**
	 * Label qui indique que le champ suivant doit contenir le nom du contact
	 */
	private JLabel TextNom = new JLabel("Nom :");
	
	/**
	 * Label qui indique que le champ suivant doit contenir le prenom du contact
	 */
	private JLabel TextPrenom = new JLabel("Prenom :");
	
	/**
	 * Label qui indique que le champ suivant doit contenir le numero de telephone du contact
	 */
	private JLabel TextTel = new JLabel("Numéro de téléphone :");
	
	/**
	 * Label qui va contenir le texte pour les différentes erreurs
	 */
	private JLabel TextErreur;
	
	/**
	 * Label qui va contenir le nom du contact
	 */
	private JLabel AfficherNom = new JLabel();
	
	/**
	 * Label qui va contenir le prenom du contact
	 */
	private JLabel AfficherPrenom = new JLabel();
	
	/**
	 * Label qui va contenir le numero de telephone du contact
	 */
	private JLabel AfficherTel = new JLabel();
	
	/**
	 * Bouton qui permet d'afficher un contact avec toutes ses infos
	 */
	private JButton afficherContact = new JButton("Afficher");
	
	/**
	 * Bouton qui permet d'arriver sur la page d'ajout d'un contact
	 */
	private JButton ajouter = new JButton ("Ajouter");
	
	/**
	 * Bouton qui va reellement ajouter le contact dans la liste
	 */
	private JButton ajouter2 = new JButton ("Ajouter");
	
	/**
	 * Bouton qui permet de supprimer le contact selectionne dans la liste
	 */
	private JButton supprimer = new JButton("Supprimer");
	
	/**
	 * Bouton qui permet d'arriver sur la page de modification d'un contact
	 */
	private JButton modifier = new JButton("Modifier");
	
	/**
	 * Bouton qui va réellement modifier le contact dans la liste
	 */
	private JButton modifier2 = new JButton("Modifier");
	
	/**
	 * Bouton qui permet de fermer la fenetre en cours et de revenir à la précédente
	 */
	private JButton ok = new JButton("OK");
	
	/**
	 * ImageIcon qui contiendra l'image du contact
	 */
	private ImageIcon photoDuContact = new ImageIcon();
	
	/**
	 * Bouton dans lequel il y aura l'ImageIcon du contact
	 */
	private JButton ajouterPhoto = new JButton();
	
	/**
	 * Police d'écriture choisi pour les textes
	 */
	private Font taille = new Font ("Arial", Font.PLAIN, 15);
	
	/**
	 * Class qui permet d'afficher la fenetre d'ajout d'un contact
	 */
	private FrameAdd fenetreAjout;
	
	/**
	 * Class qui permet d'afficher la fenetre de modification d'un contact
	 */
	private FrameEdit fenetreEdition;
	
	/**
	 * Class qui permet d'afficher la fenetre d'erreur en cas de champs non remplis
	 */
	private JDialog fenetreErreur;
	
	/**
	 * Class qui permet d'afficher la fenetre d'erreur en cas de mauvais format pour le numero de telephone
	 */
	private JDialog fenetreErreurTel;
	
	/**
	 * Class qui permet d'afficher la fenetre d'affichage d'un contact
	 */
	private JDialog fenetreAfficher;
	
	/**
	 * Class qui permet d'afficher la galerie d'images pour associer une image a un contact
	 */
	private JDialog fenetrePhoto;
	
	/**
	 * Boolean qui empeche l'ouverture de 2 fenetres
	 */
	private boolean erreurDouble = false;
	
	/**
	 * Boolean qui empeche la creation de 2 contacts similaires
	 */
	private boolean creation = false;
	
	public Contact(InterfacePrincipale interfacePrinci) throws IOException {
		
		super(interfacePrinci);
		
		sud.setBackground(new Color(128,128,128));
		
		//Enleve les decorations
		setUndecorated(true);
		
		setTitle("Contact");
		
		//Lien avec ListeContact
		liste = new ListeContact(this);

		//Création de la JTable avec ListeContact
		tableau = new JTable(liste.getArray2D(), new Object[] {"Nom", "Prenom"});
		//Regle la hauteur de la ligne
		tableau.setRowHeight(40);

		//Création de la JScrollPane en inserant la JTable a l'interieur
		scroll = new JScrollPane(tableau);

		//Regle la taille du ScrollPane
		scroll.setPreferredSize(new Dimension((int)getContentPane().getPreferredSize().getWidth()-10, 600));

		//Regle la dimension du bouton qui contiendra l'ImageIcon du contact
		ajouterPhoto.setPreferredSize(new Dimension(150, 190));
		
		//Ajout des ActionListener aux boutons "Afficher", "Ajouter", "Modifier" et "Supprimer"
		afficherContact.addActionListener(new SelectButton());
		ajouter.addActionListener(new SelectButton());
		modifier.addActionListener(new SelectButton());
		supprimer.addActionListener(new SelectButton());

		//Ajout du ScrollPane (qui contient la JTable qui contient la List) dans le panel pNord
		pNord.add(scroll);
		
		//Ajout du panel pNord dans le panel centre
		centre.add(pNord, BorderLayout.NORTH);
		
		// Ajout du panel pSud dans le panel CENTER 
		add(centre, BorderLayout.CENTER);
		
		//Ajout des boutons
		pSud.add(afficherContact);
		pSud.add(ajouter);
		pSud.add(modifier);
		pSud.add(supprimer);
		centre.add(pSud, BorderLayout.SOUTH);
		
		//Mise en page
		centre.setBackground(Color.black);
		pSud.setBackground(Color.black);
		pNord.setBackground(Color.black);

		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);

		tableau.setBackground(Color.DARK_GRAY);
		tableau.setForeground(Color.white);
		tableau.setFont(taille);

		afficherContact.setBackground(Color.darkGray);
		afficherContact.setForeground(Color.WHITE);
		ajouter.setBackground(Color.darkGray);
		ajouter.setForeground(Color.WHITE);
		supprimer.setBackground(Color.darkGray);
		supprimer.setForeground(Color.WHITE);
		modifier.setBackground(Color.darkGray);
		modifier.setForeground(Color.WHITE);
	}

	/**
	 * Class qui definit les actions des boutons
	 */
	class SelectButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//Quand on appuie sur le bouton ajouter de la page qui affiche la liste des contacts
			if(e.getSource() == ajouter) {
				//Création de la fenêtre d'ajout d'un contact
				fenetreAjout = new FrameAdd() ;
				fenetreAjout.setVisible(true) ;
				//Remise a 0 de l'icon du bouton
				ajouterPhoto.setIcon(null);

				//Remise a 0 des champs
				nom.setText("");
				prenom.setText("");
				tel.setText("");

				Contact.this.erreurDouble = false;
				Contact.this.creation = false;
			}

			//Quand on appuie sur le bouton ajouter de la page ajouter et que tous les champs sont remplis 
			if(e.getSource() == ajouter2 && (!nom.getText().equals("") && !prenom.getText().equals("") && !tel.getText().equals(""))) {
				//Variable qui contiendra le numero de telephone
				long telChiffre = 0;			
				
				try {
					//Conversion du JTextField en Long
					telChiffre = Long.parseLong(tel.getText());
					//Ajouter 1 seule fois
					if(creation == false){
						//Ajout du contact
						liste.addContact(nom.getText(), prenom.getText(), telChiffre, photoDuContact);
						Contact.this.creation = true;
					}
					//Reactualisation du panel de la JTable
					refresh();
					//Ferme la fenêtre
					fenetreAjout.dispose();
				}
				//Si le format du numero de telephone n'est pas respecte
				catch(NumberFormatException ex){
					//Creation de la fenetre d'erreur pour le format du numero de telephone
					fenetreErreurTel = new ErreurTel();
					fenetreErreurTel.setLocationRelativeTo(fenetreAjout);
					fenetreErreurTel.setVisible(true);
				}
			}

			//Quand on appuie sur le bouton supprimer de Contact
			if(e.getSource() == supprimer) {
				//Supprimession du contact sélectionné
				liste.deleteContact(tableau.getSelectedRow());
				//Actualisation de la JTable
				refresh();				
			}

			//Quand on appuie sur modifier dans la fenêtre Contact
			if(e.getSource() == modifier) {
				//Seulement si un contact est selectionne
				if(tableau.getSelectedRow()!=-1){
					//Creation de la fenetre de modification
					fenetreEdition = new FrameEdit();
					fenetreEdition.setVisible(true);
					Contact.this.erreurDouble = false;
				}
			}

			//Quand on appuie sur modifier dans la fenêtre modifier et que tous les champs sont remplis
			if(e.getSource() == modifier2 && (!nom.getText().equals("") && !prenom.getText().equals("") && !tel.getText().equals(""))) {
				long telChiffre = 0;
				try {
					telChiffre = Long.parseLong(tel.getText());
				}
				catch(NumberFormatException ex) {
					fenetreErreurTel = new ErreurTel();
					fenetreErreurTel.setVisible(true);
				}
				//Modification du contact
				liste.editContact(tableau.getSelectedRow(), new InfoContact(nom.getText(), prenom.getText(), telChiffre, photoDuContact));
				refresh();
				fenetreEdition.dispose();
			}
			 
			//Quand tu appuie sur modifier ou ajouter et que pas tous les champs sont remplis
			if((e.getSource() == ajouter2 || e.getSource() == modifier2) && (nom.getText().equals("") || prenom.getText().equals("") || tel.getText().equals(""))) {
				//Creation de fenetre d'erreur
				fenetreErreur = new Erreur();
				//Tant qu'on a pas ferme cette fenetre, ne permet pas d'acceder aux autres
				fenetreErreur.setModal(true);
				
				//Centre la fenêtre en fonction de la fenetre precedente
				if(e.getSource()==ajouter2) {
					fenetreErreur.setLocationRelativeTo(fenetreAjout);
				}
				else {
					fenetreErreur.setLocationRelativeTo(fenetreEdition);
				}
				fenetreErreur.setVisible(true);
			}
			
			//Quand on appuie sur afficher dans Contact
			if(e.getSource() == afficherContact) {
				//Creation de la fenetre d'affichage de contact
				fenetreAfficher = new FrameAfficher();
				fenetreAfficher.setModal(true);
				fenetreAfficher.setVisible(true);
			}
			
			//Quand on appuie sur OK dans la fenêtre Afficher
			if(e.getSource() == ok) {
				//Ferme la fenêtre
				fenetreAfficher.dispose();
			}
			
			//Quand on appuie sur ajouter photo dans la fenêtre d'ajout
			if(e.getSource() == ajouterPhoto && erreurDouble == false) {
				//Creation de la fenetre permettant de selectionner une image de la galerie d'images
				fenetrePhoto = new FramePhoto();
				fenetrePhoto.setModal(true);
				fenetrePhoto.setVisible(true);
				Contact.this.erreurDouble = true;
			}
		}
	}	

	/**
	 * Class permettant d'afficher la fenetre d'ajout d'un contact
	 */
	class FrameAdd extends BaseDialog {
		JPanel center = new JPanel();

		public FrameAdd(){
			super(null);
			setTitle("Ajouter");
			center.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
			add(center, BorderLayout.CENTER);
			sud.setBackground(new Color(128, 128, 128));

			center.add(ajouterPhoto);
			center.add(TextNom);
			center.add(nom) ;
			center.add(TextPrenom);
			center.add(prenom) ;
			center.add(TextTel);
			center.add(tel) ;
			center.add(ajouter2);
			ajouter2.addActionListener(new SelectButton());
			ajouterPhoto.addActionListener(new SelectButton());

			//Mise en page
			center.setBackground(Color.lightGray);
			TextNom.setForeground(Color.black);			
			TextPrenom.setForeground(Color.black);
			TextTel.setForeground(Color.black);

			//Reglement de la police d'ecriture
			TextPrenom.setFont(taille);
			TextTel.setFont(taille);

			ajouterPhoto.setBackground(Color.black);
			ajouterPhoto.setForeground(Color.white);

			ajouter2.setBackground(Color.black);
			ajouter2.setForeground(Color.white);
		}
	}

	/**
	 * Class permettant d'afficher la fenetre de modification d'un contact
	 */
	class FrameEdit extends BaseDialog {

		JPanel center = new JPanel();

		public FrameEdit(){
			super(null);

			setTitle("Modifier");
			sud.setBackground(new Color(128, 128, 128));
			add(center, BorderLayout.CENTER);
			
			center.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50 ));

			center.add(ajouterPhoto);
			center.add(TextNom);
			center.add(nom);
			center.add(TextPrenom);
			center.add(prenom);
			center.add(TextTel);
			center.add(tel);
			center.add(modifier2);
			
			//Defini le texte du JTextField "Nom"
			nom.setText((String)tableau.getValueAt(tableau.getSelectedRow(), 0));
			
			//Defini l'image du contact
			ajouterPhoto.setIcon((ImageIcon)liste.getArray2D()[tableau.getSelectedRow()][3]);
			
			//Defini le texte du JTextField "Prenom"
			prenom.setText((String)tableau.getValueAt(tableau.getSelectedRow(), 1));

			//Defini le texte du JTextField "Telephone"
			tel.setText(String.valueOf(liste.getArray2D()[tableau.getSelectedRow()][2]));			

			ajouterPhoto.addActionListener(new SelectButton());
			modifier2.addActionListener(new SelectButton());

			//Mise en page
			center.setBackground(Color.lightGray);

			ajouterPhoto.setBackground(Color.black);
			ajouterPhoto.setForeground(Color.white);
			modifier2.setForeground(Color.white);
			modifier2.setBackground(Color.black);

			TextNom.setFont(taille);
			TextPrenom.setFont(taille);
			TextTel.setFont(taille);
		}
	}

	/**
	 * Class permettant d'afficher l'erreur qui est du au faite que tous les champs ne sont pas remplis
	 */
	class Erreur extends JDialog {
		JPanel center = new JPanel ();

		public Erreur(){
			setTitle("Erreur");
			setModal(true);
			setSize(320,150);
			add(center, BorderLayout.CENTER);
			
			TextErreur = new JLabel("Veuillez remplir tous les champs du contact!");

			center.add(TextErreur);
			center.setBackground(Color.lightGray);
		}	
	}
	
	/**
	 * Class permettant d'afficher l'erreur qui est du au faite qu'on rentre du texte au lieu de chiffres
	 */
	class ErreurTel extends JDialog	{
		JPanel center = new JPanel();

		public ErreurTel(){
			setTitle("Erreur");
			setModal(true);
			setSize(320,150);
			add(center, BorderLayout.CENTER);
			
			TextErreur = new JLabel("Veuillez entrer des chiffres pour le n° de téléphone.");
			
			center.add(TextErreur);
			center.setBackground(Color.lightGray);
		}	
	}
	
	/**
	 * Class permettant d'afficher les informations du contact selectionne
	 */
	class FrameAfficher extends BaseDialog {
		
		JPanel center = new JPanel ();
		Font TDetail = new Font("Arial", Font.PLAIN, 13);
		
		public FrameAfficher(){
			super(null);

			setTitle("Informations du contact");
			sud.setBackground(new Color(128, 128, 128));
			center.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));

			center.add(TextNom);
			center.add(AfficherNom);
			AfficherNom.setText((String)tableau.getValueAt(tableau.getSelectedRow(), 0));
			center.add(TextPrenom);
			center.add(AfficherPrenom) ;
			AfficherPrenom.setText((String)tableau.getValueAt(tableau.getSelectedRow(), 1));
			center.add(TextTel);
			center.add(AfficherTel) ;
			AfficherTel.setText(String.valueOf(liste.getArray2D()[tableau.getSelectedRow()][2]));
			center.add(ok);
			
			ok.addActionListener(new SelectButton());
			add(center, BorderLayout.CENTER);
			
			//Mise en page
			center.setBackground(Color.lightGray);
			ok.setBackground(Color.black);
			ok.setForeground(Color.white);
			
			TextNom.setFont(taille);
			TextPrenom.setFont(taille);
			TextTel.setFont(taille);
			
			AfficherNom.setFont(TDetail);
			AfficherPrenom.setFont(TDetail);
			AfficherTel.setFont(TDetail);	
		}
	}

	/**
	 * Class permettant d'afficher la galerie d'images
	 */
	class FramePhoto extends BaseDialog {		
		JPanel center = new JPanel();
		
		private JPanel panelCentral = new JPanel();
		private GridLayout galleryOfPictures = new GridLayout(4, 2);
		private JPanel panel = new JPanel();
		private JScrollPane scrollPane;

		//Fichier où il y a toutes les images
		private File imagesFile = new File("./Images"); 

		//Tableau de String qui va stocké tous les chemins des photos
		private String [] listImages = imagesFile.list();

		private ImageIcon img;
		
		//Tableau de boutons dans lesquels on va ajouter les ImageIcon
		private JButton[] buttons = new JButton[listImages.length];

		public FramePhoto(){
			super(null);
			sud.setBackground(new Color(128, 128, 128));
			initialize();

			for(int j=0; j<listImages.length; j++){

				//Va chercher l'image et configure sa taille
				img = new ImageIcon(imagesFile + "/" + listImages[j]);
				Image image = img.getImage();
				Image newimg = image.getScaledInstance(200, 300,  java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(newimg);

				//Dans chaque bouton, mettre une image
				buttons[j] = new JButton(img);
				
				//Taille des boutons
				buttons[j].setPreferredSize(new Dimension(150, 190)); 

				//PANEL
				panelCentral.add(buttons[j]);

				//Quand on clique sur une image, ca revient sur la fenetre d'ajout ou de modification de contact et affiche l'image selectionne dans le bouton
				buttons[j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton)e.getSource();
						photoDuContact = (ImageIcon)b.getIcon();
						ajouterPhoto.setIcon(photoDuContact);
						fenetrePhoto.dispose();
					}
				});
			}
		}

		/**
		 * Class permettant d'initialiser l'aspect de la galerie d'images
		 */
		private void initialize() {
			// SPACE AROUND PHOTOS
			galleryOfPictures.setHgap(5);
			galleryOfPictures.setVgap(5);

			// PANELS
			panelCentral.setBackground(Color.BLACK);
			panelCentral.setLayout(galleryOfPictures);
			panel.setBackground(Color.GRAY);

			scrollPane = new JScrollPane(panelCentral);
			scrollPane.setPreferredSize(new Dimension(480,700));

			add(panel);
			add(scrollPane);
		}
	}

	/**
	 * Class permettant de rafraichir la JTable et la JScrollPane
	 */
	private void refresh() {
		pNord.remove(scroll);
		tableau = new JTable(liste.getArray2D(), new Object[] {"Nom", "Prenom"});
		tableau.setRowHeight(40);
		scroll = new JScrollPane(tableau);
		scroll.setPreferredSize(new Dimension((int)getContentPane().getPreferredSize().getWidth()-10, 600));
		pNord.add(scroll);
		pNord.revalidate();
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		tableau.setBackground(Color.DARK_GRAY);
		tableau.setForeground(Color.white);
		tableau.setFont(taille);
	}
}