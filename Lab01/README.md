I am running Java opend 15
java 15.0.2 2021-01-19
Java(TM) SE Runtime Environment (build 15.0.2+7-27)
Java HotSpot(TM) 64-Bit Server VM (build 15.0.2+7-27, mixed mode, sharing)


Instructions to run my code:

Java:
execute the following commands

cd newmath_java\newmath_java\src
javac Main.java
java Main


Junit:

javac -encoding UTF-8 -sourcepath src -d dist src/*.java

javac -encoding UTF-8 -sourcepath test -d dist -cp dist;lib/junit-platform-console-standalone-1.7.1.jar test/*.java

java -jar lib/junit-platform-console-standalone-1.7.1.jar --class-path dist --scan-class-path


Elixir:
mix compile
iex.bat -S mix

ExUnit:
mix test
