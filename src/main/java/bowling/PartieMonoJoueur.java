package bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {
	
	public static final int NB_QUILLES = 10;
	public static final int NB_TOURS = 10;
	private int numTour = 1;
	private List<Tour> partie = new ArrayList<>();

	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		for (int i = 1; i <= NB_TOURS; i++) {
			partie.add(new Tour(i));
		}
	}

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		if (estTerminee()) throw new IllegalStateException("la partie est fini !");
		partie.get(numTour-1).lancer(nombreDeQuillesAbattues);
		if (!partie.get(numTour-1).estFini()) return true;
		if (numTour < NB_TOURS) numTour++;
		return false;
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score() {
		int score = 0;
		for (int i = 0; i < NB_TOURS-1; i++) {
			Tour tour = partie.get(i);
			score += tour.getNbQuilleTombeesLancer1() + tour.getNbQuilleTombeesLancer2();
			if (tour.estSpare()) {
				score+=partie.get(i+1).getNbQuilleTombeesLancer1();
			}
			else if (tour.estStrike()){
				if (i+1 == NB_TOURS-1 || !partie.get(i+1).estStrike()){
					score+=partie.get(i+1).getNbQuilleTombeesLancer1() + partie.get(i+1).getNbQuilleTombeesLancer2();
				}
				else {
					score+=partie.get(i+1).getNbQuilleTombeesLancer1() + partie.get(i+2).getNbQuilleTombeesLancer1();
				}
			}
		}
		score += partie.get(NB_TOURS-1).getNbQuilleTombeesLancer1() + partie.get(NB_TOURS-1).getNbQuilleTombeesLancer2() + partie.get(NB_TOURS-1).getNbQuilleTombeesLancer3();
		System.out.println(partie.get(NB_TOURS-1).getNbQuilleTombeesLancer1() + partie.get(NB_TOURS-1).getNbQuilleTombeesLancer2() + partie.get(NB_TOURS-1).getNbQuilleTombeesLancer3());
		System.out.println("lancer 1 " + partie.get(NB_TOURS-1).getNbQuilleTombeesLancer1());
		System.out.println("lancer 2 " + partie.get(NB_TOURS-1).getNbQuilleTombeesLancer2());
		return score;
	}

	/**
	 * @return vrai si la partie est terminée pour ce joueur, faux sinon
	 */
	public boolean estTerminee() {
		return partie.get(NB_TOURS-1).estFini();
	}


	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		if (estTerminee()) return 0;
		return numTour;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		if (estTerminee()) return 0;
		else if (numTour == NB_TOURS) return partie.get(NB_TOURS-1).getNumCoup() + 1;
		else return partie.get(numTour).getNumCoup();
	}

}
