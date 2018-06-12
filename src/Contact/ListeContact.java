/**
 * @author Beyza Salyador
 * Class qui permet la creation de l'ArrayList qui contient les contacts ainsi que l'ajout, la modification et la suppression d'un contact
 */

package Contact;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import GUI.Contact;
import Serialization.Serialisation;

public class ListeContact {
	
	Contact contact;
	
	/**
	 * Creation de l'ArrayList
	 */
	private List<InfoContact> contacts;
	
	public ListeContact(Contact Cont) throws IOException {
		//Creation du fichier .ser
		creationFichier();
		
		this.contact = Cont;
		contacts = (List<InfoContact>)Serialisation.deseralisation("./Contacts/contact.ser");
	}
	
	/**
	 * Methode permettant d'ajouter un contact
	 */
	public void addContact(String nom, String prenom, long numeroTel, ImageIcon image){
		contacts.add(new InfoContact(nom, prenom, numeroTel, image));
		Serialisation.serialisation(contacts, "./Contacts/contact.ser");
	}
	/*
	public void addContact(String nom, String prenom, long numeroTel){
		contacts.add(new InfoContact(nom, prenom, numeroTel));
		Serialisation.serialisation(contacts, "./Contacts/contact.ser");
	}
	*/
	/**
	 * Suppression d'un contact
	 */
	public void deleteContact(int ligne){
		contacts.remove(ligne);
		Serialisation.serialisation(contacts, "./Contacts/contact.ser");
	}
	
	/**
	 * Edition d'un contact
	 */
	public void editContact(int ligne, InfoContact ic){
		contacts.set(ligne, ic);
		Serialisation.serialisation(contacts, "./Contacts/contact.ser");
	}
	
	/**
	 * Insertion de l'Arraylist dans un tableau pour pouvoir y acceder avec la JTable
	 */
	public Object[][] getArray2D(){
		Object[][] data = new Object[contacts.size()][4];
        int i = 0;
        while (i < contacts.size()) {
            data[i][0] = contacts.get(i).getNom();
            data[i][1] = contacts.get(i).getPrenom();
            data[i][2] = contacts.get(i).getNumTelephone();
            data[i][3] = contacts.get(i).getPhoto();
            i++;
        }
        return data;
	}
	
	/**
	 * Creation d'un fichier .ser pour faire la serialisation (si fichier inexistant)
	 */
	private void creationFichier() {
		File fichier = new File("./Contacts/contact.ser");
		if (fichier.exists() == false) {
			Serialisation.serialisation(new ArrayList<InfoContact>(), "./Contacts/contact.ser");
		}			
	}
}