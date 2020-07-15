package ru.ak.compress.archivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

import ru.ak.compress.exception.CompressException;

/**
 * @author a.kakushin
 */
public abstract class ApacheCommonCompressArchive extends AbstractArchive {

    public ApacheCommonCompressArchive() {
        super();
    }

    public ApacheCommonCompressArchive(String format, String fileName) {
        super(format, fileName);
    }

    @Override
    public List<String> entries() throws CompressException {
        List<String> result = new ArrayList<>();

        try (ArchiveInputStream ais = new ArchiveStreamFactory()
                .createArchiveInputStream(super.getFormat(), new FileInputStream(super.getFileName()))) {

            ArchiveEntry entry;
            while ((entry = ais.getNextEntry()) != null) {
                result.add(entry.getName());
            }
        } catch (Exception ex) {
            throw new CompressException(ex.getMessage());
        }

        return result;
    }

    @Override
    public List<String> decompress(String destination) throws CompressException {
        List<String> result = new ArrayList<>();

        try (ArchiveInputStream ais = new ArchiveStreamFactory()
                .createArchiveInputStream(super.getFormat(), new FileInputStream(super.getFileName()))) {

            ArchiveEntry entry;
            while ((entry = ais.getNextEntry()) != null) {
                String target = destination + File.separator + entry.getName();
                try (FileOutputStream fos = new FileOutputStream(target)) {
                    ais.transferTo(fos);
                    result.add(target);
                }
            }
        } catch (Exception ex) {
            throw new CompressException(ex.getMessage());
        }

        return result;
    }

    @Override
    public String decompress(String destination, String entryName) throws CompressException {
        String result = null;

        try (ArchiveInputStream ais = new ArchiveStreamFactory()
                .createArchiveInputStream(super.getFormat(), new FileInputStream(super.getFileName()))) {

            ArchiveEntry entry;
            while ((entry = ais.getNextEntry()) != null) {
                if (entry.getName().equalsIgnoreCase(entryName)) {                
                    result = destination + File.separator + entry.getName();
                    try (FileOutputStream fos = new FileOutputStream(result)) {
                        ais.transferTo(fos);
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            throw new CompressException(ex.getMessage());
        }

        return result;
    }   
}