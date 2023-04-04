

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
		User connectCompte = compteConnect.compteConnect;
        if(connectCompte != null) {
    		System.out.println("test");
	        request.setAttribute("identifiant", connectCompte.IDENTIFIANT );
	        request.setAttribute("prenom", connectCompte.PRENOM );
	        request.setAttribute("nom", connectCompte.NOM );
	        request.setAttribute("bestScore", connectCompte.BEST_SCORE );
	        request.setAttribute("lastScore", connectCompte.LAST_SCORE );
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
		varglobale compteConnect = (varglobale) getServletContext().getAttribute("varglobale");
		User connectCompte = compteConnect.compteConnect;
        if(connectCompte != null) {
	        request.setAttribute("identifiant", connectCompte.IDENTIFIANT );
	        request.setAttribute("prenom", connectCompte.PRENOM );
	        request.setAttribute("nom", connectCompte.NOM );
	        request.setAttribute("bestScore", connectCompte.BEST_SCORE );
	        request.setAttribute("lastScore", connectCompte.LAST_SCORE );
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	}

}
