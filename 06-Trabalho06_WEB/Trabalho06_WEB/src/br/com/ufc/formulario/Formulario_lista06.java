package br.com.ufc.formulario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ufc.model.Pessoa;

@WebServlet("/adicionaPessoa")
public class Formulario_lista06 extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Pessoa> listaPessoas = new ArrayList<Pessoa>();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		Pessoa auxPessoa = new Pessoa();
		
		String nomePessoa = req.getParameter("nome");
		String timePessoa = req.getParameter("time");
		String [] hobbies = req.getParameterValues("hobbies");
		
		auxPessoa.setNome(nomePessoa);
		auxPessoa.setTime(timePessoa);
		auxPessoa.setHobbies(hobbies);
		
		listaPessoas.add(auxPessoa);
		
		out.println("<html>");
			out.println("<head>");
				out.println("<meta charset=\"UTF-8\">");
				out.println("<title>");
					out.println("Formulario Pessoa");
				out.println("</title>");
			out.println("</head>");
			out.println("<body>");

		
				out.println("<h1>Lista de Pessoas Cadastradas</h1>");
				if(listaPessoas.isEmpty()) {
					out.println("Não há pessoas cadastradas!");
				}else {
					for(Pessoa p: listaPessoas) {
						out.println("Nome: " + p.getNome() + "<br>");
						out.println("Time: " + p.getTime() + "<br>");
						out.println("Hobbies: " +  "<br>");
						for(String h: p.getHobbies()) {
							out.println(h+"<br>");
						}
						
						out.println("----------------------------------");
						out.println("<br>");
					}	
				}
			
			out.println("</body>");
		out.println("</html>");
	}
}
