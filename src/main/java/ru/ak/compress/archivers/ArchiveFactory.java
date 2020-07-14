package ru.ak.compress.archivers;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class ArchiveFactory {

    public static IArchive create(String format, String fileName) {
        IArchive archive = null;

        if (format.contentEquals("rar")) {
            archive = new Rar(fileName);

        } else if (format.contentEquals(ArchiveStreamFactory.AR)) {
            archive = new Ar(fileName);

        } else if (format.contentEquals(ArchiveStreamFactory.ARJ)) {
            archive = new Arj(fileName);

        } else if (format.contentEquals(ArchiveStreamFactory.CPIO)) {
            archive = new Cpio(fileName);            

        } else if (format.contentEquals(ArchiveStreamFactory.DUMP)) {
            archive = new Dump(fileName); 

        } else if (format.contentEquals(ArchiveStreamFactory.JAR)) {
            archive = new Jar(fileName); 

        } else if (format.contentEquals(ArchiveStreamFactory.SEVEN_Z)) {
            archive = new SevenZ(fileName);

        } else if (format.contentEquals(ArchiveStreamFactory.TAR)) {
            archive = new Tar(fileName);
            
        } else if (format.contentEquals(ArchiveStreamFactory.ZIP)) {
            archive = new Zip(fileName);

        }

        return archive;
    }
}