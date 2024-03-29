run-dist:
	./app/build/install/app/bin/app /home/timur/IdeaProjects/java-project-71/app/src/test/resources/file1.json /home/timur/IdeaProjects/java-project-71/app/src/test/resources/file2.json


gendiff-h:
	./app/build/install/app/bin/app -h

.DEFAULT_GOAL := build-run

clean:
		./gradlew clean

build:
		./gradlew clean build

install:
		./gradlew clean install

run-dist-stylish:
		./build/install/app/bin/app -f stylish .app/src/test/resources/nestedFile1.json .app/src/test/resources/nestedFile2.json

run-dist-plain:
		./build/install/app/bin/app -f plain ./src/test/resources/nestedFile1.yml ./src/test/resources/nestedFile2.yml

run-dist-json:
		./build/install/app/bin/app -f json ./src/test/resources/nestedFile1.json ./src/test/resources/nestedFile2.yml

run:
		./gradlew run

test:
		./gradlew test

report:
		./gradlew jacocoTestReport

lint:
		./gradlew checkstyleMain checkstyleTest

update-deps:
		./gradlew useLatestVersions


build-run: build run

.PHONY: build


