#############################################################
# Analyse et traitement des données
# TP n°3 :  Probabilités et statistiques
#############################################################

# Probabilités
# Approche de la probabilité par la fréquence relative
# Pile ou face ?

require(stats)

# 10 valeurs aléatoires

for (i in 1:10)
{
  x <- round(runif(1))
  print(x)
}

# Que des "1" ou que des "0", c'est rare... mais c'est possible
# En général, il y a le plus souvent à peu près autant de "0" que de "1",
# mais sur 10 valeurs seulement, ce n'est pas significatif...


# Fréquence relative des "faces"
# en tant que fonction du nombre de lancers de pièce
# (avec 1000 lancers de pièce)

proba <- as.vector(1:1000)

sum <- 0
for (i in 1:1000)
{
  x <- round(runif(1))
  sum <- sum + x
  proba[i] <- sum / i
}

plot(1:1000,proba[1:1000],"l", xlim=c(1,1000), ylim=c(0,1))
segments(0,0.5,1000,0.5, col="red")

# Avec 10 000 lancers...

proba <- as.vector(1:10000)

sum <- 0
for (i in 1:10000)
{
  x <- round(runif(1))
  sum <- sum + x
  proba[i] <- sum / i
}

plot(1:10000,proba[1:10000],"l", xlim=c(1,10000), ylim=c(0,1))
segments(0,0.5,10000,0.5, col="red")

# Variables aléatoires et distributions de probabilité
# Distribution de probabilité pour des variables aléatoires discrètes

# Avec un dé

max <- 6
nb_events <- max
proba <- as.vector(1:max)
for (i in 1:max)
{
  proba[i] <- 1/nb_events
}

require(ggplot2)

qplot(factor(1:max),proba[1:max], 
      xlab="Valeur obtenue avec un seul dé",
      ylab="Probabilité") + geom_bar(stat="identity")

# Avec deux dés

max_dice <- 6
score_max <- max_dice * 2
nb_events <- max_dice ^ 2
proba <-  rep.int(0,score_max)
proba <- as.vector(proba)

for (i in 1:max_dice)
{
  for (j in 1:max_dice)
    {
    proba[i+j] <- proba[i+j] + 1/nb_events
    }
}

qplot(factor(1:score_max),proba[1:score_max], 
      xlab="Valeur obtenue avec la somme de deux dés",
      ylab="Probabilité")  + geom_bar(stat="identity")



# Espérance

expec <- 0

for (i in 1:score_max)
{
    expec <- expec + proba[i] * i
}

print(expec)

# Variance

variance <- 0

for (i in 1:score_max)
{
  variance <- variance + (expec - i)^2 * proba[i]
}

print(variance)
st_deviation <- sqrt(variance)
print(st_deviation)

# Seconde manière de calculer la variance (variance bis)

expec_square <- 0

for (i in 1:score_max)
{
  expec_square <- expec_square + proba[i] * i^2
}

variance_bis <- expec_square - (expec^2)
print(variance_bis)

# Estimateur non biaisé de la variance (pour des observations i.i.d.)

u_variance <- variance * (nb_events / (nb_events - 1))



# Autre méthode

sum <-  rep.int(0,nb_events)
sum <- as.vector(sum)
event <- 0
for (i in 1:max_dice)
{
  for (j in 1:max_dice)
  {
    event <- event + 1
    sum[event] <- i+j
  }
}
hist(sum, breaks = c(1:12), col = "blue1")
mean(sum)
var(sum)
sd(sum)
var(sum)


#########################

revision_grade <- read.csv("~/R/data/revision_grade.csv", sep=";")

table(revision_grade)

cor(revision_grade)

#########################

data("iris")

pairs(iris[-5], bg=iris$Species, pch=21)

cor(iris[-5])


# install.packages("PerformanceAnalytics", lib="C:/Program Files/R/R-2.15.2/library")
library(PerformanceAnalytics)
chart.Correlation(iris[-5], bg=iris$Species, pch=21)


#######################
# Distibution normale #
#######################
x=seq(-4,4,length=200)
y=1/sqrt(2*pi)*exp(-x^2/2)
plot(x,y,type="l",lwd=5,col="blue")

