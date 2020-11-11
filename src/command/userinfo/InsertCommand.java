package command.userinfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import beans.userinfo.SignDAO;
import command.main.Command;

public class InsertCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		SignDAO dao = new SignDAO();
		
		
		// 파일 업로드
		ServletContext context = request.getServletContext();
		
		// 파일 경로
		String saveDirectory = context.getRealPath("upload/user");
		
		System.out.println("업로드 경로: " + saveDirectory);
		
		int maxPostSize = 5 * 1024 * 1024; // 최대 5M
		String encoding = "utf-8"; //인코딩
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest multi = null;
		
		System.out.println("request getContentType : " + request.getContentType());


		
		// MultipartRequest 생성 단계에서 이미 파일 저장됨.
		try {
			multi = new MultipartRequest(
					request,
					saveDirectory,
					maxPostSize,
					encoding,
					policy
					);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		List<String> originalFileNames = new ArrayList<String>();
		List<String> fileSystemNames = new ArrayList<String>();
		
		Enumeration names = multi.getFileNames();	// type='file' 요소 name 추출
		
		String img = "";
		
		while (names.hasMoreElements()) {
	         String name = (String)names.nextElement();
	         String originalFileName = multi.getOriginalFileName(name);
	         String fileSystemName = multi.getFilesystemName(name);
	         System.out.println("첨부파일: "+ originalFileName + "->" + fileSystemName);
	         
	         if(originalFileName != null && fileSystemName != null) {
	            originalFileNames.add(originalFileName);
	            fileSystemNames.add(fileSystemName);
	         }
	         if(fileSystemName != null)
	            img = fileSystemNames.get(0);
	         else
	        	 img = "noimg.gif";
	    }
		
		System.out.println("img: " + img);
		
		
		String id = multi.getParameter("id");
		String pw = multi.getParameter("psw");
		String name = multi.getParameter("name");
		String address1 = multi.getParameter("roadFullAddr");
		String address2 = multi.getParameter("jibunAddr");
		String email = multi.getParameter("email");
		String phone = multi.getParameter("phone");
		String petName = multi.getParameter("petName");
		String dogBreed = multi.getParameter("petKind");
		int petAge = Integer.parseInt(multi.getParameter("petAge"));

		String address = address1+"&"+address2;
		
		if(id != null && pw != null && name != null && address != null 
				&& email != null && phone != null && petName != null 
				&& dogBreed != null && petAge != 0
				&& id.trim().length() > 0 && pw.trim().length() > 0 
				&& name.trim().length() > 0 && address.trim().length() > 0 
				&& email.trim().length() > 0 && phone.trim().length() > 0 
				&& petName.trim().length() > 0 && dogBreed.trim().length() > 0) {
			
			try {
				
				cnt = dao.signInsert(id, pw, name, address, email, phone, petName, dogBreed, petAge, img);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		request.setAttribute("sign", cnt);

	}

}
