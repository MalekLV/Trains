package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CentreDeControle extends Actions {
    public CentreDeControle() {
        super("Centre de contrôle", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherMain();
        Carte carte = joueur.piocher();
        if(carte!=null){
            List<Bouton> boutons = Bouton.listeDeBouton(joueur.getCartesDeLaPartie());
            String res = joueur.choisir("Quelle carte pensez vous avoir sur le sommet de votre pioche",null,boutons,false);
            if(Objects.equals(res, carte.getNom())){
                joueur.log("La carte qui se trouvait au dessus de la pioche était bien : "+carte);
                joueur.addMain(carte);
            }else{
                joueur.ajouterTopPioche(carte);
                joueur.log("La carte qui se trouvait au dessus de la pioche était : "+carte);
            }
        }
    }
}
