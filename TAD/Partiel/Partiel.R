
############################################################
################ CORRECTION DU PARTIEL DE R ################
############################################################

#-----------------------------------------------------------------------------------------------------------------------


# Question 1 : Affichez le résumé des informations sur le jeu de données tourbillons. 
# Affichez un histogramme des valeurs pour chaque variable avec la fonction qplot du package ggplot2. 
# Testez avec différentes valeurs du paramètre binwidth si nécessaire pour plus de précision dans le graphique. 
# Est-ce que les variables semblent suivre une distribution normale ? Pourquoi ?

# Réponse :

# On load le dataset
tourbillon <- read.csv("C:/M1_DSC/TAD/Partiel/tourbillon.csv")
# Afficher le résumé du dataset
summary(tourbillon)

# Affichez un histogramme des valeurs pour chaque variable avec la fonction qplot du package ggplot2.

# On load le package ggplot2
library(ggplot2)
# On affiche l'histogramme des valeurs pour chaque variable avec la fonction qplot du package ggplot2
qplot(tourbillon$x, binwidth = 0.1)
qplot(tourbillon$y, binwidth = 0.1)
qplot(tourbillon$x, binwidth = 0.2)
qplot(tourbillon$y, binwidth = 0.2)
qplot(tourbillon$x, binwidth = 0.3)
qplot(tourbillon$y, binwidth = 0.3)
qplot(tourbillon$x, binwidth = 0.4)
qplot(tourbillon$y, binwidth = 0.4)

# Est-ce que les variables semblent suivre une distribution normale ? Pourquoi ?
# --> Oui car les variables sont centrées sur 0 et semble symétriques.


#-----------------------------------------------------------------------------------------------------------------------


# Question 2 : Réalisez une ACP sur votre jeu de données.
# Représentez vos données avec les deux (premières) composantes principales de l’ACP.
# En commentaire, indiquez quel est l’intérêt d’une représentation effectuée avec l’ACP en général ainsi que l’intérêt d’une ACP pour ce jeu de données en particulier.


# Réponse :

# Affiche l'ACP
pairs(tourbillon)

# Quel est l’intérêt d’une représentation effectuée avec l’ACP en général ?
# --> L'intérêt d'une représentation effectuée avec l'ACP en général est de pouvoir visualiser les données et de pouvoir les analyser.

# Quel est l’intérêt d’une ACP pour ce jeu de données en particulier ?
# --> L'intérêt d'une ACP pour ce jeu de données en particulier est de pouvoir visualiser la disposition des points en fonction de  x et y.


#-----------------------------------------------------------------------------------------------------------------------

# Question 3 : Affichez un graphique avec la variable x pour l’axe des abscisses et la variable y pour l’axe des ordonnées.
# À votre avis, est-il possible de réaliser un modèle de régression linéaire afin de prévoir la valeur de y en fonction de x?
# Si oui, donnez les paramètres a et b du modèle y = a.x + b. Si non, expliquez pourquoi en commentaire.


# Réponse :

# Affichez un graphique avec la variable x pour l’axe des abscisses et la variable y pour l’axe des ordonnées.
# On affiche le graphique avec la variable x pour l'axe des abscisses et la variable y pour l'axe des ordonnées
plot(tourbillon$x, tourbillon$y)

# À votre avis, est-il possible de réaliser un modèle de régression linéaire afin de prévoir la valeur de y en fonction de x?
# --> Non car les points ne sont pas alignés. De plus, plusieurs valeurs de y peuventcorrespondre à une même valeur de x.


#-----------------------------------------------------------------------------------------------------------------------


# Question 4 : Créez un nouveau jeu de données d’un millier de points de la façon suivante :
# - Soit a une variable entière allant de 1 à 1000
# - Pour chaque valeur de a, calculez les valeurs des variables suivantes :
#      - soit b une variable réelle égale à a/100
#      - soit x une variable réelle égale à b × sin(b)
#      - soit y une variable réelle égale à b × cos(b)
# Les valeurs de x et y seront stockées dans un nouveau dataframe que vous afficherez. 
# En vous inspirant des résultats obtenus, indiquez en commentaire comment le jeu de données tourbillon a pu être généré.



# Réponse :

# Soit a une variable entière allant de 1 à 1000
a <- 1:1000
# Pour chaque valeur de a, calculez les valeurs des variables suivantes :
#      - soit b une variable réelle égale à a/100
b <- a / 100
#      - soit x une variable réelle égale à b × sin(b)
x <- b * sin(b)
#      - soit y une variable réelle égale à b × cos(b)
y <- b * cos(b)
# Les valeurs de x et y seront stockées dans un nouveau dataframe que vous afficherez.
# On crée le dataframe tourbillon2 avec les variables x et y
tourbillon2 <- data.frame(x, y)
# On affiche le dataframe tourbillon2
tourbillon2

