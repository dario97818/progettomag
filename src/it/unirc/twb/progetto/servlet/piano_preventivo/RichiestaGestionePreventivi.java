package it.unirc.twb.progetto.servlet.piano_preventivo;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unirc.twb.progetto.been.Annuncio;
import it.unirc.twb.progetto.been.AnnuncioDAO;
import it.unirc.twb.progetto.been.Piano;
import it.unirc.twb.progetto.been.PianoDAO;
import it.unirc.twb.progetto.been.Preventivo;
import it.unirc.twb.progetto.been.PreventivoDAO;

/**
 * Servlet implementation class RichiestaGestionePreventivi
 */
@WebServlet("/RichiestaGestionePreventivi")
public class RichiestaGestionePreventivi extends HttpServlet {
	PianoDAO pianoDAO=new PianoDAO();
	PreventivoDAO preventivoDAO= new PreventivoDAO();
	AnnuncioDAO annuncioDAO=new AnnuncioDAO();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RichiestaGestionePreventivi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//restituirà una listaPreventiviUtente e una listaPianiUtente
		
		Preventivo preventivo =new Preventivo();
		Annuncio annuncio=new Annuncio();
		HttpSession se=request.getSession();
		if(se.getAttribute("auth")==null)
		{
			response.sendRedirect("/Errore.jsp");
		}
		int ID = (int)se.getAttribute("id_utente");
		
		//PARTE RELATIVA A TUTTI I PREVENTIVI RICHIESTI DAL UTENTE
		Vector<Preventivo> listaPreventivi=new Vector<Preventivo>();
		listaPreventivi=preventivoDAO.getPreventiviFromRichiedente(ID);
		//creo un vettore di array in cui metterò solo le informazioni utili all'utente
		//le informazioni saranno: id_preventivo; prezzo bloccato; numero mesi; data di generazione; id_annuncio e titolo dell'annuncio.
		Vector<String[]> listaPreventiviUtente=new Vector<String[]>();
		
		for(Preventivo p : listaPreventivi) {//ASSEMBLO IL VETTORE DA RESTITUIRE ALLA JSP
			String[] rowPreventiviUtente=new String[6];
			rowPreventiviUtente[0]=String.valueOf(p.getId_Preventivo());
			rowPreventiviUtente[1]=String.valueOf(p.getPrezzo_Bloccato());
			rowPreventiviUtente[2]=String.valueOf(p.getN_mesi());
			rowPreventiviUtente[3]=p.getData().toString();
			Piano pi=new Piano();
			pi.setId_Piano(p.getId_Piano());
			pi=pianoDAO.getPiano(pi);
			Annuncio an=new Annuncio();
			an.setId_annuncio(pi.getId_annuncio());
			an=annuncioDAO.getAnnuncio(an);
			rowPreventiviUtente[4]=String.valueOf(an.getId_annuncio());
			rowPreventiviUtente[5]=an.getTitolo();
			listaPreventiviUtente.add(rowPreventiviUtente);
		}
		
		
		
		
		//PARTE RELATIVA A TUTTI I PIANI GENERATI DALL'UTENTE
		Vector<Piano> listaPiani= new Vector<Piano>();
		listaPiani=pianoDAO.getPianoFromUtente(ID);
		//del piano l'utente visualizzerà:id del piano, percentuale del prezzo bloccato, max mesi, id dell'annuncio e titolo dell'annuncio
		Vector<String[]> listaPianiUtente=new Vector<String[]>();
		for (Piano p: listaPiani) {
			String[] rowPianiUtente=new String[5];
			rowPianiUtente[0]=String.valueOf(p.getId_Piano());
			rowPianiUtente[1]=String.valueOf(p.getPerc_anticipo())+"%";
			rowPianiUtente[2]=String.valueOf(p.getMax_mesi());
			Annuncio an=new Annuncio();
			an.setId_annuncio(p.getId_annuncio());
			an=annuncioDAO.getAnnuncio(an);
			rowPianiUtente[3]=String.valueOf(an.getId_annuncio());
			rowPianiUtente[4]=String.valueOf(an.getTitolo());
			listaPianiUtente.add(rowPianiUtente);
		}
		
		
	
		//RESTITUZIONE RISULTATI
		request.setAttribute("listaPiani", listaPianiUtente);
		request.setAttribute("listaPreventivi", listaPreventiviUtente);
		request.getRequestDispatcher("/Preventivi/VisualizzaGestionePreventivi.jsp").forward(request, response);
		
	}

}
