package com.helio4.bancol.avaluos.servicio.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;


public class ImageCompressor {
	
	public static final void resizeQuality(String path,String pathImageCompressed) {
			FileOutputStream os = null;
			try {
				File imageFile = new File(path);
//				if (imageFile.length() == 80000 ) {
					 InputStream is = new FileInputStream(imageFile);
					    
						File compressedImageFile = new File(pathImageCompressed);
						os = new FileOutputStream(compressedImageFile);
						//0 no importa la calidad, 1 es muy  importante la calidad
						float quality = 0.5f; // Change this as needed
						
					
						BufferedImage image = ImageIO.read(is);
						

						    // get all image writers for JPG format
						Iterator<ImageWriter> writers = ImageIO
						            .getImageWritersByFormatName("jpg");

						if (!writers.hasNext()) {
							throw new IllegalStateException("No writers found");
						}

						ImageWriter writer = (ImageWriter) writers.next();
						ImageOutputStream ios = ImageIO.createImageOutputStream(os);
						writer.setOutput(ios);

						    // set compression quality
						ImageWriteParam param = writer.getDefaultWriteParam();

						param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
						param.setCompressionQuality(quality);

						writer.write(null, new IIOImage(image, null, null), param);
						
						//rm imageFile &&  mv compessedImageFile imageFile
						compressedImageFile.renameTo(imageFile);
						os.close();
						is.close();
//				}
			   
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public final static String  getExtension(String pathImage) {
		return pathImage;
	}
}
