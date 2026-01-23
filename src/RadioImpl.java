/**
 * Clase que posee el objeto que actuara como radio con sus métodos.
 * @author Diego Castillo
 * @author Henry Guzmán
 * @since 2026-01-23
 */
public class RadioImpl implements Radio {
	/**
	 * Atributo que permite indica su estado de encendido o apagado.
	 */
    private boolean encendido;
    
    /**
     * Atributo que indica el modo de la frecuencia AM o FM.
     */
    private boolean modoAM;
    
    /**
     * Atributo que indica la frecuencia que se utiliza.
     */
    private double frecuenciaActual;
    
    /**
     * Atributo que almacena botones para cambiar a frecuencia específica (12)
     */
    private double[] botones;

    /**
     * Atributos que limitan las frecuencias del modo AM.
     */
    private static final double AM_MIN = 530;
    private static final double AM_MAX = 1610;
    private static final double AM_STEP = 10;

    /**
     * Atributos que limitan las frecuencias del modo FM.
     */
    private static final double FM_MIN = 87.9;
    private static final double FM_MAX = 107.9;
    private static final double FM_STEP = 0.2;

    /**
     * Constructor principal de la clase.
     */
    public RadioImpl() {
        this.encendido = false;
        this.modoAM = true;
        this.frecuenciaActual = AM_MIN;
        this.botones = new double[12];

        for (int i = 0; i < 12; i++) {
            botones[i] = 0.0;
        }
    }

    /**
     * Método de interfaz que cambia el estado de la radio a encendido.
     */
    @Override
    public void prenderRadio() {
        if (!encendido) {
            encendido = true;
            System.out.println("Radio encendido");
            mostrarEstado();
        } else {
            System.out.println("El radio ya está encendido");
        }
    }

    /**
     * Método de interfaz que cambia el estado de la radio a apagado.
     * @return encendido en valor falso
     */
    @Override
    public void apagarRadio() {
        if (encendido) {
            encendido = false;
            System.out.println("Radio apagado");
        } else {
            System.out.println("El radio ya está apagado");
        }
        return;
    }

    /**
     * Método de interfaz que cambia la frecuencia según el modo.
     */
    @Override
    public void avanzarEstacion() {
        if (!encendido) {
            System.out.println("El radio está apagado. Enciéndelo primero.");
            return;
        }

        if (modoAM) {
            frecuenciaActual += AM_STEP;
            if (frecuenciaActual > AM_MAX) {
                frecuenciaActual = AM_MIN;
            }
        } else {
            frecuenciaActual += FM_STEP;
            frecuenciaActual = Math.round(frecuenciaActual * 10.0) / 10.0;
            if (frecuenciaActual > FM_MAX) {
                frecuenciaActual = FM_MIN;
            }
        }
        mostrarEstado();
    }

    /**
     * Método de interfaz que guarda una estación en un botón específico.
     * @param numeroBoton indica el botón a usar (entre 1 y 12).
     */
    @Override
    public void guardarEstacion(int numeroBoton) {
        if (!encendido) {
            System.out.println("El radio está apagado. Enciéndelo primero.");
            return;
        }

        if (numeroBoton < 1 || numeroBoton > 12) {
            System.out.println("Número de botón inválido. Debe ser entre 1 y 12.");
            return;
        }

        botones[numeroBoton - 1] = frecuenciaActual;
        String banda = modoAM ? "AM" : "FM";
        System.out.println("Emisora guardada en botón " + numeroBoton + ": " +
                frecuenciaActual + " " + banda);
    }

