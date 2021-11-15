package user.pedro.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import user.pedro.demo.model.ValidateCode;

@RestController
@RequestMapping("/api/v1/user")
public class ValidateCodeController {

	// 生成驗證碼圖片
	@RequestMapping("/getCaptchaImage")
	@ResponseBody
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("image/png");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expire", "0");
			response.setHeader("Pragma", "no-cache");

			ValidateCode validateCode = new ValidateCode();

			// 直接返回圖片
			validateCode.getRandomCodeImage(request, response);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	

}