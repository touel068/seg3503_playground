# seg3103_playground Lab 5

| Outline | Value |
| --- | --- |
| Cours | SEG 3503 |
| Date | Été 2021 |
| professeur | Andrew Forward, aforward@uottawa.ca |
| TA | Aymen Mhamdi, amham077@uottawa.ca |
| Équipe | Alex DeGrace (300071786) et Thomas Ouellette (300081063) |


# Part 1 grades

# Part 2 twitter

## Tests inital

![Inital tests actual failed](screenshot/TwitterInitalTest_Failed.png)

Dans cette photo actualTest n'a pas passer car la method est random

![Inital tests actual pass](screenshot/TwitterInitalTest_Pass.png)

Dans cette photo actualTest a pas passer car la method est random

## Twitter lookForAtSymbol

![Inital tests actual pass](screenshot/TwitterLookForAtSymbol_Pass.png)

Dans cette photo on voit que le test lookForAtSymbol a passer

## Twitter dontReturnSubstringMarches

![Inital tests actual pass](screenshot/TwitterDontReturnSubstringMarches_Failed.png)

Dans cette photo on voit que le test dontReturnSubstringMarches n'a pas passer

## Twitter superStringNotFound

![Inital tests actual pass](screenshot/TwitterSuperStringNotFound_Pass.png)

Dans cette photo on voit que le test superStringNotFound a passer

## Twitter handlerNull

![Inital tests actual pass](screenshot/TwitterHandlerNull_Failed.png)

Dans cette photo on voit que le test handlerNull n'a pas passer

## Twitter refactor

![Inital tests actual pass](screenshot/Twitter_AllTestsPassAfterRefactor.png)

Dans cette photo on voit que tout les tests ont passer apres le refactor. Le test superStringNotFound ne passait pas puisque la methode isMentionned utilisait contains. Le test handlerNull_Failed ne passait pas puisque la methode isMentionned ne verifiait pas pour un entrer null. 