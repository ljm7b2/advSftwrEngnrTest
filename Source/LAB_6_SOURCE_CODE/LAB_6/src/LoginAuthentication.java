

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.client.FindIterable;

/**
 * Servlet implementation class LoginAuthentication
 */
@WebServlet("/LoginAuthentication")
public class LoginAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Boolean isVerified = false;
    HttpServletRequest request2;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAuthentication() {
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
		//DB db = mongoClient.getDB(uri.getDatabase());
//		db.getCollection("users").insertOne(new Document()
//				.append("username", "luke")
//				.append("password", "jello"));
		
		FindIterable<Document> iterable = db.getCollection("users").find();
		
		
		iterable.forEach(new Block<Document>() {
			
		    @Override
		    public void apply(final Document document) {
		        System.out.println(document);
		        isVerified(document);
		    } 
		});
		mongoClient.close();
	}
	
	void isVerified(Document loginInfo){

		
		if(loginInfo.get("username").equals(request2.getParameter("username")) &&
				loginInfo.get("password").equals(request2.getParameter("password"))){
			System.out.println(loginInfo);
			System.out.println(request2.getParameter("username"));
			System.out.println(request2.getParameter("password"));
			isVerified = true;
		}

		
	}
}
