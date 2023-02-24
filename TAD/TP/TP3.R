#################### 1 - PROBABILITÉ #################### 

# On importe les librairies nécessaires à l'exécution du script
require(stats)

# On représente la fréquence de tirage de 0 et de 1 pour 10 tirages
for (i in 1:10) {
    x <- round(runif(1))
    print(x)
}

# On représente la fréquence de tirage de "face" (0) sur 1000 tirages et 
# on affiche la probabilité théorique sous forme de ligne rouge
proba <- as.vector(1:1000)
sum <- 0
for (i in 1:1000) {
    x <- round(runif(1))
    sum <- sum + x
    proba[i] <- sum / i
}
plot(1:1000, proba[1:1000], "l", xlim = c(1, 1000), ylim = c(0, 1))
segments(0, 0.5, 1000, 0.5, col = "red")

# On représente la fréquence de tirage de "face" (0) sur 10000 tirages et 
# on affiche la probabilité théorique sous forme de ligne rouge
proba <- as.vector(1:10000)
sum <- 0
for (i in 1:10000) {
    x <- round(runif(1))
    sum <- sum + x
    proba[i] <- sum / i
}
plot(1:10000, proba[1:10000], "l", xlim = c(1, 10000), ylim = c(0, 1))
segments(0, 0.5, 10000, 0.5, col = "red")

# Dans les dux cas (1000 et 10000 tirages), la fréquence de tirage tend vers 0.5 (la probabilité théorique)

#################### 2 - VARIABLES ALÉATOIRES ET DISTRIBUTION DE PROBABILITÉ ####################

### Illustration avec un dé à 6 faces ###

# max = 6 car 6 faces
max <- 6
# Nbre d'événements possibles
nb_events <- max
# On crée un vecteur de 6 éléments
proba <- as.vector(1:max)
# On initialise la probabilité de chaque événement à 1 / nb_events
for (i in 1:max) {
    proba[i] <- 1 / nb_events
}

# On vas représenter en graphique a barres la proba d'avoir chaque valeur d'un dé (1 à 6)

require(ggplot2)
qplot(factor(1:max), proba[1:max], xlab = "Valeur obtenue avec un seul dé", ylab = "Probabilité") + geom_bar(stat = "identity")

### Illustration avec deux dé à 6 faces ###

max_dice <- 6
score_max <- max_dice * 2
nb_events <- max_dice^2

proba <- rep.int(0, score_max)
proba <- as.vector(proba)

for (i in 1:max_dice) {
    for (j in 1:max_dice) {
        proba[i + j] <- proba[i + j] + 1 / nb_events
    }
}

qplot(factor(1:score_max), proba[1:score_max], xlab = "Valeur obtenue avec deux dés", ylab = "Probabilité") + geom_bar(stat = "identity")

## Calcule de l'espérance d'une variable aléatoire ##

expec <- 0
# On calcule l'espérance en multipliant la probabilité de chaque événement par sa valeur
for (i in 1:score_max) {
    expec <- expec + proba[i] * i
}
print(expec)

## Calcule de la variance d'une variable aléatoire ##

variance <- 0
# On calcule la variance en multipliant la probabilité de chaque événement par la différence entre sa valeur et l'espérance au carré
for (i in 1:score_max) {
    variance <- variance + (expec - i)^2 * proba[i]
}
print(variance)
# On calcule l'écart-type en prenant la racine carrée de la variance
st_deviation <- sqrt(variance)
print(st_deviation)

# Il éxiste une autre méthode pour calculer la variance  : V(X) = E(X^2) - E(X)^2

expec2 <- 0
# On calcule l'espérance en multipliant la probabilité de chaque événement par sa valeur au carré
for (i in 1:score_max) {
    expec2 <- expec2 + proba[i] * i^2
}
# On calcule la variance en soustrayant l'espérance au carré à l'espérance au carré
variance2 <- expec2 - expec^2
print(variance2)

# Le plus souvent le dénominateur n - 1 est utilisé au lieu de n pour donner une estimation non biaisée 
# de la variance d'observation de variable indépendante et identiquement distribuée

# On calcule la variance non biaisée
u_variance <- variance * (nb_events / (nb_events - 1))


## Fonctions statistique de R ##

# mean() : calcule la moyenne
# var() : calcule la variance
# sd() : calcule l'écart-type
# hist() : représente un histogramme
# sum() : calcule la somme

# On calcule la moyenne, la variance et l'écart-type de la somme de deux dés
sum <- rep.int(0, nb_events)
sum <- as.vector(sum)
event <- 0

for (i in 1:max_dice) {
    for (j in 1:max_dice) {
        event <- event + 1
        sum[event] <- i + j
    }
}

hist(sum, breaks = c(1:12), col = "blue1")
mean(sum)
var(sum)
sd(sum)

### Distribution de probabilité pour des variables aléatoires continues ###

# On charge le dataset iris
data(iris)
# On affiche le résumé du dataset
summary(iris)

# pairs() : représente les relations entre les variables d'un dataset
# cor() : calcule la corrélation entre les variables d'un dataset

# Ici on affiche les relations entre les variables du dataset iris
pairs(iris[-5], bg = iris$Species, pch = 21)
cor(iris[-5])

# install.packages("PerformanceAnalytics")

library(PerformanceAnalytics)
# Correlation() : représente la corrélation entre les variables d'un dataset
chart.Correlation(iris[-5], bg = iris$Species, pch = 21)

# Le diagramme a plusieurs informations :
# - En diagonale, on a la distribution unimodale de chaque variable,
#   affichée sous forme d'histogramme et des graphiques de densité à noyau
# - A la droite de la diagonale, il y a les valeurs des corrélation par paire entre les variables, 
#   avec le niveau de corrélation représenté par un nombre d'étoile rouge
# - Plus le coefficient de corrélation est élevé, plus grande est la taille de la police
# - A gauche de la diagonale, il y a la matrice de nuage de points, avec une ligne rouge destiné a 
#   faciliter l’interprétation de la manière dont les deux variables représentées sont reliées entre elles.

## LOI NORMAL ##

