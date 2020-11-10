package it.unirc.twb.progetto.servlet.piano_preventivo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.twb.progetto.been.PreventivoDAO;

/**
 * Servlet implementation class testing_preventivo
 */
@WebServlet("/testing_preventivo")
public class testing_preventivo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testing_preventivo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int n_mesi=Integer.valueOf(request.getParameter("max_mesi"));
		int id_piano=Integer.valueOf(request.getParameter("id_piano"));
		float prezzo_bloc=Float.valueOf(request.getParameter("prezzo_bloccato")); 
		response.getWriter().append("mesi scelti: " + n_mesi+"\n"+"id del piano: " + id_piano+"\n"+"prezzo bloccato: " + prezzo_bloc+"\n");
		PreventivoDAO pDAO=new PreventivoDAO();
		pDAO.function_Calcola(n_mesi, id_piano, prezzo_bloc);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	String stringa=(String)request.getAttribute("max_mesi"); 
			int n_mesi=Integer.valueOf(request.getParameter("max_mesi"));
		int id_piano=Integer.valueOf(request.getParameter("id_piano"));
		float prezzo_bloc=Float.valueOf(request.getParameter("prezzo_bloccato")); 
		response.getWriter().append("mesi scelti: " + n_mesi+"\n"+"id del piano: " + id_piano+"\n"+"prezzo bloccato: " + prezzo_bloc+"\n");
	//	PreventivoDAO pDAO=new PreventivoDAO();
	//	pDAO.function_Calcola(n_mesi, id_piano, prezzo_bloc);
		System.out.println(n_mesi);
		
	}

}
