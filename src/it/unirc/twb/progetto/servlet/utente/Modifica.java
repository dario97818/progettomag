package it.unirc.twb.progetto.servlet.utente;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Utente;
import it.unirc.twb.progetto.been.UtenteDAO;

/**
 * Servlet implementation class Modifica
 */
@WebServlet("/Modifica")
public class Modifica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Modifica() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s=request.getSession();
		int id=Integer.valueOf(request.getParameter("id"));
		if(s.getAttribute("auth-admin")==null)
		{
			int p=(int)s.getAttribute("id_utente");
			if(id!=p)
			{
				response.sendRedirect("/index.jsp");
			}
		}
		Utente u =new Utente();
		UtenteDAO udao=new UtenteDAO();
		
		String nome =request.getParameter("name");
		String cognome =request.getParameter("cognome");
		String email =request.getParameter("email");
		String pwd =request.getParameter("pwd");
		String tel =request.getParameter("tel");
		String ind =request.getParameter("ind");
		boolean attivo;
		String startDateStr = request.getParameter("data");
		if(request.getParameter("utente")!=null) {
			attivo=true;
		}
		else
		{
			if(request.getParameter("attivo")==null)
				attivo=false;
			else
				attivo=true;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date startDate=null;
		try {
			startDate = sdf.parse(startDateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
		u.setId(id);
		u.setNome(nome);
		u.setCognome(cognome);
		u.setDatadinascita(sqlDate);
		u.setEmail(email);
		u.setPass(pwd);
		u.setTel(tel);
		u.setIndirizzo(ind);
		u.setAttivo(attivo);
		Utente prova=new Utente();
		prova.setId(id);
		prova=udao.getUtente(prova);
		//controllo password
		if(u.getPass().equals(""))
		{
			u.setPass(prova.getPass());
		}
		else
		{
			u.setPass(converti(pwd));
		}


		if(udao.ControlloEmail(u) || (prova.getEmail().equals(email))) {//controllo se l'email nuova esiste oppure non ha cambiato email
			if(udao.modificaUtente(u))
			{
				response.sendRedirect("/index.jsp");
			}

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
