package it.unirc.twb.progetto.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class codifica
 */
@WebServlet("/codifica")
public class codifica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public codifica() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		


		String message=request.getParameter("testo");
		 String md5 = "";
	         
	       
	        try {
	            MessageDigest digest = MessageDigest.getInstance("MD5");//Create MessageDigest object for MD5
	            digest.update(message.getBytes(), 0, message.length());//Update input string in message digest
	            md5 = new BigInteger(1, digest.digest()).toString(16);//Converts message digest value in base 16 (hex)
	  
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }


		




		
		response.getWriter().append(md5);

		
	}

}
