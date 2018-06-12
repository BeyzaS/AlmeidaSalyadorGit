/**
 * @author Beyza Salyador & Marco Almeida
 * Class qui cree la galerie d'images.
 * Il est possible d'ajouter et de supprimer une image, mais egalement d'afficher une image en grand en double-cliquant dessus.
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Refreshable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Contact.InfoContact;

class Pictures extends BaseDialog {

	/**
	 * Panel central qui contient les 2 autres panels
	 */
	private JPanel panelCentral = new JPanel();

	/**
	 * Panel qui contient le ScrollPane
	 */
	private JPanel pNorth = new JPanel();

	/**
	 * Panel qui contient les boutons "Ajouter" et "Supprimer"
	 */
	private JPanel pSouth = new JPanel();

	/**
	 * GridLayout qui definit la mise en page de l'ArrayList de boutons
	 */
	private GridLayout galleryOfPictures = new GridLayout(4, 2);

	/**
	 * Panel qui contient le GridLayout
	 */
	private JPanel grille = new JPanel(galleryOfPictures);

	/**
	 * JScrollPane qui contient le panel grille qui contient le GridLayout
	 */
	private JScrollPane scrollPane = new JScrollPane(grille);

	/**
	 * Boutons permettant d'ajouter une photo
	 */
	private JButton ajouterPhoto = new JButton("Ajouter");

	/**
	 * Boutons permettant de supprimer une photo
	 */
	private JButton supprimerPhoto = new JButton("Supprimer");

	/**
	 * ImageIcon qui contient l'image du bouton selectionne
	 */
	private ImageIcon imageAgrandie;

	/**
	 * Bouton qui contient l'ImageIcon du bouton selectionne
	 */
	private JButton imageAgrandieBouton;

	/**
	 * Dossier qui contient les images de la galerie
	 */
	private File dossierDesImages = new File("./Images");

	/**
	 * Tableau qui contient tous les chemins des images
	 */
	private String [] listeCheminsDesImages = dossierDesImages.list();

	/**
	 * ImageIcon qui represente chaque image dans la galerie
	 */
	private ImageIcon chaqueImage;

	/**
	 * Dimension des JButton contenant les ImageIcon
	 */
	private Dimension size = new Dimension(150, 190);

	/**
	 * ArrayList de boutons qui est de la meme longueur que le tableau listeCheminsDesImages
	 */
	private List<JButton> boutonsImages = new ArrayList<JButton>(listeCheminsDesImages.length);

	/**
	 * Variable qui represente l'index du bouton (de l'ArrayList boutonsImages) surlequel on vient de cliquer
	 */
	private int indexRecherche;

	public Pictures(InterfacePrincipale interfacePrinci){

		super(interfacePrinci);

		sud.setBackground(new Color(128,128,128));

		setBackground(Color.BLACK);
		setUndecorated(true);

		//Ajout du panelCentral au centre
		add(panelCentral, BorderLayout.CENTER);

		//Ajout du panel pNorth et pSouth dans le panelCentral
		panelCentral.add(pNorth, BorderLayout.NORTH);
		panelCentral.add(pSouth, BorderLayout.SOUTH);

		//Ajout du ScrollPane dans le panel pNorth
		pNorth.add(scrollPane);
		//Reglement des dimensions du ScrollPane
		scrollPane.setPreferredSize(new Dimension(480, 700));

		//Ajout des boutons "Ajouter", "Supprimer" dans le panel pSouth
		pSouth.add(ajouterPhoto);
		pSouth.add(supprimerPhoto);

		//Reglement des espaces entre les boutons du GridLayout
		galleryOfPictures.setHgap(5);
		galleryOfPictures.setVgap(5);

		//Ajout de l'ActionListener au bouton "Ajouter"
		ajouterPhoto.addActionListener(new AjouterClick());

		//Appel de la méthode qui va initialiser la galerie d'images
		initialiseGalerieImages();
	}

	/**
	 * @author Beyza Salyador
	 * Methode qui initialise la galerie d'images
	 */
	public void initialiseGalerieImages() {

		for(int i=0; i<listeCheminsDesImages.length; i++){

			//Defini l'image qu'il faut prendre dans le dossier Images
			chaqueImage = new ImageIcon(dossierDesImages + "/" + listeCheminsDesImages[i]);
			//Prend l'image
			Image image = chaqueImage.getImage();
			//Reglement des dimensions l'image
			Image newimg = image.getScaledInstance(200, 300,  java.awt.Image.SCALE_SMOOTH);
			//Met l'image dans l'ImageIcon
			chaqueImage = new ImageIcon(newimg);

			//Creation d'un bouton avec l'ImageIcon a l'interieur
			boutonsImages.add(new JButton(chaqueImage));
			//Reglement de la dimension du bouton
			boutonsImages.get(i).setPreferredSize(size);

			//Ajout du bouton dans le panel qui a comme layout le GridLayout
			grille.add(boutonsImages.get(i));

			//Ajout de MouseListener sur chaque bouton
			boutonsImages.get(i).addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent mouseEvent) {
					//Si double-clic
					if(mouseEvent.getClickCount() == 2) {
						//Recuperation du bouton sur lequel on a clique
						JButton b = (JButton)mouseEvent.getSource();

						//Recuperation de l'image et insertion dans imageAgrandie
						imageAgrandie = (ImageIcon)b.getIcon();
						Image image = imageAgrandie.getImage();
						Image newimg2 = image.getScaledInstance(400,  633, java.awt.Image.SCALE_SMOOTH);
						imageAgrandie = new ImageIcon(newimg2);
						imageAgrandieBouton = new JButton(imageAgrandie);

						//Creation de la fenetre qui va afficher l'image en grand
						ImageAgrandie ia = new ImageAgrandie();
						ia.setVisible(true);
					}

					//Si un seul clic
					if(mouseEvent.getClickCount() == 1) {
						//Recuperation du bouton sur lequel on a clique
						JButton b = (JButton)mouseEvent.getSource();
						//Recuperation de l'index du bouton qui se trouve dans l'ArrayList
						indexRecherche = boutonsImages.indexOf(b);
						//Ajout d'un ActionListener sur le bouton supprimer
						supprimerPhoto.addActionListener(new SupprimerClick());
					}
				}
			});
		}
	}

	/**
	 * @author Beyza Salyador
	 * Class qui affiche l'image selectionne en grand
	 */
	class ImageAgrandie extends BaseDialog {

		public ImageAgrandie() {
			super(null);

			sud.setBackground(new Color(128,128,128));

			imageAgrandieBouton.setBackground(Color.LIGHT_GRAY);
			//Ajout du bouton selectionne dans la fenetre
			add(imageAgrandieBouton);
		}
	}

	/**
	 * @author Marco Almeida
	 * Class qui supprime l'image selectionne de la galerie d'images
	 */
	class SupprimerClick implements ActionListener {
		public void actionPerformed(ActionEvent e){

			int num = indexRecherche+1;

			if(num < 10) {
				File f = new File("./Images/numero0" + num + ".jpeg"); 
				f.delete();
			}
			else {
				File f = new File("./Images/numero" + num + ".jpeg"); 
				f.delete();
			}
		}
	}

	/**
	 * @author Marco Almeida
	 * Class qui ajoute l'image selectionne dans l'explorateur windows dans la galerie d'images
	 */
	class AjouterClick implements ActionListener {
		public void actionPerformed(ActionEvent e){		

			//JFileChoose permettant d'avoir acces a l'explorateur windows
			JFileChooser browser = new JFileChooser();

			//FileNameExtensionFilter qui permet de selectionner uniquement les fichiers .jpeg
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG Image", "jpeg");

			//Variable qui va contenir la valeur que retourne le JFileChooser
			int returnValue;

			//Fichier selectionne dans l'explorateur windows
			File selectedFile;

			//Associer le filtre de fichiers au JFileChooser
			browser.setFileFilter(filter);
			//Reglement des dimensions du JFileChooser
			browser.setPreferredSize(new Dimension(410, 510));

			//Contient la valeur que retourne le JFileChooser
			returnValue = browser.showOpenDialog(pNorth);

			//Si la valeur que retourne le JFileChooser est egale a APPROVE.OPTION
			if(returnValue == browser.APPROVE_OPTION) {

				//Stockage du fichier selectionne dans la variable selectedFile
				selectedFile = browser.getSelectedFile();

				//Stockage du nom et de l'extension du fichier selectionne
				String nomImage = selectedFile.getName();

				//Creation d'un fichier sous Images au meme nom que le nom du fichier selectionne
				File f = new File("./Images/" + nomImage);

				//Renomme le chemin du selectedFile dans le but de le deplacer sous le dossier Images
				selectedFile.renameTo(f);		
			}
		}
	}
}
