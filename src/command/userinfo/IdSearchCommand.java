package command.userinfo;

import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.userinfo.SignDAO;
import beans.userinfo.UserDTO;
import command.main.Command;

public class IdSearchCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		SignDAO dao = new SignDAO();
		UserDTO [] arr = null;
		int cnt = 0;
		String email = request.getParameter("email");
		
		try {
			arr = dao.selectByEmail(email);
			if(arr == null || arr.length == 0) {
				cnt = 0;
			}else {
				cnt = 1;
			}
		}catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		if(cnt==0) {
	         request.setAttribute("result", cnt);
	         return;
	    }
		
		String host = "smtp.naver.com"; 
		// 네이버일 경우 네이버 계정, gmail경우 gmail 계정 
		String user = "sopsop111@naver.com"; 
		// 패스워드 
		String password = "wpwneh8845!";      
		
		// SMTP 서버 정보를 설정한다. 
		Properties props = new Properties(); 
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", 587); 
		props.put("mail.smtp.auth", "true"); 
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication(user, password); 
			} 
		}); 
		
		try { 
			MimeMessage message = new MimeMessage(session); 
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(arr[0].getEmail())); 
			
			// 메일 제목
			message.setSubject("DogWorld ID 찾기"); 
			// 메일 내용
			message.setText("회원의 아이디는 "+arr[0].getId() + " 입니다."); 
			// send the message 
			Transport.send(message); 
			System.out.println("Success Message Send"); 
		} catch (MessagingException e) { 
			cnt = 0;
			e.printStackTrace(); 
		}
		
		request.setAttribute("result", cnt);
		
	}	
}
