package bowling;

public class Tour {
	
	private int numTour;
	private int nbQuilleTombeesLancer1 = 0;
	private int nbQuilleTombeesLancer2 = 0;
	private int nbQuilleTombeesLancer3 = 0;
	private int numCoup = 1;
	boolean estFini = false;
	
	public Tour(int numTour){
		this.numTour = numTour;
	}
	
	public boolean estFini(){
		return estFini;
	}
	
	public boolean estStrike(){
		return nbQuilleTombeesLancer1 == PartieMonoJoueur.NB_QUILLES;
	}
	
	public boolean estSpare(){
		return numCoup == 2 && nbQuilleTombeesLancer1+nbQuilleTombeesLancer2 == PartieMonoJoueur.NB_QUILLES && numTour != PartieMonoJoueur.NB_TOURS;
	}
	
	public void lancer(int valeur){
		switch (numCoup){
			case 1:
				nbQuilleTombeesLancer1 += valeur;
				if (nbQuilleTombeesLancer1 == PartieMonoJoueur.NB_QUILLES) estFini = true;
				else if (nbQuilleTombeesLancer1 == PartieMonoJoueur.NB_QUILLES && numTour == PartieMonoJoueur.NB_TOURS) numCoup += 2;
				else numCoup++;
				break;
			case 2:
				nbQuilleTombeesLancer2 += valeur;
				estFini = true;
				if (numTour == PartieMonoJoueur.NB_TOURS) {
					numCoup++; 
					if (nbQuilleTombeesLancer1+nbQuilleTombeesLancer2 < PartieMonoJoueur.NB_QUILLES) estFini = true;
				}
				break;
			case 3:
				nbQuilleTombeesLancer3 += valeur;
				estFini = true;
				break;
		}
	}

	public int getNbQuilleTombeesLancer1() {
		return nbQuilleTombeesLancer1;
	}

	public int getNbQuilleTombeesLancer2() {
		return nbQuilleTombeesLancer2;
	}

	public int getNbQuilleTombeesLancer3() {
		return nbQuilleTombeesLancer3;
	}

	public int getNumCoup() {
		return numCoup;
	}
}
