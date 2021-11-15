package user.pedro.demo.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//  生成隨機驗證碼
public class ValidateCode {
    
    private static Random random = new Random();
    private int width = 160;// 寬
    private int height = 40;// 高
    private int lineSize = 30;// 干擾線數量
    private int stringNum = 4;//隨機產生字元的個數
    
    private String randomString = "thisisrandomstringforvalidatecode";
    
    private final String sessionKey = "RANDOMKEY";
    
    
    /*
     *  獲取字型
     */
    private Font getFont() {
        return new Font("Times New Roman", Font.ROMAN_BASELINE, 40);
    }
    
    /*
     *  獲取顏色
     */
    private static Color getRandomColor(int fc, int bc) {
        
        fc = Math.min(fc, 255);
        bc = Math.min(bc, 255);
        
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 12);
        
        return new Color(r, g, b);
    }
    
    /*
     *  繪製干擾線
     */
    private void drawLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(20);
        int yl = random.nextInt(10);
        g.drawLine(x, y, x + xl, y + yl);
    }
    
    /*
     *  獲取隨機字元
     */
    private String getRandomString(int num) {
        num = num > 0 ? num : randomString.length();
        return String.valueOf(randomString.charAt(random.nextInt(num)));
    }
    
    /*
     *  繪製字串
     */
    private String drawString(Graphics g, String randomStr, int i) {
        g.setFont(getFont());
        g.setColor(getRandomColor(108, 190));
        System.out.println(random.nextInt(randomString.length()));
        String rand = getRandomString(random.nextInt(randomString.length()));
        randomStr += rand;
        g.translate(random.nextInt(3), random.nextInt(6));
        g.drawString(rand, 40 * i + 10, 25);
        return randomStr;
    }
    
    /*
     *  生成隨機圖片
     */
     public void getRandomCodeImage(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        // BufferedImage類是具有緩衝區的Image類,Image類是用於描述影象資訊的類
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setColor(getRandomColor(105, 189));
        g.setFont(getFont());
        
        // 繪製干擾線
        for (int i = 0; i < lineSize; i++) {
            drawLine(g);
        }
        
        // 繪製隨機字元
        String random_string = "";
        for (int i = 0; i < stringNum; i++) {
            random_string = drawString(g, random_string, i);
        }
        
        System.out.println(random_string);
        
        g.dispose();
        
        session.removeAttribute(sessionKey);
        session.setAttribute(sessionKey, random_string);
        
        String base64String = "";
        try {
            //  直接返回圖片
           ImageIO.write(image, "PNG", response.getOutputStream());   
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
     
     /*
      *  生成隨機圖片，返回 base64 字串
      */
     public String getRandomCodeBase64(HttpServletRequest request, HttpServletResponse response) {
         HttpSession session = request.getSession();
         // BufferedImage類是具有緩衝區的Image類,Image類是用於描述影象資訊的類
         BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
         Graphics g = image.getGraphics();
         g.fillRect(0, 0, width, height);
         g.setColor(getRandomColor(105, 189));
         g.setFont(getFont());
         
         // 繪製干擾線
         for (int i = 0; i < lineSize; i++) {
             drawLine(g);
         }
         
         // 繪製隨機字元
         String random_string = "";
         for (int i = 0; i < stringNum; i++) {
             random_string = drawString(g, random_string, i);
         }
         
         System.out.println(random_string);
         
         g.dispose();
         
         session.removeAttribute(sessionKey);
         session.setAttribute(sessionKey, random_string);
         
         String base64String = "";
         try {
             //  直接返回圖片
             //  ImageIO.write(image, "PNG", response.getOutputStream());
             //返回 base64
             ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ImageIO.write(image, "PNG", bos);
             
             byte[] bytes = bos.toByteArray();
             Base64.Encoder encoder = Base64.getEncoder();
             base64String = encoder.encodeToString(bytes);
             
         } catch (Exception e) {
             e.printStackTrace();
         }
         
         return base64String;
     }
    
}
