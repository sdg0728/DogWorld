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
import beans.myPage.DiaryFileDAO;
import command.main.Command;

public class DiaryUpdateCommand implements Command {

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
		
		// 파일 삭제
		DiaryFileDAO fileDao = new DiaryFileDAO();
		String delFiles = multi.getParameter("delFile");
		int delFileno = 0;
		if(delFiles != null) {
			delFileno = Integer.parseInt(delFiles);
		}
		
		try {
			fileDao.deleteFilesByNo(delFileno, request);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		int no = Integer.parseInt(multi.getParameter("no"));
		String title = multi.getParameter("title");
		String regdate = multi.getParameter("regdate");
		String content = multi.getParameter("content");
		String id = multi.getParameter("id");
		
		if(title!=null && regdate != null && id != null
				&& title.trim().length()>0 && regdate.trim().length() >0
				&& id.trim().length() >0) {
			try {
				System.out.println("???? 덜어옴??");
				cnt = dao.update(no, title, content, img);
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("no", no);
		request.setAttribute("change", cnt);
		
		

	}

}
