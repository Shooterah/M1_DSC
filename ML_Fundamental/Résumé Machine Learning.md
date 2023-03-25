
# Introduction

## Petit rappel

### Apprentissage supervisé : 
C'est comme apprendre à un robot à distinguer des chats et des chiens en lui montrant des photos de chats et de chiens avec des étiquettes. Le robot apprend à partir de ces exemples et peut ensuite dire si une nouvelle photo montre un chat ou un chien.

### Apprentissage non supervisé : 
C'est quand le robot doit trouver des motifs ou des groupes dans les données sans étiquettes. Imagine que tu donnes au robot plein de photos d'animaux sans dire quel animal est sur chaque photo. Le robot essaie de regrouper les photos d'animaux qui se ressemblent.

### Apprentissage par renforcement : 
Ici, le robot apprend en essayant différentes actions et en recevant des récompenses ou des punitions. Pense à un robot qui apprend à jouer à un jeu vidéo : il essaie différentes stratégies et celles qui lui permettent de gagner des points sont renforcées.

### Fonction de coût (ou d'erreur) : 
C'est une mesure qui dit au robot à quel point il se trompe dans ses prédictions. Le robot essaie de minimiser cette fonction pour améliorer ses performances.

### Gradient descendant : 
C'est une méthode que le robot utilise pour ajuster ses paramètres et réduire l'erreur. Imagine que le robot est en haut d'une colline et qu'il veut descendre au point le plus bas. Le gradient descendant est comme une boussole qui indique la direction à prendre pour y arriver.

### Overfiting et underfitting : 
Le surapprentissage, c'est quand le robot apprend trop bien les exemples et devient moins bon pour généraliser à de nouvelles situations. Le sous-apprentissage, c'est quand le robot n'apprend pas assez bien et fait beaucoup d'erreurs sur les nouvelles situations.

### Cross validation : 
C'est une méthode pour évaluer la performance du robot en utilisant différentes parties des exemples pour l'entraînement et le test. Ça aide à éviter le surapprentissage.

### Réseaux de neurones : 
Les réseaux de neurones sont des modèles inspirés du cerveau qui aident le robot à apprendre des fonctions complexes. Ils sont composés de neurones artificiels connectés les uns aux autres.

## Gradient descendant :

La descente de gradient est une méthode utilisée pour trouver le minimum d'une fonction de coût. Imagine que tu es au sommet d'une montagne et que tu veux descendre dans la vallée en suivant le chemin le plus rapide. La descente de gradient te permet de faire cela en te donnant la direction à prendre pour descendre rapidement.

### Termes a connaitre :

#### Fonction de coût (J) : 
C'est une mesure de l'erreur commise par le modèle de machine learning. Plus la valeur de la fonction de coût est faible, meilleure est la performance du modèle.

#### Paramètres (θ) : 
Ce sont les valeurs que le modèle utilise pour faire ses prédictions. En ajustant les paramètres, on peut améliorer la performance du modèle.

#### Taux d'apprentissage (α) : 
C'est un nombre qui détermine la taille des pas que le modèle fait pour se déplacer vers le minimum de la fonction de coût. Un taux d'apprentissage trop grand peut faire que le modèle "saute" par-dessus le minimum, et un taux d'apprentissage trop petit peut prendre beaucoup de temps pour converger.

### Étapes pour calculer la descente de gradient :
**1. Initialiser les paramètres :** On commence par choisir des valeurs initiales pour les paramètres θ. On peut les initialiser à zéro ou à de petites valeurs aléatoires.

**2. Calculer le gradient :** Le gradient est un vecteur qui indique la direction dans laquelle la fonction de coût augmente le plus rapidement. Pour minimiser la fonction de coût, on veut aller dans la direction opposée. Pour trouver le gradient, on calcule la dérivée partielle de la fonction de coût par rapport à chaque paramètre θ. Cela nous donne un vecteur de gradients ∇J(θ).

**3. Choix d'un taux d'apprentissage :** On choisit un taux d'apprentissage α. C'est un nombre qui détermine la taille des pas que le modèle fait pour se déplacer vers le minimum de la fonction de coût. Un taux d'apprentissage trop grand peut faire que le modèle "saute" par-dessus le minimum, et un taux d'apprentissage trop petit peut prendre beaucoup de temps pour converger.

**4. Mettre à jour les paramètres :** On met à jour les paramètres en les déplaçant dans la direction opposée au gradient. On multiplie le gradient par le taux d'apprentissage α et on soustrait le résultat aux paramètres actuels. La formule de mise à jour est la suivante : θ = θ - α ∇J(θ).

**5. Répéter les étapes 2 et 3 :** On répète les étapes 2 et 3 jusqu'à ce que la fonction de coût atteigne un minimum ou que le nombre maximum d'itérations soit atteint.

### Exemple avec un modèle a 2 dimensions :

