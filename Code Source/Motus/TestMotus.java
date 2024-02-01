package Motus;
import java.util.Scanner;
import java.util.ArrayList;
public class TestMotus implements Verif{//cette classe implemente l'intérface Verif pour la verification des entrées//
	static {
		//un bloc static pour afficher MOTUS au debut du programme//
		System.out.println("\n"
		+ "███╗   ███╗ ██████╗ ████████╗██╗   ██╗███████╗\n"
		+ "████╗ ████║██╔═══██╗╚══██╔══╝██║   ██║██╔════╝\n"
		+ "██╔████╔██║██║   ██║   ██║   ██║   ██║███████╗\n"
		+ "██║╚██╔╝██║██║   ██║   ██║   ██║   ██║╚════██║\n"
		+ "██║ ╚═╝ ██║╚██████╔╝   ██║   ╚██████╔╝███████║\n"
		+ "╚═╝     ╚═╝ ╚═════╝    ╚═╝    ╚═════╝ ╚══════╝\n"
		+ "\n                            Développé par ELHADHARI Muslim                \n"
		+ "");
	}
	public static void main(String[] args) {
		//Un tableau d'objets WordList pour le stockage des objets utilisé//
		//ce tableau a des cases au nombres de mes fichiers texte (7)//
		WordList array[];
		array=new WordList[7];
		//objet scanner pour les entrées//
		Scanner sc=new Scanner(System.in);
		if(args.length>2 || args.length==0) {//verification des arguments données//
			System.out.println("Erreur des arguments,taper 'help' pour plus d'informations");
			sc.close();
			return;
		}
		else if(args[0].equals("help")) {//argument help pour une page explicatif//
			System.out.println("Pour configurer taper 'config' suite avec la taille des mots de dictionnaire ");
			System.out.println("Pour commencer a jouer taper 'jeu' suite avec la taille de mot caché");
		}
		//************************************************************************************************************************//
		else if(args[0].equals("config") && Verif.verifargs(args[1])) {
			//verification des arguments de configuration ainsi que args[1] doit etre un numéro//
			System.out.println("---------------------------------------");
			System.out.println("Bienvenue au mode Configuration");
			System.out.println("---------------------------------------");
			String choix;
			do {
				int d=Integer.valueOf(args[1]);
				WordList w=new WordList(d);//objet de type WordList pour stocker les mots extraits//
				Scanner sca = new Scanner(new
				TestMotus().getClass().getClassLoader().getResourceAsStream("d"+Integer.parseInt(
					args[1])+".txt"));
				while(sca.hasNext()) {//extraire les mots du fichier texte et les ajout au notre obket WordList//
					w.ajoutMot(sca.next()); 
				}
				if(array[d-3]==null) {
					array[d-3]=w;}//stocker notre objet WordList dans notre Tableau//
					//les actions a faire/
					System.out.println("***********************************************************************");
					System.out.println("Donner une action:");
					System.out.println("1)Recherche un mot dans le dictionnaire");
					System.out.println("2)Ajouter un mot dans le dictionnaire");
					System.out.println("3)Supprimer un mot dans le dictionnaire");
					System.out.println("4)Afficher votre dictionnaire");
					System.out.println("***********************************************************************");
					//entrée du choix//
					String ch=sc.next();
					switch(ch) {
						case("1"):
							System.out.println("***********************************************************************");
							System.out.println("Donner le mot à rechercher:");
							String mt=sc.next();
							String res=(array[d-3].rechercheMot(mt))?"Mot trouvé":"mot non trouvé";
							System.out.println("résultat de recherche:"+res);
							System.out.println("***********************************************************************");
							break;
						case("2"):
							System.out.println("***********************************************************************");
							System.out.println("Donner un mot à ajouter:");
							String mot=sc.next();
							if(array[d-3].ajoutMot(mot)){
								System.out.println("Mot bien ajouté");}
								System.out.println("***********************************************************************");
								break;
						case("3"):
							System.out.println("Donner le mot a supprimer:");
							mot=sc.next();
							array[d-3].supprimerMot(mot);
							break;
						case("4"):
							array[d-3].afficher();break;
						default:
							System.out.println("il faut choisir un choix entre <1 et 4> !");
					}
					//demande a l'utilisateur si il veut quitter ou compléter la configuration//
					System.out.println("Taper STOP pour quitter le mode de configuration ou n'importe quel touche pour continuer");
					choix=sc.next();
			}while(!choix.equals("STOP"));
			System.out.println("Voulez vous basculer vers le mode jeu ou quitter l'application:\n<OUI | NON>");
			String ch2=sc.next();
			if(ch2.equals("OUI")) {
				args[0]="jeu";
			} 
		}
		//************************************************************************************************************************//
		if(args[0].equals("jeu") && args.length==2){
			//verification des arguments de configuration ainsi que args[1] doit etre un numéro//
			if(Verif.verifargs(args[1])) {
				int taille=Integer.parseInt(args[1]);
				Scanner scanner = new Scanner(new
				TestMotus().getClass().getClassLoader().getResourceAsStream("d"+Integer.parseInt(
					args[1])+".txt"));
				//extraire les mots selon 'taille'//
				if(array[taille-3]==null) {
					String ligne;
					WordList w=new WordList(taille);
					while(scanner.hasNext()) {
						ligne=scanner.nextLine();
						//ajouter aussi notre objet WordList dans le tableau//
						array[taille-3]=w;
						array[taille-3].ajoutMot(ligne);}
				}
				System.out.println("---------------------------------------");
				System.out.println("Bienvenue au mode Jeu");
				System.out.println("---------------------------------------");
				System.out.println("Donner le nombre d'essais entre <1 et 10> :");
				//demande d'entrée le nombre d'essais//
				int nb=sc.nextInt();
				while(nb<1 || nb>10) {
					System.out.println("Donner le nombre d'essais:");
					nb=sc.nextInt();
				}
				Game.setNbEssais(nb);//affecter le valeur nb comme NbEssais d'une jeu//
				System.out.println("Donner le niveau du Jeu 'FACILE' ou 'DIFFICILE':");
				String niv=sc.next();
				Game.setNiv(niv);
				Game.avNiveau(array[taille-3]);
				System.out.println("Indiquer le mode de Jeu:");
				System.out.println("1-Mode Un seul Joueur(Un mode simple cosiste a trouver le mot choisit par hassard)");
				System.out.println("2-Mode Deux Joueurs(Un mode consiste a trouver le plus possible de lettres entre trois mots mélangées");
				//Remarque 3 :le mode 3 est comme une idée et n'est pas implementés//
				System.out.println("3-Mode Un seul Joueur avancé(Un mode consiste a donner une phrase et completer avec des mots absents(N'EST PAS DISPONIBLE)");
				String mode=sc.next();;
				while(!mode.equals("1") && !mode.equals("2")) {
					System.out.println("Donner un mode valide:");
					mode=sc.next();;					
				}
				//************************************************************************************************************************//
				if(mode.equals("1")) {
					//si le mode choisit et le premier on declare un ArrayList de type Game pour enregistrer les parties jouées//
					String nouvPart;
					System.out.println("***********************************************************************");
					ArrayList<Game> parties=new ArrayList<Game>();
					do {
						Game jeu=new Game(array[taille-3].getDict());
						//initialise une partie d'aprés le dictionnaire extrait precedement//
						boolean gagne=false;
						while(jeu.getNbEssaisRestants()>0 && gagne==false) {
							//les instruction necessaires pour compléter une partie//
							String essai;
							System.out.println("donner l'essai numero "+(Game.getNbEssais()-jeu.getNbEssaisRestants()+1)+":");
							essai=sc.next();
							if(Verif.verifmots(essai,args[1])) {
								if(jeu.testMot(essai)) {
									System.out.println("Congrats vous avez gangées");
									jeu.afficheMotCache();
									gagne=true;
								}
								else {
									jeu.affMotTrouve();
								}
							}
							else {
								System.out.println("Veuiller respecter la taille !!! ");
							}
						}
						//calcul la resultat d'une partie et l'ajouter au ArraylList parties//
						jeu.calculPart();
						parties.add(jeu);
						//demande au joueur si il veut jouer une autre partie ou non//
						System.out.println("Taper 'STOP' pour terminer le jeu ou n'importe quel touche pour continuer à jouer");
						nouvPart=sc.next();
					}while(!nouvPart.equals("STOP"));
					System.out.println("--------------------------------------------------------------");
					//résumé des parties jouées ainsi que le score final//
					System.out.println("MERCI DE JOUER MOTUS");
					System.out.println("Le Score finale est:");
					int in=1;
					for(Game i:parties) {
						System.out.println("--------------------------------------------------------------");
						System.out.println("Partie:"+in);
						if(i.getGagne()) {
							System.out.println("Etat de Partie:Gagné");
						}
						else {
							System.out.println("Etat de partie:Perdue");
						}
						i.afficheMotCache();
						System.out.println("--------------------------------------------------------------");
						in++;
					}
					System.out.println("La totalité des parties");
					System.out.println("Le nombre de partie gagnées est "+Game.nbPartGagnees());
					System.out.println("Le nombre de partie perdues est "+Game.nbPartPerdues());
				}
				//************************************************************************************************************************//
				else {
					//c'est le choix de jouer une partie MultiJoueur//
					System.out.println("***********************************************************************");
					Game.setNbEssais(nb*2);
					System.out.println("Donner le Nom et prenom de premier joueur separé par un point:");
					String j1=sc.next();
					System.out.println("Donner le Nom et prenom de deuxieme joueur separé par un point:");
					String j2=sc.next();
					//création d'une instance de type Game_Multijoueur//
					Game jeu_m=new Game_MultiJoueur(array[taille-3].getDict(),j1,j2);
					String essai;
					int ind=0;
					//tableau a le but de manipuler les noms des joueurs selon un indice ind//
					String nom[]= {j1,j2};
					while(jeu_m.getNbEssaisRestants()>0 && jeu_m.getGagne()==false) {
						//instructions necessaires pour une partie MultiJoueur//
						System.out.println(nom[ind]+" donner une essai:");
						essai=sc.next();
						((Game_MultiJoueur)jeu_m).testMot(essai,Integer.toString(ind+1));
						jeu_m.affMotTrouve();
						((Game_MultiJoueur)jeu_m).CalcScore(ind+1);
						ind++;
						ind=ind%2;
					}
					//a la fin du partie on obtient un résumé concernant les details//
					((Game_MultiJoueur)jeu_m).whoWin();
					//NB:le Mode MultiJoueur ne prie en charge des parties successives//
				}
			}
			else {
				System.out.println("Veuiller choisir la taille des mots entre < 3 et 9> ");
			}
		}
		sc.close();
		System.out.println("\n");
		System.out.println("-----------------------------MINI PROJET JAVA-------------------------------");
	}
}
