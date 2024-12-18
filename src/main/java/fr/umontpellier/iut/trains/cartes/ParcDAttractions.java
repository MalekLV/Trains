package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParcDAttractions extends Actions {
    public ParcDAttractions() {
        super("Parc d'attractions", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.ajouterArgent(getOr());
        ListeDeCartes cartes = joueur.getCartesEnJeu();
        List<String> choix = cartes.chercherTrainsList();
        if(!choix.isEmpty()){
            String res = joueur.choisir("De quelle carte train voulez vous récupérer l'argent ?", choix,null, false);
            joueur.ajouterArgent(joueur.retrouveCarte(res,cartes).getOr());
        }
    }
}