# En vous inspirant des résultats obtenus, indiquez en commentaire comment le jeu de données tourbillon a pu être généré.
# --> Le jeu de données tourbillon a pu être généré en utilisant ce procéssus 4 fois avec des valeurs différentes car il y a 4 spirales.


#-----------------------------------------------------------------------------------------------------------------------


# Question 5 : Réalisez des classifications avec la méthode des k-means en faisant varier le nombre de clusters de 2 à 10. 
# Pour cela, vous utiliserez la fonction kmeans avec les deux paramètres que sont le jeu de données (c’est-à-dire le dataframe tourbillon) et k (c’est-à-dire le nombre de clusters à obtenir). 
# Pour chaque classification, affichez dans une nouvelle fenêtre les résultats obtenus, c’est-à-dire en faisant un plot du jeu de données et en utilisant pour chaque point la couleur correspondant au numéro du cluster issu de la classification (c’est-à-dire la composante cluster issue de l’objet retourné par la fonction kmeans).
# Après observation graphique de ces résultats, quel est le meilleur résultat obtenu sur ces 9 classifications par la méthode des k-means ?
# Est-ce pour autant un résultat satisfaisant ? Indiquez en commentaire pour quelle(s) raison(s).


# Réponse :

# Réalisez des classifications avec la méthode des k-means en faisant varier le nombre de clusters de 2 à 10.
for (i in 2:10) {
  # On utilise la fonction kmeans avec les deux paramètres que sont le jeu de données (c’est-à-dire le dataframe tourbillon) et k (c’est-à-dire le nombre de clusters à obtenir)
  kmeans(tourbillon, i)
  # Pour chaque classification, affichez dans une nouvelle fenêtre les résultats obtenus, c’est-à-dire en faisant un plot du jeu de données et en utilisant pour chaque point la couleur correspondant au numéro du cluster issu de la classification (c’est-à-dire la composante cluster issue de l’objet retourné par la fonction kmeans)
  plot(tourbillon, col = kmeans(tourbillon, i)$cluster)
}

# Après observation graphique de ces résultats, quel est le meilleur résultat obtenu sur ces 9 classifications par la méthode des k-means ?
# --> Le meilleur résultat obtenu sur ces 9 classifications par la méthode des k-means est soit le 2 soit le 4.
# --> Le 2 car les spirales sont bien séparées en 2 groupes vers y = 0.
# --> Le 4 car les spirales sont bien séparées en 4 groupes vers y = 0 et x = 0. Une forme de croix est visible qui délimite les 4 groupes.

# Est-ce pour autant un résultat satisfaisant ? Indiquez en commentaire pour quelle(s) raison(s).
# --> Ouais car les spirales sont bien séparées en 2 ou 4 groupes vers y = 0 et x = 0. De plus, le rapport x et y est bien visible avec ces 2 ou 4 clusters.


#-----------------------------------------------------------------------------------------------------------------------


# Question 6 : Réalisez des classifications avec la méthode de la classification ascendante hiérarchique (CAH).
# Pour cela, vous utiliserez la fonction hclust qui prend en paramètres la matrice de distance et une méthode d’agglomération.
# Pour la matrice de distance, vous utiliserez la fonction dist appliquée au jeu de données tourbillon (sans préciser de paramètre sur la méthode de distance, c’est la distance euclidienne qui est employée par défaut).
# Pour la méthode d’agglomération, vous testerez les résultats obtenus au moyen des trois méthodes suivantes :
#    - agglomération avec le lien moyen (utilisation de la valeur moyenne des distances aux points issus du cluster, method="average"),
#    - agglomération avec saut minimal (ou méthode du lien simple, c’est-à-dire la distance au point le plus proche du cluster, method="single"),
#    - agglomération avec saut maximal (méthode des liens complets, c’est-à-dire la distance au point le plus éloigné du cluster, method="complete").
# Pour chacune des classifications hiérarchiques employant une méthode d’agglomération donnée, affichez dans une nouvelle fenêtre le dendrogramme associé au résultat de la classification.
# À l’aide de la fonction rect.hclust, affichez des rectangles autour du nombre de clusters que vous aurez choisi.
# En commentaire, indiquez pour quelle(s) raison(s) vous avez décidé de couper votre dendrogramme à tel ou tel endroit ou en tel ou tel nombre de clusters.
# Pour chacune de ces trois classifications, affichez dans une nouvelle fenêtre les résultats obtenus, c’est-à-dire en faisant un plot du jeu de données et en utilisant pour chaque point la couleur correspondant au numéro du cluster issu de la classification (c’est-à-dire la valeur de l’objet retourné par la fonction cutree appliquée sur l’objet retourné par hclust, en indiquant un nombre de clusters donné).
# Y a-t-il une méthode d’agglomération qui donne des meilleurs résultats de classification que les autres sur ce jeu de données particulier ?


