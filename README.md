# Educational practice in 4th semestr of BSU FAMCS
Some tasks that I've done for Educational practice during my study in BSU

## First task - Inheritance and polymorphism.

Create an application that meets the requirements below. Inheritance is used only in those tasks in which it is logically justified. Argument for the class belonging to each created method and correctly override for each class the `equals()`, `hashCode()`, `toString()` methods. Develop a UML diagram using an environment.
Requirements: create an object of the Computer class using the classes Hard drive, Disk drive, RAM, Processor. Methods: enable, disable, check for viruses, display the size of the hard drive on the console.

## Second task - Inheritance and polymorphism.

Create a console application that satisfies the following requirements:
- Use the capabilities of OOP: classes, inheritance, polymorphism, encapsulation;
- Each class should have a meaningful name and an informative composition;
- Inheritance should be applied only when it makes sense;
- When coding, conventions on the design of the java code convention code should be used;
- Classes should be correctly laid out in packages;
- The console menu should be minimal;
- Use xml files to store initialization parameters.
Develop a UML diagram using an environment.
Requirements: motorcyclist. Define the hierarchy of ammunition. Equip a motorcyclist. Calculate the cost. Sort ammunition based on weight. Find elements of ammunition corresponding to a given range of price parameters.

## Third task - Swing: user interface.
#### Task 1.
Display list. List item: country name and flag. Selecting an item displays the flag, country name, and capital in the label. Capitals with countries are stored in Map. Flags can be taken in the attached archive.
#### Task 2.
The JTable table contains information about tour packages (flag of the country - picture, description, price). In the fourth column, the checkbox (JCheckBox) allows you to select a tour. It is necessary to calculate the cost of the order and display in the table. Implement the ability to add/edit a tour.

## Forth task - Clocks
#### Task 1.
Implement an application that displays a circle and radius. The radius rotates clockwise around the center circles at a speed of 60 sec/one revolution, i.e. Is the second hand of a clock.
#### Task 2.
The graphic image is loaded and moves in a circle inscribed in the window. Speed (linear) is set and changed slider and does not depend on the size of the window (use the formula), the direction of movement is selected from the list.
#### Task 3.
Implement an application that builds a pie chart. The number of categories and their meanings may be different. The number of categories, their names and values are specified in a JSON file. Generate and handle exceptions for incorrect input data, including with negative. Input data are displayed in the form of a legend and / or signatures.

## Fifth task - Interface to offer in Java FX

#### Task 1
Regular expressions. From the drop-down list, you can select the data type (natural number, integer, floating-point number (do not forget about the 1e-5 record), date, time, e-mail). Next to the list is a text box with verified data. “Turn on” the green circle if the text field corresponds to the selected data type and the red circle otherwise.

#### Task 2
Regular expressions. The multiline test field contains text. Put all dates from this field in the list.

## Sixth task - "Excel Date lite"

Use Swing or Java FX.
Using a table component (for example, JTable), develop an application that allows you to work with dates (for example, using the GregorianCalendar, Calendar, Date classes) and perform the following operations:
1. Calculations of the type = operand operation numerical constant, where operation - + -, operand - date (for example, = 20.12.17 + 27);
2. Calculations of the type = operand operation numerical constant, where operation - + -, operand is the number or address of the cell (for example, = B3-72);
3. Calculations of the MIN, MAX functions over the dates or addresses of the cells (for example, = min (03/21/12, B2, C4); the number of function parameters at one’s own discretion (variable (preferred) or fixed)
4. Handle errors (including cyclical)
Implement the MVC design pattern. The formula can be entered in the table cell (preferably) or in another element. Depending on the implementation of paragraphs 3, 4 and the type of input of the formula (cell / other element), the maximum score may be less than 10.
P.S. Excel supports editing and recounting cells. The task has more weight.
