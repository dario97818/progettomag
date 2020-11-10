package it.unirc.twb.progetto.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.twb.progetto.been.Utente;
import it.unirc.twb.progetto.been.UtenteDAO;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	UtenteDAO uDAO=new UtenteDAO();

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrazione() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utente u =new Utente();
		String nome =request.getParameter("name");
		String cognome =request.getParameter("cognome");
		String email =request.getParameter("email");
		String pwd =request.getParameter("pwd");
		String tel =request.getParameter("tel");
		String ind =request.getParameter("ind");
		String startDateStr = request.getParameter("data");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		java.util.Date startDate=null;
		try {
			startDate = sdf.parse(startDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDate a=LocalDate.now();
		
		
		java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
		LocalDate b=LocalDate.of(sqlDate.getYear()+1900,sqlDate.getMonth()+1,sqlDate.getDay());
		
		if(Period.between(b, a).getYears()<18)
			response.sendRedirect("/Errore.jsp");
		
		u.setNome(nome);
		u.setCognome(cognome);
		u.setDatadinascita(sqlDate);
		u.setEmail(email);
		u.setPass(converti(pwd));
		u.setTel(tel);
		u.setIndirizzo(ind);
		if(uDAO.ControlloEmail(u)) {


			if (uDAO.salvaUtente(u))
				response.sendRedirect("/index.jsp");
			else{
				Cookie c=new Cookie("reggfallita","true");
				response.addCookie(c);
				request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
				//response.sendRedirect("/registrazione.jsp");


			}
		}
		else
		{
			Cookie c=new Cookie("email","true");
			response.addCookie(c);
			response.sendRedirect("/registrazione.jsp");
		}

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
