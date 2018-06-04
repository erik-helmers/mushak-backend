package utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * USAGE HERE
 * By Erik Helmers, the 21/05/2018
 */
public class PathsUtils {

    class FileHandler extends SimpleFileVisitor<Path>{

        int hash = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            hash += file.hashCode();
            return FileVisitResult.CONTINUE;
        }
    }



}
