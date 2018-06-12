/**
 * @author Beyza Salyador
 * Class qui contient les données des contacts
 */

package Contact;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class InfoContact implements Serializable{

	/**
	 * Un contact a un nom
	 */
	private String nom;
	
	/**
	 * Un contact a un prenom
	 */
	private String prenom;
	
	/**
	 * Un contact a un numero de telephone
	 */
	private long numTelephone;
	
	/**
	 * Un contact peut avoir une photo
	 */
	private ImageIcon photo;
	
	/**
	 * Constructeur de l'objet InfoContact
	 */
	public InfoContact(String nom, String prenom, long numTelephone, ImageIcon photo){
		this.nom = nom;
        this.prenom = prenom;
        this.numTelephone = numTelephone;
        this.photo = photo;
	}
	
	public InfoContact(String nom, String prenom, long numTelephone){
		this.nom = nom;
        this.prenom = prenom;
        this.numTelephone = numTelephone;
        this.photo = null;
	}

	/**
	 * Methode qui permet de recuperer le nom du contact
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Methode qui permet de modifier le nom du contact
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Methode qui permet de recuperer le prenom du contact
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Methode qui permet de modifier le prenom du contact
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Methode qui permet de recuperer le numero de telephone du contact
	 */
	public long getNumTelephone() {
		return numTelephone;
	}

	/**
	 * Methode qui permet de modifier le numero de telephone du contact
	 */
	public void setNumTelephone(long numTelephone) {
		this.numTelephone = numTelephone;
	}

	/**
	 * Methode qui permet de recuperer l'image du contact
	 */
	public ImageIcon getPhoto() {
		return photo;
	}

	/**
	 * Methode qui permet de modifier l'image du contact
	 */
	public void setPhoto(ImageIcon photo) {
		this.photo = photo;
	}	
}