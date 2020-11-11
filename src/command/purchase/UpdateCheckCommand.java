package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class UpdateCheckCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		int cnt = 0;
		
		int purchase_id = Integer.parseInt(request.getParameter("purchase_id"));
		int p_check = Integer.parseInt(request.getParameter("p_check"));
		
		try {
			cnt += dao.updateCheck(purchase_id, p_check);
			
			dao = new GoodsDAO();
			cnt += dao.updateAdminCheck(purchase_id, p_check);
			
			request.setAttribute("updateChk", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
