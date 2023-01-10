#! /bin/bash
# Create directory "target"
mkdir target

# compile original class
javac -classpath ./lib/jcommander-1.27.jar:./lib/JCDP-2.0.3.1.jar -sourcepath ./src -d ./target src/java/edu/school21/printer/*/*.java

# copy resources
cp -r src/resources target/.

#copy object class libs
jar -xf lib/jcommander-1.27.jar
jar -xf lib/JCDP-2.0.3.1.jar
mv com target/
rm -rf META-INF

#create jarfile
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

# To run, enter the command and put arg (--white=COLOR --black=COLOR)
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
