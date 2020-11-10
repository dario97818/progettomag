package it.unirc.twb.progetto.servlet.admin;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Amministratore;
import it.unirc.twb.progetto.been.AmministratoreDAO;

/**
 * Servlet implementation class NuovoAdmin
 */
@WebServlet("/NuovoAdmin")
public class NuovoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NuovoAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		if(se.getAttribute("auth-admin")!=null)
		{
			Enumeration<String> n=request.getParameterNames();
			String pw = n.nextElement();
			AmministratoreDAO adao=new AmministratoreDAO();
			Amministratore a=new Amministratore();
			a.setPass(converti(pw));
			if(adao.Create(a))
			{
				request.getRequestDispatcher("/RichiediGestisciAdimn").forward(request, response);
			}
		}
		else
			response.sendRedirect("/Errore.jsp");



	}


	private String converti(String message) {
		String md5 = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");//Create MessageDigest object for MD5
			digest.update(message.getBytes(), 0, message.length());//Update input string in message digest
			md5 = new BigInteger(1, digest.digest()).toString(16);//Converts message digest value in base 16 (hex)

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}

}
