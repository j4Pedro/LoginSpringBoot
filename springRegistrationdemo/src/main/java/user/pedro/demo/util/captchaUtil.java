package user.pedro.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;

public class captchaUtil {
	public static Captcha createCaptcha(int width, int height) {

		return new Captcha.Builder(width, height).addBackground(new GradiatedBackgroundProducer()).addText()
				.addNoise(new CurvedLineNoiseProducer()).build();

	}

	public static String encodeBase64(Captcha captcha) {
		String imageData = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(captcha.getImage(), "png", os);
			Base64.getEncoder().encode(os.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageData ;
	}
}
