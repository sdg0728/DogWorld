package command.purchase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.purchase.AjaxCardJSON;
import beans.purchase.CardDTO;
import command.main.Command;

public class ajaxCardCommand implements Command {

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
			CardDTO card = (CardDTO)request.getAttribute("card");
			List<CardDTO> cardList = new ArrayList<CardDTO>();
			
			AjaxCardJSON list = new AjaxCardJSON(); //response할 java객체
			
			if(card == null) {
				list.setStatus("FAIL");
			} else {
				cardList.add(card);
				list.setStatus("OK");
				list.setCount(1);
				list.setList(cardList);
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
