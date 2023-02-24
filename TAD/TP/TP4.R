## ALGEBRE LINEAIRE ##

x1 <- seq(-5, 5, length = 200)
l1 <- (3 * x1 - 6) / 4
l2 <- (-x1 - 3) / 2

plot(x1, l1, col = "blue", type = "l", lwd = 1, ylim = range(c(l1, l2)))
par(new = TRUE)
plot(x1, l2, col = "red", type = "l", lwd = 1, ylim = range(c(l1, l2)))

# On affiche la solution de l'itersection des deux droites sous forme de pointillé :
segments(-5, -1.5, 0, -1.5, col = "black", lty = 3)
segments(0, -5, 0, -1.5, col = "black", lty = 3)

# On vas représenter ces équation sous forme de matrice

# On créé la matrice A
A <- matrix(c(3, 1, -4, 2), ncol = 2)
print(A)
# On créer la matrice inversé de A en utilisant la fonction solve()
Ainv <- solve(A)
print(Ainv)
# On créé la matrice b
b <- matrix(c(6, -3), ncol = 1)
print(b)
# On résoud le système d'équation
x <- Ainv %*% b
print(x)