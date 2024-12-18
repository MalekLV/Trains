package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrainDeMarchandises extends Trains {
    public TrainDeMarchandises() {
        super("Train de marchandises", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        List<String> choix = new ArrayList<>();
        if(joueur.getNBFeraille()>0){
            choix.add("Ferraille");
        }
        String res = joueur.choisir("Voulez vous défausser une ferraille pour gagner un or ?",choix,null,true);
        while (!Objects.equals(res, "")){
            if(Objects.equals(res, "Ferraille")) {
                joueur.supprimerFerailleMain();
                joueur.ajouterArgent(1);
            }
            if(joueur.getNBFeraille()==0){
                choix.remove("Ferraille");
            }
            res = joueur.choisir("Voulez vous défausser une ferraille pour gagner un or ?",choix,null,true);
        }
    }
}
