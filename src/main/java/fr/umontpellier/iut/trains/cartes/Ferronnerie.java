package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Ferronnerie extends Actions {
    public Ferronnerie() {
        super("Ferronnerie", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.ajouterArgent(getOr());
        joueur.nvFerronnerie();
    }
}
