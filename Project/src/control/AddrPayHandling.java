package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PaymentDS;
import model.PaymentBean;
import model.AddressDS;
import model.AddressBean;
import model.UserBean;

@WebServlet("/AddrPayHandling")
public class AddrPayHandling extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static AddressDS modelAddress = new AddressDS();
	static PaymentDS modelPayment = new PaymentDS();
       
    public AddrPayHandling() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		UserBean user = (UserBean)request.getSession().getAttribute("utenteAttivo");
		if(request.getParameter("action").equals("nuovoIndirizzo"))
		{
			AddressBean address = new AddressBean();
			address.setVia(request.getParameter("via"));
			address.setCivico(request.getParameter("civico"));
			address.setCAP(request.getParameter("CAP"));
			address.setCitta(request.getParameter("citta"));
			address.setProvincia(request.getParameter("provincia"));
			address.setUtente(user.getUsername());
			
			try 
			{
				modelAddress.doSave(address);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		else if(request.getParameter("action").equals("nuovoPagamento"))
		{
			PaymentBean payment = new PaymentBean();
			payment.setNumero(request.getParameter("numeroCarta"));
			payment.setCVV(Integer.parseInt(request.getParameter("CVV")));
			payment.setScadenza(request.getParameter("scadenza"));
			payment.setUtente(user.getUsername());
			
			try 
			{
				modelPayment.doSave(payment);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		else if(request.getParameter("action").equals("cancellaIndirizzo"))
		{
			try 
			{
				modelAddress.doDelete(Integer.parseInt(request.getParameter("codice")));
			} 
			catch (NumberFormatException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		else if(request.getParameter("action").equals("cancellaPagamento"))
		{
			try 
			{
				modelPayment.doDelete(Integer.parseInt(request.getParameter("codice")));
			} 
			catch (NumberFormatException | SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/pages/main/areaUtente.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
