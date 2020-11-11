package command.purchase;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import beans.purchase.AjaxBoardCartJSON;
import beans.purchase.GoodsDTO;
import beans.purchase.PurchaseDTO;
import command.main.Command;

public class ajaxCartCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// parameter 받아오기, 없으면 json response (디폴트)
		String reqType = (String) request.getParameter("reqType");

		if (reqType == null) reqType = "json";

		switch (reqType) {
		case "json":
			responseJSON(request, response); // Jackson 사용
			break;

		default:
			responseJSON(request, response); // Jackson 사용
			break;
		}
	}

	
	private void responseJSON(HttpServletRequest request, HttpServletResponse response) {
		GoodsDTO[] goodsArr = (GoodsDTO[])request.getAttribute("goods");
		PurchaseDTO[] cartArr = (PurchaseDTO[])request.getAttribute("cart");
		
		AjaxBoardCartJSON list = new AjaxBoardCartJSON(); //response할 java객체
		
		if(goodsArr == null) {
			list.setStatus("FAIL");
		} else {
			list.setStatus("OK");
			list.setCount(1);
			list.setList(Arrays.asList(goodsArr));
			list.setCart(Arrays.asList(cartArr));
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String jsonString = mapper.writeValueAsString(list);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
