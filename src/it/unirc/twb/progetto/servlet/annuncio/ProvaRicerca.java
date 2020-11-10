package it.unirc.twb.progetto.servlet.annuncio;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;

/**
 * Servlet implementation class ProvaRicerca
 */
@WebServlet("/ProvaRicerca")
public class ProvaRicerca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProvaRicerca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AnnuncioDAO aDAO =new AnnuncioDAO();
		Vector<Annuncio> annunci = aDAO.getAnnunci();
		request.setAttribute("risultatiRicerca", annunci);
		request.getRequestDispatcher("/Annuncio/RisultatiRicerca.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
