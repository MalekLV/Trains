package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;


public class TrainPostal extends Trains {

    public TrainPostal() {
        super("Train postal",4, 1 );
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.ajouterArgent(joueur.retireXCartes());
    }
}
