import random
import tkinter as tk
from tkinter import ttk
import queue
from re import *
from sympy import *
import numpy as np
import matplotlib.pyplot as plt
import sqlite3 as sq
def main():
    app = RandMath()
    app.mainloop()
class RandMath(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Multi App")
        self.geometry("400x200")

        self.columnconfigure(0, weight=1)
        self.columnconfigure(1, weight=3)
        self.rowconfigure(0, weight=1)
        #so that we can access show_frames using page names rather than variables
        self.frames = {}

        #putting in 2 instances of the parent as argument 
        frame = MainPage(self, self)
        frame.grid(row=0, column=0, sticky="nsew")
        frame2 = RandEqPage(self, self)
        frame2.grid(row=0, column=0, sticky="nsew")
        frame3 = Calc(self, self)
        frame3.grid(row=0, column=0, sticky="nsew")
        frame4 = GraphingCalc(self, self)
        frame4.grid(row=0, column=0, sticky="nsew")
        self.frames[MainPage] = frame
        self.frames[RandEqPage] = frame2
        self.frames[Calc] = frame3
        self.frames[GraphingCalc] = frame4
        #shows mainpage first
        self.show_frame(MainPage)

    def show_frame(self, page):
        frame = self.frames[page]
        frame.tkraise()
class MainPage(ttk.Frame):
    def __init__(self, parent, controller):
        super().__init__(parent)

        self.columnconfigure((0,1,2,3), weight=3)
        self.rowconfigure((0,1), weight=3)

        label = ttk.Label(self, text="Main Page", font=("Arial", 14))
        label.grid(column=2,row=0, columnspan=4, sticky='nsew')
        button = ttk.Button(self, text="Go to Random Equation Page", command=lambda: controller.show_frame(RandEqPage))
        button.grid(column=1,row=1,sticky='nsew')
        button_2 = ttk.Button(self, text="Go to Calc Page", command=lambda: controller.show_frame(Calc))
        button_2.grid(column=2,row=1,sticky='nsew')
        button_3 = ttk.Button(self, text="Go to Graph Calc page", command=lambda: controller.show_frame(GraphingCalc))
        button_3.grid(column=3,row=1,sticky='nsew')
class RandEqPage(ttk.Frame):
    def __init__(self, parent, controller):
        super().__init__(parent)
        #create a queue
        self.q = queue.Queue()
        random_number = 0
        #entry validation
        def only_numbers(text):
            if text.isdigit() or text == "-":  # Allow only digits
                return True
            else:
                return False
        
        def check(event):
            correct = event
            txt = self.entry_math.get()
            if int(txt) == int(correct):
                self.correct_or_not.config(text="Correct, congrats!")
                self.next_eq.grid(row=5, column=1)
                self.give_up.grid_forget()

            else:
                self.correct_or_not.config(text="You Are Wrong, Try Again")
                self.give_up.grid(row=6, column=1)
                self.next_eq.grid_forget()
                
        def num_getter():
            l = self.math_maker()
            math_text = ""
            correct_ans = 1
            x=0
            for i in l:
                x=x+1
                correct_ans = correct_ans*i
                if x != len(l):
                    math_text = math_text + str(i) + " X "
                else:
                    math_text = math_text + str(i)
            return correct_ans, math_text
        
        #needs work
        def go_next():
            self.label_entry.grid(row=1, column=0, sticky="nsew")
            self.entry.grid(column=0, row=2, sticky="nsew")
            self.entry_button.grid(row=3, column=0, sticky="nsew")
            self.start_button.grid(row=4, column=0, sticky="nsew")
            self.label_entry.config(text="1st range")
            forget_eq_buttons()

        
        def start_math():
            label_start = ttk.Label(self, text="Equations", font=("Arial", 14))
            label_start.grid(row=0, column=1, sticky="nsew")
            correct_ans, math_text = num_getter()

            self.label_math = ttk.Label(self, text=math_text, font=("Arial", 12))
            self.label_math.grid(row=1, column=1, sticky="nsew")

            self.entry_math = ttk.Entry(self)
            self.entry_math.grid(row=2, column=1, sticky="nsew")

            self.button_math = ttk.Button(self, text="Lock In", command=lambda: check(correct_ans))
            self.button_math.grid(row=3, column=1, sticky="nsew")

            self.correct_or_not = ttk.Label(self, text="", font=("Arial", 10))
            self.correct_or_not.grid(row=4, column=1, sticky="nsew")

            self.give_up = ttk.Button(self, text="Give Up", command=go_next)

            self.next_eq = ttk.Button(self, text="Next", command=go_next)
            
          
        def forget_buttons():
            self.label_entry.grid_forget()
            self.entry.grid_forget()
            self.entry_button.grid_forget()
            self.start_button.grid_forget()
            start_math()

        def forget_eq_buttons():
            self.label_math.grid_forget()
            self.entry_math.grid_forget()
            self.button_math.grid_forget()
            self.correct_or_not.grid_forget()
            self.give_up.grid_forget()
            self.next_eq.grid_forget()
    
        self.columnconfigure((0,1), weight=1)
        self.rowconfigure((0,1,2,3,4,5,6), weight=1)

        label = ttk.Label(self, text="Second Page", font=("Arial", 14))
        label.grid(row=0, column=0, sticky="nsew")

        vcmd = (self.register(only_numbers), "%S")  # "%S" gets the typed character
        self.number_of_range = self.q.qsize() + 1
        self.label_entry = ttk.Label(self, text=f"{self.number_of_range}st range", font=("Arial", 10))
        self.label_entry.grid(row=1, column=0, sticky="nsew")

        self.entry = ttk.Entry(self, validate="key", validatecommand=vcmd)
        self.entry.grid(column=0, row=2, sticky="nsew")

        self.entry_button = ttk.Button(self, text="range", command=self.get_range)
        self.entry_button.grid(row=3, column=0, sticky="nsew")

        self.start_button = ttk.Button(self, text="Start", command=forget_buttons)
        self.start_button.grid(row=4, column=0, sticky="nsew")


        # have this button at the buttom at all times
        button = ttk.Button(self, text="Go Back", command=lambda: controller.show_frame(MainPage))
        button.grid(row=6, column=0, sticky="nsew")

    def get_range(self):
            #we need to use que
            text = self.entry.get()
            #left, right = text.split("-")
            #random_number = random.randint(int(left), int(right))
            self.q.put(text)
            self.entry.delete(0, 'end')
            self.label_entry.config(text=f"{self.q.qsize() + 1}st range")
            print(self.q.qsize())

    def math_maker(self):
        l = []
        for i in range(self.q.qsize()):
            x, y = self.q.get().split("-")
            new = random.randint(int(x), int(y))
            l.append(new)
        return l
class Calc(ttk.Frame):
    def __init__(self, parent, controller):
        super().__init__(parent)
        self.button_list = []
        #columns 0-3 is for normal calc, rest is for sci
        self.columnconfigure((0,1,2,3,4,5), weight=1)
        self.rowconfigure((0,1,2,3,4,5,6), weight=1)
        

        self.shift_mode = False
        def sci_calculator(num=None):
            if num == "shift":
                self.shift_mode = True
            elif num == 'sin/asin':
                if self.shift_mode == True:
                    num = 'asin('
                    self.shift_mode = False
                elif self.shift_mode == False:
                    num = 'sin('
            elif num == 'cos/acos':
                if self.shift_mode == True:
                    num = 'acos('
                    self.shift_mode = False
                elif self.shift_mode == False:
                    num = 'cos('
            elif num == 'tan/atan':
                if self.shift_mode == True:
                    num = 'atan('
                    self.shift_mode = False
                elif self.shift_mode == False:
                    num = 'tan('
            elif num == 'ln/e':
                if self.shift_mode == True:
                    num = 'e'
                    self.shift_mode = False
                elif self.shift_mode == False:
                    num = 'ln('
            elif num == 'rad':
                    num = 'rad('
            elif num == 'pi':
                self.shift_mode == False
                num = 'pi'
            elif num == 'log':
                self.shift_mode == False
                num = 'log('
            elif num == '(':
                num = '('
            elif num == ')':
                num = ')'
            elif num == 'X10^x':
                num = '*10^'
            else:
                return num
            return num
        def equal(screen):
            import re
            #for sin-1
            if match := re.search(r'sin^-1\(([0-9])\)', screen):
                print('yes')


            return screen
            ...
        def calculator(num=None, lbl=None):
            import re
            self.button_list.append(str(num))
            screen = lbl["text"]
            #if len(button_list) >= 2 and button_list[-2] == '=': [This block of code resets the screen after = is pressed and a button is pressed]
                    #lbl.config(text=str(num))
                    #return
            num = sci_calculator(num)
            if num == 'C':
                lbl.config(text='')
            elif num == 'shift':
                pass
            elif num == '=':
                # screen = equal(screen)
                #changes ^ to ** before eval
                if 'e' in screen:
                    e = re.sub('e', 'math.e', screen)
                    screen = e
                if '^' in screen:  
                    expo = re.sub(r'\^', '**', screen)
                    screen = expo
                if 'rad' in screen:
                    radical = re.sub('rad', 'sqrt', screen)
                    screen = radical
                
                elif screen[0] in ['-', '+', '*', '/', '^', '%'] or screen[-1] in ['-', '+', '*', '/', '^', '%']:
                    lbl.config(text="ERROR")
                result = eval(screen)
                round_result = round(result, 2)
                lbl.config(text=str(round_result))
            #clears the screen and replaces it with the num if there is an error and a button is pressed
            elif screen == 'ERROR':
                lbl.config(text=str(num))
            else:
                new_calc_label = screen + str(num)
                lbl.config(text=new_calc_label)

             
        def calc_grid(grid, labl):    
            calc_buttons = [
            (1, 1, 0), (2, 1, 1), (3, 1, 2), ('+', 1, 3),
            (4, 2, 0), (5, 2, 1), (6, 2, 2), ('*', 2, 3),
            (7, 3, 0), (8, 3, 1), (9, 3, 2), ('-', 3, 3),
            (0, 4, 0), ('.', 4, 1), ('=', 4, 2), ('/', 4, 3),
            ('C', 5, 0), ('%', 5, 1), ('^', 5, 2)
            ]
            for (num, row_, col) in calc_buttons:
                x = ttk.Button(grid, text=num, command= lambda num=num, lbl=labl: calculator(num, lbl))
                x.grid(row=row_, column = col, sticky='nsew', padx=1, pady=1)
        def calc_sci(grid, labl):
            sci_calc_buttons = [
            ('sin/asin', 1, 4), ('log', 1, 5),
            ('cos/acos', 2, 4), ('ln/e', 2, 5),
            ('tan/atan', 3, 4), ('X10^x', 3, 5),
            ('shift', 4, 4), ('rad', 4, 5),
            ('(', 5, 4), (')', 5, 5),
            ("pi", 6, 4)
            ]
            self.sci_buttons = []
            for (num, row_, col) in sci_calc_buttons:
                #i need a new command for this
                x = ttk.Button(grid, text=num, command= lambda num=num, lbl=labl: calculator(num, lbl))
                x.grid(row=row_, column = col, sticky='nsew', padx=1, pady=1)
                self.sci_buttons.append(x)
                
            go_sci.grid_forget()
            close_sci.grid(row=5, column=3, sticky="nsew")
        def forget_sci():
            close_sci.grid_forget()
            for i in self.sci_buttons:
                i.grid_forget()
            go_sci.grid(row=5, column=3)
            ...
        calc_label = tk.Label(self, text="")
        calc_label.grid(row=0, column=0, columnspan=5)
        calc_grid(self, calc_label)
        go_sci = ttk.Button(self, text="sci", command=lambda: calc_sci(self, calc_label))
        go_sci.grid(row=5, column=3, sticky="nsew")
        close_sci = ttk.Button(self, text="OG", command=forget_sci)
        go_back = ttk.Button(self, text="Back", command=lambda: controller.show_frame(MainPage))
        go_back.grid(row=6, column=0, columnspan=4, sticky="nsew")

        #note to self: figure out a way to switch 'sci' button, figure out how to get radical, figure out how to fix my shift trig functions,
        #figure out how to use log better, maybe add a variable rad later and fix my e^x issue
        #idc about validation, after I finish the above I will move to the graphing calc which will be my biggest project yet
        # I plan to use a db like sql for my graphing calc and matplotlib
class GraphingCalc(ttk.Frame):
    def __init__(self, parent, controller):
        super().__init__(parent)
        conn = sq.connect('graph_data.db')
        cursor = conn.cursor()
        self.columnconfigure((0,1,2,3,4,5), weight=1)
        self.rowconfigure((0,1), weight=1)

        def graph(row):
            row = row[0].replace(" ", "")
            y=np.linspace(-10,10,100)
            x = np.linspace(-10, 10, 100)
            if row.startswith("y"):
                eq = row[2:]
                #x = np.linspace(-10, 10, 100)
                try:
                    y = eval(eq, {"x": x, "np": np})
                    if np.isscalar(y):
                        y = np.full_like(x, y)
                except:
                    y = np.full_like(x, float(eq))
                plt.plot(x, y)
            elif row.startswith("x"):
                eq = row[2:]
                #y=np.linspace(-10,10,100)
                try:
                    x = eval(eq, {"y": y, "np": np})
                    if np.isscalar(x):
                        x = np.full_like(y, x)
                except:
                    x=np.full_like(y, float(eq))
                y = np.clip(y, -1000, 1000)
                
                plt.plot(x,y)
                

        def graph_eq():
            cursor.execute("Select expression from graph;")
            rows = cursor.fetchall()

            for row in rows:
                graph(row)
            
            plt.xlabel("x")
            plt.ylabel("y")
            plt.ylim(-10, 10)
            plt.xlim(-10, 10)
            plt.grid(True)
            plt.show()

        def add_eq():
            eq = entry_graph.get()
            eq_list.insert(tk.END, eq)
            entry_graph.delete(0, tk.END)
            cursor.execute("insert into graph (expression) values (?)", (eq,))
            conn.commit()

        def delete_eq():
            try:
                selected_index = eq_list.curselection()[0] #gets the selected item index
                eq_to_delete = eq_list.get(selected_index) 
                eq_list.delete(selected_index)  
                cursor.execute("DELETE FROM graph WHERE expression = ?", (eq_to_delete,))
                conn.commit()
            except IndexError:
                print("No item selected")  # Handle if no item is selected

        def clear():
            eq_list.delete(0, tk.END)
            reset_table()
        def reset_table():
            cursor.execute("DELETE FROM graph")  # remove all rows
            conn.commit()

        cursor.execute("""
        Create table if not exists graph(
            id integer primary key,
            expression text 
        )
        """)
        conn.commit()

        entry_graph = ttk.Entry(self)
        entry_graph.grid(row=0, column=0)

        add = ttk.Button(self, text="add", command=add_eq)
        add.grid(row=0, column=1)

        delete = ttk.Button(self, text="delete", command=delete_eq)
        delete.grid(row=0, column=2)

        clear_ = ttk.Button(self, text="clear", command=clear)
        clear_.grid(row=0, column=3)

        eq_list = tk.Listbox(self)
        eq_list.grid(row=1, column=0, columnspan=4)

        graph_button = ttk.Button(self, text="graph", command=graph_eq)
        graph_button.grid(row=0, column=4)

        go_back = ttk.Button(self, text="back", command=lambda: controller.show_frame(MainPage))
        go_back.grid(row=0, column=5, sticky="nsew")


if __name__ == "__main__":
    main()


