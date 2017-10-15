package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class checkcode extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * ��ͼ
		 */
		//step1�ڴ�Ӱ����󻭲�
		BufferedImage image = new BufferedImage(90,30,BufferedImage.TYPE_INT_RGB);
		//step2��û���
		Graphics g = image.getGraphics();
		//step3������������ɫ
		g.setColor(new Color(255,255,255));
		//step4���������ñ�����ɫ
		g.fillRect(0, 0, 90, 30);
		//step5,���¸�����������ɫ
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.setFont(new Font(null,Font.ITALIC,24));
		//step6,����һ�������(��֤��)
		String number = getNumber();
		//����֤��󶨵�session�������棬������֤
		HttpSession session = request.getSession();
		session.setAttribute("number", number);
		//����֤����Ƴ�ͼƬ
		g.drawString(number, 2, 23);
		//step7��һЩ������
		g.drawOval(0, 0, r.nextInt(90), r.nextInt(30));
		for(int i = 1;i<=8;i++){
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(90), r.nextInt(30),r.nextInt(90), r.nextInt(30));	
		}
		/*
		 * ѹ��ͼƬ�������
		 */
		//���÷��������ص��������ͣ�ͼƬ��
		response.setContentType("image/jpeg");
		OutputStream ops = response.getOutputStream();
		//ѹ��ͼƬ�����
		javax.imageio.ImageIO.write(image, "jpeg", ops);
		ops.close();
	}
	//����Ϊ5���ַ�����֤�루��Щ�ַ���Ҫ���A~Z��0~9��ѡȡ��
	private String getNumber(){
		String number = "";
		String chars="ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
		Random r = new Random();
		for(int i = 0;i<5;i++){
			number+=chars.charAt(r.nextInt(chars.length()));	
		}
		return number;
	}

}
