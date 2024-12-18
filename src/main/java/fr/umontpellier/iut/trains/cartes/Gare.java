package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.Tuile;
import fr.umontpellier.iut.trains.plateau.TuileVille;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gare extends Carte {
    public Gare() {
        super("Gare",3,0);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choix = joueur.getVillesPossibles();
        String res = joueur.choisir("Où voulez vous poser votre gare ?", choix,null,false);
        if(!Objects.equals(res, "")){
                Tuile tuile = joueur.getTuile(Integer.parseInt(res.split(":")[1]));
                ((TuileVille) tuile).poserGare();
                joueur.diminuerGare();
        }else{
            joueur.log("Impossible de poser une gare");
        }
        joueur.ajouterFeraille();
    }
}
