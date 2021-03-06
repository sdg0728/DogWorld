package command.purchase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class GoodsInsertCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
GoodsDAO dao = new GoodsDAO();
		
		ServletContext context = request.getServletContext();
		
		//서블릿 산의 upload폴더 경로 가져오기
		String saveDirectory = context.getRealPath("upload/goods");
		System.out.println("업로드 경로: "+saveDirectory);
		
		int maxPostSize = 5 * 1024 * 1024; //최대 5M
		String encoding = "UTF-8"; //인코딩
		FileRenamePolicy policy = new DefaultFileRenamePolicy(); //중복 파일명에 대한 rename 정책
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(
					request,
					saveDirectory,
					maxPostSize,
					encoding,
					policy
					);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//--------------------------------
		// 2. 업로드된 파일의 '원본이름'과 '저장된 이름' 받아오기
		
		Enumeration<String> names = multi.getFileNames(); //type='file' 요소 name들 추출
		String[] image = new String[2];
		
		int i = 0;
		while(names.hasMoreElements()) {
			String name = (String)names.nextElement();
			String fileSystemName = multi.getFilesystemName(name);
			
			System.out.println("첨부파일: "+fileSystemName);
			
			if(fileSystemName != null) {
				image[i] = "../../upload/goods/" + fileSystemName;
			}
			i++;
		}
		String img = image[1];
		String detail_img = image[0];
		
		int price = Integer.parseInt(multi.getParameter("price"));
		int category = Integer.parseInt(multi.getParameter("category"));
		int count = Integer.parseInt(multi.getParameter("count"));
		String name = multi.getParameter("name");
		
		try {
			cnt = dao.insertGoods(name, price, img, detail_img, category, count);
			request.setAttribute("insertGoods", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
