package org.educa.service;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;
import org.educa.dao.VueloDAO;
import org.educa.dao.VueloDAOImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

public class VueloService {

    private final VueloDAO vueloDAO = new VueloDAOImpl();

    private static final BigDecimal PRECIO_MINUTO = new BigDecimal("0.75");

    /**
     * Obtiene los vuelos no cancelados que salen de un aeropuerto
     *
     * @param origen el {@link AeropuertoEntity} de origen
     * @return los {@link VueloEntity} con su aeropuerto de destino
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    public List<VueloEntity> findByOrigen(AeropuertoEntity origen) throws SQLException {
        return vueloDAO.findByOrigen(origen);
    }

    /**
     * Calcula el precio base del vuelo: 0,75 € por cada minuto entre la salida y la llegada
     *
     * @param vuelo el {@link VueloEntity}
     * @return el precio base con 2 decimales
     */
    public BigDecimal getPrecioBase(VueloEntity vuelo) {
        long minutos = Duration.between(vuelo.getFecSalida(), vuelo.getFecLlegada()).toMinutes();
        return PRECIO_MINUTO.multiply(BigDecimal.valueOf(minutos)).setScale(2, RoundingMode.HALF_UP);
    }
}
