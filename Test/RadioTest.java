import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

	public class RadioTest {

	    private RadioImpl radio;

	    @BeforeEach
	    void setUp() {
	        radio = new RadioImpl();
	    }

	    @Test
	    void testPrenderYApagarRadio() {
	        radio.prenderRadio();
	        assertTrue(radio.isEncendido());

	        radio.apagarRadio();
	        assertFalse(radio.isEncendido());
	    }

	    @Test
	    void testCambiarAMyFM() {
	        radio.prenderRadio();

	        radio.cambiarAM();
	        assertEquals("AM", radio.getBanda());

	        radio.cambiarFM();
	        assertEquals("FM", radio.getBanda());
	    }

	    @Test
	    void testAvanzarEstacionFM() {
	        radio.prenderRadio();
	        radio.cambiarFM();

	        double estacionInicial = radio.getEstacionActual();
	        radio.avanzarEstacion();

	        assertEquals(estacionInicial + 0.2, radio.getEstacionActual(), 0.0001);
	    }

	    @Test
	    void testAvanzarEstacionAM() {
	        radio.prenderRadio();
	        radio.cambiarAM();

	        int estacionInicial = radio.getEstacionAM();
	        radio.avanzarEstacion();

	        assertEquals(estacionInicial + 10, radio.getEstacionAM());
	    }

	    @Test
	    void testGuardarYCargarEstacion() {
	        radio.prenderRadio();
	        radio.cambiarFM();

	        radio.avanzarEstacion();
	        double estacionGuardada = radio.getEstacionActual();

	        radio.guardarEstacion(1);

	        radio.avanzarEstacion();
	        radio.cargarEstacion(1);

	        assertEquals(estacionGuardada, radio.getEstacionActual(), 0.0001);
	    }

	    @Test
	    void testBotonInvalido() {
	        radio.prenderRadio();

	        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	            radio.guardarEstacion(13);
	        });

	        assertNotNull(exception);
	    }
	}
