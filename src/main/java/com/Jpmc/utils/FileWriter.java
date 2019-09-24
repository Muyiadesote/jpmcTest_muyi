package com.Jpmc.utils;


import java.io.IOException;
import java.nio.file.Paths;


public class FileWriter {

    private java.io.FileWriter fw;
    private CharSequence newLine = System.getProperty("line.separator");
    String fullPath;
    String directory;

    public FileWriter(String fileName){
        directory = "src/test/resources/";
        fullPath = directory + fileName;
    }

    public void writeToFile(String writeText) {
        try {
            if(fw == null){
                fw = new java.io.FileWriter(Paths.get(fullPath).toFile());
            }
            fw.write(writeText);
//            fw.append(newLine);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToFile(String appendText) {
        try {
            fw = new java.io.FileWriter(Paths.get(fullPath).toFile(), true);
            fw.append(appendText);
//            fw.append(newLine);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endFile() {
        try {
            if(fw != null){
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}