# Réponse :

# On utilise la fonction hclust avec les deux paramètres que sont la matrice de distance (c’est-à-dire la fonction dist appliquée au jeu de données tourbillon) et la méthode d’agglomération (c’est-à-dire method="average")
cahMoyen = hclust(dist(tourbillon), method = "average")
cahMinimal = hclust(dist(tourbillon), method = "single")
cahMaximal = hclust(dist(tourbillon), method = "complete")
# Pour chacune des classifications hiérarchiques employant une méthode d’agglomération donnée, affichez dans une nouvelle fenêtre le dendrogramme associé au résultat de la classification
plot(cahMoyen)
plot(cahMinimal)
plot(cahMaximal)
# À l’aide de la fonction rect.hclust, affichez des rectangles autour du nombre de clusters que vous aurez choisi
rect.hclust(cahMoyen, k = 4, border = "red")
rect.hclust(cahMinimal, k = 4, border = "red")
rect.hclust(cahMaximal, k = 4, border = "red")
# En commentaire, indiquez pour quelle(s) raison(s) vous avez décidé de couper votre dendrogramme à tel ou tel endroit ou en tel ou tel nombre de clusters
# --> On a décidé de couper le dendrogramme à 4 car on voit bien 4 groupes de points.

# Pour chacune de ces trois classifications, affichez dans une nouvelle fenêtre les résultats obtenus, c’est-à-dire en faisant un plot du jeu de données et en utilisant pour chaque point la couleur correspondant au numéro du cluster issu de la classification (c’est-à-dire la valeur de l’objet retourné par la fonction cutree appliquée sur l’objet retourné par hclust, en indiquant un nombre de clusters donné)
plot(tourbillon, col = cutree(cahMoyen, 4))
plot(tourbillon, col = cutree(cahMinimal, 4))
plot(tourbillon, col = cutree(cahMaximal, 4))

# Y a-t-il une méthode d’agglomération qui donne des meilleurs résultats de classification que les autres sur ce jeu de données particulier ?
# --> Oui, la méthode d’agglomération avec le lien minimal donne des meilleurs résultats de classification que les autres sur ce jeu de données particulier car on voit bien 4 groupes de points qui correspondent aux 4 spirales.


#-----------------------------------------------------------------------------------------------------------------------


# Question 7 : Réalisez des classifications avec la méthode DBSCAN, c’est-à-dire Density-Based Spatial Clustering of Applications with Noise (regroupement spatial basé sur la densité pour des applications présentant des données bruitées).
# Pour cela, installez le package dbscan et chargez-le.
# La méthode DBSCAN se lance avec la fonction dbscan du package du même nom avec 3 paramètres :
# - x = le nom du jeu de données sur lequel appliquer la méthode (ici, le dataframe tourbillon),
# - eps, la taille (rayon) du voisinage epsilon (ici, vous ferons varier epsilon de 0.1 à 0.9)
# - minPts, le nombre de points minimum requis dans le voisinage eps pour constituer des clusters (et ne pas considérer qu’il s’agit de bruit) autour des points centraux (ici, nous garderons la valeur du paramètre par défaut qui est de 5 points).
# Pour chacune des 9 classifications obtenues en faisant varier epsilon de 0.1 à 0.9 (avec un pas de 0.1), affichez dans une nouvelle fenêtre les résultats obtenus, c’est-à-dire en faisant un plot du jeu de données et en utilisant pour chaque point la couleur correspondant au numéro du cluster issu de la classification (c’est-à-dire la valeur de l’objet retourné par la fonction dbscan appliquée sur le jeu de données).
# Y a-t-il des valeurs pour epsilon qui donnent des meilleurs résultats de classification que d’autres sur ce jeu de données ? 
# Si oui, indiquez en commentaires pour quelles valeurs et essayez de comprendre pourquoi.


# Réponse :

# On installe le package dbscan et on le charge
install.packages("dbscan")
library(dbscan)

# Pour chacune des 9 classifications obtenues en faisant varier epsilon de 0.1 à 0.9 (avec un pas de 0.1), affichez dans une nouvelle fenêtre les résultats obtenus, c’est-à-dire en faisant un plot du jeu de données et en utilisant pour chaque point la couleur correspondant au numéro du cluster issu de la classification (c’est-à-dire la valeur de l’objet retourné par la fonction dbscan appliquée sur le jeu de données)
for (i in seq(0.1, 0.9, 0.1)) {
  plot(tourbillon, col = dbscan(x = tourbillon, eps = i, minPts = 5)$cluster)
}

