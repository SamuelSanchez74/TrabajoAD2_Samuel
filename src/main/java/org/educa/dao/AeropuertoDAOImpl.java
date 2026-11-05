package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AeropuertoDAOImpl implements AeropuertoDAO {

    private static final String SQL_FIND_ALL =
            "SELECT id_aeropuerto, cod_iata, nombre, ciudad, pais FROM aeropuertos ORDER BY id_aeropuerto";

    @Override
    public List<AeropuertoEntity> findAll() throws SQLException {
        List<AeropuertoEntity> aeropuertos = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                aeropuertos.add(new AeropuertoEntity(
                        rs.getInt("id_aeropuerto"),
                        rs.getString("cod_iata"),
                        rs.getString("nombre"),
                        rs.getString("ciudad"),
                        rs.getString("pais")));
            }
        }
        return aeropuertos;
    }
}
