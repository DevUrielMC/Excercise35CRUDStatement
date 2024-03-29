package com.excercise35crudstatement.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excercise35crudstatement.model.Products;

/**
 * Servlet implementation class ReadIndivitualServlet
 */
@WebServlet("/ReadIndivitualServlet")
public class ReadIndivitualServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html charset='utf-8'");
		PrintWriter output = response.getWriter();
		
		//1. Declaramos variables
		Products myProduct = new Products();
		myProduct.setIdProduct(Integer.parseInt(request.getParameter("txtIdProduct")));
		
		String urlServer="jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String username="root";
		String pass="admin";
		String sentenciaSQL="SELECT * FROM productos WHERE idProducto = "+myProduct.getIdProduct();
				
		//2. Declaramos objetos
		Connection conn = null;
		Statement stmnt = null;
		ResultSet rs = null;
		
		try
		{
			//3. Instanciamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			//4. Abrimos la conexi�n
			conn = DriverManager.getConnection(urlServer, username, pass);
			//5. Preparamos el statement
			stmnt = conn.createStatement();
			
			//6. Ejecutamos la sentencia sql
			rs = stmnt.executeQuery(sentenciaSQL);
			
			//7. Procesamos los datos
			rs.next();
			output.append("<p>");
			output.append("Id Producto:"+rs.getInt(1));
			output.append("<br/>");
			output.append("Nombre Producto:"+rs.getString("nombreProducto"));
			output.append("<br/>");
			output.append("Precio Producto:"+rs.getDouble("precioProducto"));
			output.append("</p>");
			output.append("<a href='jsp/ReadIndividual.jsp'>Regresar</a>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				stmnt.close();
				conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//8. Cerramos la conexi�n
		output.close();
	}

}
