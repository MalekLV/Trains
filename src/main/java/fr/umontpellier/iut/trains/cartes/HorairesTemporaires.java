package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class HorairesTemporaires extends Actions {
    public HorairesTemporaires() {
        super("Horaires temporaires", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        int nbTrain = 0;
        ListeDeCartes cartes = new ListeDeCartes();
        ListeDeCartes trains = new ListeDeCartes();
        Carte carte = joueur.piocher();
        while(nbTrain<2 && carte!=null){
            joueur.log("A pioché : "+carte);
            if(Trains.class.isAssignableFrom(carte.getClass())){
                nbTrain+=1;
                trains.add(carte);
            }else{
                cartes.add(carte);
            }
            if(nbTrain<2){
                carte = joueur.piocher();
            }
        }
        joueur.addAllMain(trains);
        joueur.addAllDefausse(cartes);
    }
}
