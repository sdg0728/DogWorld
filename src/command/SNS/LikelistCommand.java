
package command.SNS;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.SNS.LikeDTO;
import beans.SNS.WriteDAO;
import command.main.Command;


public class LikelistCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO(); 
		LikeDTO [] like_list = null;
		
		try {
			String user_id = request.getParameter("user_id"); 

			like_list = dao.likeselect(user_id);  
			request.setAttribute("list", like_list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
