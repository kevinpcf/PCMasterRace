package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AddressBean;
import model.AddressDS;
import model.PaymentBean;
import model.PaymentDS;
import model.UserBean;
import model.UserDS;

@WebServlet("/LoginHandling")
public class LoginHandling extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UserDS modelUser = new UserDS();
	static AddressDS modelAddress = new AddressDS();
	static PaymentDS modelPayment = new PaymentDS();
       
    public LoginHandling() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("action").equals("login"))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserBean user = new UserBean();
			
			try 
			{
				user = (UserBean)modelUser.doRetrieveByKey(username, password);
			} 
			catch (SQLException e) 
			{
				response.sendRedirect("pages/error/loginFallito.html");
			}
			if(user.getUsername().equals(username))
			{
				request.getSession().setAttribute("utenteAttivo", user);
				if(user.isAdmin())
				{
					request.getSession().setAttribute("admin", true);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/admin/paginaAdmin.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/main/paginaPrincipale.jsp");
					dispatcher.forward(request, response);
				}
			}
			else
			{
				response.sendRedirect("pages/error/loginFallito.html");
			}
		}
		
		else if(request.getParameter("action").equals("registrazione"))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String via = request.getParameter("via");
			String civico = request.getParameter("civico");
			String CAP = request.getParameter("CAP");
			String citta = request.getParameter("citta");
			String provincia = request.getParameter("provincia");
			String numeroCarta = request.getParameter("numeroCarta");
			String CVV = request.getParameter("CVV");
			String scadenza = request.getParameter("scadenza");
			
			List<UserBean> utenti = new ArrayList<>();
			try 
			{
				utenti = (List<UserBean>)modelUser.doRetrieveAll();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			boolean b=false;
			for(int i=0;i<utenti.size() && b==false;i++)
			{
				if(username.equals(utenti.get(i).getUsername())) b=true;
			}
			
			if(!b)
			{
				UserBean user = new UserBean();
				AddressBean address = new AddressBean();
				PaymentBean payment = new PaymentBean();
				
				user.setNome(nome);
				user.setCognome(cognome);
				user.setUsername(username);
				user.setPassword(password);
				user.setAdmin(false);
				
				address.setUtente(username);
				address.setVia(via);
				address.setCivico(civico);
				address.setCAP(CAP);
				address.setCitta(citta);
				address.setProvincia(provincia);
				
				payment.setUtente(username);
				payment.setNumero(numeroCarta);
				payment.setCVV(Integer.parseInt(CVV));
				payment.setScadenza(scadenza);
				
				try 
				{
					modelUser.doSave(user);
					modelAddress.doSave(address);
					modelPayment.doSave(payment);
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				response.sendRedirect("pages/auth/login.html");
			}
			else
			{
				response.sendRedirect("pages/auth/registrazione.html");
			}
		}
		
		else if(request.getParameter("action").equals("logout"))
		{
			request.getSession().invalidate();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/product");
			dispatcher.forward(request, response);
		}

	}

}
