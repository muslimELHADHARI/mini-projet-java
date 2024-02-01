package Motus;
import java.util.ArrayList;
import java.util.Iterator;
public class WordList {//La classe permettant de gérer les mots//
	private int tailleMot;
	private int nbMots;
	private ArrayList<String> dictionnaire;
	public WordList(int t) {
		tailleMot=t;
		dictionnaire=new ArrayList<String>();
		
	}
	//Les getteurs//
	public int getTaille() {
		return tailleMot;
	}
	public int getNombre() {
		return nbMots;
	}
	public ArrayList<String>getDict(){
		return dictionnaire;
	}
	//Un setteur//
	public void setTaille(int t) {
		tailleMot=t;
	}
	//méthode pour la recherche d'un element dans le dictionnaire// 
	public boolean rechercheMot(String m) {
		if(m.length()!=tailleMot) {
			return false;
		}
		else {
			for(Iterator<String> i=dictionnaire.iterator();i.hasNext();) {
				if(i.next().equals(m)) {
	     			return true;
				}
			}
			return false;
		}
		
	}
	//méthode pour l'ajout d'un element dans le dictionnaire//
	public boolean ajoutMot(String s) {
		if(rechercheMot(s)) {
			System.out.println("Erreur,Le mot deja existe dans le dictionnaire");
			return false;
		}
		else if(s.length()==tailleMot) {
			dictionnaire.add(s);
			//Pour le tri de dictionnaire j'utilise la methode sort()//
			//on donnne le comparateur String::compareTo pour comparer les elements lexicalement//
			dictionnaire.sort(String::compareTo);
	 	 	nbMots++;
	 	 	return true;
		}
		else {
	    	System.out.println("Le mot entréé ne respecte la taille de dictionnaire");
		}
    	return false;
	}
	//méthode pour la suppression d'un element dans le dictionnaire//
	public void supprimerMot(String s) {
		System.out.println("***********************************************************************");
		if(!rechercheMot(s)){
			System.out.println("Mot non trouvée dans le dictionnaire !");
		}
		else {
			int ind=dictionnaire.indexOf(s);
			dictionnaire.remove(ind);
			System.out.println("Mot bien supprimé");
		}
		System.out.println("***********************************************************************");
	}
	//méthode pour afficher les details de dictionnaire//
	public void afficher() {
		System.out.println("***********************************************************************");
		System.out.println("La taille des mots dans ce dictionnaire est:"+tailleMot);
		System.out.println("***********************************************************************");
		System.out.println("Le nombre des mots dans ce dictionnaire est:"+nbMots);
		System.out.println("***********************************************************************");
		System.out.println("Les mots presents dans le dictionnaire sont:");
		System.out.println(dictionnaire);
		System.out.println("***********************************************************************");
	}
}
