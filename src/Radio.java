/**
 * Es la interfaz principal del programa.
 * @author Diego Castillo
 * @author Henry Guzmán
 * @since 2026-01-23
 */
public interface Radio {
	/**
	 * Enciende la radio.
	 */
    void prenderRadio();
    
    /**
     * Apaga la radio.
     */
    void apagarRadio();
    
    /**
     * Permite cambiar la estación de la radio.
     */
    void avanzarEstacion();
    
    /**
     * Permite guardar una estación en determinado botón.
     * @param numeroBoton indica el botón a usar (entre 1 y 12).
     */
    void guardarEstacion(int numeroBoton);
    
    /**
     * Permite usar un botón para cambiar de estación.
     * @param numeroBoton indica el botón a usar (entre 1 y 12).
     */
    void cargarEstacion(int numeroBoton);
    
    /**
     * Permite cambiar el modo de frecuencia a FM.
     */
    void cambiarFM();
    
    /**
     * Permite cambiar el modo de frecuencia a AM.
     */
    void cambiarAM();
}
