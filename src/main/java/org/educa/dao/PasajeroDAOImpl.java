package org.educa.dao;

import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasajeroDAOImpl implements PasajeroDAO {

    private static final String SQL_INSERT =
            "INSERT INTO pasajeros (id_reserva, dni, nombre, p_apellido, s_apellido, email, telefono, fec_nac) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_pasajero";

    @Override
    public void save(Connection connection, PasajeroEntity pasajero) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            ps.setInt(1, pasajero.getIdReserva());
            ps.setString(2, pasajero.getDni());
            ps.setString(3, pasajero.getNombre());
            ps.setString(4, pasajero.getPrimerApellido());
            ps.setString(5, pasajero.getSegundoApellido());
            ps.setString(6, pasajero.getEmail());
            ps.setString(7, pasajero.getTelefono());
            ps.setDate(8, Date.valueOf(pasajero.getFecNac()));
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                pasajero.setIdPasajero(rs.getInt(1));
            }
        }
    }
}
