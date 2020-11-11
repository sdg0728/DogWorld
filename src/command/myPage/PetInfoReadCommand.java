package command.myPage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.MyPetDAO;
import beans.myPage.ScheduleDAO;
import beans.myPage.ScheduleDTO;
import beans.userinfo.PetDTO;
import command.main.Command;

public class PetInfoReadCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		MyPetDAO dao = new MyPetDAO();
		
		PetDTO [] arr = null;
		
		String id = request.getParameter("id");
		
		try {
			arr = dao.selectById(id);
			request.setAttribute("pet", arr);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(arr != null && arr.length == 1) {
				
				// 이미지 파일 여부 세팅
				String realPath = "";
				String saveFolder = "upload/user";
				ServletContext context = request.getServletContext();
				realPath = context.getRealPath(saveFolder);
				
				for(PetDTO filearr : arr) {
					String downloadFilePath = realPath + File.separator + filearr.getImg();
					System.out.println("downloadFilePath: "+ downloadFilePath);
					BufferedImage imgData = ImageIO.read(new File(downloadFilePath));
					if(imgData != null) {
						filearr.setImage(true);  // 이미지 여부 체크
					}
				}
				
				request.setAttribute("file", arr);  
			}
		} catch (IOException e) {
			System.out.println("이미지 못 찾는다!");
		}

	}

}
