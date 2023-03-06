# On load le dataset iris
data(iris)
mydata <- iris[1:4]
class <- as.matrix(iris[5])

# On créer un kmeans avec 3 clusters
kmeans.result <- kmeans(mydata, 3)
print(summary(kmeans.result))
# On affiche les clusters
print(table(class, kmeans.result$cluster))

# On affiche les pourcentages de variance expliquée
w <- (kmeans.result$tot.withinss / kmeans.result$totss) * 100
b <- (kmeans.result$betweenss / kmeans.result$totss) * 100
print(paste("Within cluster sum of squares:", round(w, 2), "%"))
print(paste("Between cluster sum of squares:", round(b, 2), "%"))
print(table(class, kmeans.result$cluster))


hc <- hclust(dist(mydata), "ave")
print(summary(hc))

win.graph(800, 600, 10)
plot(hc, hang = -1, labels = class)


idx <- sample(1:dim(iris)[1], 40)
irisSample <- iris[idx, ]
irisSample$Species <- NULL
hc <- hclust(dist(irisSample), method = "ave")
win.graph(800, 600, 10)
plot(hc, hang = -1, labels = iris$Species[idx])

rect.hclust(hc, k = 3)
groups <- cutree(hc, k = 3)


rm(list = ls())
mydata <- read.table("~/R/data/ruspini.txt", quote = "\"")

print(mydata)