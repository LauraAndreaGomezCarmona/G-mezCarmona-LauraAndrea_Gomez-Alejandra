package dh.backend.test;

import dh.backend.dao.impl.OdontologoDaoH2;
import dh.backend.model.Odontologo;
import dh.backend.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);
    private static OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    @BeforeAll
    static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/db_clinica_1605;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testear que un odontologo fue guardado")
    void testOdontologoGuardado(){
        Odontologo Odontologo = new Odontologo(1, "45758", "Carla","Montana");
        Odontologo odontologoDesdeLaBD = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(OdontologoDesdeLaBD);
    }

    @Test
    @DisplayName("Testear busqueda Odontologo por id")
    void testOdontologoPorId(){
        Integer id = 1;
        Odontologo odontologoEncontrado = odontologoService.buscarPorId(id);

        assertEquals(id, odontologoEncontrado.getId());
    }

    @Test
    @DisplayName("Testear busqueda todos los Odontologos")
    void testBusquedaTodos() {
        Odontologo odontologo = new Odontologo(1, "45758", "Carla","Montana");
        odontologoService.registrarOdontologo(odontologo);

        List<Odontologo> odontologos = odontologoService.buscarTodos();

        assertEquals(2, odontologos.size());

    }


}