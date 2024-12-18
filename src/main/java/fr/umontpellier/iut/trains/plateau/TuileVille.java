package fr.umontpellier.iut.trains.plateau;


/**
 * Classe représentant une tuile ville (où l'on peut poser des gares)
 */
public class TuileVille extends Tuile {
    /**
     * Nombre maximum de gares que l'on peut poser sur la tuile
     */
    private final int nbGaresMax;
    /**
     * Nombre de gares posées sur la tuile
     */
    private int nbGaresPosees;

    @Override
    public int getSurcout() {
        return 1+nbGaresPosees;
    }

    @Override
    public int getNbGares(){
        return nbGaresPosees;
    }

    /**
     * Renvoie true s'il est possible de mettre une gare sur cette tuile
     */
    public boolean isGarePossible(){
        return nbGaresPosees<nbGaresMax;
    }

    /**
     * Pose une gare sur la tuile
     */
    public void poserGare(){
        if(isGarePossible()){
            nbGaresPosees++;
        }
    }


    public TuileVille(int taille) {
        super();
        this.nbGaresMax = taille;
        this.nbGaresPosees = 0;
    }
}
