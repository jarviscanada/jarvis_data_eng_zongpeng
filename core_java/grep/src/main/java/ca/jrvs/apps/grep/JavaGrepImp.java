package ca.jrvs.apps.grep;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaGrepImp implements JavaGrep {

  private String rootPath;
  private String regex;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: regex rootPath outFile");
    }
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      throw new RuntimeException("Failed to execute the program.");
    }
  }

  @Override
  public void process() throws IOException {
    List<File> listFile = listFiles(rootPath);
    List<String> listString = new ArrayList<>();
    List<String> matchedString = new ArrayList<>();
    for (File f : listFile) {
      listString = readLines(f);
        for (String s : listString) {
            if (constainsPattern(s)) {
                matchedString.add(s);
            }
        }
    }
    writeToFile(matchedString);
  }

  @Override
  public List<File> listFiles(String rootDir) throws IOException {
    File root = new File(rootDir);
    File[] list = root.listFiles();
    List<File> fileList = new ArrayList<>();
    if (list == null) {
      return null;
    }
    for (File f : list) {

      if (f.isDirectory()) {
        List<File> tempF = listFiles(f.getAbsolutePath());
        fileList.addAll(tempF);
      } else {
        fileList.add(f);
      }
    }

    return fileList;
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> listString = new ArrayList<>();
    try {
      Scanner scanner = new Scanner(inputFile);
      while (scanner.hasNextLine()) {
        listString.add(scanner.nextLine());
      }
      scanner.close();
    } catch (IOException e) {
      throw new RuntimeException("Unable to find the file.", e);
    }
    return listString;
  }

  @Override
  public boolean constainsPattern(String line) {
    return line.matches(this.regex);
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    Path file = (new File(outFile)).toPath();
    try {
      Files.createFile(file);
    } catch (IOException ignoredIfExist) {
      ;
    }

    Files.write(file, lines);
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
