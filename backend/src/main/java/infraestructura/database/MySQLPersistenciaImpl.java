package infraestructura.database;

import domain.models.Usuario;
import infraestructura.IPersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLPersistenciaImpl implements IPersistencia {

    private Connection conexion;

    public MySQLPersistenciaImpl() {
        this.conexion = DatabaseConnection.getConnection();
    }

    @Override
    public void saveUser(Usuario user) {

        String sql = """
                INSERT INTO users (username, name, email, password)
                VALUES (?, ?, ?, ?)
                """;

        try {
            PreparedStatement prep = conexion.prepareStatement(sql);

            prep.setString(1, user.getUsername());
            prep.setString(2, user.getName());
            prep.setString(3, user.getEmail());
            prep.setString(4, user.getPassword());

            prep.executeUpdate();
            prep.close();
            System.out.println("se guardaron en la database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Usuario findUserById(int id) {

        String sql = "SELECT * FROM users WHERE id = ?";
        Usuario user = null;

        try {
            PreparedStatement prep = conexion.prepareStatement(sql);
            prep.setInt(1, id);

//            obtenemos el usuario de la database
            ResultSet set =  prep.executeQuery();

            if (set.next()) {
                user = new Usuario();
                user.setUsername(set.getString("username"));
                user.setName(set.getString("name"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));

                return user;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public ArrayList<Usuario> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(int id) {

    }
}
