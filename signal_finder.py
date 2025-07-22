import matplotlib.pyplot as plt
import numpy as np
import sympy
import random

#x(t) = Asin(Cwt+a), 'w' being the angular frequency and 'a' being the phase shift
def main():
    while True:
        A, C, phaseAngle = menu()
        outA = varyA(int(A))
        outC = varyC(int(C))
        outAngle = varyAngle(int(phaseAngle))
        plotter(outA, outC, outAngle)
        x = input("Do you want to continue (press y if yes)? ")
        if x == 'y':
            continue
        else:
            break
        
def menu():
    print("x(t) = Asin(Cwt+a), 'w' being the angular frequency and 'a' being the phase shift")
    print("----------------------------------------------------------------")
    print("Vary A: you have 4 options")
    print("1. A>1")
    print("2. 0<A<1")
    print("3. A<0")
    print("4. your own value")
    A = input("")
    print("----------------------------------------------------------------")
    print("Vary C: you have 4 options")
    print("1. C>1")
    print("2. 0<C<1")
    print("3. C<0")
    print("4. your own value")
    C = input("")
    print("----------------------------------------------------------------")
    print("Vary a (phase shift): you have 3 options")
    print("1. a>0")
    print("2. a<0")
    print("3. your own value")
    phaseAngle = input("")
    print("----------------------------------------------------------------")
    print("The program will set the angular frequency as 2pie, with frequency being 1Hz")

    return (A, C, phaseAngle)

def varyA(choice):
    out = 0
    if choice == 1:
        out = random.uniform(1, 10)
    elif choice == 2:
        out = random.uniform(0, 1)
    elif choice == 3:
        out = random.uniform(-10, 0)   
    elif choice == 4:
        out = float(input("what is your desired value for A: "))
    return out 
def varyC(choice):
    out = 0
    if choice == 1:
        out = random.uniform(1, 10)
    elif choice == 2:
        out = random.uniform(0, 1)
    elif choice == 3:
        out = random.uniform(-10, 0)   
    elif choice == 4:
        out = float(input("what is your desired value for C: "))
    return out 
def varyAngle(choice):
    out = 0
    #I want to exclude 0 because then the plot will be flat
    if choice == 1:
        out = random.randint(1, 90)
    elif choice == 2:
        out = random.randint(-90, -1)
    elif choice == 3:
        out = float(input("what is your desired value for a: "))
    out = np.deg2rad(out)
    return out
def plotter(A, C, phase):
    w = 2*np.pi
    t = np.linspace(0, 2, 1000)
    #func = x(t) = Asin(Cwt + a)
    func = A * np.sin(C*w*t + phase)
    plt.plot(t, func)
    plt.xlim(left=0, right=2)
    plt.title(f'x(t) = {A:.2f}sin({C:.2f}wt + {np.rad2deg(phase):.2f})')
    plt.xlabel('Time')
    plt.ylabel('Amplitude')
    plt.grid(True)
    plt.show()
    
if __name__ == "__main__":
    main()