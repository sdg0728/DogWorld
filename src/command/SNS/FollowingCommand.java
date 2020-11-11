package command.SNS;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SNS.FriendsDTO;
import beans.SNS.WriteDAO;
import command.main.Command;


public class FollowingCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		WriteDAO dao = new WriteDAO(); 
		FriendsDTO [] fw_list = null;
		
		try {
			String following = request.getParameter("following"); 
			System.out.println("following"+following);
			
			fw_list = dao.selectByfwid(following);  
	
			System.out.println(Arrays.toString(fw_list));
			request.setAttribute("list", fw_list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
