package command.myPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import command.main.Command;

public class BreedInfoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String reqType = request.getParameter("reqType");
		if(reqType == null) reqType = "json";
			
		try {
			responesJSON(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void responesJSON(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		
		String kind = (String) request.getAttribute("kind");
		
		String clientID="x2ZcgeUwW5KYJ6lvOcNH"; //네이버 개발자 센터에서 발급받은 clientID입력
        String clientSecret = "l1rN4O6ySD";        //네이버 개발자 센터에서 발급받은 clientSecret입력
        URL url = new URL("https://openapi.naver.com/v1/search/encyc.json?query="+URLEncoder.encode(kind, "UTF-8")); //API 기본정보의 요청 url을 복사해오고 필수인 query를 적어줍니당! 
        
        URLConnection urlConn=url.openConnection(); //openConnection 해당 요청에 대해서 쓸 수 있는 connection 객체 
        
        urlConn.setRequestProperty("X-Naver-Client-ID", clientID);
        urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
    
        StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		String result= sb.toString();
		
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject) parser.parse(result); 
		JSONArray parse_item = (JSONArray) obj.get("items");
		
		JSONObject list;
		
		ArrayList<Map> arr = new ArrayList<Map>();
		
		for(int i = 0 ; i < parse_item.size(); i++) {
			list = (JSONObject) parse_item.get(i);
			Object title = list.get("title");
			Object link = list.get("link");
			Object thumbnail = list.get("thumbnail");
			Object description = list.get("description");
			if(list.get("link").toString().indexOf("cid=40942") > -1) {
				Map map = new HashMap();
				
				map.put("title",title);
				map.put("link",link);
				map.put("description",description);
				map.put("thumbnail",thumbnail);
				
				arr.add(map);
				
				System.out.println("title: "+ title);
				System.out.println("link: "+ link);
				System.out.println("description: "+ description);
				System.out.println("thumbnail: "+ thumbnail);
			}
		}
		 
		request.setAttribute("data", arr);
		
	}

}
