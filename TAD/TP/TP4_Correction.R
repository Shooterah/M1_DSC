#############################################################
# Analyse et traitement des données
# TP n°4 :  Algèbre linéaire, ACP et régression linéaire
#############################################################

####################
# Algèbre linéaire #
####################

x1 <- seq(-5, 5, length=200)
l1 <- (3*x1 - 6) / 4
l2 <- (-x1 - 3) / 2

plot(x1, l1, col="blue", type="l",lwd=1, ylim=range(c(l1,l2)))
par(new=TRUE)
plot(x1, l2, col="red", type="l",lwd=1, ylim=range(c(l1,l2)))

segments(-5,-1.5,0,-1.5, col="black",lty=3)
segments(0,-5,0,-1.5, col="black", lty=3)

A <- matrix(c(3, 1, -4, 2), ncol=2) ; print(A)
Ainv <- solve(A) ; print(Ainv)
b <- matrix(c(6, -3), ncol=1) ; print(b)
x <- Ainv %*% b ; print(x)

# Exercice et solution
A <- matrix(c(3, 1, 2, -3), ncol=2) ; print(A)
Ainv <- solve(A) ; print(Ainv)
b <- matrix (c(7, -5) , ncol=1) ; print(b)
x <- Ainv %*% b ; print (x)

# Vérification
x1 <- 1
x2 <- 2
3*x1 + 2*x2
x1 - 3*x2


#######
# ACP #
#######

library(readr)
planets <- as.data.frame(read_csv(file="~/R/data/planets.csv", 
                                  col_names = TRUE))
planets 
summary(planets)
names <- planets[,1] 
names
rownames(planets) <- names
planets 
planets[,1] <- NULL 
planets


pairs(planets)

planets.log <- log(planets) 
colnames(planets.log) <- paste("log(",colnames(planets),")", sep="") 
pairs(planets.log)

win.graph(800,600,10)
plot(x = planets.log[,1], y= planets.log[,3], cex=(round(planets.log[,2])),
     xlab = colnames(planets.log[1]), ylab = colnames(planets.log[3]))

text(x=planets.log[,1], y=planets.log[,3], labels=names, cex = 0.8, col = "blue")

library(rgl) 
plot3d(x=planets.log[,1],
       y=planets.log[,2],
       z=planets.log[,3],
       xlab=colnames(planets.log[1]),
       ylab=colnames(planets.log[2]),
       zlab=colnames(planets.log[3]))
text3d(x=planets.log[,1],
       y=planets.log[,2],
       z=planets.log[,3],
       text=names,
       cex=0.8,
       col="blue")


planets.pca <- princomp(planets.log) 
planets.pca 
win.graph(800, 600, 10)
biplot(planets.pca)



#######################
# Régression linéaire #
#######################

euc <- read.table("~/R/data/eucalyptus.txt", header=T)
summary(euc)
plot(ht~circ,data=euc)

regeuc <- lm(ht~circ,data=euc)
summary(regeuc)
regeuc

attributes(regeuc)
coef(regeuc)

abline(regeuc,col="red")
circ=seq(min(euc[,"circ"]),max(euc[,"circ"]),length=100)
grid<-data.frame(circ)
CIpred<-predict(regeuc,new=grid,interval="pred",level=0.95)
matlines(grid$circ,cbind(CIpred),lty=c(1,2,2),col=2)

multreg<-lm(ht~circ+I(sqrt(circ)),data=euc)
summary(multreg)
plot(ht~circ,data=euc)
circ=seq(min(euc[,"circ"]),max(euc[,"circ"]),length=100)
grid2<-data.frame(circ)
CIpred2<-predict(multreg,new=grid2,interval="pred",level=0.95)
matlines(grid2$circ,cbind(CIpred2),lty=c(1,2,2),col=2)

win.graph(800,600,10)
plot(regeuc)
plot(multreg)
