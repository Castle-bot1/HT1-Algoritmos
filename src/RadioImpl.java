public class RadioImpl implements Radio {
    private boolean encendido;
    private boolean modoAM;
    private double frecuenciaActual;
    private double[] botones;


    private static final double AM_MIN = 530;
    private static final double AM_MAX = 1610;
    private static final double AM_STEP = 10;

    private static final double FM_MIN = 87.9;
    private static final double FM_MAX = 107.9;
    private static final double FM_STEP = 0.2;

    public RadioImpl() {
        this.encendido = false;
        this.modoAM = true;
        this.frecuenciaActual = AM_MIN;
        this.botones = new double[12];

        for (int i = 0; i < 12; i++) {
            botones[i] = 0.0;
        }
    }

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

    @Override
    public void apagarRadio() {
        if (encendido) {
            encendido = false;
            System.out.println("Radio apagado");
        } else {
            System.out.println("El radio ya está apagado");
        }
    }

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

    private void mostrarEstado() {
        String banda = modoAM ? "AM" : "FM";
        System.out.println("-------------------------------------------------------");
        System.out.println("Frecuencia: " + frecuenciaActual + " " + banda + "       ");
        System.out.println("-------------------------------------------------------");
    }

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
}