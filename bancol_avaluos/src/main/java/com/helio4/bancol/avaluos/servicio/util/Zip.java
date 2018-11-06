package com.helio4.bancol.avaluos.servicio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zip {
    
    private static Logger log = LoggerFactory
            .getLogger(Zip.class);

    public static void compressFiles(List<String> srcFiles, String zipFile) {
        // create byte buffer
        byte[] buffer = new byte[1024];
        FileOutputStream fos;
        ZipOutputStream zos = null;
        try {
            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);
        } catch (FileNotFoundException e) {
            log.error("No se encontro el archivo: ", zipFile);
            return;
        }
        try {
            for (String ruta : srcFiles) {

                File srcFile = new File(ruta);

                FileInputStream fis = new FileInputStream(srcFile);

                // begin writing a new ZIP entry, positions the stream to the
                // start of the entry data
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                // close the InputStream
                fis.close();
            }
        } catch (IOException ioe) {
            log.error("Error creating zip file: ", ioe);
        } finally {
            // close the ZipOutputStream
            try {
                zos.finish();
                zos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
