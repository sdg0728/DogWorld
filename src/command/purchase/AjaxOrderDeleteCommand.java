package command.purchase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.purchase.AjaxBoardCartJSON;
import beans.purchase.AjaxOrderDelJSON;
import beans.purchase.GoodsDTO;
import beans.purchase.PurchaseDTO;
import command.main.Command;

public class AjaxOrderDeleteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
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
		int cnt = (int)request.getAttribute("orderDel");
		
		AjaxOrderDelJSON list = new AjaxOrderDelJSON(); //response할 java객체
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(cnt);
		
		if(cnt == 0) {
			list.setStatus("FAIL");
		} else {
			list.setStatus("OK");
			list.setCount(1);
			list.setList(arr);
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
