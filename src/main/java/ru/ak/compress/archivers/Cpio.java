package ru.ak.compress.archivers;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class Cpio extends ApacheCommonCompressArchive {
    
    public Cpio(String fileName) {
        super(ArchiveStreamFactory.CPIO, fileName);
    }      
}