package org.educa.service;

import org.educa.dao.AeropuertoDAO;
import org.educa.dao.AeropuertoDAOImpl;
import org.educa.entity.AeropuertoEntity;

import java.sql.SQLException;
import java.util.List;

public class AeropuertoService {

    private final AeropuertoDAO aeropuertoDAO = new AeropuertoDAOImpl();

    /**
     * Obtiene todos los aeropuertos del sistema
     *
     * @return la lista de {@link AeropuertoEntity}
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    public List<AeropuertoEntity> findAll() throws SQLException {
        return aeropuertoDAO.findAll();
    }
}
