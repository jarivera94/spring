
package com.helio4.bancol.avaluos.servicio.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.com.certicamara.commons.ProcessResponseSign;
import co.com.certicamara.sign.Sign;
import co.com.certicamara.sign.SignFactory;
import co.com.certicamara.sign.SignatureParameters;
import co.com.certicamara.sign.certificate.CertificateConfiguration;
import co.com.certicamara.sign.certificate.CertificateFromBytes;
import co.com.certicamara.sign.pdf.PdfParameters;
import co.com.certicamara.sign.utils.UtilsSign;
import co.com.certicamara.verify.certificates.revocation.RevocationVerify;
import co.com.certicamara.verify.certificates.revocation.VerifyType;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfSignatureAppearance.RenderingMode;

public class PDFDigitalSign {
	private String path;
	private String p12Certificate;
	private String certificatePassword;
	private String pdf2SignPath;
	private String pdf2SignImagePath;
	private String crlPathDirectory;
	private String keyStorePath;
	private String signPath;
	private String signReason;
	private String signLocation;
	private String signFieldName;
	private int signPage;
	private int lowerLeftX;
	private int lowerLeftY;
	private int upperRightX;
	private int upperRightY;
	private String contentSignature;
	private boolean imageValidation;
	
	public PDFDigitalSign(String path){
		this.path = path;
	}
	
	public void setup() {
		pdf2SignImagePath = path+"/sign/Firma.png";
		p12Certificate = 	path+"/sign/CERTICAMARA2014.p12";
		crlPathDirectory = 	path+"/sign/CRL/crl";
		keyStorePath = 		path+"/sign/keystore/Keystore";
		signPath =			path+"/sign/sign";
		
		signReason = "Aprobación del Documento";
		signLocation = "Bogotá - Colombia - Certicámara S.A.";
		signFieldName = "Firma Digital Certicámara";
		contentSignature = "Bogotá - Colombia - Certicamara S.A. Jose Nicolas Bayona Vargas RNA 1467";
		
		signPage = 5;
		lowerLeftX = 380;
		lowerLeftY = 70;
		upperRightX = lowerLeftX+90;
		upperRightY = lowerLeftY+50;
		
		imageValidation = true;
	}
	
	public String obtenerPassword(String archivo) {
        String cadena = null;
        FileReader f;
		try {
			f = new FileReader(archivo);
			BufferedReader b = new BufferedReader(f);
			cadena = b.readLine();
	        b.close();
	        f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return cadena;
    }

	public void firmar(String rutaPDF) {
		setup();
		
		pdf2SignPath = rutaPDF;
		
		certificatePassword = obtenerPassword(signPath);
		
		String[] cadena = certificatePassword.split(" ");
		
		byte[] arreglo = new byte[cadena.length];
		
		for(int i=0; i<cadena.length; i++){
			arreglo[i] = new Integer(cadena[i]).byteValue();
		}
		
		try {
			certificatePassword = Cifrador.descifra(arreglo);
			
			CertificateConfiguration cert = new CertificateFromBytes(UtilsSign.getBytesFromFile(p12Certificate),certificatePassword);
			RevocationVerify revocationVerify = new RevocationVerify(VerifyType.CRL_OCSP,null,crlPathDirectory,null);
			revocationVerify.setKeyStorePath(keyStorePath);
			ArrayList<SignatureParameters> lista = new ArrayList<SignatureParameters>();
			for(int i=0;i<1;i++){	
				PdfParameters signParameters = new PdfParameters(UtilsSign.getBytesFromFile(pdf2SignPath),revocationVerify,cert);
				signParameters.setInformation(signReason, signLocation);
				
				signParameters.setImageSettings(signFieldName, UtilsSign.getBytesFromFile(pdf2SignImagePath), 
						new Rectangle(lowerLeftX,lowerLeftY,upperRightX,upperRightY),signPage,
						imageValidation, contentSignature, RenderingMode.DESCRIPTION);
				signParameters.setVerifyDocument(true);
				lista.add(signParameters);	
			}
			
			Sign s = SignFactory.getSigner(SignFactory.PDF, lista);
			List<ProcessResponseSign> listaR = s.signData();
			for(ProcessResponseSign pp :listaR){
				if(pp.isExito()){	
					UtilsSign.saveSignedFile(pdf2SignPath, pp.getResultado());
				}else{
					/*for(MessageResponse mm : pp.getMessageResponse()){
						System.out.println(mm.getCodigo()+" "+mm.getMensaje());
					}*/
				}
			}
		}
		catch (Exception e) {
			System.out.println("FirmaValida: " + e.toString());
		}
	
	}
}
