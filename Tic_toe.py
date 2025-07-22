import csv
import random
from tabulate import tabulate
import re

#How to make the computer actually become challenging, tidy up check_win (optional), option for user to play as O
fieldnames=["1", "2", "3"]
def main():
    Condition = True
    csv1 = "some1.csv"
    default(csv1)
    print(tabulate(save(csv1), headers="keys", tablefmt="presto"))
    while Condition == True:
        Q = input("Where do you want to put the X? (Answer in 'row, column' form, from 1 to 3): ")
        if check_input(Q, csv1) == False:
            print("Try again!", end="\n")
            continue

        userAnswer(csv1, Q, "X")
        if check_win(csv1) == True:
            print(tabulate(save(csv1), headers="keys", tablefmt="presto"))
            Condition = False
            continue
        if check_tie(csv1) == True:
            print(tabulate(save(csv1), headers="keys", tablefmt="presto"))            
            Condition = False
            continue
        computer_(csv1)
        if check_win(csv1) == True:
            print(tabulate(save(csv1), headers="keys", tablefmt="presto"))
            Condition = False
            continue
        if check_tie(csv1) == True:
            print(tabulate(save(csv1), headers="keys", tablefmt="presto"))
            Condition = False
            continue


def default(file):
    with open(file, "w", newline="") as idk:
        o = csv.DictWriter(idk, fieldnames=fieldnames)
        o.writeheader()
        o.writerows([{"1":"1", "2":"1", "3":"1"}, 
                     {"1":"2", "2":"2", "3":"2"},
                     {"1":"3", "2":"3", "3":"3"}])

def save(file):
    tic_info = []
    with open(file, "r") as fil:
        x = csv.DictReader(fil)
        for row in x:
            tic_info.append(row)
    return tic_info


def userAnswer(file, cords, answer):
    #copies the csv file
    copy = save(file)
    #turns the cords into usable data
    a, b = cords.split(", ")
    a = int(a) - 1  
    b = int(b) - 1 
    copy[a][fieldnames[b]] =  answer
    with open(file, "w", newline="") as something:
        y = csv.DictWriter(something, fieldnames=fieldnames)
        y.writeheader()
        y.writerows(copy)
    return copy

def check_win(x):
    row = save(x)
    
    #diagonal right to left
    if row[0]["1"] == row[1]["2"] and row[0]["1"] == row[2]["3"] and row[0]["1"] == "X":
        print("X is the winner!!")
        return True
    elif row[0]["1"] == row[1]["2"] and row[0]["1"] == row[2]["3"] and row[0]["1"] == "O":
        print("O is the winner!!")
        return True
    
    #diagonal left to right
    elif row[2]["1"] == row[1]["2"] and row[2]["1"] == row[0]["3"] and row[2]["1"] == "X":
        print("X is the winner!!")
        return True
    elif row[2]["1"] == row[1]["2"] and row[2]["1"] == row[0]["3"] and row[2]["1"] == "O":
        print("O is the winner!!")
        return True   
    
    for up in range(3): 
        #Vertical
        if row[0][fieldnames[up]] == row[1][fieldnames[up]] and row[0][fieldnames[up]] == row[2][fieldnames[up]] and row[0][fieldnames[up]] == "X":
            print("X is the winner!!")
            return True
        if row[0][fieldnames[up]] == row[1][fieldnames[up]] and row[0][fieldnames[up]] == row[2][fieldnames[up]] and row[0][fieldnames[up]] == "O":
            print("O is the winner!!")
            return True
        #Horizontal
        if row[up]["1"] == row[up]["2"] and row[up]["1"] == row[up]["3"] and row[up]["1"] == "X":
            print("X is the winner!!")
            return True
        if row[up]["1"] == row[up]["2"] and row[up]["1"] == row[up]["3"] and row[up]["1"] == "O":
            print("O is the winner!!")
            return True
    return False

def check_tie(file):
    tie = []
    x = save(file)
    for i in range(len(x)):
        for j in range(len(fieldnames)):
            tie.append(x[i][fieldnames[j]])
    if all(item in ['X', 'O'] for item in tie):
        print("It is a tie!")
        return True
    return False

    

def computer_(file):
    y = save(file)
    while True:    
        a = random.randrange(1, 4)
        b = random.randrange(1, 4)
        if y[a-1][fieldnames[b-1]] == "O" or y[a-1][fieldnames[b-1]] == "X" :
            continue
        else:
            break
        
    cord = f"{a}, {b}"
    x = userAnswer(file, cord, "O")
    print(tabulate(x, headers="keys", tablefmt="presto"))

def check_input(Q, file):
    try:
        a, b = Q.split(", ")
        a = int(a)
        b = int(b)
    except Exception as e:
        return False
    y = save(file)
    if y[a-1][fieldnames[b-1]] == "X" or y[a-1][fieldnames[b-1]] == "O":
        return False
    if a>3 or b>3 or a<1 or b<1:
        return False
    return True

def board(file):
    x = save(file)
    print(tabulate(x, headers="keys", tablefmt="presto"))

def goodMoves():
    ...


main()