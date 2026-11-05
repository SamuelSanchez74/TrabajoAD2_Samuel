package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImpl implements ClienteDAO {

    private static final String SQL_FIND_BY_DNI =
            "SELECT id_cliente, dni, nombre, p_apellido, s_apellido, email, telefono " +
                    "FROM clientes WHERE dni = ?";

    private static final String SQL_INSERT =
            "INSERT INTO clientes (dni, nombre, p_apellido, s_apellido, email, telefono) " +
                    "VALUES (?, ?, ?, ?, ?, ?) RETURNING id_cliente";

    @Override
    public ClienteEntity findByDNI(String dni) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_DNI)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ClienteEntity(
                            rs.getInt("id_cliente"),
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("p_apellido"),
                            rs.getString("s_apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"));
                }
                return null;
            }
        }
    }

    @Override
    public void save(ClienteEntity cliente) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getPrimerApellido());
            ps.setString(4, cliente.getSegundoApellido());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getTelefono());
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                cliente.setIdCliente(rs.getInt(1));
            }
        }
    }
}
