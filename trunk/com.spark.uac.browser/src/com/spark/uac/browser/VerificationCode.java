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
 * ��֤��
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author yl
 * @version 2011-7-5
 */
public class VerificationCode {

	/**
	 * ��Ч�� �ݶ�10����
	 */
	public static final long life = 1000*60*5;

	//��֤������
	private String code;
	//��֤��ͼƬ
	private ImageDescriptor image;
	
	private int width,height;

	public VerificationCode(int width,int height) {
		this.width = width;
		this.height = height;
		init();
	}

	private void init() {
		// ���ڴ��д���ͼ��
//		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// ��ȡͼ��������
		Graphics g = image.getGraphics();

		// ���������
		Random random = new Random();

		// �趨����ɫ
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		// �趨����
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// ���߿�
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// ȡ�����������֤��(4λ����)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// ����֤����ʾ��ͼ����
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));// ���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
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
	 * ��֤ ��֤�� �Ƿ���ȷ
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
	
	private static Color getRandColor(int fc, int bc) {// ������Χ��������ɫ
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
