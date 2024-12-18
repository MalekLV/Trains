package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonnelDeGare extends Actions {
    public PersonnelDeGare() {
        super("Personnel de gare", 2, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<Bouton> boutons = new ArrayList<>();
        boutons.add(new Bouton("piocher", "piocher"));
        boutons.add(new Bouton("argent", "argent"));
        boutons.add(new Bouton("ferraille","ferraille"));
        String res = joueur.choisir("Quel action voulez vous réaliser à ce tour, piocher une carte, récupérer un or, ou défausser une ferraille ?",null,boutons,false);
        if(Objects.equals(res, "piocher")){
            joueur.piocherMain();
        } else if (Objects.equals(res, "argent")) {
            joueur.ajouterArgent(1);
        }else{
            joueur.supprimerFerailleMain();
        }

    }
}
