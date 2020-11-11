package command.purchase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class WriteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		GoodsDAO dao = new GoodsDAO();
		// 1. MultipartRequest 객체 생성 --> 파일 업로드 됨
				ServletContext context = request.getServletContext();
				
				//서블릿 산의 upload폴더 경로 가져오기
				String saveDirectory = context.getRealPath("upload/purchase");
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
				String image = "";
				
				while(names.hasMoreElements()) {
					String name = (String)names.nextElement();
					String fileSystemName = multi.getFilesystemName(name);
					
					System.out.println("첨부파일: "+fileSystemName);
					
					if(fileSystemName != null) {
						image = "../../upload/purchase/" + fileSystemName;
					}else {
						image = "../../upload/purchase/noimg.gif";
					}
				}
		
		String user_id = multi.getParameter("user_id");
		int goods_id = Integer.parseInt(multi.getParameter("goods_id"));
		String content = multi.getParameter("content");
		
		System.out.println(user_id + " " + goods_id + " " + content + " " + image);
		
		try {
			//첨부파일 정보도 같이 INSERT
			cnt = dao.insertReview(user_id, goods_id, content, image);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("insert", cnt);
		request.setAttribute("goods_id", goods_id);
	}

}
