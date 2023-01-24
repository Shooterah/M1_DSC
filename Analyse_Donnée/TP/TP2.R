
########## FONCTIONS ##########

f = function() {
  x = 1
  y = 2
  print(x + y)
}

f()

########## CONDITIONS ##########

if (1 < 2) {
  print("OUI")
} else {
  print("NON")
}

########## BOUCLES ##########

repeat {
  print("Je répète a l'infinie")
}

i = 5

repeat {
  if (i > 25) break
  else {
    print(i)
    i = i + 5
  }
}


i = 5

while (i <= 25) {
  print(i)
  i = i + 5
}


for (i in seq(from = 5, to = 25, by = 5)) {
  print(i)
}


########## GRAPH NUAGE DE POINTS ##########

data(mtcars)
summary(mtcars)

library(ggplot2)

qplot(mtcars$wt, mtcars$mpg)
qplot(wt, mpg, data = mtcars)

########## GRAPH LINEAIRE ##########

data(pressure)
summary(pressure)

plot(pressure$temperature, pressure$pressure, type = "l") # l comme linéaire
points(pressure$temperature, pressure$pressure)
lines(pressure$temperature, pressure$pressure / 2, col = "red")
points(pressure$temperature, pressure$pressure / 2, col = "red")

qplot(pressure$temperature, pressure$pressure, geom = "line")
qplot(temperature, pressure, data = pressure, geom = c("line", "point"))

########## GRAPH BARRES ##########

data(BOD)
summary(BOD)

barplot(BOD$demand, names.arg = BOD$Time)

table(mtcars$cyl)

barplot(table(mtcars$cyl))

qplot(BOD$Time, BOD$demand) + geom_bar(stat = "identity")

########## HISTOGRAMME ##########

hist(mtcars$mpg)

hist(mtcars$mpg, breaks = 10)

qplot(mpg, data = mtcars, binwidth = 4)

########## BOITE A MOUSTACHE ##########

data(ToothGrowth)
summary(ToothGrowth)

plot(ToothGrowth$supp, ToothGrowth$len)
boxplot(len ~ supp, data = ToothGrowth)
boxplot(len ~ supp + dose, data = ToothGrowth)
qplot(ToothGrowth$supp, ToothGrowth$len, geom = "boxplot")
qplot(interaction(ToothGrowth$supp, ToothGrowth$dose), ToothGrowth$len, geom = "boxplot")

########## COURBES ##########

curve(x ^ 3 - 5 * x, from = -4, to = 4)

myfunc = function(xvar) {
  1 / (1 + exp(-xvar + 10))
}

curve(myfunc(x), from = 0, to = 20)
# Add a line :
curve(1 - myfunc(x), add = TRUE, col = "red")

f = ggplot(data.frame(x = c(0, 20)), aes(x))

myfunc = function(xvar) {
  1 / (1 + exp(-xvar + 10))
}

myfunc2 = function(xvar) {
  1 - (1 / (1 + exp(-xvar + 10)))
}

f + stat_function(fun = myfunc, colour = "red") + stat_function(fun = myfunc2, colour = "blue")

########## REPRÉSENTATION PAR PAIRES ##########

data(iris)
summary(iris)

pairs(iris[1:4])
pairs(iris[1:4], pch = 21, bg = c("red", "green3", "blue")[unclass(iris$Species)])

########## TITRES DE GRAPHIQUES ##########

myfunc = function(xvar) {
  1 / (1 + exp(-xvar + 10))
}

curve(myfunc(x),
      xlab = "activation",
      ylab = "treshold function",
      from = 0, to = 20)

########## EXERCICE ##########

