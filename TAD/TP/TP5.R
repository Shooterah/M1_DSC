###########################################
########### PROGRAMMING K-MEANS ###########
###########################################

###### K-MEANS ALGORITHM ######

# On load les données
data(iris)
# On récupère les données des colonnes 1 à 4
mydata <- iris[1:4]
# On récupère les classes qui sont dans la 5ème colonne
class <- as.matrix(iris[5])
# On récupère le nombre de lignes et de colonnes
n_c <- ncol(mydata)
n_r <- nrow(mydata)
# On récupère le nombre de dimensions
dim <- n_r

# On créer une fonction pour normaliser les données
normalize <- function(row){
    return((row - mean(row)) / sd(row))
}
# On normalise les données
mydata_zs <- apply(mydata, 2, normalize)

# On demande a l'utilisateur le nombre de clusters
k <- readline(prompt = "Enter the number of clusters: ")
# On affiche le nombre de clusters
print(paste("Number of clusters: ", k))
# On convertit le nombre de clusters en entier
k <- as.integer(k)

# On récupère des points aléatoires d'apres l'algo de k-means
random_points <- sample(1:dim, k, replace = FALSE)
# On créer une matrice pour stocker les position
means <- matrix(nrow = k, ncol = n_c)
# On récupère les positions des points aléatoires qui seront les position moyenne (centroids) # nolint
for (i in 1:k){
    for (j in 1:n_c) {
        means[i, j] <- mydata_zs[random_points[i], j]
    }
}

# On créer une matrice pour stocker les clusters
clusters <- matrix(nrow = n_r, ncol = 1, 0)
# On créer une matrice pour stocker les clusters précédents
prev_cluster <- matrix(nrow = n_r, ncol = 1, 0)
# On créer deux points pour calculer la distance, vecteur A et B
A <- matrix(nrow = n_c, ncol = 1, 0) # nolint
B <- matrix(nrow = n_c, ncol = 1, 0) # nolint
# Nombre de boucle avant convergence
nb_loops <- 0
# On créer une variable pour savoir si la convergence est en cours
convergence_in_progress <- TRUE

# On créer une fonction pour calculer la distance entre deux points (vecteurs)
d <- function(X, Y){
    distance <- 0
    for (z in 1:n_c) {
        distance <- distance + (X[z, 1] - Y[z, 1])^2
    }
    return(sqrt(distance))
}

# x va nous permettre de parcourir les lignes de la matrice des données normalisées #nolint
x <- 1:dim
# On plot les données normalisées avec en x le length et en y le width des pétales # nolint
plot(mydata_zs[x, 3], mydata_zs[x, 4], col = clusters[x, 1])
# Par vas nous permettre de plot plusieurs graphiques sur la même fenêtre
par(new = TRUE)
# On plot les points aléatoires qui seront les centroids
y <- 1:k
plot(means[y, 3], means[y, 4],
    col = "blue", xlab = "", ylab = "",
    xlim = range(mydata_zs[x, 3]), ylim = range(mydata_zs[x, 4]), pch = 15
)


# On créer une boucle tant que la convergence est en cours
while (convergence_in_progress) {
    # Pour tout individu de la matrice des données normalisées
    for (i in 1:dim) {
        # Pour toutes les variables de l'individu
        for (j in 1:n_c) {
            # On construit le vecteur A
            A[j, 1] <- mydata_zs[i, j]
        }
        # Pour tout les centroids
        for (j in 1:k) {
            # On construit le vecteur B
            for (z in 1:n_c) {
                B[z, 1] <- means[j, z]
            }
            # On calcule la distance entre le vecteur A et le vecteur B
            distance[j] <- d(A, B)
        }
        # Initialisation : la distance minimal vas être infinie et le nombre de cluster a 0 #nolint
        min_distance <- Inf
        cluster <- 0
        # Pour tout les centroids
        for (j in 1:k) {
            # Si la distance actuelle est plus petite que la distance minimal
            # Alors on change le nombre de cluster et la distance minimal
            if (distance[j] < min_distance) {
                cluster <- j
                min_distance <- distance[j]
            }
            # Le nombre de cluster trouvé vas être ajouté a la varibale clusters
            clusters[i, 1] <- cluster
        }
        # Pour vérifier si la convergence n'est pas achevé, on doit tester
        # Si les clusters courant et ceux obtenue précédement sont identique.
        # (Pour tout individu, si ils ne diffèrent pas d'un élément, alors ils sont identique)
        for (i in 1:k) {
            if (prev_cluster[i, 1] != clusters[i, 1]) {
                convergence_in_progress <- FALSE
            }
        }
    }
}

# x va nous permettre de parcourir les lignes de la matrice des données normalisées #nolint
x <- 1:dim
# On plot les données normalisées avec en x le length et en y le width des pétales # nolint
plot(mydata_zs[x, 3], mydata_zs[x, 4], col = clusters[x, 1])
# Par vas nous permettre de plot plusieurs graphiques sur la même fenêtre
par(new = TRUE)
# On plot les points aléatoires qui seront les centroids
y <- 1:k
plot(means[y, 3], means[y, 4],
    col = "blue", xlab = "", ylab = "",
    xlim = range(mydata_zs[x, 3]), ylim = range(mydata_zs[x, 4]), pch = 15
)
