

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.bdd.Utilisateur;
import com.octest.beans.User;
import com.octest.dao.*;
import com.octest.varglobale.varglobale;

/**
 * Servlet implementation class profil
 */
@WebServlet("/profil")
public class profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	public User compteConnect;
       
	public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
	
    public profil() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		varglobale compteConnect = (varglobale) getServletContext().getAttribute("varglobale");
		compteConnect.modification = false;
        if(compteConnect.compteConnect != null) {
        	request.setAttribute("modif", false );
	        request.setAttribute("identifiant", compteConnect.compteConnect.IDENTIFIANT );
	        request.setAttribute("prenom", compteConnect.compteConnect.PRENOM );
	        request.setAttribute("nom", compteConnect.compteConnect.NOM );
	        request.setAttribute("bestScore", compteConnect.compteConnect.BEST_SCORE );
	        request.setAttribute("lastScore", compteConnect.compteConnect.LAST_SCORE );
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
		varglobale compteConnect = (varglobale) getServletContext().getAttribute("varglobale");
        compteConnect.modification = !compteConnect.modification;
		request.setAttribute("modif", compteConnect.modification );
		if(compteConnect.modification == true) {
			if(compteConnect.compteConnect != null) {
		        request.setAttribute("identifiant", compteConnect.compteConnect.IDENTIFIANT );
		        request.setAttribute("prenom", compteConnect.compteConnect.PRENOM );
		        request.setAttribute("nom", compteConnect.compteConnect.NOM );
		        request.setAttribute("bestScore", compteConnect.compteConnect.BEST_SCORE );
		        request.setAttribute("lastScore", compteConnect.compteConnect.LAST_SCORE );
	        }
		}
		else {
			if(compteConnect.compteConnect != null) {        
		        String sIdentifiant = request.getParameter("identifiant");
		        String sPrenom = request.getParameter("prenom"); 
		        String sNom = request.getParameter("nom");
		        System.out.println(sIdentifiant);
		        System.out.println(sPrenom);
		        System.out.println(sNom);
		        compteConnect.compteConnect = utilisateurDao.modifProfil(compteConnect.compteConnect.IDENTIFIANT, sIdentifiant, sNom, sPrenom);
		        if(compteConnect.compteConnect != null) {
		        	request.setAttribute("identifiant", sIdentifiant );
			        request.setAttribute("prenom", sPrenom );
			        request.setAttribute("nom", sNom );
			        request.setAttribute("bestScore", compteConnect.compteConnect.BEST_SCORE );
			        request.setAttribute("lastScore", compteConnect.compteConnect.LAST_SCORE );
		        }
	        }
		}
        this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	}

}
