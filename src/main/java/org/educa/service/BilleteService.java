package org.educa.service;

import org.educa.dao.BilleteDAO;
import org.educa.dao.BilleteDAOImpl;
import org.educa.entity.BilleteEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class BilleteService {

    private static final int EDAD_INFANTIL = 12;
    private static final int RESERVAS_PARA_DESCUENTO = 3;
    private static final BigDecimal FACTOR_DESCUENTO_INFANTIL = new BigDecimal("0.80");
    private static final BigDecimal FACTOR_DESCUENTO_RESERVAS = new BigDecimal("0.90");

    private final BilleteDAO billeteDAO = new BilleteDAOImpl();
    private final VueloService vueloService = new VueloService();

    /**
     * Calcula y asigna el precio final del billete:
     * precio base del vuelo + suplemento del asiento, con un 20% de descuento si el pasajero tiene menos
     * de 12 años en la fecha de salida y después un 10% si el cliente tiene más de 3 reservas previas.
     * El resultado se redondea a 2 decimales.
     *
     * @param billete     el {@link BilleteEntity} con pasajero, vuelo y asiento informados
     * @param numReservas el número de reservas previas del cliente
     */
    public void calculatePrecio(BilleteEntity billete, int numReservas) {
        BigDecimal precio = vueloService.getPrecioBase(billete.getVuelo())
                .add(billete.getAsiento().getSuplemento());

        LocalDate fechaSalida = billete.getVuelo().getFecSalida().toLocalDate();
        int edad = Period.between(billete.getPasajero().getFecNac(), fechaSalida).getYears();

        if (edad < EDAD_INFANTIL) {
            precio = precio.multiply(FACTOR_DESCUENTO_INFANTIL);
        }
        if (numReservas > RESERVAS_PARA_DESCUENTO) {
            precio = precio.multiply(FACTOR_DESCUENTO_RESERVAS);
        }
        billete.setPrecio(precio.setScale(2, RoundingMode.HALF_UP));
    }

    /**
     * Guarda el billete dentro de la transacción en curso
     *
     * @param connection la conexión de la transacción
     * @param billete    el {@link BilleteEntity} a guardar
     * @throws SQLException error al ejecutar la sentencia SQL
     */
    public void save(Connection connection, BilleteEntity billete) throws SQLException {
        billeteDAO.save(connection, billete);
    }
}