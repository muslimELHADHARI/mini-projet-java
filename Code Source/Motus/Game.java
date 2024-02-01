package Motus;
import java.util.ArrayList;
import java.util.Random;
import java.lang.StringBuilder;
public class Game {//Classe pour la gestion du jeu//
	private static int nbEssais;
	private int nbEssaisRestants=nbEssais;
	private static int nbPartGagnees=0;
	private static int nbPartPerdues=0;
	private StringBuilder motCache;
	private StringBuilder motTrouve;
	private static boolean niveau;//Attribut indique le niveau du partie(Facile ou non)
	private boolean gagne=false;//attribut représente l'etat du partie//
	//Constructeur prend comme paramétres un ArrayList pour extraire une mot aléatoire//
	public Game(ArrayList<String> a) {
	Random r=new Random();
		int ind=r.nextInt(a.size());
		String mot=a.get(ind);
		motCache=new StringBuilder(mot);
		motTrouve=new StringBuilder();
	}
	//Nos setteurs//
	public void setmotTrouve(StringBuilder s) {
		motTrouve=s;
	}
	public void setmotCache(StringBuilder s) {
		motCache=s;
	}
	public static void setNbEssais(int nb) {
		nbEssais=nb;
	}
	public void setNbEssaisRestants(int nb) {
		nbEssaisRestants=nb;
	}
	//methode pour afficher le mot secret//
	public void afficheMotCache() {
		System.out.println("Le mot caché de cette partie est:"+motCache);
	}
	//Nos Getteurs//
	public StringBuilder getMotCache() {
		return motCache;
	}
	public StringBuilder getMotTrouve() {
		return motTrouve;
	}
	public static int getNbEssais() {
		return nbEssais;
	}
	public boolean getGagne() {
		return gagne;
	}
	public int getNbEssaisRestants() {
		return nbEssaisRestants;
	}
	public void setGagne(boolean g) {
		gagne=g;
	}
	public static int nbPartGagnees() {
	   return nbPartGagnees;}
	public static int nbPartPerdues() {
			return nbPartPerdues;
	}
	//Méthode pour afficher le mot trouvé//
	public void affMotTrouve() {
  		System.out.println("les lettre trouvées et bien placées sont:");
		System.out.println(motTrouve);
		motTrouve.setLength(0);//a la fin de chaque essai en mise a zero la valeur de motTrouve//
	}
	//Méthode pour comparer le mot saisit avec le mot secret en construit aussi le motTrouve//
	public boolean testMot(String...a) {//Utilisation d'un paramétre Varargs pour l'utilisation dans la classe fille(Game_Multijoueur)
		if(a.length==1) {
			String s=a[0];
		nbEssaisRestants--;
		if(s.length()==motCache.length()) {
	 	for(int i=0;i<s.length();i++) {
			//toute essai prend en charge la majuscule et minuscule//
	     	if(s.toLowerCase().charAt(i)==motCache.toString().toLowerCase().charAt(i)) {
	     	motTrouve.append(String.valueOf(s.charAt(i)));
	
	     	} 
			else {
		    	motTrouve.append("*");
			}
			}
		}
		else {
			for(int i=0;i<motCache.length();i++) {
				motTrouve.append("*");
			}
		}
		gagne=motCache.toString().toLowerCase().equals(s.toString().toLowerCase());
		return(gagne);
		}
		else {
			System.out.println("Erreur");
			return false;
		}
	}
	//Méthode pour établir la difficilité du jeu//
	public static void setNiv(String m) {
		if(m.toUpperCase().equals("FACILE") || m.toUpperCase().equals("DIFFICILE")) {
			niveau=(m.toUpperCase().equals("FACILE")) ? true:false;
		}
		else {
			System.out.println("Niveau Invalide,Mode difficile par défaut !!!");
		}
	} 
	//Méthode pour donner les avantages de chaque niveau//
	public static void avNiveau(WordList wd) {
		if(!niveau) {
			System.out.println("***********************************************************************");
			System.out.println("AUCUNE INDICATION");
			System.out.println("***********************************************************************");		
		}
		else {
			System.out.println("***********************************************************************");
			System.out.println("Pour faciliter voici ce dictionnaire:");
			System.out.println(wd.getDict());
			System.out.println("***********************************************************************");
		}
	}
	//méthode pour déterminer a la fin de chaque partie,sa état//
	public void calculPart() {
	if(gagne==false) {
		nbPartPerdues++;
	}
	else {
		nbPartGagnees++;
	}
	}}

