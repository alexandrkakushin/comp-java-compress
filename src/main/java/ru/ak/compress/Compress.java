package ru.ak.compress;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import ru.ak.compress.archivers.ArchiveFactory;
import ru.ak.compress.archivers.IArchive;
import ru.ak.compress.exception.CompressException;

/**
 * Web-сервис для для для работы с архивами
 *
 * @author akakushin
 */
@WebService(name = "Compress", serviceName = "Compress", portName = "CompressPort")
public class Compress {

    @WebMethod
    public List<String> entries(
        @WebParam(name = "format") String format, 
        @WebParam(name = "fileName") String fileName)
            throws CompressException {

        List<String> result = new ArrayList<>();
        
        IArchive archive = ArchiveFactory.create(format.toLowerCase().trim(), fileName);
        if (archive != null) {
            result = archive.entries();
        }

        return result;
    }

    @WebMethod
    public List<String> decompressAll(
        @WebParam(name = "format") String format,
        @WebParam(name = "archiveName") String archiveName,
        @WebParam(name = "destination") String destination) throws CompressException {

        List<String> result = new ArrayList<>();

        IArchive archive = ArchiveFactory.create(format.toLowerCase().trim(), archiveName);
        if (archive != null) {
            result = archive.decompress(destination);
        }        

        return result;
    }

    @WebMethod
    public String decompressEntry(
        @WebParam(name = "format") String format,
        @WebParam(name = "archiveName") String archiveName,
        @WebParam(name = "destination") String destination,
        @WebParam(name = "entryName") String entryName) throws CompressException {

        String result = null;

        IArchive archive = ArchiveFactory.create(format.toLowerCase().trim(), archiveName);
        if (archive != null) {
            result = archive.decompress(destination, entryName);
        }        

        return result;
    }
}