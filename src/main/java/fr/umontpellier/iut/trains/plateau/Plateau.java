package fr.umontpellier.iut.trains.plateau;

import java.util.Arrays;
import java.util.List;

public enum Plateau {
    OSAKA, TOKYO;

    /**
     * @return une liste de tuiles représentant le plateau d'une des deux villes
     *         disponibles (Osaka ou Tokyo)
     */
    public List<Tuile> makeTuiles() {
        return switch (this) {
            case OSAKA -> makeTuilesOsaka();
            case TOKYO -> makeTuilesTokyo();
        };
    }

    /**
     * @return le nom de la ville correspondant au plateau
     */
    public String getNomVille() {
        return switch (this) {
            case OSAKA -> "Osaka";
            case TOKYO -> "Tokyo";
        };
    }

    /**
     * @return les tuiles qui forment le plateau "Osaka"
     */
    private static List<Tuile> makeTuilesOsaka() {
        Tuile[][] array = new Tuile[][] {
                new Tuile[] {
                        new TuileEtoile(4),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileEtoile(4),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileVille(3),
                        new TuileEtoile(3),
                },
                new Tuile[] {
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileVille(2),
                        new TuileVille(1),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                },
                new Tuile[] {
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileVille(1),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(2),
                },
                new Tuile[] {
                        new TuileVille(3),
                        new TuileVille(1),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                },
                new Tuile[] {
                        new TuileEtoile(3),
                        new TuileMer(),
                        new TuileMer(),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileVille(1),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileEtoile(3),
                },
                new Tuile[] {
                        new TuileMer(),
                        new TuileMer(),
                        new TuileMer(),
                        new TuileVille(2),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileVille(2),
                },
                new Tuile[] {
                        new TuileMer(),
                        new TuileMer(),
                        new TuileMer(),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileVille(1),
                        new TuileVille(1),
                },
                new Tuile[] {
                        new TuileMer(),
                        new TuileMer(),
                        new TuileEtoile(2),
                        new TuileEtoile(2),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileVille(1),
                        new TuileEtoile(3),
                },
        };
        placerTuiles(array);
        array[0][0].supprimerVoisine(array[1][0]);
        array[0][1].supprimerVoisine(array[1][1]);
        array[0][5].supprimerVoisine(array[0][6]);
        array[0][6].supprimerVoisine(array[1][5]);
        array[0][6].supprimerVoisine(array[1][6]);
        array[4][4].supprimerVoisine(array[5][3]);
        array[4][4].supprimerVoisine(array[5][4]);
        array[5][3].supprimerVoisine(array[5][4]);
        array[4][9].supprimerVoisine(array[5][8]);
        array[7][2].supprimerVoisine(array[7][3]);
        array[7][7].supprimerVoisine(array[7][8]);

        return Arrays.stream(array).flatMap(Arrays::stream).toList();
    }

    private static void placerTuiles(Tuile[][] array) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (j + 1 < array[i].length) { // voisine de droite
                    array[i][j].ajouterVoisine(array[i][j + 1]);
                }
                if (i + 1 < array.length) {
                    if (j < array[i + 1].length) { // voisine du bas
                        array[i][j].ajouterVoisine(array[i + 1][j]);
                    }
                    if (i % 2 == 0 && j > 0) { // voisine bas-gauche (ligne paire)
                        array[i][j].ajouterVoisine(array[i + 1][j - 1]);
                    }
                    if (i % 2 == 1 && j + 1 < array[i + 1].length) { // voisine bas-droite (ligne impaire)
                        array[i][j].ajouterVoisine(array[i + 1][j + 1]);
                    }
                }
            }
        }
    }

    /**
     * @return les tuiles qui forment le plateau "Tokyo"
     */
    private static List<Tuile> makeTuilesTokyo() {
        Tuile[][] array = new Tuile[][] {
                new Tuile[] {
                        new TuileEtoile(4),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileEtoile(4),
                },
                new Tuile[] {
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileVille(1),
                },
                new Tuile[] {
                        new TuileVille(3),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileEtoile(3)
                },
                new Tuile[] {
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileVille(2),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(2),
                },
                new Tuile[] {
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileMer(),
                        new TuileEtoile(2),
                },
                new Tuile[] {
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(1),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileMer(),
                        new TuileMer(),
                },
                new Tuile[] {
                        new TuileEtoile(3),
                        new TuileTerrain(TypeTerrain.MONTAGNE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileTerrain(TypeTerrain.PLAINE),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(1),
                        new TuileMer(),
                        new TuileMer(),
                        new TuileMer(),
                        new TuileMer(),
                },
                new Tuile[] {
                        new TuileEtoile(2),
                        new TuileVille(3),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(2),
                        new TuileTerrain(TypeTerrain.FLEUVE),
                        new TuileVille(2),
                        new TuileMer(),
                        new TuileMer(),
                        new TuileMer(),
                },
        };
        placerTuiles(array);
        array[1][2].supprimerVoisine(array[2][2]);
        array[2][6].supprimerVoisine(array[3][5]);
        array[2][6].supprimerVoisine(array[3][6]);
        array[3][5].supprimerVoisine(array[3][6]);
        array[3][5].supprimerVoisine(array[4][6]);
        array[3][6].supprimerVoisine(array[4][6]);
        array[6][0].supprimerVoisine(array[7][0]);

        return Arrays.stream(array).flatMap(Arrays::stream).toList();
    }

    /**
     * Renvoie une chaîne de caractères représentant les coordonnées (ligne,
     * colonne) d'une tuile à partir de son index.
     * <p>
     * Les tuiles d'un plateau sont indexées dans le sens de la lecture : les 10
     * tuiles de la première ligne, puis les 9 tuiles de la seconde ligne, etc.
     * 
     * @param i index de la tuile (entre 0 et 75)
     * @return une chaîne de caractères de la forme "(l, c)" où l est le numéro de
     *         ligne et c le numéro de colonne de la tuile d'indice i
     */
    public static String getCoordonnees(int i) {
        int strip = i / 19;
        int stripIndex = i % 19;
        if (stripIndex < 10) {
            return String.format("(%d, %d)", 2 * strip, stripIndex);
        } else {
            return String.format("(%d, %d)", 2 * strip + 1, stripIndex - 10);
        }
    }
}
