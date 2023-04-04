

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.bdd.Utilisateur;
import com.octest.beans.User;
import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;
import com.octest.forms.ConnexionForms;

/**
 * Servlet implementation class inscription
 */
@WebServlet("/inscription")
public class inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        request.setAttribute("utilisateurs", utilisateurDao.lister());
        this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        User utilisateur = new User();
        utilisateur.setIDENTIFIANT(request.getParameter("id"));
        utilisateur.setMDP(request.getParameter("mdp"));
        utilisateur.setPRENOM(request.getParameter("prenom"));
        utilisateur.setNOM(request.getParameter("nom"));
        utilisateur.setDATE_CREATION(java.time.LocalDate.now());
        utilisateur.setDATE_MODIF(java.time.LocalDate.now());
        utilisateur.setBEST_SCORE(0);
        utilisateur.setLAST_SCORE(0);
        
        System.out.println(utilisateur.IDENTIFIANT);
        System.out.println(utilisateur.MDP);
        System.out.println(utilisateur.NOM);
        System.out.println(utilisateur.PRENOM);
        System.out.println(utilisateur.DATE_CREATION);
        System.out.println(utilisateur.DATE_MODIF);
        System.out.println(utilisateur.BEST_SCORE);
        System.out.println(utilisateur.LAST_SCORE);

        
        utilisateurDao.ajouter(utilisateur);
        
        request.setAttribute("utilisateurs", utilisateurDao.lister());
		
        this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

}
