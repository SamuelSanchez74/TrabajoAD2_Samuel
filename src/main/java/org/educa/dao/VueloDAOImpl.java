package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloDAOImpl implements VueloDAO {

    private static final String SQL_FIND_BY_ORIGEN =
            "SELECT v.id_vuelo, v.num_vuelo, v.id_avion, v.fec_salida, v.fec_llegada, v.estado, " +
                    "d.id_aeropuerto, d.cod_iata, d.nombre, d.ciudad, d.pais " +
                    "FROM vuelos v INNER JOIN aeropuertos d ON d.id_aeropuerto = v.id_destino " +
                    "WHERE v.id_origen = ? AND v.estado <> 'CANCELADO' " +
                    "ORDER BY v.fec_salida, v.id_vuelo";

    @Override
    public List<VueloEntity> findByOrigen(AeropuertoEntity origen) throws SQLException {
        List<VueloEntity> vuelos = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_ORIGEN)) {
            ps.setInt(1, origen.getIdAeropuerto());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VueloEntity vuelo = new VueloEntity();
                    vuelo.setIdVuelo(rs.getInt("id_vuelo"));
                    vuelo.setNumVuelo(rs.getString("num_vuelo"));
                    vuelo.setOrigen(origen);
                    vuelo.setDestino(new AeropuertoEntity(
                            rs.getInt("id_aeropuerto"),
                            rs.getString("cod_iata"),
                            rs.getString("nombre"),
                            rs.getString("ciudad"),
                            rs.getString("pais")));
                    vuelo.setIdAvion(rs.getInt("id_avion"));
                    vuelo.setFecSalida(rs.getTimestamp("fec_salida").toLocalDateTime());
                    vuelo.setFecLlegada(rs.getTimestamp("fec_llegada").toLocalDateTime());
                    vuelo.setEstado(rs.getString("estado"));
                    vuelos.add(vuelo);
                }
            }
        }
        return vuelos;
    }
}
