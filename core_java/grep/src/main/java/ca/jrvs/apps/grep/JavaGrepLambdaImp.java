package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {


  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: regex rootPath outFile");
    }
    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      throw new RuntimeException("Failed to execute the program.");
    }
  }

  @Override
  public List<File> listFiles(String rootDir) throws IOException {
    List<File> fileList = new ArrayList<>();
    Files.walk(Paths.get(rootDir))
        .filter(Files::isRegularFile)
        .map((p) -> new File(p.toString()))
        .forEach(fileList::add);

    return fileList;
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> listString = new ArrayList<>();
    try (Stream<String> lines = Files.lines(Paths.get(inputFile.toURI()))) {
      lines.forEach(listString::add);
    } catch (IOException e) {
      throw new Runtime Exception("File cannot be opened");
    }
    return listString;
  }


}
