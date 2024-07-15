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
import java.lang.module.Configuration;
import java.util.ArrayList;

@WebServlet("/api/users")
public class UsuarioController extends HttpServlet {

    //   Service y Mapper
    UsuarioService service = new UsuarioService();
    ObjectMapper mapper = new ObjectMapper();

//    Config CORS


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfiguracionCORS(resp);
    }

    private void ConfiguracionCORS(HttpServletResponse resp) {

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "content-type, authorization");

    }

    //    GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfiguracionCORS(resp);
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

        } else {

            ArrayList<Usuario> listUsuarios = service.getAllUsers();

            if (!listUsuarios.isEmpty()) {

                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(mapper.writeValueAsString(listUsuarios));

            } else {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                resp.getWriter().write("No se encontro a ningun usuario");
            }

        }




    }

//    POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfiguracionCORS(resp);
        Usuario user = mapper.readValue(req.getInputStream(), Usuario.class);
        service.saveUser(user);

        resp.setStatus(201);
        resp.setContentType("application/json");
//        devolvemos el usuario creado
        resp.getWriter().write(mapper.writeValueAsString(user));
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConfiguracionCORS(resp);
        String id_str = req.getParameter("id");

        try {
            int id = Integer.valueOf(id_str);
            service.deleteUser(id);


            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().write("Se elimino al usuario con id:" + id);


        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }


    }
}
