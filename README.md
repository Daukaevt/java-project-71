-------------------------
### Hexlet tests and linter status:
[![Actions Status](https://github.com/Daukaevt/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/Daukaevt/java-project-71/actions)


<a href="https://codeclimate.com/github/Daukaevt/java-project-71/maintainability"><img src="https://api.codeclimate.com/v1/badges/84efb75e67b6241e9519/maintainability" /></a>

<a href="https://codeclimate.com/github/Daukaevt/java-project-71/test_coverage"><img src="https://api.codeclimate.com/v1/badges/84efb75e67b6241e9519/test_coverage" /></a>

------------------------
About:


	- The program compares two configuration files.

	- The result of file comparison can be displayed in different formats.

	- The diff is built based on how the files have changed relative to each other, the keys are displayed in alphabetical order. Below is an example of what should be the result of this step: 
    asciinema link: https://asciinema.org/a/wy6Sif66IPMaML0Bzl6e2uz9Q

-------------------------
System requirements:


	JDK 20

-------------------------
SETUP:


	make run-dist
		runs test distributive.

	./app/build/install/app/bin/app /home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.json /home/timur/IdeaProjects/java-project-71/app/src/test/resources/file2.json
		another way to run test distributive.

	make gendiff-h
		help.


