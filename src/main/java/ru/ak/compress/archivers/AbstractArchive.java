package ru.ak.compress.archivers;

public abstract class AbstractArchive implements IArchive {
    
    private String format;
    private String fileName;

    public AbstractArchive() {
    }

    public AbstractArchive(String format, String fileName) {
        this();

        this.format = format;
        this.fileName = fileName;
    }

    public String getFormat() {
        return format;
    }

    public String getFileName() {
        return fileName;
    }
}