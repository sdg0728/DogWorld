
package command.SNS;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SNS.FriendsDTO;
import beans.SNS.WriteDAO;
import command.main.Command;


public class FollowerCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO(); 
		FriendsDTO [] f_list = null;
		
		try {
			String follower = request.getParameter("follower"); 
			f_list = dao.selectByfid(follower);  
	
			System.out.println(Arrays.toString(f_list));
			request.setAttribute("list", f_list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
