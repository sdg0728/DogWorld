package command.SNS;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.SNS.WriteDAO;
import beans.SNS.WriteDTO;
import beans.userinfo.PetDTO;
import beans.userinfo.UserDTO;
import command.main.Command;

public class SearchCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO();  // DAO 객체 생성
		UserDTO [] arr = null;
		PetDTO [] arr2 = null;
		String kind = request.getParameter("name");
		System.out.println("name:????" + kind);
		try {
			
			arr = dao.selectBySearchUid(kind);  // 트랜잭션 수행
			
			dao = new WriteDAO();
			arr2 = dao.selectBySearchKind(kind);
			
		
			if(arr.length != 0) {
				request.setAttribute("list", arr); 
			} else if(arr2.length != 0) {
				request.setAttribute("list2", arr2);
			} else {
				request.setAttribute("list3", null);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
