package user.pedro.demo;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;

public class testCaptcha {

	/**測試
	 * */
	public static Captcha createCaptcha(int width, int height) {
		Captcha captcha = new Captcha.Builder(width, height)
				.addBackground(new GradiatedBackgroundProducer())
				.addText()
				.addNoise(new CurvedLineNoiseProducer())
				.build();
		return captcha;
	}

	public static void createImage(Captcha captcha) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(captcha.getImage(), "png", os);
			FileOutputStream fs = new FileOutputStream("C:/captcha/captcha.png");
			fs.write(os.toByteArray());
			fs.flush();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Captcha captcha  = createCaptcha(200,80);
		createImage(captcha);
		System.out.println("Done");
	}

}
