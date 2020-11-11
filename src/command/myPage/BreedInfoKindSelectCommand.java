package command.myPage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.myPage.MyPetDAO;
import beans.userinfo.PetDTO;
import command.main.Command;

public class BreedInfoKindSelectCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		MyPetDAO dao = new MyPetDAO();
		
		PetDTO [] arr = null;
		
		String id = request.getParameter("id");
		try {
			arr = dao.selectById(id);
			
			String kind = arr[0].getDogBreed();
			
			request.setAttribute("kind", kind);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
