package command.myPage;

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

import beans.myPage.DiaryDAO;
import command.main.Command;

public class DiaryWriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int cnt = 0;
		DiaryDAO dao = new DiaryDAO();
		
		// 파일 업로드
		ServletContext context = request.getServletContext();
		
		// 파일 경로
		String saveDirectory = context.getRealPath("upload/diary");
		
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
		
		String title = multi.getParameter("title");
		String regdate = multi.getParameter("regdate");
		String content = multi.getParameter("content");
		String id = multi.getParameter("id");
		
		if(title!=null && regdate != null && id != null
				&& title.trim().length()>0 && regdate.trim().length() >0
				&& id.trim().length() >0) {
			try {
				
				// 첨푸 파일 정보도 같이 INSERT
				cnt = dao.insert(title, regdate, content, img, id);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("wirte", cnt);
				
	}

}
