# Introduction
This app will find the matched lines in all files under a directory recursively based on the pattern provided. It includes two ways of doing this: regular way to store everything in memory, and stream way for big size file. Through the implementation of this App, I am familar with different Java I/O packages, Collections, IDE functions/shortcuts, lambda, and stream API. The advantages of stream API and lambda function are studied and analyzed.

# Usage
The program take three inputs: matching pattern, search directory, and directory for output file.
For example, with three inputs like `.*IllegalArgumentException.* ./grep/src /tmp/grep.out`. The program will look for the line in all files under `./grep/src` that contains `IllegalArgumentException` in it. Then it will write the matched lines to `/tmp/grep.out`. The program will automatically create the file if it does not exist.

# Pseudocode
For the `process` method, it works like following pseudocode:
```
matchedLines = [] for storing the matched pattern
listFile = [] for all file under a directory "rootDir" using listFiles
for file in readLines(listFiles) 
	listString = [] all lines read from file by readLines
	for all line in listString
		if containsPattern(line) 
			matchedLines.add(line) 
writeToFile(matchedLines)
```
# Performance Issue
For the `JavaGrepImp`, it uses basic file I/O technique where it will store all the lines read to the memory. However, when the size of the file become larger and larger, the memory will be consumed entirely. To improve performance of memory cosuming, stream along with lambda function is implemented. It will significantly increase the memory it takes when a large file present.

# Future Improvement
1) Combine two file into one with a function that can detect the size of all files under a directory and decide which methods to use.
2) Do a performance test among different I/O packages and choose a faster one.
3) Adding thread to the program to further improve performance when encounters a huge amount of directory under search directory.
