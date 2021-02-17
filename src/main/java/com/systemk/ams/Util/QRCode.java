package com.systemk.ams.Util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCode {
	
	public String QrCreate(String str, String path) {
		try {
			  return createQRCodeImage(str, path,  100, 100, 0x00000000, 0xFFFFFFFF);
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
		}	
		return null;

	}
	public String createQRCodeImage(String text, String path, int width, int height, int qrDarkColor, int qrLightColor)throws WriterException, IOException {
	  QRCodeWriter qrCodeWriter = new QRCodeWriter();
	  BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height); //텍스트, 바코드 포맷,가로,세로
	    
	  MatrixToImageConfig config = new MatrixToImageConfig(qrDarkColor , qrLightColor); //진한색, 연한색
	  BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix , config);

	  File temp = new File(path + text + ".png"); 
	  ImageIO.write(qrImage, "png",temp );
		
	  String webPath = temp.toString();
	  webPath = "resources/saveImg/qrImg/" + text + ".png";
	  Timer timer = new Timer();
	  TimerTask m_task = new TimerTask() {
		  @Override
		  public void run() {
			  temp.delete();
		  }
	  };
	  timer.schedule(m_task, 3000);
	  return text + ".png";
	}
}
