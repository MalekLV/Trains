package fr.umontpellier.iut.trains;

import java.util.*;

import fr.umontpellier.iut.trains.cartes.*;
import fr.umontpellier.iut.trains.plateau.*;

public class Joueur {
    /**
     * Le jeu auquel le joueur est rattaché
     */
    private Jeu jeu;
    /**
     * Nom du joueur (pour les affichages console et UI)
     */
    private String nom;
    /**
     *  Score actuel du joueur
     */
    private int score;
    /**
     * Quantité d'argent que le joueur a (remis à zéro entre les tours)
     */
    private int argent;
    /**
     * Nombre de points rails dont le joueur dispose. Ces points sont obtenus en
     * jouant les cartes RAIL (vertes) et remis à zéro entre les tours
     */
    private int pointsRails;
    /**
     * Nombre de jetons rails disponibles (non placés sur le plateau)
     */
    private int nbJetonsRails;
    /**
     * Liste des cartes en main
     */
    private ListeDeCartes main;
    /**
     * Liste des cartes dans la pioche (le début de la liste correspond au haut de
     * la pile)
     */
    private ListeDeCartes pioche;
    /**
     * Liste de cartes dans la défausse
     */
    private ListeDeCartes defausse;
    /**
     * Liste des cartes en jeu (cartes jouées par le joueur pendant le tour)
     */
    private ListeDeCartes cartesEnJeu;
    /**
     * Liste des cartes reçues pendant le tour
     */
    private ListeDeCartes cartesRecues;

    /**
     * Couleur du joueur (utilisé par l'interface graphique)
     */
    private CouleurJoueur couleur;
    /**
     * Dictionnaire des effets passifs piochables dans un tour
     */
    private Map<String, Boolean> effetsPassifs;

    /**
     * Le joueur a-t-il jouer un ou une ferronnerie a ce tour ? le nombre de ferronnerie jouée a ce tour
     */
    private int ferronnerie;

    public Joueur(Jeu jeu, String nom, CouleurJoueur couleur) {
        this.jeu = jeu;
        this.nom = nom;
        this.couleur = couleur;
        score=0;
        nbJetonsRails = 20;

        initialiserEffetPassif();

        main = new ListeDeCartes();
        defausse = new ListeDeCartes();
        pioche = new ListeDeCartes();
        cartesEnJeu = new ListeDeCartes();
        cartesRecues = new ListeDeCartes();

        // créer 7 Train omnibus (non disponibles dans la réserve)
        pioche.addAll(FabriqueListeDeCartes.creerListeDeCartes("Train omnibus", 7));
        // prendre 2 Pose de rails de la réserve
        for (int i = 0; i < 2; i++) {
            pioche.add(jeu.prendreDansLaReserve("Pose de rails"));
        }
        // prendre 1 Gare de la réserve
        pioche.add(jeu.prendreDansLaReserve("Gare"));

        // mélanger la pioche
        pioche.melanger();
        // Piocher 5 cartes en main
        // Remarque : on peut aussi appeler piocherEnMain(5) si la méthode est écrite
        for (int i = 0; i < 5; i++) {
            main.add(pioche.remove(0));
        }

    }

    public void initialiserEffetPassif(){
        effetsPassifs = new HashMap<>();
        effetsPassifs.put("Coopération",false);
        effetsPassifs.put("Dépotoir",false);
        effetsPassifs.put("Pont en acier",false);
        effetsPassifs.put("Train matinal",false);
        effetsPassifs.put("Tunnel",false);
        effetsPassifs.put("Viaduc",false);
        effetsPassifs.put("Voie souterraine",false);
        ferronnerie=0;
        argent = 0;
        pointsRails = 0;
    }

    public ListeDeCartes getMain() {
        return main;
    }

    public ListeDeCartes getPioche() {
        return pioche;
    }

    public ListeDeCartes getDefausse() {
        return defausse;
    }

