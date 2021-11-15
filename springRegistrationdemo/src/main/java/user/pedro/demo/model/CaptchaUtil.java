package user.pedro.demo.model;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.NumbersAnswerProducer;


public class CaptchaUtil {
	
	//Captcha class object
	public static Captcha createCaptcha(int width,int height) {
		Captcha captcha = new Captcha.Builder(width,height)
		.addBackground()
		.addText(new NumbersAnswerProducer())
		.addNoise(new CurvedLineNoiseProducer())
		.build();
		return captcha;
		
	}
	// convert to binary String
	public static String encodeBase64(Captcha captcha) {
		String imageData = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(captcha.getImage(),"png",os);
			byte[] arr = Base64.getEncoder().encode(os.toByteArray());
			imageData = new String(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageData;
	}
	
	//convert into image
//	public static void createImage(Captcha captcha) {
//		try {
//			ByteArrayOutputStream os = new ByteArrayOutputStream();
//			ImageIO.write(captcha.getImage(),"png",os);
//			FileOutputStream fs = new FileOutputStream("C:/captcha/captcha.png"); 
//			fs.write(os.toByteArray());
//			fs.flush();
//			fs.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
