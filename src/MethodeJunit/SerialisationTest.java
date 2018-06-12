/**
 * @author Beyza Salyador
 * Class qui teste la serialisation et la deseralisation pour le JUnit
 */

package MethodeJunit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.Serializable;

import org.junit.Test;

import Serialization.Serialisation;

	public class SerialisationTest {

		String filepath = "./Contacts/JUnit.ser";
		
		File file = new File("./Contacts/JUnit.ser");
		
		TestJUNIT MonTestJunit = new TestJUNIT("Salyador", "Beyza");
		
		@Test
		public void testSerialisation() {
			//Regarde si ca a bien enregistre au bon endroit
			assertTrue(Serialisation.serialisation(MonTestJunit, filepath));
		}
		
		@Test
		public void testDeseralisation() {
			TestJUNIT o = (TestJUNIT)Serialisation.deseralisation(filepath);
			//Test si o est une instance de la class TestJUNIT
			assertTrue(o instanceof TestJUNIT);
			//Test si le nom est egal a "Salyador"
			assertTrue(o.nom.equals("Salyador"));
			//Test si le nom est egal a "Beyza"
			assertTrue(o.prenom.equals("Beyza"));
		}
		
	}
	
	class TestJUNIT implements Serializable {
		
		String nom;
		String prenom ;
		
		public TestJUNIT(String nom, String prenom) {
			this.nom = nom ; 
			this.prenom = prenom;
		}
	}
