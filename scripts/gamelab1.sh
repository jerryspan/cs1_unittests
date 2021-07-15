#! /bin/sh
cp $FIXTURES/GameLabWeek1Test.java . && cp $FIXTURES/InputOutput.java .

for i in "$*"
do
   echo $i
done