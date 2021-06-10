# seg3103_playground

| Outline | Value |
| --- | --- |
| Cours | SEG 3503 |
| Date | Été 2021 |
| professeur | Andrew Forward, aforward@uottawa.ca |
| TA | Aymen Mhamdi, amham077@uottawa.ca |
| Équipe | Alex DeGrace (300071786)|


# Step took for this lab

### Step 0 - Compile and run test

To compile the test file and the fizzbuzz implementation I ran the following commande in WSL Ubuntu from the fizzbuzz_Alex folder:

##### Compile source code

javac -encoding UTF-8 --source-path src -d dist src/*.java

##### Compile test code

javac -encoding UTF-8 --source-path test -d dist -cp dist:lib/junit-platform-console-standalone-1.7.1.jar test/*.java

##### Run test code

java -jar lib/junit-platform-console-standalone-1.7.1.jar --class-path dist --scan-class-path

### Step 1 - Created a Fizzbuzz.java and a FizzbuzzTest.java file

The Fizzbuzz.java file contains a method called get fizzbuzz(int) that take an int and return the correct string using the fizzbuzz game logic. For now no logic had been applie the method only return null. The test file contains no test for the moment. This is the inital commit.