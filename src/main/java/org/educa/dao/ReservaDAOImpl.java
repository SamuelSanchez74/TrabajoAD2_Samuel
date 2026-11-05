package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOImpl implements ReservaDAO {

    private static final String SQL_FIND_BY_CLIENTE =
            "SELECT id_reserva, cod_reserva, fec_reserva, estado " +
                    "FROM reservas WHERE id_cliente = ? ORDER BY fec_reserva, id_reserva";

    private static final String SQL_EXISTS_BY_CODIGO =
            "SELECT 1 FROM reservas WHERE cod_reserva = ?";

    private static final String SQL_INSERT =
            "INSERT INTO reservas (cod_reserva, id_cliente) VALUES (?, ?) RETURNING id_reserva";

    @Override
    public List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        List<ReservaEntity> reservas = new ArrayList<>();
        try (Connection connection = ConnectionPool.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_FIND_BY_CLIENTE)) {
            ps.setInt(1, cliente.getIdCliente());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReservaEntity reserva = new ReservaEntity();
                    reserva.setIdReserva(rs.getInt("id_reserva"));
                    reserva.setCodReserva(rs.getString("cod_reserva"));
                    reserva.setCliente(cliente);
                    reserva.setFecReserva(rs.getTimestamp("fec_reserva").toLocalDateTime());
                    reserva.setEstado(rs.getString("estado"));
                    reservas.add(reserva);
                }
            }
        }
        return reservas;
    }

    @Override
    public boolean existsByCodigo(Connection connection, String codReserva) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_EXISTS_BY_CODIGO)) {
            ps.setString(1, codReserva);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public void save(Connection connection, ReservaEntity reserva) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_INSERT)) {
            ps.setString(1, reserva.getCodReserva());
            ps.setInt(2, reserva.getCliente().getIdCliente());
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                reserva.setIdReserva(rs.getInt(1));
            }
        }
    }
}