package jframe.main;

public class SharedData {
    private static SharedData instance;
    private String fileContent;

    private SharedData() {
        // Private constructor to prevent direct instantiation
    }

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }
}