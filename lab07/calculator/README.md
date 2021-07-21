# seg3103_playground Lab 7

| Outline | Value |
| --- | --- |
| Cours | SEG 3503 |
| Date | Été 2021 |
| professeur | Andrew Forward, aforward@uottawa.ca |
| TA | Aymen Mhamdi, amham077@uottawa.ca |
| Équipe | Alex DeGrace (300071786) et Thomas Ouellette (300081063) |


Screenshot of the SpotBug with the 18 original bugs:
![SpotBug](screenshots/SpotBugs.PNG)

Screenshot of he applications running: 
![App](screenshots/apprunning.PNG)


# Bugs
1.Comparison of String parameter using == or != in CalCFrame.clickCheck(String) 
Screenshot of Bug Fix:
![bug1](screenshots/bug1.PNG)
Bug Fix Description: The equals() method should be used
Bug Description: == is used to compare the string s to “”, this is inadequate for string since the == operator checks whether the references to the objects are equal not if the string content is the same