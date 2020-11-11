package command.userinfo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.DiaryDTO;
import beans.myPage.MyPetDAO;
import beans.userinfo.PetDTO;
import beans.userinfo.SignDAO;
import beans.userinfo.UserDTO;
import command.main.Command;

public class UserInfoViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		SignDAO dao = new SignDAO();
		MyPetDAO pdao = new MyPetDAO();
		UserDTO [] uArr = null;
		PetDTO [] pArr = null;
		
		String id = request.getParameter("id");
		
		try {
			uArr = dao.selectById(id);
			request.setAttribute("user", uArr);
			
			pArr = pdao.selectById(id);
			request.setAttribute("pet", pArr);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(pArr != null && pArr.length == 1) {
				
				// 이미지 파일 여부 세팅
				String realPath = "";
				String saveFolder = "upload/user";
				ServletContext context = request.getServletContext();
				realPath = context.getRealPath(saveFolder);
				
				for(PetDTO filearr : pArr) {
					String downloadFilePath = realPath + File.separator + filearr.getImg();
					System.out.println("downloadFilePath " + downloadFilePath);
					BufferedImage imgData = ImageIO.read(new File(downloadFilePath));
					System.out.println(imgData);
					if(imgData != null) {
						filearr.setImage(true);  // 이미지 여부 체크
					}
				}
				
				request.setAttribute("fileList", pArr);  
			}
		} catch (IOException e) {
			System.out.println("노이미지");
		}
	}

}
