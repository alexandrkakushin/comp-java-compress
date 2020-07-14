package ru.ak.compress.archivers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

import ru.ak.compress.exception.CompressException;

public class Rar extends AbstractArchive {

    public Rar(String fileName) {
        super("rar", fileName);
    }

    @Override
    public List<String> entries() throws CompressException {
        List<String> result = new ArrayList<>();
        
        try (Archive rarFile = new Archive(new FileInputStream(super.getFileName()))) {            
            FileHeader entry;
            while ((entry = rarFile.nextFileHeader()) != null){
                result.add(entry.getFileNameString());
            }
            
        } catch (Exception ex) {
            throw new CompressException(ex.getMessage());
        } 
        
        return result;
    }

    @Override
    public List<String> decompress(String destination) throws CompressException {
        List<String> result = new ArrayList<>();

        try (Archive rarFile = new Archive(new FileInputStream(super.getFileName()))) {            
            FileHeader entry;
            while ((entry = rarFile.nextFileHeader()) != null){
                String target = destination + File.separator + entry.getFileNameString();
                try (FileOutputStream fos = new FileOutputStream(target)) {
                    rarFile.extractFile(entry, fos);
                }
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

        try (Archive rarFile = new Archive(new FileInputStream(super.getFileName()))) {            
            FileHeader entry;
            while ((entry = rarFile.nextFileHeader()) != null){
                if (entry.getFileNameString().equalsIgnoreCase(entryName)) {                   
                    result = destination + File.separator + entryName;
                    try (FileOutputStream fos = new FileOutputStream(result)) {
                        rarFile.extractFile(entry, fos);
                        break;
                    }
                }
            }
            
        } catch (Exception ex) {
            throw new CompressException(ex.getMessage());
        } 

        return result;
    }    
}