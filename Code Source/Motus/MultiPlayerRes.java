package Motus;
import java.util.ArrayList;
public interface MultiPlayerRes {//Interface pour la gestiion du mode MultiJoueur//
	final static double REWARD=50;//un attribut représente la recomponse de trouver un lettre correcte//
	final static double TAUX_BONUS=0.3;//un attribut représente le taux de bonus//
	static double max(double s1,double s2) {
		return (s1>s2)? s1 :s2;//méthode qui détermine le max entre deux doubles(j'evite l'utilisation de java.math)//
	}
	static boolean verifLettre(ArrayList a,char s) {//méthode qui verife si un lettre déja trouvé ou non
		return a.contains(s);
	}
	public abstract double calcBonus(double s);//méthode abstrait pour la définition du formule de bonus//

}
