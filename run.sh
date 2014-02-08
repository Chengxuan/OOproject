export ANT_HOME=$1
export PATH=$PATH:$ANT_HOME/bin/
ant
read -p "Ant build finish, press [Enter] to start runner class"
export CLASSPATH=.:./encode/encoder.jar
javac Runner.java 
java Runner
read -p "Runner show finish, press [Enter] to start unit test"
cd encode/build/
java -cp .:../../encode/encoder.jar:../../src/lib/junit-4.11.jar:../../src/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ie.gmit.test.TestHuffmanTree
java -cp .:../../encode/encoder.jar:../../src/lib/junit-4.11.jar:../../src/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ie.gmit.test.TestEncoderException
java -cp .:../../encode/encoder.jar:../../src/lib/junit-4.11.jar:../../src/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ie.gmit.test.TestEncoderReliability
java -cp .:../../encode/encoder.jar:../../src/lib/junit-4.11.jar:../../src/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ie.gmit.test.TestEncoderSpecificEfficiency
java -cp .:../../encode/encoder.jar:../../src/lib/junit-4.11.jar:../../src/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ie.gmit.test.TestFactoryCommand
java -cp .:../../encode/encoder.jar:../../src/lib/junit-4.11.jar:../../src/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ie.gmit.test.TestFactoryPoly
read -p "Test finish, press [Enter] to exist"