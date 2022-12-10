# AdventOfCode2022

My solutions for the 2022 Advent of Code. They should be written in such a way that someone else could follow along as they build their own solutions. My aim is to create a robust main class (Named 'AoCRunner') that will handle the bulk of the Advent of Code setup, and then anyone following along can create their own solutions and only copy AoCRunner.

## Setup Guide

For anyone following along wanting to use the same development environment, here are my setup steps:
Note: If copying this project as a base for your own solutions, skip steps 6, 7, and 8.

1. Install VS Code
1. Install the [Java Language Pack Extension](vscode:extension/vscjava.vscode-java-pack) for VS Code.
1. Install a Java JDK - when the above step completes, VS Code should have a popup prompting to set up a JDK, I used Adoptium's Temurin 17.
1. Restart VS Code.
1. Create a Java Project (CTRL + Shift + P, choose Java: Create Java Project)
1. For build tools, I chose Gradle (May need to install Gradle for Java when it prompts for it), chose Groovy, named the project "AdventOfCode2022".
1. I renamed the generated file 'App.java' to 'AoCRunner.java', and updated the `build.gradle` file to replace the existing value for `mainClass` to: `mainClass = 'adventofcode2022.AoCRunner'`.
1. Now all set up, can run the project using the play button in VS Code!

Note: You may run into issues where VS Code is not checking syntax of new files. The advice [here](https://stackoverflow.com/a/64689498) should be able to help fix the issue.

## Solving Problems Guide

This was created for others to follow along!
If you are following along after the fact and answers have already been added to this project,
take a first attempt at solving the problems yourself before looking at the solution.
Advent of Code is a great learning opportunity and you will have a better learning experience
if you try your own solution first!

If you want to use this project as a starter for your own Advent of Code solutions, follow these steps:
1. Choose the problem you want to solve on [Advent of Code](https://adventofcode.com/2022/).
   All of the examples in the below instructions use XA as the problem number. So if you are solving
   Day 5's solution, use 5 instead of X in all of the steps.
   Additionally, many days of the challenge have multiple parts. The also often build off of
   the previous step. Because of this, I suggest you create separate folders for each part.
   So maybe even have 5A, 5B, etc. That way, if you make a lot of changes for part B, you still
   have part A to go back to for a reference.
1. Read the instructions in 'app/src/main/java/adventofcode2022/Template.java'.
1. Solve the problem, putting your solution in the Solve() method of your created class.
1. As you work on the problem, if following the setup steps above, you should be able to use
   the VS Code 'Play' button. Make sure to test early and often!
1. When you have an answer, input your answer on the Advent of Code website.

Happy coding!!