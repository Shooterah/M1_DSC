###############################################
######## Travailler avec des variables ######## 
###############################################

######## AFFECTATION VARIABLE ######## 

x = 1

######## Numérique ######## 

x = 10.5 # Valeur décimal

x # Affiche la valeur de x

class(x) # Affiche la class de x

k = 1

k # Affiche la valeur de k

class(k) # Affiche la class de k

is.integer(k) # k est un integer ?

######## ENTIER ######## 

y = as.integer(3)

y # Affiche la valeur de y

class(y) # Affiche la valeur de y

is.integer(y) # y est integer ?

######## NOMBRE COMPLEXE ######## 

z = 1 + 2i # Création d'un nombre complex

z # Affiche la valeur de z

class(z) # Affiche la class de z

######## BOOLEAN ######## 

x = 1;
y = 2 # 2 valeurs

z = x > y # Test

z # Affiche la valeur de z

class(z) # Affiche la class de z

######## CARACTERE ######## 

x = as.character(3.14) # Caractère String

x # Affiche la valeur de x

class(x) # Affiche la class de x

fname = "Joe";
lname = "Smith"

paste(fname, lname) # Concatenation et affichage

######## VECTEURS ######## 

c(2, 3, 5) # Affichage d'un vecteur

n = c(2, 3, 5)

s = c("aa", "bb", "cc", "dd", "ee")

c(n, s) # Concatenation de 2 vecteur

s[2:4] # Affiche les valeurs du vecteur entre les indices 2 et 4

######## MATRICE ######## 

A = matrix(
  c(2, 4, 3, 1, 5, 7), # Les data (éléments)
  nrow = 2, # Nombre de lignes
  ncol = 3, # Nombre de colonnes
  byrow = TRUE # Rempli la matrice par ligne
)

A # Affiche la matrice

A[2, 3] # 2e ligne 3e colonne

A[2,] # 2e ligne

A[, 3] # 3e colonne

A[, c(1, 3)] # 1ere et 3e colonne

dimnames(A) = list(
  c("row1", "row2"), # Nom des lignes
  c("col1", "col2", "col3") # Nom des colonnes
)

A["row2", "col3"] # Element ligne 2 colonne 3

t(A) # Transposé de la matrice A

######## LISTE ######## 

n = c(2, 3, 5)
s = c("aa", "bb", "cc", "dd", "ee")
b = c(TRUE, FALSE, TRUE, FALSE, FALSE)

x = list(n, s, b, 3, A) # x contient la liste (copies) de n, s, b, 3 et A
x # Affiche x

######## DATA FRAME ######## 

n = c(2, 3, 5)
s = c("aa", "bb", "cc")
b = c(TRUE, FALSE, TRUE)

df = data.frame(n, s, b) # Data frame contenant comme colonne les donnée de n, s et b
df # Affiche df

######## FONCTIONS ######## 

c(1, 2, 3)

f = function(x, y) {
  c(x + 1, y + 1)
}

f(1, 2)


###############################################
########## OPÉRATIONS DE BASE DANS R ########## 
###############################################


1 + 2 + 3
1 + 2 * 3
(1 + 2) * 3
17 + 2
2 ^ 10
3 == 4


###############################################
############ RÉPERTOIRE DE TRAVAIL ############ 
###############################################


getwd() # Retourne le dossier de travail actuel

setwd("C:\M1_DSC\Analyse_Donnée\TP") # Set le nouveau dossier de travail


######## L'espace de travail ########

ls(list) #  Pour voir quelles variables sont définies dans l’espace de travail


###############################################
######## EXTENSION AU MOYEN DE PACKAGES ####### 
###############################################


install.packages("nom du package") # Install un package


###############################################
############## ENTRÉES / SORTIES ############## 
###############################################


######## Une sorte de programme de type 'hello world' ######## 

h = "Hello"
yourname = readline("What is your name ?")
print(paste(h, yourname))

######## Importation et exportation des données ######## 

summary(df)


###############################################
#################### GRAPH #################### 
###############################################

x = seq(0, 10, length = 200)
y = dnorm(x, mean = 5, sd = 1)
plot(x, y, "l", lwd = 1, col = "red")


###############################################
################## EXERCICE ################### 
###############################################

library(readr)
housing <- read_csv("C:/M1_DSC/Analyse_Donnée/TP/housing.csv")
View(housing)
summary(housing)


