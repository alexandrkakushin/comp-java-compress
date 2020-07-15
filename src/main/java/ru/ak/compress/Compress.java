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
 * @author a.kakushin
 */
@WebService(name = "Compress", serviceName = "Compress", portName = "CompressPort")
public class Compress {

    @WebMethod
    public List<String> entries(
        @WebParam(name = "format") String format, 
        @WebParam(name = "fileName") String fileName)
            throws CompressException {

        List<String> result = new ArrayList<>();

        if (format == null || fileName == null) {
            throw new CompressException("Check parameters (is null): format, fileName");
        } else {
            IArchive archive = ArchiveFactory.create(format.toLowerCase().trim(), fileName);
            if (archive != null) {
                result = archive.entries();
            }
        }

        return result;
    }

    @WebMethod
    public List<String> decompressAll(
        @WebParam(name = "format") String format,
        @WebParam(name = "archiveName") String archiveName,
        @WebParam(name = "destination") String destination) throws CompressException {

        List<String> result = new ArrayList<>();
        if (format == null || archiveName == null || destination == null) {
            throw new CompressException("Check parameters (is null): format, archiveName, destination");
        } else {
            IArchive archive = ArchiveFactory.create(format.toLowerCase().trim(), archiveName);
            if (archive != null) {
                result = archive.decompress(destination);
            }
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
        if (format == null || archiveName == null || destination == null || entryName == null) {
            throw new CompressException("Check parameters (is null): format, archiveName, destination, entryName");
        } else {
            IArchive archive = ArchiveFactory.create(format.toLowerCase().trim(), archiveName);
            if (archive != null) {
                result = archive.decompress(destination, entryName);
            }
        }

        return result;
    }
}