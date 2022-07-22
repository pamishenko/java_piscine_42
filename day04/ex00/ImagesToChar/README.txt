#! /bin/bash
# To compile, enter the command:
# Create directory "target"
mkdir target
javac -d ./target/ src/java/edu/school21/printer/*/*.java

# To run, enter the command:
java -cp ./target edu.school21.printer.app.MainClass ' ' '#' $PWD/it.bmp
# arg[0] - white color
# arg[1] - black color
# arg[2] - image file