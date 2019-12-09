package ca.jrvs.apps.practice;

public class RegexExcImp implements RegexExc {


    @Override
    public boolean matchJpeg(String filename) {
        return filename.matches("[a-zA-Z0-9]*\\.Jpeg");
    }

    @Override
    public boolean matchIp(String ip) {
        return ip.matches("[0-9]?[0-9]?[0-9]\\.[0-9]?[0-9]?[0-9]\\.[0-9]?[0-9]?[0-9]\\.[0-9]?[0-9]?[0-9]");
    }

    @Override
    public boolean isEmptyLine(String line) {
        return line.matches("^\n");
    }
}
