package org.educa.service;

import org.educa.entity.ClienteEntity;
import org.educa.dao.ClienteDAO;
import org.educa.dao.ClienteDAOImpl;

import java.sql.SQLException;
import java.util.Locale;

public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAOImpl();

    /**
     * Busca un cliente por su DNI (sin distinguir mayúsculas de minúsculas)
     *
     * @param dni el DNI del cliente
     * @return el {@link ClienteEntity} o {@code null} si no está registrado
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    public ClienteEntity findByDNI(String dni) throws SQLException {
        return clienteDAO.findByDNI(normalizar(dni));
    }

    /**
     * Da de alta un cliente y le asigna su identificador
     *
     * @param cliente el {@link ClienteEntity} a registrar
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    public void saveCliente(ClienteEntity cliente) throws SQLException {
        cliente.setDni(normalizar(cliente.getDni()));
        clienteDAO.save(cliente);
    }

    private String normalizar(String dni) {
        return dni.trim().toUpperCase(Locale.ROOT);
    }
}
