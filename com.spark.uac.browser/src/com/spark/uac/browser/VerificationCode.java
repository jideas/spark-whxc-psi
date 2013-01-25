package com.spark.uac.browser;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.jiuqi.dna.ui.wt.graphics.DataImageDescriptor;
import com.jiuqi.dna.ui.wt.graphics.ImageDescriptor;

/**
 * 
 * <p>
 * 验证码
 * </p>
 * 
 * <p>
 * Copyright: 版权所有 (c) 2002 - 2008<br>
 * Company: 久其
 * </p>
 * 
 * @author yl
 * @version 2011-7-5
 */
public class VerificationCode {

	/**
	 * 有效期 暂定10分钟
	 */
	public static final long life = 1000*60*5;

	//验证码内容
	private String code;
	//验证码图片
	private ImageDescriptor image;
	
	private int width,height;

	public VerificationCode(int width,int height) {
		this.width = width;
		this.height = height;
		init();
	}

	private void init() {
		// 在内存中创建图象
//		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}

		this.code = sRand;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpg", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
 		this.image = DataImageDescriptor.createImageDescriptor(null,out.toByteArray());
 		startDelete();
	}

	private void startDelete(){
		new Thread(){
			
			public void run(){
				try{
					Thread.sleep(life);
					code = null;
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}					
			}
		}.start();

    }

	/**
	 * 验证 验证码 是否正确
	 * 
	 * @param code
	 * @return boolean
	 */
	public boolean validation(String code){
		if(this.code==null)return false;
		return this.code.equals(code);
	}
	
	public boolean isValid(){
		return code!=null;
	}
	
	private static Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
		int tema,temb;
		Random random = new Random();
		if (fc > 255) {
			tema = 255;
		} else {
			tema = fc;
		}
		if (bc > 255) {
			temb = 255;
		} else {
			temb = bc;
		}
		int r = tema + random.nextInt(temb - tema);
		int g = tema + random.nextInt(temb - tema);
		int b = tema + random.nextInt(temb - tema);
		return new Color(r, g, b);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ImageDescriptor getImage() {
		return image;
	}

	public void setImage(ImageDescriptor image) {
		this.image = image;
	}

	public void change() {
		init();
	}
}