    /**
     * Método de interfaz que cambia de frecuencia según un botón específico.
     * @param numeroBoton indica el botón a usar (entre 1 y 12).
     */
    @Override
    public void cargarEstacion(int numeroBoton) {
        if (!encendido) {
            System.out.println("El radio está apagado. Enciéndelo primero.");
            return;
        }

        if (numeroBoton < 1 || numeroBoton > 12) {
            System.out.println("Número de botón inválido. Debe ser entre 1 y 12.");
            return;
        }

        double frecuenciaGuardada = botones[numeroBoton - 1];

        if (frecuenciaGuardada == 0.0) {
            System.out.println("No hay ninguna emisora guardada en el botón " + numeroBoton);
            return;
        }


        if (frecuenciaGuardada >= AM_MIN && frecuenciaGuardada <= AM_MAX) {
            modoAM = true;
        } else {
            modoAM = false;
        }

        frecuenciaActual = frecuenciaGuardada;
        System.out.println("Cargando emisora del botón " + numeroBoton);
        mostrarEstado();
    }

    /**
     * Método de interfaz que cambia el modo a FM.
     */
    @Override
    public void cambiarFM() {
        if (!encendido) {
            System.out.println("El radio está apagado. Enciéndelo primero.");
            return;
        }

        if (!modoAM) {
            System.out.println("Ya estás en modo FM");
            return;
        }

        modoAM = false;
        frecuenciaActual = FM_MIN;
        System.out.println("Cambiado a FM");
        mostrarEstado();
    }

    /**
     * Método de interfaz que cambia el modo a AM.
     */
    @Override
    public void cambiarAM() {
        if (!encendido) {
            System.out.println("El radio está apagado. Enciéndelo primero.");
            return;
        }

        if (modoAM) {
            System.out.println("Ya estás en modo AM");
            return;
        }

        modoAM = true;
        frecuenciaActual = AM_MIN;
        System.out.println("Cambiado a AM");
        mostrarEstado();
    }

    /**
     * Método que muestra en que modo de frecuencia se encuentra la radio.
     */
    private void mostrarEstado() {
        String banda = modoAM ? "AM" : "FM";
        System.out.println("-------------------------------------------------------");
        System.out.println("Frecuencia: " + frecuenciaActual + " " + banda + "       ");
        System.out.println("-------------------------------------------------------");
    }

    /**
     * Método que muestra los botones y sus frecuencias específicas.
     */
    public void mostrarBotonesGuardados() {
        System.out.println("\n Emisoras guardadas:");
        System.out.println("=====================================");
        for (int i = 0; i < 12; i++) {
            if (botones[i] != 0.0) {
                String banda = (botones[i] >= AM_MIN && botones[i] <= AM_MAX) ? "AM" : "FM";
                System.out.println("Botón " + (i + 1) + ": " + botones[i] + " " + banda);
            } else {
                System.out.println("Botón " + (i + 1) + ": [Vacío]");
            }
        }
        System.out.println("=====================================\n");
    }
    
    
    /**
     * Método para JUnit devuelve el estado de encendido de la radio.
     * @return encendido es true o false
     */
    public boolean isEncendido() {
        return encendido;
    }
    
    /**
     * Método para JUnit obtiene la frecuencia del modo AM.
     * @return frecuenciaActual la frecuencia en la que esta el modo AM.
     */
    public int getEstacionAM() {
        if (!modoAM) {
            throw new IllegalStateException("La radio no está en modo AM");
        }
        return (int) frecuenciaActual;
    }
    
    /**
     * Método para JUnit obtiene el modo de frecuencia.
     * @return modoAM que puede ser AM o FM.
     */
    public String getBanda() {
        return modoAM ? "AM" : "FM";
    }
    
    /**
     * Método para JUnit obtiene la estación.
     * @return frecuenciaActual la estación actual en cualquier modo.
     */
    public double getEstacionActual() {
        return frecuenciaActual;
    }
    
    /**
     * Método para JUnit obtiene el botón.
     * @param numeroBoton el botón a usar (entre 1 y 12)
     * @return botón de mayor frecuencia de uso.
     */
    public double getFrecuenciaBoton(int numeroBoton) {
        if (numeroBoton < 1 || numeroBoton > 12) {
            throw new IllegalArgumentException("Botón inválido");
        }
        return botones[numeroBoton - 1];
    }
}