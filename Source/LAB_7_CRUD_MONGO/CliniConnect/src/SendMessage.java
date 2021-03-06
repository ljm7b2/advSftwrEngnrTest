

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class SendMessage
 */
@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Boolean isVerified = false;
    HttpServletRequest request2;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject info = new JSONObject();
		
		verifyLogin();
		try{
			if(isVerified){
				//response.getWriter().append("Served Success at: ").append(request.getContextPath());
				info.put("login", "success");
			}
			else{
				//response.getWriter().append("Served fail at: ").append(request.getContextPath());
				info.put("login", "failure");
			}
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		response.setContentType("application/json");     
		PrintWriter out = response.getWriter();  
		out.print(info);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request2 = request;
		isVerified = false;
		doGet(request, response);
	}
	
	
	void verifyLogin(){
		
		MongoClientURI uri = new MongoClientURI("mongodb://admin:admin@ds019698.mlab.com:19698/clinicdb");
		MongoClient mongoClient = new MongoClient(uri);
		MongoDatabase db = mongoClient.getDatabase("clinicdb");
		
		FindIterable<Document> iterable = db.getCollection("users").find();		
		
		iterable.forEach(new Block<Document>() {
			
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		        try {
					isVerified(document);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
		});
		mongoClient.close();
	}
	
	void isVerified(Document loginInfo) throws IOException{

		
		if(loginInfo.get("username").equals(request2.getParameter("username"))){
			System.out.println(loginInfo);
			System.out.println(request2.getParameter("username"));
			isVerified = true;
			String apiKey = "AIzaSyD5T5ouxL80ESZIRBfVmNiKob1Cwt3biIc";
			int numOfRetries = 3;
			Sender sender = new Sender(apiKey);
			Message message = new Message.Builder()
			    .addData("message", "Hi " + request2.getParameter("username")+ " this is a reminder for your upcoming appointment" +
			    " at " + request2.getParameter("time") +
			    " on " + request2.getParameter("message")+ ". We look forward to your visit! " +
			    request2.getParameter("extraMsg")) 
			    .build();
			Result result = sender.send(message, loginInfo.get("token").toString(), numOfRetries);
		}

		
	}

}