    public ListeDeCartes getCartesEnJeu() {
        return cartesEnJeu;
    }

    public String getNom() {
        return nom;
    }

    public CouleurJoueur getCouleur() {
        return couleur;
    }

    public Map<String, ListeDeCartes> getReserve(){
        return jeu.getReserve();
    }

    public int getNbJetonsRails() {
        return nbJetonsRails;
    }

    public int getnbCarteEnMain(){
        return main.size();
    }

    public List<String> getCartesDeLaPartie(){
        return jeu.getListeNomsCartes().stream().toList();
    }

    public void ferronnerieActif() {
        argent+=ferronnerie*2;
    }

    public void gagnerRail(){
        pointsRails++;
        ajouterFeraille(); // ajoute ferraille ?
        ferronnerieActif();
    }

    /**
     * Augmente le score du joueur de + n
     */
    public void augmenterScore(int n){
        score+=n;
    }

    /**
     * Augmente l'argent du joueur de + n
     */
    public void ajouterArgent(int n){
        argent+=n;
    }

    /**
     * Enleve une gare au stock
     */
    public void diminuerGare(){jeu.diminuerGares();}

    /**
     * Changer la valeur de dépotoir avec le booleen en entrée
     */
    public void changerEffetPassif(String nomEffet, Boolean boo){
        effetsPassifs.replace(nomEffet,boo);
    }

    /**
     * Renvoie la valeur de l'effet passif
     */
    public boolean getEffetPassif(String nomEffet){
        return effetsPassifs.get(nomEffet);
    }

    /**
     * Augmente de 1 la valeur de ferronnerie (une ferronnerie a été jouée)
     */
    public void nvFerronnerie(){
        ferronnerie+=1;
    }

    /**
     * Calcule le score des cartes Victoires dans une liste de carte
     */
    public int calculerScoreVictoires(ListeDeCartes cartes) {
        int scoreTotal = 0;
        for (Carte carte : cartes) {
            if (Victoires.class.isAssignableFrom(carte.getClass())) {
                scoreTotal += ((Victoires) carte).getVictoires();
            }
        }
        return scoreTotal;
    }


    /**
     * Renvoie le score total du joueur
     * <p>
     * Le score total est la somme des points obtenus par les effets suivants :
     * <ul>
     * <li>points de rails (villes et lieux éloignés sur lesquels le joueur a posé
     * un rail)
     * <li>points des cartes possédées par le joueur (cartes VICTOIRE jaunes)
     * <li>score courant du joueur (points marqués en jouant des cartes pendant la
     * partie p.ex. Train de tourisme)
     * </ul>
     *
     * @return le score total du joueur
     */
    public int getScoreTotal() {
        int scoreTotal = score;

        for (Tuile tuile : jeu.getTuiles()){
            if (tuile.hasRail(this)) {
                if(TuileEtoile.class.isAssignableFrom(tuile.getClass())){
                    scoreTotal+=((TuileEtoile) tuile).getSurcout();
                }else{
                    scoreTotal+=tuile.getNbGares() * 2;
                    if(tuile.getNbGares()==3){
                        scoreTotal+=2;
                    }
                }
            }
        }
        scoreTotal += calculerScoreVictoires(main);
        scoreTotal += calculerScoreVictoires(pioche);
        scoreTotal += calculerScoreVictoires(defausse);
        scoreTotal += calculerScoreVictoires(cartesEnJeu);
        scoreTotal += calculerScoreVictoires(cartesRecues);
        return scoreTotal;
    }

    /**
     * Renvoie le nombre de ferrailles en main du joueur
     */
    public int getNBFeraille(){
        int nbf=0;
        for(Carte carte : main){
            if(Objects.equals(carte.getNom(), "Ferraille")){
                nbf+=1;
            }
        }
        return nbf;
    }