Considérons une fonction de coût J(θ1, θ2) définie comme suit :

J(θ1, θ2) = (θ1 - 1)^2 + (θ2 + 1)^2

Notre objectif est de trouver les valeurs de θ1 et θ2 qui minimisent cette fonction de coût.

#### 1. Initialisation des paramètres

Choisissons des valeurs initiales pour θ1 et θ2. Disons que θ1 = 3 et θ2 = -2.

#### 2. Calcul du gradient

Nous devons maintenant calculer les dérivées partielles de la fonction de coût par rapport à θ1 et θ2 pour obtenir les gradients :

- ∂J/∂θ1 = 2 * (θ1 - 1)
- ∂J/∂θ2 = 2 * (θ2 + 1)

À θ1 = 3 et θ2 = -2, les gradients sont : ∂J/∂θ1 = 4 et ∂J/∂θ2 = -4.

#### 3. Choix d'un taux d'apprentissage

Prenons un taux d'apprentissage α = 0.1.

#### 4. Mise à jour des paramètres

Maintenant, mettons à jour θ1 et θ2 en utilisant les formules de mise à jour :

θ1 = θ1 - α * ∂J/∂θ1
θ2 = θ2 - α * ∂J/∂θ2

θ1 = 3 - 0.1 * 4 = 2.6
θ2 = -2 - 0.1 * (-4) = -1.6

#### 5. Répétition des étapes 2 à 4

Répétons les étapes 2 à 4 pour quelques itérations supplémentaires :

- Itération 2 :
  - Gradients : ∂J/∂θ1 = 3.2 et ∂J/∂θ2 = -3.2
  - Mise à jour : θ1 = 2.6 - 0.1 * 3.2 = 2.26, θ2 = -1.6 - 0.1 * (-3.2) = -1.28

- Itération 3 :
  - Gradients : ∂J/∂θ1 = 2.52 et ∂J/∂θ2 = -2.52
  - Mise à jour : θ1 = 2.26 - 0.1 * 2.52 = 1.998, θ2 = -1.28 - 0.1 * (-2.52) = -1.028

Après quelques itérations, nous constatons que θ1 se rapproche de 1 et θ2 se rapproche de -1. Si nous continuons à répéter le processus, θ1 et θ2 convergeront vers 1 et -1, respectivement, qui sont les minimums de la fonction de coût J(θ1, θ2) = (θ1 - 1)^2 + (θ2 + 1)^2.

#### 6. Récapitulatif

Dans cet exemple de descente de gradient avec deux dimensions, nous avons utilisé la méthode pour minimiser la fonction de coût J(θ1, θ2) = (θ1 - 1)^2 + (θ2 + 1)^2. En calculant les gradients (les dérivées partielles) et en mettant à jour les paramètres θ1 et θ2 avec un taux d'apprentissage α, nous avons constaté que θ1 et θ2 convergent vers les valeurs minimales de 1 et -1, respectivement.

Cet exemple illustre le principe de la descente de gradient pour un problème bidimensionnel. Dans les problèmes de machine learning, la fonction de coût et les paramètres peuvent avoir de nombreuses dimensions, mais l'approche pour calculer les gradients et mettre à jour les paramètres reste similaire.


# K-nearest Neighbors :

L'algorithme des k-plus proches voisins est une méthode simple de classification qui fonctionne en trouvant les k exemples les plus proches d'un nouvel exemple inconnu et en attribuant la classe majoritaire parmi ces voisins.

### Explication :

Algorithme 1 : Classificateur k-NN

Entrées : 
- x : l'exemple inconnu à classifier
- S : l'ensemble des exemples d'apprentissage avec leurs classes associées
- d : une fonction de distance pour mesurer la proximité entre les exemples
- k : le nombre de voisins à considérer pour la classification

Sortie :
- la classe attribuée à l'exemple inconnu x

Début
1. Pour chaque (x0, y0) dans S, faire :
   1.1. Calculer la distance d(x0, x) entre l'exemple inconnu x et l'exemple d'apprentissage x0.

2. Trier les n distances par ordre croissant.

3. Compter le nombre d'occurrences de chaque classe yj parmi les k plus proches voisins de x.

4. Attribuer à x la classe la plus fréquente parmi les k plus proches voisins.

Fin

### Exemple :

Pour illustrer l'algorithme des k-plus proches voisins (k-NN) avec un exemple graphique, supposons que nous ayons un ensemble de points bidimensionnels (x1, x2) appartenant à deux classes différentes, que nous représenterons par des cercles rouges et des triangles bleus. Imaginons que le graphe ressemble à ceci :

```python
y
|
|
|       R         B
|    B       R
|       R         B
|    B       R
|       R         B
|
+-----------------x
```


Ici, R représente les cercles rouges (classe 1) et B représente les triangles bleus (classe 2).