x=seq(-4,4,length=200)
par(new = TRUE)
y=dnorm(x,mean=0,sd=1)
plot(x,y,type="p",lwd=1,col="yellow")



x <- seq(-10,100,.1)
normdensity1 <- dnorm(x,mean=10,sd=5)
normdensity2 <- dnorm(x,mean=40,sd=2.5)
normdensity3 <- dnorm(x,mean=70,sd=10)
plot(x,normdensity1,type="l",col="red", ylim=range(c(normdensity1,normdensity2,normdensity3)))
par(new = TRUE)
plot(x,normdensity2,type="l",col="green",ylim=range(c(normdensity1,normdensity2,normdensity3)), axes = FALSE, xlab = "", ylab = "")
par(new = TRUE)
plot(x,normdensity3,type="l",col="blue", ylim=range(c(normdensity1,normdensity2,normdensity3)), axes = FALSE, xlab = "", ylab = "")



pnorm(1, mean=0, sd=1)

y=dnorm(x)
plot(x,y,type="l", lwd=2, col="blue")
x=seq(-4,1,length=200)
y=dnorm(x)
polygon(c(-4,x,1),c(0,y,0),col="gray")


# avec un écart-type
x=seq(-4,4,length=200)
y=dnorm(x)
plot(x,y,type="l", lwd=2, col="blue")
x=seq(-1,1,length=100)
y=dnorm(x)
polygon(c(-1,x,1),c(0,y,0),col="gray")

pnorm(1,mean=0,sd=1)-pnorm(-1,mean=0,sd=1)
# 68%

# avec deux écarts-types (2 sd)

x=seq(-4,4,length=200)
y=dnorm(x)
plot(x,y,type="l", lwd=2, col="blue")
x=seq(-2,2,length=200)
y=dnorm(x)
polygon(c(-2,x,2),c(0,y,0),col="gray")

pnorm(2,mean=0,sd=1)-pnorm(-2,mean=0,sd=1)
# 95%

######################################################################
# Petite astuce :
# - en considérant la moyenne + ou - 1 écart-type, il n'y a qu'un peu
#   moins d'un tiers de la population qui nous échappe (100% - 68%) ;
# - en considérant la moyenne + ou - 2 écarts-types, il n'y a qu'un peu
#   moins de 5% de la population qui nous échappe (100 - 95,7%) ;
# - en considérant la moyenne + ou - 1 écart-type, il n'y a qu'un peu
#   moins de 0,5% de la population qui nous échappe (100 - 99,7%).
######################################################################

# Loi normale avec une moyenne mu = 3500 grammes
# et un écart-type sigma = 600 grammes

# Quelle est la proportion de poids de naissance 
# compris entre 2900 and 4700 grammes ?
pnorm(4700,mean=3500,sd=600)-pnorm(2900,mean=3500,sd=600)
# 0.8185946

# quelle est la proportion de poids w n'apparaissant que dans 2,5% des cas ?
qnorm(1 - 2.5/100, mean = 3500, sd = 600)
# 4675.978


(4700-3500)/600

1 - 0.8413


z <- qnorm(2.5/100, mean = 3500, sd = 600, lower.tail = FALSE)
z


x=seq(-4,4,length=200)
y=dnorm(x)
plot(x,y,type="l", lwd=2, col="blue")
x=seq(-2,2,length=200)
y=dnorm(x)
polygon(c(-2,x,2),c(0,y,0),col="gray")


z <- qnorm(2.5/100, mean = 3500, sd = 600, lower.tail = FALSE)

x <- seq(0, 7000, length=200)
y <- dnorm(x, mean= 3500, sd = 600) # same as y <- dnorm((x - 3500) / 600)  
plot(x, y, type="l", lwd=2, col="gray")
z <- qnorm(2.5/100, mean = 3500, sd = 600, lower.tail = FALSE)
x <- seq(z, 7000, length=100)
y <- dnorm(x, mean= 3500, sd = 600)
polygon(c(z, x, 7000), c(0, y, 0), col="navy")
mtext("Quelle est la proportion de poids w n'apparaissant que dans 2,5% des cas?", side=1, adj=1, col="navy")

