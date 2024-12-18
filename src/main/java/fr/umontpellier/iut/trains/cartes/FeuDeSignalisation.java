package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FeuDeSignalisation extends Actions {
    public FeuDeSignalisation() {
        super("Feu de signalisation", 2,0);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherMain();
        Carte carte = joueur.piocher();
        if(carte!=null){
            joueur.log("Voici la carte se trouvant au dessus de votre pioche : "+carte.getNom());
            List<Bouton> boutons = new ArrayList<>();
            boutons.add(new Bouton("oui", "oui"));
            boutons.add(new Bouton("non", "non"));
            String res = joueur.choisir("Voulez vous défausser cette carte (oui) où la garder sur le dessus de votre pioche (non) ?",null,boutons,false);
            if(Objects.equals(res, "oui")){
                joueur.addDefausse(carte);
            }else{
                joueur.ajouterTopPioche(carte);
            }
        }
    }
}
