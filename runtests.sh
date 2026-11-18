#!/bin/bash

let total=0;
let correct=0;
let failed=0;

for x in tests/*.in; do
    if [ -e ${x%.in}.import ]; then
        java -cp :po-uilib.jar:. -Dimport=${x%.in}.import -Din=$x -Dout=${x%.in}.outhyp erp.app.App;
    else
        java -cp po-uilib.jar:. -Din=$x -Dout=${x%.in}.outhyp erp.app.App;
    fi

# remove empty lines
    perl -i -ne 'print unless /^[[:space:]]*$/' ${x%.in}.outhyp

    diff -w ${x%.in}.out ${x%.in}.outhyp > /dev/null
    res=$? ;
    if [ $res -ne 0 ]; then
        diff -wB ${x%.in}.out ${x%.in}.outhyp > /dev/null
        res=$? ;
    fi
    if [ $res -ne 0 ]; then 
        diff -cwB ${x%.in}.out ${x%.in}.outhyp > ${x%.in}.diff ;
    else
        rm -f ${x%.in}.diff ${x%.in}.outhyp ;
    fi
    if [ -s ${x%.in}.diff ]; then
        echo -n "F";
        failures=$failures"Fail: $x: See file ${x%.in}.diff\n" ;
        let failed++;
    else
        let correct++;
        echo -n "."
#        rm -f ${x%.in}.diff ${x%.in}.outhyp ; 
    fi
    let total++;
done

rm -f saved*
let res=100*$correct/$total
echo ""
echo "Total Tests = " $total
echo "Passed = " $correct "(" $res"% )"
echo "Failed = " $failed
echo $failures
printf "$failures"
echo "Done."

