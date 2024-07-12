package services;

import domain.models.Usuario;
import infraestructura.IPersistencia;
import infraestructura.database.MySQLPersistenciaImpl;

import java.util.ArrayList;

public class UsuarioService implements IPersistencia {

    IPersistencia persistencia = new MySQLPersistenciaImpl();


    @Override
    public void saveUser(Usuario user) {
        persistencia.saveUser(user);
    }

    @Override
    public Usuario findUserById(int id) {
        return persistencia.findUserById(id);
    }

    @Override
    public ArrayList<Usuario> getAllUsers() {
        return persistencia.getAllUsers();
    }

    @Override
    public void deleteUser(int id) {
        persistencia.deleteUser(id);
    }
}
