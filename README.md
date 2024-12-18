[](ressources/logo.jpeg)

# Projet - _Trains_

### IUT Montpellier-Sète – Département Informatique

Ce projet a lieu dans le cadre des Situations d'Apprentissage et Évaluation du BUT Informatique (SAE S2.01 et S2.02). Il fera intervenir plusieurs compétences acquises durant le Semestre 2 : le développement orienté objets, les notions de qualité de développement (tests unitaires, gestion de version, _etc._), les algorithmes de graphes et les interfaces homme-machine.

## Calendrier de travail

Le projet a été conçu pour être réalisé de façon incrémentale en 3 phases :

- **Phase 1 : _développement de la mécanique du jeu en Java_**

  - **Période :** 25 mars -- 28 avril 2024
  - **Cours concerné** : [_Développement Orienté Objets_](https://gitlabinfo.iutmontp.univ-montp2.fr/dev-objets)
  - **Enseignants :**
    [Malo Gasquet](mailto:malo.gasquet@umontpellier.fr),
    [Sophie Nabitz](mailto:sophie.nabitz@univ-avignon.fr),
    [Cyrille Nadal](mailto:cyrille.nadal@umontpellier.fr),
    [Victor Poupet](mailto:victor.poupet@umontpellier.fr),
    [Gilles Trombettoni](mailto:gilles.trombettoni@umontpellier.fr),
    [Petru Valicov](mailto:petru.valicov@umontpellier.fr)
  - [forum Piazza](https://piazza.com/class/lrahb0patze3u4) pour poser vos questions
  - **Modalités de notation de la Phase 1** :
    - tests unitaires automatisés secrets
    - individualisation des notes en fonction des contributions de chaque membre de l'équipe

  Après votre rendu, une correction pour cette phase vous sera fournie afin que vous puissiez continuer sur les phases 2 et 3 du projet.

- **Phase 2 : _mise en œuvre des algorithmes de graphes_**

  - **Période (prévisionnelle) :** 13 mai -- 10 juin 2024
  - **Cours concerné** : _Graphes_
  - **Enseignants :**
    [Alexandre Bazin](mailto:alexandre.bazin@umontpellier.fr),
    [Thomas Haettel](mailto:thomas.haettel@umontpellier.fr),
    [Alain Marie-Jeanne](mailto:alain.marie-jeanne@umontpellier.fr)
  - **Modalités de notation de la Phase 2** :
    - implémentation en Java du code : note 1
    - interrogation écrite individuelle : note 2\
      l'interrogation portera sur les notions utilisées dans la réalisation de cette phase du projet.
    - note Phase 2 : **Min (note 1, note 2)**

- **Phase 3 (suite de la Phase 1) : _implémentation d'une interface graphique sous [JavaFX](https://openjfx.io/)_**
  - **Période (prévisionnelle) :** mi-mai -- juin 2023
  - **Cours concerné** : _[Développement d’applications avec IHM](https://gitlabinfo.iutmontp.univ-montp2.fr/ihm)_
  - **Enseignants :**
    [Sophie Nabitz](mailto:sophie.nabitz@univ-avignon.fr),
    [Cyrille Nadal](mailto:cyrille.nadal@umontpellier.fr),
    [Nathalie Palleja](mailto:nathalie.palleja@umontpellier.fr),
    [Xavier Palleja](mailto:xavier.palleja@umontpellier.fr),
    [Petru Valicov](mailto:petru.valicov@umontpellier.fr),
    [Zouhayra Ayadi](mailto:zouhayra.ayadi@umontpellier.fr)
  - **Modalités de notation de la Phase 3** : À définir ultérieurement

## Notation des SAE 1 et SAE 2

- **SAE 1 :** note de la Phase 1 : 50%, note de la Phase 3 : 50%
- **SAE 2 :** note de la Phase 1 : 30%, note de la Phase 2 : 70%

## Consignes générales

Vous devez commencer par lire [les règles du jeu](https://gitlabinfo.iutmontp.univ-montp2.fr/dev-objets/ressources/-/blob/master/regles.pdf). Des vidéos de présentation du jeu :

- [Courte, en français](https://www.youtube.com/watch?v=PfovGtlg-yg&ab_channel=Ludovox)
- [Longue, en anglais, mais assez bien détaillée](https://www.youtube.com/watch?v=jdaFC3Tom3c&ab_channel=TheTabletopTutor).

Un squelette du code vous est fourni et vous devrez :

- écrire le corps des fonctions non implémentées qui vous sont fournies
- ajouter des fonctions/attributs/classes là où cela vous semble utile
- vérifier que votre programme marche en faisant des tests unitaires
- sauf indication explicite de la part des enseignants, ne pas modifier la signature des méthodes/attributs/classes qui vous sont données

Le fichier [IO.md](IO.md) contient des précisions sur les entrées/sorties avec des exemples :
* les entrées attendues de la part de l'utilisateur dans différents scénarios
* les résultats attendus de la part du programme

Quelques consignes concernant l'écriture des tests vous sont données dans le fichier [Consignes.md](Consignes.md).

Pensez à consulter régulièrement les [FAQ](FAQ.md). Vous serez informés s'il y a des nouveautés (surveillez le [Forum Piazza](https://piazza.com/class/lrahb0patze3u4)).

Elles seront complétées pour chaque phase au fur et à mesure et vous en serez informés.

_**Le non-respect de ces consignes aura des fortes implications sur la note finale.**_

## Présentation

Le but de ce projet est de produire une implémentation en _Java_ du jeu de plateau japonais _Trains_.

Le principe du jeu est de construire un réseau ferroviaire en plaçant des rails sur un plateau réprésentant la région autour de Tokyo ou d'Osaka. Chacun à leur tour les joueurs jouent des cartes qui permettent à la fois d'acheter de nouvelles cartes pour améliorer leur _deck_ et de placer des rails ou des villes sur le plateau de jeu pour améliorer leur réseau ferroviaire.

Avant de poursuivre la lecture des consignes, assurez-vous d'avoir lu et compris [les règles du jeu](https://gitlabinfo.iutmontp.univ-montp2.fr/dev-objets/ressources/-/blob/master/regles.pdf).

## Architecture générale du code

Le projet est structuré en 3 paquetages :`fr.umontpellier.iut.gui`, `fr.umontpellier.iut.trains` et `fr.umontpellier.iut.trains.data`. Les classes du paquetage `fr.umontpellier.iut.gui` servent uniquement à l'interface graphique et, sauf indication contraire, vous n'aurez pas à les modifier.

Les 2 autres paquetages `fr.umontpellier.iut.trains` et `fr.umontpellier.iut.trains.data`, représentent le code métier du projet. Voici le diagramme de classes **simplifié** :

<img style="max-width: 600px; height: auto; " src="//www.plantuml.com/plantuml/png/XLHDJzj04BtxLunyAQNQ22fEA8f0AA2YG16at2Vsu2oiTztzGAqA_wVv7VbZ7NiJiebBUt7YzvxVp9iT-yHvj3xKAdDFKZTeiGPjIdBKmE7HGO9Qr4yTUdYGPGqMJrWHvDwYr6vGi0cv78R3SwDAigCH_Ce0SEPOK7We53e7Pwsg8m04bwdX6EwybRhAEkmJF9f0Tk-Ihy5-PFRPo9AfronxtHIDDRThiIZMkAJ0jz6Dd3-NMkwrJZDZ5A5UCJPeHbD_lchZYNNUSsFceNNgr6UjBq73VHQoGKNmFKYebhyjWK9eE9IaGPIIPuiYFc2NYuVbmf8keaw5LWeyMo3jfLyk7D-0-e1DesI1NXeDGYJQphFLivyfPATmu8nc5wCBCBLqp9R8g28e9LPyKZN_MpVXObyJX7gvy7F3H8nEpeDAq9rqvN9HAEvylXEHTDcDQJFlU5P_FzG8eQquBwDI-nNK6FxlRb4gjuNhsILvpfWowMPCJR3fkRL48mst6U5kmNPm7NrQUYAT7tQQqKfKaRyhZAMfyQWwYzZCQxjl_JLl57h2qDjpWEuI9p8DKj4AVjl3UrbIRmyZYnpDasqpxpqUhmQwQYMHpT7r9NqtJdGzkq1BBecptd00qg66wS-6fcJRJ4cVDvFJBzVZjzkhhzVJqui4E9-ClzsFrsuxUVjs0ZoNWUEqsZiFtqkbQEjH7IDSuO-OeK_T64Va4hPlUqNhlUnpOs_aJirdL1HiVsRR0OLu6KLamy7mHOWaIf-9tKGyAP-OSR5uh91_7WoE078Xzhjlq1f-x7xFJB2eVJuK8cM5bTNSYr4Urnxsy_WozFYI7Z0uzmz5_9QFDRl_JtHBHI3tlgf6gJTi5aSYbCZtspVOIKqkZ_dNd1WnPr4g9g9RTaAw3BNw0m00"  width=1300 alt="diagramme de classes">

Dans le diagramme ci-dessus, sont représentés seulement les méthodes et attributs contribuant à la compréhension de la modélisation orientée objet du jeu :

> **Important :** Sauf indication contraire, les déclarations des attributs et les signatures des méthodes qui vous sont données doivent rester intactes.

### La classe `Carte`

La classe `Carte` représente les cartes que les joueurs peuvent jouer pendant leur tour.

Il existe 39 modèles de cartes utilisées dans le jeu, chacune ayant un ou plusieurs types parmi :

- _Train_ (bleu)
- _Rail_ (vert)
- _Gare_ (violet)
- _Action_ (rouge)
- _Victoire_ (jaune)
- _Ferraille_ (gris)

La plupart des cartes n'ont qu'un seul type qui correspond à la couleur du contour de la carte, mais il existe 5 cartes bleues qui sont à la fois de type _Train_ et de type _Action_ (triangle rouge avec un point d'exclamation) : _TGV_, _Train de marchandises_, _Train de tourisme_, _Train matinal_ et _Train postal_.

Le répertoire `front/assets/cartes` de ce dépôt contient des scans de chacune des cartes qui définissent leurs propriétés.

Dans le code qui vous est fourni la classe `Carte` n'a qu'un seul attribut `nom` (le nom qui est imprimé dans l'encadré en haut de la carte) mais vous pouvez ajouter les attributs et méthodes qui vous semblent nécessaires pour représenter les caractéristiques des cartes du jeu (type, coût d'achat, _etc._). Le constructeur fourni est également très basique (il se contente de fixer le nom de la carte) et vous devrez donc ajouter un ou plusieurs constructeurs plus riches en fonction des attributs que vous ajoutez à la classe.

Chaque modèle de carte du jeu est représenté par une sous-classe de `Carte`. Ces sous-classes vous sont données, mais elles ne contiennent aucun code pour le moment (autre que le nom de la carte). Une grande partie du travail de ce projet sera d'implémenter les spécificités de chacune de ces cartes en complétant ces sous-classes. Attention, chacune de ces sous-classes doit avoir un constructeur qui ne prend pas d'arguments (même signature que le constructeur qui est fourni).

Par ailleurs, pour le moment toutes les classes représentant des cartes héritent directement de `Carte`. Cependant, plusieurs groupes de cartes partagent des propriétés et des comportements (par exemple les cartes d'une même couleur). Afin d'éviter la duplication de code il peut donc être judicieux de définir de nouvelles sous-classes abstraites de `Carte` et de faire hériter les classes représentant les cartes du jeu de ces sous-classes.

**Remarque :** dans le code qui vous est fourni, l'ensemble des cartes utilisées au cours d'une partie (cartes initialement distribuées au joueur et cartes disponibles dans la réserve) sont instanciées lors de l'appel au constructeur de `Jeu` (dont une partie instanciées dans le constructeur de `Joueur` qui est appelé par celui de `Jeu`) à l'aide de la classe `FabriqueListeDeCartes`. Les constructeurs de `Jeu` et `Joueur` ne devraient pas être modifiés et par conséquent, vous ne devriez pas avoir à instancier de nouvelles cartes. Veillez également à ce que toutes les instances de `Carte` créées en début de partie soient bien conservées pendant l'exécution du programme : elles peuvent passer d'une liste à une autre, mais ne devraient pas disparaître. En revanche, dans l'écriture de vos tests unitaires, vous pouvez créer des cartes artificiellement pour les besoins de vos tests.

### La classe `Joueur`

Cette classe représente l'état et les actions d'un joueur (il y aura donc une instance de cette classe par joueur de la partie).

En particulier, les cartes possédées par un joueur sont représentées en 5 listes :

- `pioche` : la pile de cartes face cachée d'où le joueur pioche ses cartes au cours du jeu. On considère que le début de la liste correspond au haut de la pile (si la pioche n'est pas vide, la prochaine carte piochée est `pioche.get(0)`)
- `main` : les cartes que le joueur a en main
- `defausse` : les cartes défaussées par le joueur soit en cours de tour, soit à la fin d'un tour avant de piocher ses nouvelles cartes en main
- `cartesEnJeu` : les cartes que le joueur a jouées de sa main pendant le tour courant
- `cartesRecues` : les cartes que le joueur a reçues (achetées ou reçues par l'effet d'une carte jouée ou d'une action) pendant le tour courant

**Remarque :** L'ordre des cartes dans la `pioche` est important, mais pas dans les autres piles.

La méthode la plus importante de la classe `Joueur` est `jouerTour()` qui regroupe tout le déroulement d'un tour du joueur. Un squelette de cette fonction vous est donné, mais vous devrez la compléter.

Prêtez une attention particulière à la documentation de la méthode `choisir` de la classe `Joueur`. C'est cette méthode qu'il faudra utiliser à chaque fois qu'un joueur doit faire un choix (jouer une carte, poser un rail, choisir parmi deux options, _etc._).

### La classe `Jeu`

C'est cette classe qui coordonne le déroulement de la partie. Lorsque le programme est exécuté, une instance de `Jeu` est créée pour exécuter la partie.

La méthode principale de cette classe est `run` qui exécute la boucle principale qui doit initialiser la partie puis exécuter les tours des joueurs (en appelant `Joueur.jouerTour()` successivement pour tous les joueurs) jusqu'à ce que la partie se termine.

La boucle principale du jeu se termine lorsque la méthode `boolean estFini()` (que vous devrez écrire) renvoie `true`. Pour rappel, la partie se termine à la fin du tour d'un joueur si l'une des conditions suivantes est vérifiée :

- Il y a au moins 4 piles vides dans la réserve (sans compter la pile de _Ferraille_) ;
- Un des joueurs a posé tous ses jetons _Rail_ (chaque joueur en a 20) sur le plateau ;
- Tous les jetons _Gare_ (il y en a 30) ont été posés sur le plateau.

## Interface utilisateur

Pour lancer une partie :

1. Exécuter la méthode `main(String args[])` de la classe `fr.umontpellier.iut.gui.GameServer`.
2. Ouvrir dans un navigateur l'adresse `http://localhost:4242`.

Vous pourrez alors observer en parallèle l'affichage dans la console et sur l'interface graphique.

**Remarque :** Il n'est pas nécessaire de comprendre les détails de fonctionnement de la classe `GameServer` dont le rôle est de connecter votre programme à un serveur web local qui permet d'afficher l'interface graphique. Vous pouvez cependant paramétrer la partie qui s'exécute en changeant les paramètres `String[] nomsJoueurs` qui permet de choisir le nombre de joueurs de la partie et `String[] nomsCartes` qui permet de choisir les cartes qui seront proposées dans les piles de réserve (utile pour tester manuellement certaines cartes).

### Interface graphique (web)

Pour rendre l'expérience ludique, et pour que votre jeu rassemble à un _vrai_ jeu, une interface graphique vous est également fournie. Cette interface interprète la saisie console et affiche le jeu de manière plus proche d'un utilisateur non-informaticien dans un navigateur web. Vous n'aurez pas à la modifier (ni à adapter votre code), cette partie étant complètement indépendante de votre projet. Nous vous conseillons d'utiliser l'interface graphique directement pour simuler votre jeu, car utiliser uniquement la console peut s'avérer particulièrement pénible.

Voici un aperçu de l'interface graphique du jeu, au milieu d'une partie avec 2 joueurs :

<img alt="Interface graphique" src="ressources/gui.png"/>

**Important** : Des méthodes spéciales `log()`, `String lireLigne()`, `prompt()`, `toLog()` et `dataMap()` ont été ajoutées aux classes. Également les définitions des méthodes `toString()` de différentes classes de l'application sont données. Toutes ces méthodes sont nécessaires pour l'IHM. **Vous ne devriez pas les modifier !**

À tout moment vous pouvez faire un appel à la fonction `log(String)` des classes `Jeu` ou `Joueur` pour afficher des messages sur l'interface (dans le carnet en bas et à gauche de l'écran). N'hésitez pas à l'appeler et lui passer le message correspondant afin de visualiser les actions de l'utilisateur.

### Interface console

Une interface utilisateur en ligne de commandes vous est également fournie. Les informations du jeu sont affichées à l'écran en utilisant la sortie standard, et les choix des joueurs peuvent se faire par lecture sur l'entrée standard (clavier). Comme dit précédemment, il vaut mieux privilégier l'interface web qui vous est fournie pour faire vos simulations.

**Important** : Si vous êtes amenés à faire des modifications du code gérant l'affichage, pour notamment afficher des informations supplémentaires, vous veillerez à ce que cela n'affecte pas le fonctionnement général de ces fonctions.

## Rendu attendu

L'intégralité du code source du projet doit résider dans le dépôt GitLab associé à votre équipe de projet. Vous devez compléter les classes Java qui vous sont données et ajouter des nouvelles classes si nécessaire.

Toutes les méthodes qui lèvent une exception avec l'instruction `throw new RuntimeException("Méthode non implémentée !")` doivent être complétées selon les spécifications (en respectant leurs signatures). Également, vous devez implémenter toutes les méthodes contenant un commentaire _"À FAIRE"_ N'hésitez pas à ajouter des classes, ou des attributs et méthodes aux classes existantes, lorsque cela vous semble nécessaire. La modification du corps des méthodes qui vous sont fournies est possible à condition de ne pas modifier le fonctionnement général de ces fonctions (décrit dans la spécification des méthodes).

> **Rappel :** pas de modification des signatures des méthodes/attributs qui vous sont fournis.

## Évaluation

L'évaluation du projet se fera à l'aide de tests unitaires automatisés. Un premier jeu de tests vous est fourni (comme d'habitude dans le répertoire `src/test/java`) pour que vous puissiez vérifier le bon fonctionnement des fonctionnalités de base. Puis, nous utiliserons un second jeu de tests (secret) pour l'évaluation finale.

Il est donc attendu que les projets rendus passent le premier jeu de tests sans erreurs, mais vous devez également vérifier par vous-mêmes (en écrivant d'autres tests unitaires) que le projet se comporte correctement dans les différents cas particuliers qui peuvent se produire, et qui ne sont pas nécessairement couverts par les tests qui vous ont été fournis.

**Remarque :** les classes de tests qui vous sont fournies contiennent dans leur nom le groupe nominal `ProfTest`. **Ces classes ne doivent pas être modifiées**. Ceci nous permettra, si besoin plus tard, d'ajouter de nouveaux tests dans vos dépôts GitLab. Par conséquent, pour écrire vos propres tests, vous créerez donc des classes de tests distinctes (vous pouvez vous inspirer du code fourni). Pour faire simple, ne faites aucune modifications dans les classes de tests fournies, et écrivez vos propres classes de tests en vous inspirant de celles-ci.

**Remarque importante** : puisque l'évaluation des rendus se fait par des tests automatisés, **les projets qui ne compilent pas ou qui ne respectent pas les signatures données seront automatiquement rejetés** et la note sera 0.
