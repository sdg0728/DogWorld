package command.myPage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.DiaryDAO;
import beans.myPage.DiaryDTO;

import command.main.Command;

public class DiaryViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		DiaryDAO dao = new DiaryDAO();
		DiaryDTO [] arr = null;
		
		int no = Integer.parseInt(request.getParameter("no"));
				
		try {
			arr = dao.selectByNo(no);
			request.setAttribute("result", arr);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(arr != null && arr.length == 1) {
				
				// 이미지 파일 여부 세팅
				String realPath = "";
				String saveFolder = "upload/diary";
				ServletContext context = request.getServletContext();
				realPath = context.getRealPath(saveFolder);
				
				for(DiaryDTO filearr : arr) {
					String downloadFilePath = realPath + File.separator + filearr.getImg();
					System.out.println("downloadFilePath " + downloadFilePath);
					BufferedImage imgData = ImageIO.read(new File(downloadFilePath));
					System.out.println(imgData);
					if(imgData != null) {
						filearr.setImage(true);  // 이미지 여부 체크
					}
				}
				
				request.setAttribute("fileList", arr);  
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
