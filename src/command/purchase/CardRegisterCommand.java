package command.purchase;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.purchase.GoodsDAO;
import command.main.Command;

public class CardRegisterCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		GoodsDAO dao = new GoodsDAO();
		int cnt = 0;
		
		String card_num = request.getParameter("card_num");
		int cvc = Integer.parseInt(request.getParameter("cvc"));
		int card_lim_year = Integer.parseInt(request.getParameter("year"));
		int card_lim_month = Integer.parseInt(request.getParameter("month"));
		int password = Integer.parseInt(request.getParameter("password"));
		String user_id = request.getParameter("user_id");
		int bank = Integer.parseInt(request.getParameter("bank"));
		
		try {
			cnt = dao.checkCard(user_id);
			
			dao = new GoodsDAO();
			if(cnt == 0) {
				cnt = dao.insertCard(card_num, cvc, card_lim_year, card_lim_month, password, user_id, bank);
				cnt = 1;
			} else {
				cnt = dao.updateCard(card_num, cvc, card_lim_year, card_lim_month, password, user_id, bank);
				cnt = 2;
			}
			
			request.setAttribute("cardReg", cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