    /**
     * Ajoute une ferraille dans les cartes recues du joueur
     */
    public void ajouterFeraille(){
        if(!getEffetPassif("Dépotoir")){
            Carte carte = jeu.prendreDansLaReserve("Ferraille");
            if(carte!=null){
                cartesRecues.add(carte);
            }
        }
    }

    /**
     * Ajoute n ferrailles dans la défausse du joueur
     */
    public void ajouterFeraille(int n){
        for(int i=0;i<n;i++){
            ajouterFeraille();
        }
    }

    /**
     * Retire et remet dans la pile de la reserve la première ferraille dans la main du joueur,
     * renvoie true s'il existait une ferraille, false sinon
     */
    public boolean supprimerFerailleMain(){
        Carte feraille = main.retirer("Ferraille");
        if(feraille!=null){
            jeu.remettreDansLaReserve(feraille);
            return true;
        }
        return false;
    }

    /**
     * Remet dans la reserve toutes les ferrailles dans la main du joueur
     */
    public void supprimerFeraillesMain(){
        boolean a = supprimerFerailleMain();
        while(a){
            a = supprimerFerailleMain();
        }
    }

    /**
     * Retire la carte en jeu en parametre et la renvoie, null sinon
     */
    public Carte retirerCarteEnjeu(String nomCarte){
        return cartesEnJeu.retirer(nomCarte);
    }

    /**
     * Ajoute toutes les cartes en parametres a la defausse du joueur
     */
    public void addAllDefausse(ListeDeCartes cartes){
        if(cartes!=null){
            defausse.addAll(cartes);
        }
    }

    /**
     * Ajoute la carte en parametres a la defausse du joueur
     */
    public void addDefausse(Carte carte){
        if(carte!=null){
            defausse.add(carte);
        }
    }

    /**
     * Ajoute toutes les cartes en parametres a la main du joueur
     */
    public void addAllMain(ListeDeCartes cartes){
        if(cartes!=null){
            main.addAll(cartes);
        }
    }

    /**
     * Ajoute la carte en parametre à la main du joueur
     */
    public void addMain(Carte carte){
        if(carte!=null){
            main.add(carte);
        }
    }


    /**
     * Ajoute la carte en parametre à la défausse du joueur (depuis la reserve)
     */
    public void prendreDansLaReserve(String nomCarte){
        Carte carte = jeu.prendreDansLaReserve(nomCarte);
        if(carte!=null){
            log("Reçoit " + carte);
            cartesRecues.add(carte);
        }
    }

    /**
     * Ajoute la carte en parametre à la main du joueur (depuis la reserve)
     */
    public void prendreDansLaReserveMain(String nomCarte){
        Carte carte = jeu.prendreDansLaReserve(nomCarte);
        if(carte!=null){
            log("Reçoit " + carte);
            main.add(carte);
        }
    }

    /**
     * Retire la carte en parametre (si elle se trouve dans les cartes jouées) du jeu
     */
    public void retirerCarte(Carte carte){
        if(cartesEnJeu.contains(carte)){
            jeu.retirerCarte(cartesEnJeu.retirer(carte.getNom()));
        }
    }

    /**
     * La carte en parametre est elle une carte action ? /!\ A potentiellement modifier si on ajoute une carte (une carte train sans action ou une carte victoire / rail / gare avec une action)
     */
    public boolean isCarteAction(Carte carte){
        return Actions.class.isAssignableFrom(carte.getClass()) || (Trains.class.isAssignableFrom(carte.getClass()) && !Objects.equals(carte.getNom(), "Train direct") && !Objects.equals(carte.getNom(), "Train express") && !Objects.equals(carte.getNom(), "Train omnibus"));
    }

    /**
     * Renvoie une liste de tout les trains (leurs noms) dans une liste de carte
     */
    public List<String> getTrainList(List<Carte> cartes){
        List<String> trains = new ArrayList<>();
        for(Carte carte : cartes){
            if(Trains.class.isAssignableFrom(carte.getClass())){
                trains.add(carte.getNom());
            }
        }
        return trains;
    }


