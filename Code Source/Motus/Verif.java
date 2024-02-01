package Motus;

public interface Verif {//Interface a but pour la verification des entrées//
	final static char digits[]= {'3','4','5','6','7','8','9'};//tableau de caractéres//
	static boolean verifargs(String s) {//méthode pour déterminer si sest un digit entre 3 et 9//
		boolean verif=false;
     	if(s.length()!=1) {
			verif=false;
		}
		else {
		for(int i=0;i<s.length();i++) {
			for(int j=0;j<digits.length;j++) {
				if(s.charAt(i)==digits[j]) {
			    	verif=true;
				}
			   }
		}}
	return verif;
	}
  static boolean verifmots(String s,String t) {//méthode sera utilisé pour la verification de validité des mots entrées//
      boolean verif=true;
	  if(s.length()!=Integer.valueOf(t)) {//verification si un mot a le meme longueur que la taille donnée//
		  verif=false;
	  }
	  else {
		  for(int i=0;i<s.length();i++) {
			  int ascii=s.charAt(i);//verification si un mot contient seulemnt des caractéres alphabetiques//
			  if(ascii>90 && ascii<97){
				  verif=false;
				  break;
			  }
			  else if(ascii<65 || ascii>122) {
				  verif=false;
				  break;
			  }
			  else {
				  verif=true;
			  }
		  }		  
	  }
	  return verif;
  }  
  }