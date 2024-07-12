package infraestructura;

import domain.models.Usuario;

import java.util.ArrayList;

public interface IPersistencia {

    //    guardar usuario
    void saveUser(Usuario user);

    //    obtener usuario por id
    Usuario findUserById(int id);

    //    Obtener todos los usuarios
    ArrayList<Usuario> getAllUsers();

    //    Eliminar usuario por id
    void deleteUser(int id);
}
