package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BureauDuChefDeGare extends Actions {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 4,0);
    }

    @Override
    public void jouer(Joueur joueur) {
        boolean peutPasser=false;
        int nbBureaux=0;
        ListeDeCartes main = joueur.getMain();
        List<String> choix =new ArrayList<>();
        for(Carte carte : main){
            if(joueur.isCarteAction(carte)){
                if(carte.getClass() == BureauDuChefDeGare.class){
                    nbBureaux++;
                }
                choix.add(carte.getNom());
            }
        }
        if(nbBureaux>1 && choix.size()==nbBureaux){
            peutPasser=true;
        }
        String res = joueur.choisir("Quel effet de carte voulez copier ?",choix,null,peutPasser);
        if(!Objects.equals(res, "")){
            Carte tempCarte = joueur.retrouveCarte(res,main);
            joueur.log("A copié l'effet de "+tempCarte.getNom());
            if(!Objects.equals(tempCarte.getNom(), "Horaires estivaux")){
                joueur.ajouterArgent(-tempCarte.getOr());
                tempCarte.jouer(joueur);
            }else{
                // évite un bug si le joueur copie la carte Horaires estivaux
                joueur.horairesEstivaux(this);
            }
        }
    }
}