    /**
     * Mélange la défausse dans la pioche
     */
    private void melangerDefausse(){
        pioche.addAll(defausse);
        defausse.clear();
        pioche.melanger();
    }

    /**
     * Ajoute la carte en parametre sur le dessus de la pioche
     */
    public void ajouterTopPioche(Carte carte){
        if(carte!=null){
            pioche.add(0,carte);
        }
    }

    /**
     * Retire et renvoie la première carte de la pioche.
     * <p>
     * Si la pioche est vide, la méthode commence par mélanger toute la défausse
     * dans la pioche.
     *
     * @return la carte piochée ou {@code null} si aucune carte disponible
     */
    public Carte piocher() {
        if(pioche.isEmpty()){
            if(defausse.isEmpty()){
                return null;
            }
            melangerDefausse();
        }
        return pioche.remove(0);
    }

    /**
     * Pioche et met directement dans la main une carte
     */
    public void piocherMain(){
        Carte carte = piocher();
        if(carte!=null){
            addMain(carte);
        }
    }

    /**
     * Fait prendre en main au joueur la carte en parametre depuis la défausse
     */
    public void prendreDefausse(String nomCarte){
        Carte carte = defausse.retirer(nomCarte);
        if(carte!=null){
            main.add(carte);
        }
    }


    /**
     * Met la carte en parametre (se trouvant de la main du joueur) dans la défausse
     */
    public void defausserCarte(String nomCarte){
        Carte carte = main.retirer(nomCarte);
        if(carte!=null){
            defausse.add(carte);
        }
    }

    /**
     * Retire et renvoie les {@code n} premières cartes de la pioche.
     * <p>
     * Si à un moment il faut encore piocher des cartes et que la pioche est vide,
     * la défausse est mélangée et toutes ses cartes sont déplacées dans la pioche.
     * S'il n'y a plus de cartes à piocher la méthode s'interromp et les cartes qui
     * ont pu être piochées sont renvoyées.
     * 
     * @param n nombre de cartes à piocher
     * @return une liste des cartes piochées (la liste peut contenir moins de n
     *         éléments si pas assez de cartes disponibles dans la pioche et la
     *         défausse)
     */
    public List<Carte> piocher(int n) {
        List<Carte> cartes = new ArrayList<>();
        if(!(pioche.isEmpty() && defausse.isEmpty())) {
            for(int i=0;i<n;i++){
                Carte carte = piocher();
                if(carte==null){
                    return cartes;
                }
                cartes.add(carte);
            }
        }
        return cartes;
    }

    /**
     * Ajoute n Cartes à la main du joueur depuis la pioche
     */
    public void piocherMain(int n){
        List<Carte> liste = piocher(n);
        main.addAll(liste);
    }

    /**
     * Met dans la défausse toutes les cartes dans la main du joueur
     */
    public void defausserMain(){
        defausse.addAll(main);
        main.clear();
    }

    /**
     * Met la carte en parametre (qui se trouvée dans main) dans les cartes en retirée du jeu
     * renvoie true si possible, false sinon
     */
    public Carte carteEcartee(String nomCarte){
        Carte carte = main.retirer(nomCarte);
        if(carte!=null){
            jeu.retirerCarte(carte);
            return carte;
        }
        return null;
    }

    /**
     * Achète la carte entrée en paramètre, si possible la donne au joueur et la retourne, sinon (carte plus en stock) retourne null
     */
    public boolean acheterCarte(String nomCarte){
        Carte carte = jeu.prendreDansLaReserve(nomCarte);
        if(carte==null){
            return false;
        }
        log("Achète la carte "+carte);
        if(getEffetPassif("Train matinal")){
            List<Bouton> boutons = new ArrayList<>();
            boutons.add(new Bouton("Oui !", "oui"));
            boutons.add(new Bouton("Non !", "non"));
            String res = choisir("Voulez vous placez cette carte au dessus de votre pioche",null,boutons,false);
            if(Objects.equals(res, "oui")){
                ajouterTopPioche(carte);
            }else{
                cartesRecues.add(carte);
            }
        }else{
            cartesRecues.add(carte);
        }
        if(Victoires.class.isAssignableFrom(carte.getClass())){
            ajouterFeraille();
            // augmenterScore(((Victoires)carte).getVictoires());
        }
        argent-=carte.getCout();
        return true;
    }

