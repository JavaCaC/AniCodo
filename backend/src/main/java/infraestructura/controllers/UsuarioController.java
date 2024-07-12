package infraestructura.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.Usuario;
import services.UsuarioService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/users")
public class UsuarioController extends HttpServlet {

    //   Service y Mapper
    UsuarioService service = new UsuarioService();
    ObjectMapper mapper = new ObjectMapper();

//    GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id_str = req.getParameter("id");

        if (id_str != null) {

            try {
                int id = Integer.valueOf(id_str);
                Usuario user = service.findUserById(id);

                if (user != null) {
                    resp.setStatus(200);
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(user));
                } else {
                    resp.getWriter().write("No se encontro a ningun usuario");
                }

            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }


    }

//    POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Usuario user = mapper.readValue(req.getInputStream(), Usuario.class);
        service.saveUser(user);

        resp.setStatus(201);
        resp.setContentType("application/json");
//        devolvemos el usuario creado
        resp.getWriter().write(mapper.writeValueAsString(user));
    }
}
