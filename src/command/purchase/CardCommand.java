package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.CardDTO;
import beans.purchase.GoodsDAO;
import command.main.Command;

public class CardCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		CardDTO dto = null;
		
		String user_id = request.getParameter("user_id");
		
		try {
			dto = dao.selectCard(user_id);
			
			request.setAttribute("card", dto);
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
}
