

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

 

/**
 * Servlet implementation class SMSInfoServlet
 */

public class SMSInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SMSInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String studentId = req.getParameter("sid");
		String classId = req.getParameter("class");
		String section = req.getParameter("section");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String contact = req.getParameter("contact");
		String msg = req.getParameter("message");
		String gender = req.getParameter("student_gender");
		String allSubjects = "";
		String[] subjects = req.getParameterValues("subjects");

		for (int i = 0; i < subjects.length; i++) {
			allSubjects = allSubjects+","+subjects[i];
		}
		//saveSMSInfo(studentId,classId,section,fname+" "+lname,contact,msg,gender,allSubjects);
		sendMessage(fname+" "+lname, contact, msg);
		out.println("<html><body>");
		out.println("Your Message has been sent to "+fname+" "+lname+"'s Parent");
		out.println("</body></html>");
	}

//	public static void saveSMSInfo(String sid,String className,String sectionName,String name,
//			String contactNo,String mesg,String gender,String subjects){
//		
//		Student student = new Student();
//        student.setStudent_Id(sid);
//        student.setClassName(className);
//        student.setSection(sectionName);
//        student.setName(name);
//        student.setGender(gender);
//        student.setParent_contact(contactNo);
//        student.setMessage(mesg);
//        student.setSubjects(subjects);
//         
//
//        SessionFactory sessionFactory = 
//                new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(student);
//        session.getTransaction().commit();
//        session.close();
//        sessionFactory.close();
//	}
	
	
	public static void sendMessage(String pname, String contact, String message) {
		try {
			message = URLEncoder.encode(message,"UTF-8");
			contact = contact.trim();
			String gupshupURL = "http://enterprise.smsgupshup.com/GatewayAPI/rest?method=SendMessage&send_to="+contact+"&msg="+message+"&msg_type=TEXT&userid=2000164615&auth_scheme=plain&password=b9gXWd&v=1.1&format=text";
			URL url = new URL(gupshupURL.trim());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.connect();
			System.out.println("URL Finally :" + url);
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				buffer.append(line).append("\n");
			}
			System.out.println(buffer.toString());
			rd.close();
			conn.disconnect();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
