package it.unirc.twb.progetto.servlet.annuncio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;

/**
 * Servlet implementation class EliminaAnnuncio
 */
@WebServlet("/EliminaAnnuncio")
public class EliminaAnnuncio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaAnnuncio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		Annuncio a=new Annuncio();
		a.setId_annuncio(Integer.valueOf(request.getParameter("id")));
		AnnuncioDAO adao=new AnnuncioDAO();
		a=adao.getAnnuncio(a);
		if(se.getAttribute("auth-admin")!=null)
		{
			if(adao.Delete(a))
			{
				response.sendRedirect("/RicercaAnnuncio?titolo=&tipologia=tutti");
			}
		}
		else
		{
			if((int)se.getAttribute("id_utente")==a.getId_utente())
			{
				if(adao.Delete(a))
				{
					response.sendRedirect("/RicercaAnnuncio?titolo=&tipologia=tutti");
				}
			}
		}
	}
	

}
