## Lexique
* La **réserve** du jeu est l'ensemble des piles de cartes qui peuvent être achetées par les joueurs + les cartes _FERRAILLE_. 

* La **main** d'un joueur est l'ensemble des cartes qu'il a en main et qu'il peut jouer.

* La **pioche** d'un joueur est l'ensemble des cartes qu'il peut piocher. Lorsque la pioche est vide, on mélange la défausse pour former une nouvelle pioche.

* La **défausse** d'un joueur est l'ensemble des cartes qu'il a défaussées (pendant ou à la fin d'un tour) qui n'ont pas encore été mélangées et remises dans la pioche (lorsque la pioche est vide et que le joueur doit piocher une carte). À la fin de chaque tour toutes les cartes restantes *en main*, *en jeu* et *reçues* sont placées dans la défausse avant que le joueur pioche 5 nouvelles cartes.

* Les **cartes reçues** par un joueur sont les cartes qu'il a achetées ou reçues par un effet (par exemple des cartes _Ferraille_). Ces cartes ne peuvent pas être jouées par le joueur.

* Les **cartes en jeu** sont les cartes qui ont été jouées par le joueur depuis sa main pendant le tour courant.

## L'organisation des piles de cartes
Une instance de `Jeu` contient normalement une réserve de 16 piles cartes, dont 8 *communes* à toutes les parties :
* _Train express_
* _Train direct_
* _Pose de rails_
* _Gare_
* _Appartement_
* _Immeuble_
* _Gratte-ciel_
* _Ferraille_

et 8 autres piles choisies aléatoirement parmi les 30 piles de cartes disponibles restantes.

**Remarque :** les joueurs ont des cartes _Train omnibus_ dans leur deck en début de partie, mais elles ne font jamais partie de la réserve.

Lorsqu'un joueur pioche des cartes, il le fait systématiquement **au sommet** de la pile. Dans ce projet, ces piles sont gérées avec des `ArrayList` et on considère que le sommet de la pile est la position 0 (voir par exemple `testPiocherCarte` dans `JoueurProfTest` pour illustration). Lorsque des cartes sont replacées sur leur pile de réserve (par ex. certaines actions replacent des _Ferraille_) elles sont également replacées au sommet (donc en position 0 de l'`ArrayList`).

# Début de partie

## Choisir où placer le premier rail de chaque joueur
- indiquer le numéro de la tuile

Tests associés (dans `JeuProfTest`):
- `testPlacerPremierRail()`

## Joueur une carte _ACTION_
Pour simplifier l'implémentation et pour mieux préciser les règles, on considérera que si un joueur joue une carte action, alors l'intégralité de l'action de la carte est exécutée (si cela est possible).

Tests associés (dans `JoueurProfTest`):
- `testActionEntièrementExecutee()`

# Tour du joueur

## Se débarrasser des ferrailles de sa main
- au début du tour, le joueur entre "_FERRAILLE_" pour indiquer qu'il souhaite déplacer les cartes ferraille de sa main vers la réserve du jeu
- défausser toutes les ferrailles n'est possible que si le joueur n'a fait rien d'autre durant ce tour du jeu

Tests associés (dans `JoueurProfTest`):
- `testDebarasserFerraille`

## Acheter des cartes
- le joueur entre le nom de la carte en majuscules pour acheter la première carte de la pioche de réserve correspondante
- si le joueur a suffisamment d'argent, la carte est ajoutée à sa pile de cartes reçues et le coût est déduit de son argent
- cas particulier de la carte _TRAIN MATINAL_ : si le joueur joue cette carte, alors après l'avoir joué et durant ce tour, il pourra choisir de placer ces cartes sur le sommet de sa pioche ou dans les cartes reçues 

Tests associés (dans `JoueurProfTest`):
- `testAcheterCarte`
- `testAcheterCarteAprèsAvoirJouerTrainMatinal`

# Score

La fonction `getScoreTotal()` de la classe `Joueur` doit retourner le nombre total des points de victoire que le joueur a accumulés. Pensez à vérifier que le score retourné tient compte correctement des différentes sources apportant des points de victoire.
