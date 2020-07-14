package ru.ak.compress.archivers;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class Tar extends ApacheCommonCompressArchive {
    
    public Tar(String fileName) {
        super(ArchiveStreamFactory.TAR, fileName);
    }      
}