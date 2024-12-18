package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;
import java.util.Objects;

public class Depot extends Actions {
    public Depot() {
        super("Dépôt", 3, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.ajouterArgent(getOr());
        joueur.piocherMain(2);
        for(int i=0;i<2 && joueur.getnbCarteEnMain()!=0;i++){
            String res = joueur.choisir("Choisissez une première carte de votre main à défausser", joueur.maintoString(), null, false);
            joueur.defausserCarte(res);
        }
    }
}
