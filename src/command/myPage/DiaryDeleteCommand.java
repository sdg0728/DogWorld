package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.DiaryDAO;
import beans.myPage.DiaryFileDAO;
import command.main.Command;

public class DiaryDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	
		int cnt = 0;
		
		DiaryDAO dao = new DiaryDAO();
		
		DiaryFileDAO fileDao = new DiaryFileDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		try {
			
			fileDao.deleteFilesByNo(no, request);
			
			cnt = dao.deleteByNo(no);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", cnt);

	}

}