#### 1-NN (1 plus proche voisin) :
La frontière de décision pour la règle du 1-NN est déterminée par le plus proche voisin de chaque point. Dans ce cas, la frontière de décision serait une ligne qui sépare les points rouges et les points bleus de manière à ce que chaque point soit plus proche de son voisin de la même classe que de son voisin de l'autre classe. La frontière de décision ressemblerait à une ligne en zigzag qui suit de près les points.

#### 3-NN (3 plus proches voisins) :
Avec la règle des 3-NN, la frontière de décision est déterminée en examinant les 3 plus proches voisins de chaque point. Dans ce cas, la frontière de décision sera plus lisse et moins sensible aux variations locales entre les points. La frontière pourrait ressembler à une ligne courbe qui sépare les points rouges et bleus.

#### Ce qui se passe lorsque k augmente et k = 14 :
Lorsque k augmente, la frontière de décision devient de plus en plus lisse et moins sensible aux variations locales entre les points. En revanche, elle pourrait être plus sensible au bruit global dans les données. Lorsque k = 14, la frontière de décision serait encore plus lisse qu'avec k = 3, mais si les données sont bruitées, il est possible que la frontière de décision soit moins précise.


En résumé, en modifiant la valeur de k, on peut contrôler la complexité de la frontière de décision et l'équilibre entre le surapprentissage (overfitting) et le sous-apprentissage (underfitting). Une petite valeur de k peut entraîner un surapprentissage en suivant de près les variations locales, tandis qu'une grande valeur de k peut entraîner un sous-apprentissage en lissant trop la frontière de décision.


### Curse of dimensionality :

La "malédiction de la dimensionnalité" (curse of dimensionality) est un problème qui affecte de nombreux algorithmes de machine learning, y compris l'algorithme des k-plus proches voisins (k-NN). Ce problème se produit lorsque le nombre de dimensions (caractéristiques) des données augmente. Il peut rendre l'analyse et la classification des données beaucoup plus difficiles pour les algorithmes basés sur la distance, comme k-NN.

Voici pourquoi la malédiction de la dimensionnalité affecte k-NN :

**Espaces vides :** Lorsque le nombre de dimensions augmente, le volume de l'espace des caractéristiques augmente exponentiellement, ce qui entraîne un grand nombre d'espaces vides. Autrement dit, les points de données deviennent très dispersés dans cet espace à dimensions élevées. Dans ce contexte, il est plus difficile pour k-NN de trouver des voisins significatifs pour un nouvel exemple, car la distance entre les points devient de plus en plus grande.

**Distances similaires :** Dans un espace à dimensions élevées, les distances entre les points deviennent souvent très similaires, ce qui rend difficile pour k-NN de distinguer les voisins pertinents des voisins moins pertinents. Cela peut entraîner une mauvaise classification des nouveaux exemples.

**Complexité computationnelle :** La malédiction de la dimensionnalité augmente également la complexité computationnelle de l'algorithme k-NN. Calculer les distances entre les points de données devient plus coûteux en termes de temps et de ressources à mesure que le nombre de dimensions augmente.

Pour atténuer les effets de la malédiction de la dimensionnalité sur l'algorithme k-NN, on peut prendre plusieurs mesures, telles que :

**Sélection des caractéristiques :** Réduire le nombre de caractéristiques en sélectionnant uniquement celles qui sont pertinentes pour le problème peut aider à minimiser les effets de la malédiction de la dimensionnalité.

**Augmentation de la taille de l'échantillon :** L'augmentation de la taille de l'échantillon d'apprentissage peut également aider à atténuer les effets de la malédiction de la dimensionnalité. Cependant, cela peut également augmenter la complexité computationnelle de l'algorithme k-NN.


### Data reduction techniques :

Les techniques de réduction de données sont des méthodes permettant de simplifier et de réduire la taille des ensembles de données, tout en conservant autant d'informations pertinentes que possible. L'algorithme que tu as fourni est une technique de réduction de données basée sur la règle du 1-NN (1 plus proche voisin) pour éliminer les exemples atypiques (outliers) et les exemples de la région d'erreur bayésienne.

#### Algorithme du cours :

Étape préliminaire : Supprime de S les outliers et les exemples de la région d'erreur bayésienne.

Entrées : S (ensemble de données)
Sortie : Scleaned (ensemble de données nettoyé)

Début
1. Sépare aléatoirement S en deux sous-ensembles S1 et S2.

2. Tant qu'il n'y a pas de stabilisation de S1 et S2, faire :
   2.1. Classifie S1 en utilisant S2 avec la règle du 1-NN.
   2.2. Supprime de S1 les instances mal classées.
   2.3. Classifie S2 en utilisant le nouveau S1 avec la règle du 1-NN.
   2.4. Supprime de S2 les instances mal classées.

3. Scleaned = S1 ∪ S2.
Fin

**Résultat :**

![Réduction de données](images/Data_Reduction.png)