package ru.ak.compress.archivers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import ru.ak.compress.exception.CompressException;

public class SevenZ extends AbstractArchive {

    public SevenZ(String fileName) {
        super(ArchiveStreamFactory.SEVEN_Z, fileName);
    }

    @Override
    public List<String> entries() throws CompressException {

        List<String> result = new ArrayList<>();

        try (SevenZFile sevenZFile = new SevenZFile(new File(super.getFileName()))) {
            SevenZArchiveEntry entry;            
            while((entry = sevenZFile.getNextEntry()) != null){
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

        try (SevenZFile sevenZFile = new SevenZFile(new File(super.getFileName()))) {
            SevenZArchiveEntry entry;            
            while((entry = sevenZFile.getNextEntry()) != null){
                String target = destination + File.separator + entry.getName();                

                FileOutputStream out = new FileOutputStream(target);
                byte[] content = new byte[(int) entry.getSize()];
                sevenZFile.read(content, 0, content.length);
                out.write(content);
                out.close();
              
                result.add(target);
            }            
        } catch (Exception ex) {
            throw new CompressException(ex.getMessage());
        }

        return result;
    }

    @Override
    public String decompress(String destination, String entryName) throws CompressException {
        String result = null;

        try (SevenZFile sevenZFile = new SevenZFile(new File(super.getFileName()))) {
            SevenZArchiveEntry entry;            
            while((entry = sevenZFile.getNextEntry()) != null) {
                if (entry.getName().equalsIgnoreCase(entryName)) {
                    result = destination + File.separator + entry.getName();                

                    FileOutputStream out = new FileOutputStream(result);
                    byte[] content = new byte[(int) entry.getSize()];
                    sevenZFile.read(content, 0, content.length);
                    out.write(content);
                    out.close();

                    break;
                }                
            }            
        } catch (Exception ex) {
            throw new CompressException(ex.getMessage());
        }

        return result;
    }    
}