#! /bin/bash
# Create directory "target"
mkdir target

# compile original class
javac -d ./target/ src/java/edu/school21/printer/*/*.java

cp -r src/resources target/.
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

# To run, enter the command and put arg (arg[0] - white color, arg[1] - black color)
java -jar target/images-to-chars-printer.jar ' ' '#'