    /**
     * Retire x cartes de la main du joueur, renvoie x
     */
    public int retireXCartes(){
        int i=0;
        List<String> choix = maintoString();
        String input = choisir("Voulez-vous retirer une carte de votre main ?", choix, null, true);
        while(!(Objects.equals(input, ""))){
            defausserCarte(input);
            i+=1;
            choix = maintoString();
            input = choisir("Voulez-vous retirer une carte de votre main ?", choix, null, true);
        }
        return i;
    }

    /**
     * Action de la carte horaires estivaux (Ici pour éviter un bug lorsque le joueur joue la carte bureau du chef de gare et copie horaires estivaux)
     */
    public void horairesEstivaux(Carte carte){
        List<Bouton> boutons = new ArrayList<>();
        boutons.add(new Bouton("oui"));
        boutons.add(new Bouton("non"));
        String res = choisir("Voulez vous écarter cette carte et gagner 3 d'or ?",null,boutons,false);
        if(Objects.equals(res, "oui")){
            retirerCarte(carte);
            ajouterArgent(3);
        }
    }

    /**
     * Joue un tour complet du joueur
     * <p>
     * Le tour du joueur se déroule en plusieurs étapes :
     * <ol>
     * <li>Initialisation
     * <p>
     * Dans ce jeu il n'y a rien de particulier à faire en début de tour à part un
     * éventuel affichage dans le log.
     * 
     * <li>Boucle principale
     * <p>
     * C'est le cœur de la fonction. Tant que le tour du joueur n'est pas terminé,
     * il faut préparer la liste de tous les choix valides que le joueur peut faire
     * (jouer des cartes, poser des rails, acheter des cartes, etc.), puis demander
     * au joueur de choisir une action (en appelant la méthode {@code choisir}).
     * <p>
     * Ensuite, en fonction du choix du joueur il faut exécuter l'action demandée et
     * recommencer la boucle si le tour n'est pas terminé.
     * <p>
     * Le tour se termine lorsque le joueur décide de passer (il choisit {@code ""})
     * ou lorsqu'il exécute une action qui termine automatiquement le tour (par
     * exemple s'il choisit de recycler toutes ses cartes Ferraille en début de
     * tour)
     * 
     * <li>Finalisation
     * <p>
     * Actions à exécuter à la fin du tour : réinitialiser les attributs
     * du joueur qui sont spécifiques au tour (argent, rails, etc.), défausser
     * toutes les
     * cartes, piocher 5 nouvelles cartes en main, etc.
     * </ol>
     */
    public void jouerTour() {
        jeu.log("<div class=\"tour\">Tour de " + toLog() + "</div>");

        boolean finTour = false;
        int nbTour=0;

        while (!finTour) {

            // Pour tester :
            // argent+=1000;

            List<String> choixPossibles = new ArrayList<>();
            for (Carte c:main) {
                String nomCarte = c.getNom();
                if(!((nbTour>0 && Objects.equals(nomCarte, "Ferraille")) || Victoires.class.isAssignableFrom(c.getClass()))){
                    choixPossibles.add(nomCarte);
                }
            }
            for (String nomCarte: jeu.getReserve().keySet()) {
                if(!jeu.getReserve().get(nomCarte).isEmpty()){
                    if(jeu.getReserve().get(nomCarte).get(0).getCout()<=argent && !Objects.equals(nomCarte, "Ferraille")){
                        choixPossibles.add("ACHAT:" + nomCarte);
                    }
                }
            }
            if(pointsRails>0){
                choixPossibles.addAll(getTuilesPossibles(false));
            }


            String choix = choisir(String.format("Tour de %s", this.nom), choixPossibles, null, true);

            if (choix.startsWith("ACHAT:")) {
                String nomCarte = choix.split(":")[1];
                if(!acheterCarte(nomCarte)){
                    nbTour-=1;
                }

            } else if (choix.equals("")) {
                finTour = true;

            } else if (choix.equals("Ferraille")) {
                if (nbTour == 0) {
                    supprimerFeraillesMain();
                    log(nom+" passe son tour et retire les ferrailles se trouvant dans son jeu");
                    finTour=true;
                }

            } else if(choix.startsWith("TUILE:")){
                if(pointsRails>0){
                    String nomTuile = choix.split(":")[1];
                    int numTuile = Integer.parseInt(nomTuile);
                    poserRails(Objects.requireNonNull(getTuile(numTuile)));
                }

            } else {
                Carte carte = main.retirer(choix);
                if(carte!=null){
                    log("Joue " + carte);
                    cartesEnJeu.add(carte);
                    carte.jouer(this);
                }else{
                    // Ne devrais jamais arriver, mais reste une précaution de plus
                    nbTour--;
                }
            }
            nbTour++;
        }

        defausserMain();

        defausse.addAll(cartesRecues);
        cartesRecues.clear();

        defausse.addAll(cartesEnJeu);
        cartesEnJeu.clear();

        main.addAll(piocher(5));
        initialiserEffetPassif();
    }

