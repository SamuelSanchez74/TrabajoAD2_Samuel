package org.educa.service;

import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;
import org.educa.dao.ReservaDAO;
import org.educa.dao.ReservaDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class ReservaService {

    private final ReservaDAO reservaDAO = new ReservaDAOImpl();

    /**
     * Obtiene las reservas previas de un cliente
     *
     * @param cliente el {@link ClienteEntity}
     * @return sus reservas ordenadas por fecha
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    public List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        return reservaDAO.findByCliente(cliente);
    }

    public void save(ReservaEntity reserva) throws SQLException {
    }
}