# Y a-t-il des valeurs pour epsilon qui donnent des meilleurs résultats de classification que d’autres sur ce jeu de données ?
# --> Oui, la valeur 0.4, 0.5 et 0.6 donne des meilleurs résultats de classification que d’autres sur ce jeu de données car on voit bien 4 groupes de points qui correspondent aux 4 spirales.


#-----------------------------------------------------------------------------------------------------------------------

# Question 8 : Installez le package cluster et chargez-le. Installez le package clusterSim et chargez-le également.
# Sélectionnez quelques classifications obtenues lors des trois questions précédentes, avec différents paramètres (différentes valeurs de k pour la méthode des k-means, différentes méthodes d’agglomération en CAH, différentes valeurs de epsilon pour DBSCAN) et affichez les résultats obtenus pour différentes mesures de qualité d’un partitionnement :
# - le coefficient de silhouette, qui est un indice moyen calculé, pour chaque point, à partir de la différence entre la distance moyenne avec les points du même groupe que lui (cohésion) et la distance moyenne avec les points des autres groupes voisins (séparation). Si cette différence est négative, le point est en moyenne plus proche du groupe voisin que du sien : il est donc mal classé. À l’inverse, si cette différence est positive, le point est en moyenne plus proche de son groupe que du groupe voisin : il est donc bien classé. Le coefficient de silhouette proprement dit est la moyenne du coefficient de silhouette pour tous les points;
# - l’indice de Davies-Bouldin, qui est une mesure de qualité d’une partition d’un ensemble de données, introduite par David L. Davies et Donald W. Bouldin en 1979, et qui se calcule à partir de la moyenne du rapport maximal entre la distance d’un point au centre de son groupe et la distance entre deux centres de groupes. La fonction silhouette du package cluster prend en paramètre le vecteur des numéros du cluster auquel appartient le point à l’issue de la classification ainsi que la matrice de distance du jeu de données. Le coefficient silhouette est la moyenne (mean) des valeurs de silhouette des points, c’est-à-dire la 3ème composante de l’objet retourné par la fonction silhouette. Le coefficient de silhouette varie entre -1 (pire classification) et 1 (meilleure classification).
# La fonction index.DB du package clusterSim prend en paramètre le jeu de données et le vecteur des numéros du cluster auquel appartient le point à l’issue de la classification. L’indice de Davies-Bouldin varie entre 0 (meilleure classification) et +∞ (pire classification).
# Indiquez en commentaire les résultats obtenus et si ces indices se prêtent bien à une aide à l’interprétation des résultats de classification sur un jeu de données tel que tourbillon.


# Réponse :

# On installe le package cluster et on le charge
install.packages("cluster")
library(cluster)

# On installe le package clusterSim et on le charge
install.packages("clusterSim")
library(clusterSim)

# On sélectionne quelques classifications obtenues lors des trois questions précédentes, avec différents paramètres (différentes valeurs de k pour la méthode des k-means, différentes méthodes d’agglomération en CAH, différentes valeurs de epsilon pour DBSCAN)
# - k-means avec k = 4
# - CAH avec le lien minimal
# - DBSCAN avec epsilon = 0.4, 0.5 et 0.6

# On affiche les résultats obtenus pour différentes mesures de qualité d’un partitionnement :
# - le coefficient de silhouette
# - l’indice de Davies-Bouldin

# Pour la méthode des k-means avec k = 4
silhouette(tourbillon, dbscan(x = tourbillon, eps = 0.4, minPts = 5)$cluster)$silhouette
index.DB(tourbillon, dbscan(x = tourbillon, eps = 0.4, minPts = 5)$cluster)

# Pour la méthode CAH avec le lien minimal
silhouette(tourbillon, dbscan(x = tourbillon, eps = 0.5, minPts = 5)$cluster)$silhouette
index.DB(tourbillon, dbscan(x = tourbillon, eps = 0.5, minPts = 5)$cluster)

# Pour la méthode DBSCAN avec epsilon = 0.4, 0.5 et 0.6
silhouette(tourbillon, dbscan(x = tourbillon, eps = 0.6, minPts = 5)$cluster)$silhouette
index.DB(tourbillon, dbscan(x = tourbillon, eps = 0.6, minPts = 5)$cluster)

# Indiquez en commentaire les résultats obtenus et si ces indices se prêtent bien à une aide à l’interprétation des résultats de classification sur un jeu de données tel que tourbillon.
# --> Les résultats obtenus sont :
# - pour la méthode des k-means avec k = 4 : silhouette = ?? et indice de Davies-Bouldin = ??
# - pour la méthode CAH avec le lien minimal : silhouette = ?? et indice de Davies-Bouldin = ??
# - pour la méthode DBSCAN avec epsilon = 0.4, 0.5 et 0.6 : silhouette = ?? et indice de Davies-Bouldin = ??
# --> 
#-----------------------------------------------------------------------------------------------------------------------