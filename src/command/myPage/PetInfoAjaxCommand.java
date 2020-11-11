package command.myPage;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.userinfo.PetDTO;
import command.main.Command;

public class PetInfoAjaxCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String reqType = request.getParameter("reqType");
		if(reqType == null) reqType = "json";
		
		responesJSON(request, response);

	}

	private void responesJSON(HttpServletRequest request, HttpServletResponse response) {
		
		PetDTO [] arr = (PetDTO [])request.getAttribute("pet");
		JSONObject jsonOutput = new JSONObject();  // 최종 결과는 object
		
		int count = (arr== null) ? 0 : arr.length;
		jsonOutput.put("count", count);
		
		
		JSONArray dataArr = new JSONArray(); // array  
		for(int i = 0; i < count; i++) {
			JSONObject dataObj = new JSONObject();
			dataObj.put("petId", arr[i].getPetId());
			dataObj.put("petName", arr[i].getPetName());
			dataObj.put("dogBreed", arr[i].getDogBreed());
			dataObj.put("petAge", arr[i].getPetAge());
			dataObj.put("img", arr[i].getImg());
			dataObj.put("id", arr[i].getId());
			
			// array 에 추가
			dataArr.put(dataObj);
			
		} // end for
				
		jsonOutput.put("data", dataArr);
		jsonOutput.put("status", "OK");
		
		
		try {
			String jsonString = jsonOutput.toString();  //  JSON object 를 문자열로 변환
			response.setContentType("application/json; charset=utf-8");  // MIME 설정
			response.getWriter().write(jsonString); //  response 내보내기
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
