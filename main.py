import random

main = []

Adrian = open("gameLists/Adrian_games.txt")
Anthony = open("gameLists/Anthony_games.txt")
Brian = open("gameLists/Brian_games.txt")
Elliott = open("gameLists/Elliott_games.txt")
Evy = open("gameLists/Evy_games.txt")
Noah = open("gameLists/Noah_games.txt")
Thad = open("gameLists/Thad_games.txt")
Zack = open("gameLists/Zack_games.txt")

Adrian_games = Adrian.readlines()
Anthony_games = Anthony.readlines()
Brian_games = Brian.readlines()
Elliott_games = Elliott.readlines()
Evy_games = Evy.readlines()
Noah_games = Noah.readlines()
Thad_games = Thad.readlines()
Zack_games = Zack.readlines()

for i in range(len(Adrian_games)):
    Adrian_games[i] = Adrian_games[i][:-1]

for i in range(len(Anthony_games)):
    Anthony_games[i] = Anthony_games[i][:-1]

for i in range(len(Brian_games)):
    Brian_games[i] = Brian_games[i][:-1]

for i in range(len(Elliott_games)):
    Elliott_games[i] = Elliott_games[i][:-1]

for i in range(len(Evy_games)):
    Evy_games[i] = Evy_games[i][:-1]

for i in range(len(Noah_games)):
    Noah_games[i] = Noah_games[i][:-1]

for i in range(len(Thad_games)):
    Thad_games[i] = Thad_games[i][:-1]

for i in range(len(Zack_games)):
    Zack_games[i] = Zack_games[i][:-1]

for i in range(len(Anthony_games)):
    if Anthony_games[i] in Adrian_games:
        if Anthony_games[i] not in main:
            main.append(Anthony_games[i])

for i in range(len(Brian_games)):
    if Brian_games[i] in Adrian_games:
        if Brian_games[i] not in main:
            main.append(Brian_games[i])

for i in range(len(Elliott_games)):
    if Elliott_games[i] in Adrian_games:
        if Elliott_games[i] not in main:
            main.append(Elliott_games[i])

for i in range(len(Evy_games)):
    if Evy_games[i] in Adrian_games:
        if Evy_games[i] not in main:
            main.append(Evy_games[i])

for i in range(len(Noah_games)):
    if Noah_games[i] in Adrian_games:
        if Noah_games[i] not in main:
            main.append(Noah_games[i])

for i in range(len(Thad_games)):
    if Thad_games[i] in Adrian_games:
        if Thad_games[i] not in main:
            main.append(Thad_games[i])

for i in range(len(Zack_games)):
    if Zack_games[i] in Adrian_games:
        if Zack_games[i] not in main:
            main.append(Zack_games[i])

print(main)
# print(main[random.randint(0, len(main) - 1)])

Adrian.close()
Anthony.close()
Brian.close()
Elliott.close()
Evy.close()
Noah.close()
Thad.close()
Zack.close()

