default: compile build

full: parser compile build

compile:
	ant

parser:
	cd src/parser; ./build.bash

build:
	ant build-jar

test: compile build
	ant test