    /**
     * Pose le rail sur la tuile et fait les actions nécessaires pour le poser
     */
    private void poserRails(Tuile tuile) {
        ajouterFeraille(getRailsTuile(tuile));
        argent-=getRailsTuile(tuile)+getSurcoutTuile(tuile);
        // if(TuileEtoile.class==tuile.getClass()){
        // augmenterScore(((TuileEtoile) tuile).getValeur());}
        tuile.ajouterRail(this);
        nbJetonsRails--;
        pointsRails--;
    }

    /**
     * Premier tour :
     * Demande au joueur de choisir un endroit ou placer son premier rail parmis les endroits possibles (plaines) et le pose
     */
    public void jouerPremierTour(){
        argent=2;
        poserRails(Objects.requireNonNull(getTuile(Integer.parseInt(choisir("Où voulez vous placer votre tout premier rails", getTuilesPossibles(true),null,false).split(":")[1]))));
        argent=0;
        pointsRails=0;
    }


    /**
     * Renvoie le surcout d'une tuile lié à son type pour le joueur
     */
    public int getSurcoutTuile(Tuile tuile){
        if(getEffetPassif("Voie souterraine")) {
            return 0;
        }
        int valeurTuile=tuile.getSurcout();
        if(tuile.getClass() == TuileTerrain.class) {
            if(valeurTuile==2 && getEffetPassif("Tunnel")) {
                return 0;
            }
            if(valeurTuile==1 && getEffetPassif("Pont en acier")) {
                return 0;
            }
        }
        if(tuile.getClass() == TuileVille.class && getEffetPassif("Viaduc")){
            return 0;
        }
        return valeurTuile;
    }

    /**
     * Renvoie le nombre de Rails d'autres joueurs sur la Tuile
     */
    public int getRailsTuile(Tuile tuile){
        if(!getEffetPassif("Coopération")&&!getEffetPassif("Voie souterraine")){
            return tuile.getNBRails();
        }
        return 0;
    }

