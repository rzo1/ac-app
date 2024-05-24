package org.example;

public class MyResource {

    private String filePath;
    private String splitChar;


    public MyResource(String filePath, String splitChar) {
        this.filePath = filePath;
        this.splitChar = splitChar;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSplitChar() {
        return splitChar;
    }

    public void setSplitChar(String splitChar) {
        this.splitChar = splitChar;
    }
}
