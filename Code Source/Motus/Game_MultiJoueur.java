package Motus;

import java.util.ArrayList;
import java.util.Random;
//Classe hérite de la classe Game a le role pour gérer le mode MultiJoueur//
public class Game_MultiJoueur extends Game implements MultiPlayerRes {//cette classe implemente aussi l'interface MultiPlayerRes
	//attributs représentent les nom des joueurs//
	private String nom_j1;
	private String nom_j2;
	//attributs représentent les scores de chaque joueur//
	private double score;
	private static double score_j1;
	private static double score_j2;
	//attributs représentant le nombre de lettres trouvés par chaque joueur//
	private int nblettres_j1=0;
	private int nblettres_j2=0;
	//objet de type ArrayList pour stocker les lettres trouvés
	private ArrayList lettreTrouve;
 public Game_MultiJoueur(ArrayList<String> a,String j1,String j2) {
		//on appelle le constructeur de la classe mére//
	    super(a);
	    //en extrait deux autres mots au hassard//
		Random r=new Random();
		int ind1=r.nextInt(a.size());
		String mot1=a.get(ind1);
		int ind2=r.nextInt(a.size());
		String mot2=a.get(ind2);
		//changement de la motCache en une nouvelle motCache due a la concaténation de trois mots//
		super.setmotCache(getMotCache().append(mot1).append(mot2));
		//on initialise les noms des joueurs ainsi que notre ArrayList//
		nom_j1=j1;
		nom_j2=j2;
		lettreTrouve=new ArrayList();
		}
	public boolean testMot(String...v) {//redéfinition de la méthode testMot pour ce mode//
		if(v.length==2) {
		 	String s=v[0];
			int ind=Integer.valueOf(v[1]);
		System.out.println("Essais Restants:"+(super.getNbEssaisRestants()-1));
		int nb=super.getNbEssaisRestants();
		nb--;
		super.setNbEssaisRestants(nb);
		if(s.length()==super.getMotCache().length()) {
		for(int i=0;i<super.getMotCache().length();i++) {
	     	if(s.toLowerCase().charAt(i)==super.getMotCache().toString().toLowerCase().charAt(i)) {
				super.setmotTrouve(super.getMotTrouve().append(String.valueOf(s.charAt(i))));
				//appels des setteurs et getteurs de superclass pour manipuler les données//
			 	if(!MultiPlayerRes.verifLettre(lettreTrouve, s.charAt(i))) {
		     	//si un joueur trouve un lettre alors il gagne une récomponse//
					score+=REWARD;
				}
				//test pour calculer le nombre de lettres trouvés par chaque joueur selon un indice//
				if(ind==1 & !MultiPlayerRes.verifLettre(lettreTrouve, s.charAt(i))) {
					nblettres_j1++;
					lettreTrouve.add(s.charAt(i));
		 		}
				if(ind==2 & !MultiPlayerRes.verifLettre(lettreTrouve, s.charAt(i))) {
					nblettres_j2++;
					lettreTrouve.add(s.charAt(i));
				}
	     	}
			else {
		    	super.setmotTrouve(super.getMotTrouve().append("*"));
			}
			}
		}
		else {
			//cas d'un entréé a un longueur supérieur a la longueur de la mot secret//
			StringBuilder res=new StringBuilder();
			for(int i=0;i<super.getMotCache().length();i++) {
				res.append("*");
			}
			super.setmotTrouve(res);
		}
		//indice pour si le mot rouvé est totallemnt trouvée ou non//
		boolean g=super.getMotCache().toString().toLowerCase().equals(s.toString().toLowerCase()); 
		super.setGagne(g);
		return(g);}	
		else {
			System.out.println("Erreur");
			return false;
		}
		}
	//implementation de la méthode abstraite calcBonus de l'intérface MultiPlayerRes//
	public double calcBonus(double s) {
		return s*TAUX_BONUS;
	}
	//méthode pour calculer le score de chaque joueur//
	public void CalcScore(int ind) {
		if(ind==1) {
			score_j1=MultiPlayerRes.max(score,score_j1);
			//si un joueur trouve nombre des lettres depassent la longuer du mot/2 alors il gange un bonus//
			if(nblettres_j1>super.getMotCache().length()/2) { 
				score_j1+=calcBonus(score_j1);
			}
		}
		else {
			score_j2=MultiPlayerRes.max(score,score_j2);
			if(nblettres_j2>super.getMotCache().length()/2) {
				score_j2+=calcBonus(score_j2);
		}}
		//on rémise la valeur de score a zéro pour autres essais//
		score=0;
	}
	//méthode pour donner un résumé concernat la partie joué ainsi que le gagnant//
	public void whoWin() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("MERCI DE JOUER MOTUS");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(nom_j1+" a trouvé "+nblettres_j1);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(nom_j2+" a trouvé "+nblettres_j2);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(nom_j1+" a comme score:"+score_j1);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(nom_j2+" a comme score:"+score_j2);
		System.out.println("--------------------------------------------------------------------------------");
		if(score_j1==score_j2) {
			System.out.println("Aucun des joueurs a gagné");
		}
		else {
			System.out.println("Le joueur qui determine le plus nombres de lettres et gagne le jeu est "+((score_j1>score_j2)?nom_j1:nom_j2));

		}
		if(super.getGagne()) {
			System.out.println("Congrats le mot caché est totallement trouvé:"+super.getMotCache());
		}
		System.out.println("--------------------------------------------------------------------------------");

	}
}