    /**
     * Renvoie l'ensemble des Tuiles possibles pour le joueur, En parametre :
     * @true si c'est le premier tour (ne fais pas attention aux voisins)
     */
    public List<String> getTuilesPossibles(boolean premierTour){
        int n = 0;
        List<String> tuiles = new ArrayList<>();

        for (Tuile tuile : jeu.getTuiles()) {
            if (!tuile.hasRail(this) && tuile.getClass() != TuileMer.class) {
                for (Tuile voisin : tuile.getVoisines()) {
                    if ((premierTour || voisin.hasRail(this)) && !(premierTour && (tuile.getClass()==TuileEtoile.class || !tuile.estVide()))) {
                        int surcout = getSurcoutTuile(tuile) + getRailsTuile(tuile);
                        if (argent >= surcout) {
                            tuiles.add("TUILE:" + n);
                        }
                        break;
                    }
                }

            }
            n++;
        }
        return tuiles;
    }


    /**
     * Renvoie la tuile correspondant au numéro sur le plateau
     */
    public Tuile getTuile(int numTuile) {
        int nbTuile=0;
        for(Tuile tuile : jeu.getTuiles()){
            if(nbTuile==numTuile){
                return tuile;
            }
            nbTuile++;
        }
        return null;
    }

    /**
     * Renvoie les numéros des tuiles poù poser une gare est possible
     */
    public List<String> getVillesPossibles() {
        List<String> tuiles = new ArrayList<>();
        if(!jeu.estFini()){
            int nbTuile=0;
            for(Tuile tuile : jeu.getTuiles()){
                if(TuileVille.class.isAssignableFrom(tuile.getClass())){
                    if(((TuileVille) tuile).isGarePossible()){
                        tuiles.add("TUILE:" + nbTuile);
                    }
                }
                nbTuile++;
            }
        }
        return tuiles;
    }


    /**
     * Attend une entrée de la part du joueur (au clavier ou sur la websocket) et
     * renvoie le choix du joueur.
     * <p>
     * Cette méthode lit les entrées du jeu ({@code Jeu.lireligne()}) jusqu'à ce
     * qu'un choix valide (un élément de {@code choix} ou la valeur d'un élément de
     * {@code boutons} ou éventuellement la chaîne vide si l'utilisateur est
     * autorisé à passer) soit reçu.
     * Lorsqu'un choix valide est obtenu, il est renvoyé par la fonction.
     * <p>
     * Exemple d'utilisation pour demander à un joueur de répondre à une question
     * par "oui" ou "non" :
     * <p>
     * 
     * <pre>{@code
     * List<String> choix = Arrays.asList("oui", "non");
     * String input = choisir("Voulez-vous faire ceci ?", choix, null, false);
     * }</pre>
     * <p>
     * Si par contre on voulait proposer les réponses à l'aide de boutons, on
     * pourrait utiliser :
     * 
     * <pre>{@code
     * List<String> boutons = Arrays.asList(new Bouton("Oui !", "oui"), new Bouton("Non !", "non"));
     * String input = choisir("Voulez-vous faire ceci ?", null, boutons, false);
     * }</pre>
     * 
     * (ici le premier bouton a le label "Oui !" et envoie la String "oui" s'il est
     * cliqué, le second a le label "Non !" et envoie la String "non" lorsqu'il est
     * cliqué)
     *
     * <p>
     * <b>Remarque :</b> Normalement, si le paramètre {@code peutPasser} est
     * {@code false} le choix
     * {@code ""} n'est pas valide. Cependant s'il n'y a aucun choix proposé (les
     * listes {@code choix} et {@code boutons} sont vides ou {@code null}), le choix
     * {@code ""} est accepté pour éviter un blocage.
     * 
     * @param instruction message à afficher à l'écran pour indiquer au joueur la
     *                    nature du choix qui est attendu
     * @param choix       une collection de chaînes de caractères correspondant aux
     *                    choix valides attendus du joueur (ou {@code null})
     * @param boutons     une liste d'objets de type {@code Bouton} définis par deux
     *                    chaînes de caractères (label, valeur) correspondant aux
     *                    choix valides attendus du joueur qui doivent être
     *                    représentés par des boutons sur l'interface graphique (le
     *                    label est affiché sur le bouton, la valeur est ce qui est
     *                    envoyé au jeu quand le bouton est cliqué) ou {@code null}
     * @param peutPasser  booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la
     *                    chaîne de caractères vide ({@code ""}) qui signifie qu'il
     *                    désire passer.
     * @return le choix de l'utilisateur (un élement de {@code choix}, ou la valeur
     *         d'un élément de {@code boutons} ou la chaîne vide)
     */
    public String choisir(
            String instruction,
            Collection<String> choix,
            List<Bouton> boutons,
            boolean peutPasser) {
        if (choix == null)
            choix = new ArrayList<>();
        if (boutons == null)
            boutons = new ArrayList<>();

        HashSet<String> choixDistincts = new HashSet<>(choix);
        choixDistincts.addAll(boutons.stream().map(Bouton::valeur).toList());
        if (peutPasser || choixDistincts.isEmpty()) {
            // si le joueur a le droit de passer ou s'il n'existe aucun choix valide, on
            // ajoute "" à la liste des choix possibles
            choixDistincts.add("");
        }

        String entree;
        // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
        while (true) {
            jeu.prompt(instruction, boutons, peutPasser);
            entree = jeu.lireLigne();
            // si une réponse valide est obtenue, elle est renvoyée
            if (choixDistincts.contains(entree)) {
                return entree;
            }
        }
    }

