package command.myPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;


import command.main.Command;


public class WeatherAjaxCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String reqType = request.getParameter("reqType");
		if(reqType == null) reqType = "json";
		
		try {
			responesJSON(request, response);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void responesJSON(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		
		String plusJuso = (String) request.getAttribute("juso");
		
		
		String fullJuso = plusJuso.split("&")[1];
		
		System.out.println(fullJuso);
		System.out.println(fullJuso.split(" ")[0]+ " " + fullJuso.split(" ")[1] + " " + fullJuso.split(" ")[2]);
		
		// 1. 날짜 표시 format
		SimpleDateFormat  formatter = new SimpleDateFormat("yyyyMMdd");    
		// 2. 오늘날짜 Data 클래스로 구하기(기준날짜가 오늘이 아니면 생략가능)
		Date today = new Date();
		// 3. 오늘날짜 format에 맞춰서 String 으로 변경(기준날짜가 오늘이 아니면 생략가능)
		String date =  formatter.format(today);
		// 4. 기준이 되는 날짜(format에 맞춘)
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		// 6. 선언된 Calendar 클래스에 기준 날짜 설정
		cal.setTime(today);
		// 7. 하루전으로 날짜 설정
		cal.add(Calendar.DATE, -1);
		// 8. 하루전으로 설정된 날짜를 설정된 format으로 String 타입 변경
		String y_date = formatter.format(cal.getTime());
		
		System.out.println(y_date);
		
		// 받아온 주소
		String juso = fullJuso.split(" ")[0]+ " " + fullJuso.split(" ")[1] + " " + fullJuso.split(" ")[2];
		
		// 좌표얻기
		String lo_result;
		String areaTop= juso.split(" ")[0];
		String areaMdl= juso.split(" ")[1];
		String areaLeaf= juso.split(" ")[2];
		String code="";
		String x="";
		String y="";
		
		URL lo_url;
		BufferedReader br;
		URLConnection lo_conn;
		
		JSONParser lo_parser;
		JSONArray jArr;
		JSONObject jobj;
		
        //시 검색
		lo_url = new URL("http://www.kma.go.kr/DFSROOT/POINT/DATA/top.json.txt");
		lo_conn = lo_url.openConnection();
		br = new BufferedReader(new InputStreamReader(lo_conn.getInputStream()));
		lo_result = br.readLine().toString();
		br.close();
		//System.out.println(result);
		
		lo_parser = new JSONParser(); 
        jArr = (JSONArray) lo_parser.parse(lo_result);
        for(int i = 0 ; i < jArr.size(); i++) {
        	jobj = (JSONObject) jArr.get(i);
        	if(jobj.get("value").equals(areaTop)) {
        		code=(String)jobj.get("code");
        		System.out.println(areaTop+"코드 : "+code);
        		break;
        	}
        }
        
        //구 검색
        lo_url = new URL("http://www.kma.go.kr/DFSROOT/POINT/DATA/mdl."+code+".json.txt");
        lo_conn = lo_url.openConnection();
		br = new BufferedReader(new InputStreamReader(lo_conn.getInputStream()));
		lo_result = br.readLine().toString();
		br.close();
		//System.out.println(result);
		
		lo_parser = new JSONParser(); 
        jArr = (JSONArray) lo_parser.parse(lo_result);
        
        for(int i = 0 ; i < jArr.size(); i++) {
        	jobj = (JSONObject) jArr.get(i);
        	if(jobj.get("value").equals(areaMdl)) {
        		code=(String)jobj.get("code");
        		System.out.println(areaMdl+"코드 : "+code);
        		break;
        	}
        }
        
        //동 검색
        lo_url = new URL("http://www.kma.go.kr/DFSROOT/POINT/DATA/leaf."+code+".json.txt");
        lo_conn = lo_url.openConnection();
		br = new BufferedReader(new InputStreamReader(lo_conn.getInputStream()));
		lo_result = br.readLine().toString();
		br.close();
		
		lo_parser = new JSONParser(); 
        jArr = (JSONArray) lo_parser.parse(lo_result);
        
        if(areaMdl.equals(juso.split(" ")[1])) {
        	for(int i = 0 ; i < jArr.size(); i++) {
            	jobj = (JSONObject) jArr.get(i);
            	
            	String leaf1=areaLeaf.substring(0,2);
            	//System.out.println(leaf1);
        		String leaf2=areaLeaf.substring(areaLeaf.length()-3,areaLeaf.length()-2);
        		String leaf3=areaLeaf.substring(areaLeaf.length()-2,areaLeaf.length());
        		
        		
        		Pattern pattern = Pattern.compile(leaf1);
        		Matcher matcher = pattern.matcher((String) jobj.get("value"));
            	if(matcher.find()) {
            		x=(String)jobj.get("x");
            		y=(String)jobj.get("y");
            		System.out.println(areaLeaf+"의 x값 : "+x+", y값 :"+y);
            		break;
            	}
            }
        }else {
        	for(int i = 0 ; i < jArr.size(); i++) {
            	jobj = (JSONObject) jArr.get(i);
            	if(jobj.get("value").equals(areaLeaf)) {
            		x=(String)jobj.get("x");
            		y=(String)jobj.get("y");
            		System.out.println(areaLeaf+"의 x값 : "+x+", y값 :"+y);
            		break;
            	}
            }
        }
		

		
		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getVilageFcst";
		// 홈페이지에서 받은 키
		String serviceKey = "PDd%2FW3ZKg2JqD6zVGpIPALEQZ3LXTxBIVdtT1lZJIQiYBTIqZ2zRPTqGW5fwgDYGq8gmF6Qd0YzY4M%2Fa5Sdrwg%3D%3D";
		String nx = x;	//위도
		String ny = y;	//경도
		String baseDate = y_date;	//조회하고싶은 날짜 이 예제는 어제 날짜 입력해 주면 됨
		String baseTime = "2300";	//API 제공 시간을 입력하면 됨
		String dataType = "json";	//타입 xml, json 등등 ..
		String numOfRows = "76";	//한 페이지 결과 수 
		
		//전날 23시 부터 153개의 데이터를 조회하면 오늘과 내일의 날씨를 알 수 있음
		
		
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
		urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
		urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
		urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
		urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8"));	/* 타입 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));	/* 한 페이지 결과 수 */
		
		/*
		 * GET방식으로 전송해서 파라미터 받아오기
		 */
		URL url = new URL(urlBuilder.toString());
		//어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
		//System.out.println(url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		String result= sb.toString();
		
		//Json parser를 만들어 만들어진 문자열 데이터를 객체화 
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject) parser.parse(result); 
		 //response 키를 가지고 데이터를 파싱 
		JSONObject parse_response = (JSONObject) obj.get("response"); 
		 //response 로 부터 body 찾기
		JSONObject parse_body = (JSONObject) parse_response.get("body"); 
		 //body 로 부터 items 찾기 
		JSONObject parse_items = (JSONObject) parse_body.get("items");

		// items로 부터 itemlist 를 받기 
		JSONArray parse_item = (JSONArray) parse_items.get("item");
		String category;
		JSONObject weather; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용
		// 카테고리와 값만 받아오기
		String day="";
		String time="";
		
		ArrayList<Map> arr = new ArrayList<Map>();
	 
		for(int i = 0 ; i < parse_item.size(); i++) {
			
			weather = (JSONObject) parse_item.get(i);
			Object fcstValue = weather.get("fcstValue");
			Object fcstDate = weather.get("fcstDate");
			Object fcstTime = weather.get("fcstTime");
			//double형으로 받고싶으면 아래내용 주석 해제
			//double fcstValue = Double.parseDouble(weather.get("fcstValue").toString());
			category = (String)weather.get("category"); 
			// 출력		
			if(!day.equals(fcstDate.toString())) {
				day=fcstDate.toString();
			}
			if(!time.equals(fcstTime.toString())) {
				time=fcstTime.toString();
//				System.out.println(day+"  "+time);
			}
			
			Map map = new HashMap();
			
			map.put("category",category);
			map.put("fcstValue",fcstValue);
			map.put("fcstDate",fcstDate);
			map.put("fcstTime",fcstTime);
			
			arr.add(map);
			
//			System.out.print("\tcategory : "+ category);
//			System.out.print(", fcst_Value : "+ fcstValue);
//			System.out.print(", fcstDate : "+ fcstDate);
//			System.out.println(", fcstTime : "+ fcstTime);
		}
		
		
		request.setAttribute("data", arr);
	
		/*
		 * 항목값	항목명	단위
		 * POP	강수확률	 %
		 * PTY	강수형태	코드값
		 * R06	6시간 강수량	범주 (1 mm)
		 * REH	습도	 %
		 * S06	6시간 신적설	범주(1 cm)
		 * SKY	하늘상태	코드값
		 * T3H	3시간 기온	 ℃
		 * TMN	아침 최저기온	 ℃
		 * TMX	낮 최고기온	 ℃
		 * UUU	풍속(동서성분)	 m/s
		 * VVV	풍속(남북성분)	 m/s
		 * WAV	파고	 M
		 * VEC	풍향	 m/s
		 * WSD	풍속	1

		 */
				
	}

}