#####################################################################################
# Tests d'hypothèse
# Exemples repris de http://www.r-tutor.com/elementary-statistics/hypothesis-testing

# Exercice sur les ampoules (test unilatéral) :

xbar = 9900            # moyenne simple sur l'échantillon 
mu0 = 10000            # valeur de la moyenne
sigma = 120            # écart-type sur la population 
n = 30                 # taille de l'échantillon 
z = (xbar - mu0)/(sigma/sqrt(n)) 
z                      # statistique de test 

alpha = .05 
z.alpha = qnorm(1 - alpha) 
-z.alpha               # valeur critique 

# Exercice sur les manchots royaux (test bilatéral) :

xbar = 14.6            # moyenne simple sur l'échantillon 
mu0 = 15.4             # valeur de la moyenne
sigma = 2.5            # écart-type sur la population 
n = 35                 # taille de l'échantillon 
z = (xbar - mu0)/(sigma/sqrt(n)) 
z                      # statistique de test 

alpha = .05 
z.half.alpha = qnorm(1 - alpha/2) 
c(-z.half.alpha, z.half.alpha)  

#####################################################################################

scores <- read.table("~/R/data/scores.txt", quote="\"")

# sapply(scores,mean)

# sapply(scores,sd)

x <- seq(1:25)

plot(x,scores[x,1])

hist(scores[x,1])

mu <- mean(scores[,1])

sigma <- sd(scores[,1])

x2=seq(20,80,length=200)

par(new = TRUE)
y=dnorm(x2,mean=mu,sd=sigma)
plot(x2,y,type="l",lwd=1,col="red", xlab="", ylab="", yaxt="n")

# Combien d'étudiants estimez-vous pouvoir trouver avec une note
# supérieure ou égale à 55 ?

# Avec le modèle (avec la distribution de la loi normale)
round(25 * (pnorm(55,mean=mu,sd=sigma, lower.tail=FALSE)))
# Pour 55 => 8

# Combien y en a-t-il en réalité ?
nb_scores <- 0
for (i in 1:25)
{
  if (scores[i,1]>= 55) nb_scores <- nb_scores + 1
}

print(nb_scores)
# En réalité => 8 ! C'est la même chose !

# Combien d'étudiants estimez-vous pouvoir trouver avec une note
# supérieure ou égale à 60 ?

# Avec le modèle (avec la distribution de la loi normale)
round(25 * (pnorm(60,mean=mu,sd=sigma, lower.tail=FALSE)))
# Pour 60 => 4

# Combien y en a-t-il en réalité ?
nb_scores <- 0
for (i in 1:25)
{
  if (scores[i,1]>= 60) nb_scores <- nb_scores + 1
}

print(nb_scores)
# En réalité => 4 ! C'est la même chose à nouveau !

# Combien d'étudiants estimez-vous pouvoir trouver avec une note
# supérieur ou égale ? 65 ?

# Avec le modèle (avec la distribution de la loi normale)
round(25 * (pnorm(65,mean=mu,sd=sigma, lower.tail=FALSE)))
# Pour 65 => 2

# Combien y en a-t-il en réalité ?
nb_scores <- 0
for (i in 1:25)
{
  if (scores[i,1]>= 65) nb_scores <- nb_scores + 1
}

print(nb_scores)
# En réalité => 2 ! C'est la même chose encore !

# Combien d'étudiants estimez-vous pouvoir trouver avec une note
# supérieur ou égale à 70 ?

# Avec le modèle (avec la distribution de la loi normale)
round(25 * (pnorm(70,mean=mu,sd=sigma, lower.tail=FALSE)))
# Pour 70 => 1

# Combien y en a-t-il en r?alit? ?
nb_scores <- 0
for (i in 1:25)
{
  if (scores[i,1]>= 70) nb_scores <- nb_scores + 1
}

print(nb_scores)
# En réalité => 1 ! C'est encore la même chose !
# En conclusion : avec ce jeu de données, la loi normale est
# plutôt un bon modèle de distribution


