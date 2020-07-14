package ru.ak.compress.archivers;

import java.util.List;

import ru.ak.compress.exception.CompressException;

public interface IArchive {

    List<String> entries() throws CompressException;

    List<String> decompress(String destination) throws CompressException; 

    String decompress(String destination, String entryName) throws CompressException;

}