package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.Objects;

public class TGV extends Trains {
    public TGV() {
        super("TGV", 2, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        ListeDeCartes cartesEnJeu = joueur.getCartesEnJeu();
        if(joueur.retrouveCarte("Train omnibus",cartesEnJeu)!=null){
            joueur.ajouterArgent(1);
        }
    }
}
