package monjeu.dominos;

import java.util.ArrayList;

import jeux.alg.AlgoJeu;
import jeux.alg.Minimax;
import jeux.modele.CoupJeu;
import jeux.modele.PlateauJeu;
import jeux.modele.joueur.Joueur;

public class PartieDominos {

    public static void main(String[] args) {

        Joueur jBlanc = new Joueur("Blanc");
        Joueur jNoir = new Joueur("Noir");

        Joueur[] lesJoueurs = new Joueur[2];

        lesJoueurs[0] = jBlanc;
        lesJoueurs[1] = jNoir;


        AlgoJeu AlgoJoueur[] = new AlgoJeu[2];
        AlgoJoueur[0] = new Minimax(HeuristiquesDominos.hblanc, jBlanc, jNoir); // Il faut remplir la méthode !!!
        AlgoJoueur[1] = new Minimax(HeuristiquesDominos.hnoir, jNoir, jBlanc);  // Il faut remplir la méthode !!!

        System.out.println("Jeux n°1 : Algorithmes pour les Jeux");
        System.out.println("Etat Initial du plateau de jeu:");

        boolean jeufini = false;
        CoupJeu meilleurCoup = null;
        int jnum;

        PlateauJeu plateauCourant = new PlateauDominos();
        PlateauDominos.setJoueurs(jBlanc, jNoir);
        // Pour savoir qui joue "noir" et qui joue "blanc"


        // A chaque itération de la boucle, on fait jouer un des deux joueurs
        // tour a tour
        jnum = 0; // On commence par le joueur Blanc (arbitraire)

        while (!jeufini) {
            System.out.println("" + plateauCourant);
            System.out.println("C'est au joueur " + lesJoueurs[jnum] + " de jouer.");
            // Vérifie qu'il y a bien des coups possibles
            // Ce n'est pas tres efficace, mais c'est plus rapide... a écrire...
            ArrayList<CoupJeu> lesCoupsPossibles = plateauCourant.coupsPossibles(lesJoueurs[jnum]);
            System.out.println("Coups possibles pour" + lesJoueurs[jnum] + " : " + lesCoupsPossibles);
            if (lesCoupsPossibles.size() > 0) {
                // On écrit le plateau

                // Lancement de l'algo de recherche du meilleur coup
                System.out.println("Recherche du meilleur coup avec l'algo " + AlgoJoueur[jnum]);
                meilleurCoup = AlgoJoueur[jnum].meilleurCoup(plateauCourant);
                System.out.println("Coup joué : " + meilleurCoup + " par le joueur " + lesJoueurs[jnum]);

                plateauCourant.joue(lesJoueurs[jnum], meilleurCoup);
                // Le coup est effectivement joué
                jnum = 1 - jnum;

            } else {
                System.out.println("Le joueur " + lesJoueurs[jnum] + " ne peut plus jouer et abandone !");
                System.out.println("Le joueur " + lesJoueurs[1 - jnum] + " a gagné cette partie !");
                jeufini = true;

            }
        }
    }
}
