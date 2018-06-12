/**
 * @author Marco Almeida
 * Class qui affiche une calculette et qui s'occupe de ses fonctionnalites
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.Calculette.CancelListener;
import GUI.Calculette.DivisionListener;
import GUI.Calculette.EqualListener;
import GUI.Calculette.MultiplicationListener;
import GUI.Calculette.NumberListener;
import GUI.Calculette.SubtractionListener;
import GUI.Calculette.SumListener;

public class Calculette extends BaseInterface {

	/**
	 * Police d'ecriture
	 */
	private Font fontDisplay = new Font("Arial", Font.BOLD, 40);

	/**
	 * Tableau qui contient les noms des boutons de la calculette
	 */
	private String[] tableauString = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};

	/**
	 * Tableau qui contient les boutons de la calculette
	 */
	private JButton[] tableauBoutons = new JButton[tableauString.length];

	/**
	 * Panel du nord
	 */
	private JPanel panelNorth = new JPanel();
	
	/**
	 * Panel qui contient les operateurs
	 */
	private JPanel operateurs = new JPanel();
	
	/**
	 * Panel qui contient les chiffres
	 */
	private JPanel chiffres = new JPanel();
	
	/**
	 * Panel qui contient le label qui contient le resultat de l'operation
	 */
	private JPanel displayButtonAndCalculation = new JPanel();
	
	/**
	 * Label qui contient le resultat de l'operation
	 */
	private JLabel afficherResultat = new JLabel();
	
	/**
	 * Dimension des boutons
	 */
	private Dimension dimensionBoutons = new Dimension(100, 120);
	
	/**
	 * Dimension des operateurs
	 */
	private Dimension dimensionOperateurs = new Dimension(80, 100);
	
	/**
	 * Police d'ecriture
	 */
	private Font font = new Font("Arial",Font.BOLD, 30);

	/**
	 * Le premier nombre sur lequel on clique
	 */
	private double number1;
	
	/**
	 * Boolean qui va definir si on clique sur un operateur
	 */
	private boolean clicOperateur = false;
	
	/**
	 * Boolean pour la mise a jour du calcul
	 */
	private boolean update = false;
	
	/**
	 * Variable representant l'operateur
	 */
	private String operateur = "";
	
	/**
	 * Panel qui va contenir les chiffres et les operateurs
	 */
	private JPanel operateursEtChiffres = new JPanel();
	
	public Calculette(InterfacePrincipale interfacePrinci){
		
		super(interfacePrinci);
		//Couleur autour des boutons du bas
		sud.setBackground(new Color(128,128,128));
		
		setBackground(Color.BLACK);
		
		//Enleve les decorations
		setUndecorated(true);
		
		initialize();
	}

	/**
	 * Methode qui customise et ajoute tous les composants pour cette class
	 */
	private void initialize() {

		//Met 0 comme valeur par defaut a l'affichage et aligne a droite
		afficherResultat = new JLabel("0");
		afficherResultat.setFont(fontDisplay);
		afficherResultat.setHorizontalAlignment(JLabel.RIGHT);
		afficherResultat.setPreferredSize(new Dimension(180, 60));
		
		operateursEtChiffres.setLayout(new BorderLayout());
		operateursEtChiffres.add(chiffres, BorderLayout.CENTER);
		operateursEtChiffres.add(operateurs, BorderLayout.EAST);
		
		//Redimensionne les panels
		operateurs.setPreferredSize(new Dimension(100, 500));
		chiffres.setPreferredSize(new Dimension(320, 500));
		displayButtonAndCalculation.setPreferredSize(new Dimension(450, 80));

		//Ajout du label d'affichage au panel
		panelNorth.setBackground(Color.GRAY);
		add(panelNorth, BorderLayout.NORTH);
		displayButtonAndCalculation.add(afficherResultat);
		
		//Cree un GridLayout
		displayButtonAndCalculation.setLayout(new GridLayout(1, 1));
		displayButtonAndCalculation.setBorder(BorderFactory.createLineBorder(Color.green	));
		
		//Met les elements de la calculette a la bonne place
		chiffres.setBackground(Color.DARK_GRAY);
		operateurs.setBackground(Color.BLACK);
		add(displayButtonAndCalculation, BorderLayout.NORTH);
		add(operateursEtChiffres,BorderLayout.CENTER);

		for(int i = 0; i < tableauString.length; i++){
			tableauBoutons[i] = new JButton(tableauString[i]);
			tableauBoutons[i].setPreferredSize(dimensionBoutons);
			tableauBoutons[i].setFont(font);

			//Cree l'ActionListener selon le bouton
			switch(i){

			//Egal
			case 11 :
				tableauBoutons[i].addActionListener(new EqualListener());
				tableauBoutons[i].setBackground(new Color(176,196,222));//MARCO
				chiffres.add(tableauBoutons[i]);
				break;

			//Reset
			case 12 :
				tableauBoutons[i].setBackground(Color.WHITE);
				tableauBoutons[i].setOpaque(true);
				tableauBoutons[i].setBorderPainted(false);
				tableauBoutons[i].setForeground(Color.red);
				tableauBoutons[i].addActionListener(new CancelListener());
				tableauBoutons[i].setPreferredSize(dimensionOperateurs);
				operateurs.add(tableauBoutons[i]);
				break;

			//Somme
			case 13 :
				tableauBoutons[i].setBackground(Color.CYAN);
				tableauBoutons[i].setOpaque(true);
				tableauBoutons[i].setBorderPainted(false);
				tableauBoutons[i].addActionListener(new SumListener());
				tableauBoutons[i].setPreferredSize(dimensionOperateurs);
				operateurs.add(tableauBoutons[i]);
				break;

			//Soustraction
			case 14 :
				tableauBoutons[i].setBackground(Color.ORANGE);
				tableauBoutons[i].setOpaque(true);
				tableauBoutons[i].setBorderPainted(false);
				tableauBoutons[i].addActionListener(new SubtractionListener());
				tableauBoutons[i].setPreferredSize(dimensionOperateurs);
				operateurs.add(tableauBoutons[i]);
				break; 

			//Multiplication
			case 15 :
				tableauBoutons[i].setBackground(Color.YELLOW);
				tableauBoutons[i].setOpaque(true);
				tableauBoutons[i].setBorderPainted(false);
				tableauBoutons[i].addActionListener(new MultiplicationListener());
				tableauBoutons[i].setPreferredSize(dimensionOperateurs);
				operateurs.add(tableauBoutons[i]);
				break;

			//Division
			case 16 :
				tableauBoutons[i].setBackground(Color.GREEN);
				tableauBoutons[i].setOpaque(true);
				tableauBoutons[i].setBorderPainted(false);
				tableauBoutons[i].addActionListener(new DivisionListener());
				tableauBoutons[i].setPreferredSize(dimensionOperateurs);
				operateurs.add(tableauBoutons[i]);
				break;

			//Numbers
			default :
				chiffres.add(tableauBoutons[i]);
				tableauBoutons[i].setBackground(new Color(176,196,222));
				tableauBoutons[i].addActionListener(new NumberListener());
				break;
			}
		}
	}

	/**
	 * Methode qui effectue le calcul selon l'operateur
	 */
	private void calculation(){
		if(operateur == "+"){
			number1 = number1 + Double.valueOf(afficherResultat.getText()).doubleValue();
			afficherResultat.setText(String.valueOf(number1));
		}
		if(operateur == "-"){
			number1 = number1 - Double.valueOf(afficherResultat.getText()).doubleValue();
			afficherResultat.setText(String.valueOf(number1));
		}          
		if(operateur == "*"){
			number1 = number1 * Double.valueOf(afficherResultat.getText()).doubleValue();
			afficherResultat.setText(String.valueOf(number1));
		}     
		if(operateur == "/"){
			try{
				number1 = number1 / Double.valueOf(afficherResultat.getText()).doubleValue();
				afficherResultat.setText(String.valueOf(number1));
			} catch(ArithmeticException e) {
				afficherResultat.setText("0");
			}
		}
	}

	/**
	 * Class qui s'occupe de l'action des chiffres
	 */
	class NumberListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String text = ((JButton)e.getSource()).getText();
			if(update){
				update = false;
			}
			else{
				if(!afficherResultat.getText().equals("0"))
					text = afficherResultat.getText() + text;
			}
			afficherResultat.setText(text);
		}
	}
	
	/**
	 * Class qui s'occupe de l'action de l'operateur d'egalite
	 */
	class EqualListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			calculation();
			update = true;
			clicOperateur = false;
		}
	}
	
	/**
	 * Class qui s'occupe de l'action de l'operateur d'addition
	 */
	class SumListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calculation();
				afficherResultat.setText(String.valueOf(number1));
			}
			else{
				number1 = Double.valueOf(afficherResultat.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "+";
			update = true;
		}
	}
	
	/**
	 * Class qui s'occupe de l'action de l'operateur de soustraction
	 */
	class SubtractionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calculation();
				afficherResultat.setText(String.valueOf(number1));
			}
			else{
				number1 = Double.valueOf(afficherResultat.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "-";
			update = true;
		}
	}
	
	/**
	 * Class qui s'occupe de l'action de l'operateur de multiplication
	 */
	class MultiplicationListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calculation();
				afficherResultat.setText(String.valueOf(number1));
			}
			else{
				number1 = Double.valueOf(afficherResultat.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "*";
			update = true;
		}
	}
	
	/**
	 * Class qui s'occupe de l'action de l'operateur de division
	 */
	class DivisionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calculation();
				afficherResultat.setText(String.valueOf(number1));
			}
			else{
				number1 = Double.valueOf(afficherResultat.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "/";
			update = true;
		}
	}
	
	/**
	 * Class qui s'occupe de l'action du bouton effacer
	 */
	class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			number1 = 0;
			clicOperateur = false;
			update = true;
			operateur = "";
			afficherResultat.setText("0");
		}
	}      
}