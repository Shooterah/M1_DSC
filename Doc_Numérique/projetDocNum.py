import pdfplumber

with pdfplumber.open("C:/M1_DSC/UEP/CV.pdf") as pdf:
    page = pdf.pages[0]
    text = page.extract_text()

with pdfplumber.open("C:/Users/flori/Downloads/CV_Jeoffrey_PEREIRA.pdf") as pdf:
    page2 = pdf.pages[0]
    text2 = page2.extract_text()

listeCompétence = ["Java", "C", "SQL", "Vue.js", "C++", "C#", "JavaScript", "Python", ".NET", "Spring",
                   "SpringBoot", "JS", "Anglais", "Espagnol", "Arabe", "Chinois", "Allemand", "Italien", "PHP", "CSS", "HTML", "Web"]
listeCompétenceTmp = []

ListeNuméroType = ["06", "07", "09"]
ListeNuméroTypeTmp = []

print()
print()


def getDataCompetence(text):
    for row in text.split("\n"):
        for row2 in row.split(" "):
            for comp in listeCompétence:
                if row2.__contains__(comp):
                    if comp not in listeCompétenceTmp:
                        listeCompétenceTmp.append(comp)


def getDataTelNumber(text):
    chercheToutNum = 0
    nbNum = 0
    numero = ""
    num14 = 0
    for row in text.split("\n"):
        for row2 in row.split(" "):
            if chercheToutNum == 1 and nbNum < 4:
                numero = numero + row2
                nbNum = nbNum + 1
            for num in ListeNuméroType:
                if row2.startswith(num):
                    if len(row2) == 10:
                        ListeNuméroTypeTmp.append(row2)
                    elif len(row2) == 2:
                        chercheToutNum = 1
                        numero = numero + row2
                    elif len(row2) == 14:
                        numero = numero + row2[0] + row2[1] + row2[3] + row2[4] + \
                            row2[6] + row2[7] + row2[9] + \
                            row[10] + row[12] + row[13]
                        num14 = 1
    if chercheToutNum == 1 or num14 == 1:
        ListeNuméroTypeTmp.append(numero)


getDataCompetence(text)
getDataTelNumber(text)
print("Competence flo : ")
print(listeCompétenceTmp)
print(ListeNuméroTypeTmp)

listeCompétenceTmp = []
ListeNuméroTypeTmp = []

getDataCompetence(text2)
getDataTelNumber(text2)
print("Competence jeof : ")
print(listeCompétenceTmp)
print(ListeNuméroTypeTmp)