    /**
     * Ajoute un message dans le log du jeu
     * 
     * @param message message à ajouter dans le log
     */
    public void log(String message) {
        jeu.log(message);
    }

    /**
     * Renvoie la carte correspondant au nom en parametre dans la liste en parametre,
     * retourne null si la carte ne se situe pas dans la liste
     */
    public Carte retrouveCarte(String nomCarte, List<Carte> cartes){
        for(Carte carte : cartes){
            if(Objects.equals(carte.getNom(), nomCarte)){
                return carte;
            }
        }
        return null;
    }

    /**
     * Renvoie une liste contenant tout les noms des cartes dans la liste en parametre
     */
    public List<String> listCarteToString(List<Carte> cartes){
        List<String> liste = new ArrayList<>();
        for(Carte carte : cartes){
            liste.add(carte.toString());
        }
        return liste;
    }

    /**
     * Renvoie une liste contenant tout les noms des cartes en mains du joueur
     */
    public List<String> maintoString(){
         return listCarteToString(main);
    }

    @Override
    public String toString() {
        // Vous pouvez modifier cette fonction comme bon vous semble pour afficher
        // d'autres informations si nécessaire
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("=== %s (%d pts) ===", nom, getScoreTotal()));
        joiner.add(String.format("  Argent: %d  Rails: %d", argent, pointsRails));
        joiner.add("  Cartes en jeu: " + cartesEnJeu);
        joiner.add("  Cartes reçues: " + cartesRecues);
        joiner.add("  Cartes en main: " + main);
        return joiner.toString();
    }

    /**
     * @return une représentation du joueur pour l'affichage dans le log du jeu
     */
    public String toLog() {
        return String.format("<span class=\"joueur %s\">%s</span>", couleur.toString(), nom);
    }

    /**
     * @return une représentation du joueur sous la forme d'un dictionnaire de
     *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     *         l'interface graphique)
     */
    Map<String, Object> dataMap() {
        return Map.ofEntries(
                Map.entry("nom", nom),
                Map.entry("couleur", couleur),
                Map.entry("scoreTotal", getScoreTotal()),
                Map.entry("argent", argent),
                Map.entry("rails", pointsRails),
                Map.entry("nbJetonsRails", nbJetonsRails),
                Map.entry("main", main.dataMap()),
                Map.entry("defausse", defausse.dataMap()),
                Map.entry("cartesEnJeu", cartesEnJeu.dataMap()),
                Map.entry("cartesRecues", cartesRecues.dataMap()),
                Map.entry("pioche", pioche.dataMap()),
                Map.entry("actif", jeu.getJoueurCourant() == this));
    }
}
