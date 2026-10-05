import java.io.*;
import java.util.*;


/* =====================================================
   ENUMERACIONES BASE
   ===================================================== */

enum TipoAsistente {
    ALUMNO,
    PROFESOR,
    INVITADO
}

enum AccionEvento {
    REGISTRO,
    ENTRADA,
    SALIDA,
    ENTRADA_MASIVA
}

enum EstadoEntrada {
    AUTORIZADO,
    NO_REGISTRADO,
    YA_DENTRO,
    AFORO_COMPLETO
}

enum EstadoSalida {
    AUTORIZADA,
    NO_REGISTRADO,
    NO_ESTA_DENTRO
}


/* =====================================================
   DTO DE ENTRADA
   ===================================================== */

final class AsistenteDTO {

    private final String id;
    private final String nombre;
    private final String tipo;

    public AsistenteDTO(
            String id,
            String nombre,
            String tipo) {

        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }
}


/* =====================================================
   DTO DE RESULTADO DE ENTRADA
   ===================================================== */

final class ResultadoEntradaDTO {

    private final int idAsistente;
    private final EstadoEntrada estado;

    public ResultadoEntradaDTO(
            int idAsistente,
            EstadoEntrada estado) {

        this.idAsistente = idAsistente;
        this.estado = estado;
    }

    public int getIdAsistente() {
        return idAsistente;
    }

    public EstadoEntrada getEstado() {
        return estado;
    }
}


/* =====================================================
   DTO DEL REPORTE
   ===================================================== */

final class ReporteDTO {

    private final int registrados;
    private final int disponibles;
    private final int aforo;
    private final int alumnos;
    private final int profesores;
    private final int invitados;
    private final double ocupacion;

    public ReporteDTO(
            int registrados,
            int disponibles,
            int aforo,
            int alumnos,
            int profesores,
            int invitados,
            double ocupacion) {

        this.registrados = registrados;
        this.disponibles = disponibles;
        this.aforo = aforo;
        this.alumnos = alumnos;
        this.profesores = profesores;
        this.invitados = invitados;
        this.ocupacion = ocupacion;
    }

    public int getRegistrados() {
        return registrados;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public int getAforo() {
        return aforo;
    }

    public int getAlumnos() {
        return alumnos;
    }

    public int getProfesores() {
        return profesores;
    }

    public int getInvitados() {
        return invitados;
    }

    public double getOcupacion() {
        return ocupacion;
    }
}


/* =====================================================
   CONTRATO DE LA CAPA DE APLICACION
   ===================================================== */

interface ControlAccesoService {

    int AFORO_MAXIMO = 10;

    boolean registrarAsistente(
            AsistenteDTO asistente);

    EstadoEntrada registrarEntrada(
            int idAsistente);

    EstadoSalida registrarSalida(
            int idAsistente);

    List<ResultadoEntradaDTO>
            registrarEntradasMasivas(
                    List<Integer> identificadores);

    ReporteDTO generarReporte();
}

/*
 * =====================================================
 * IMPLEMENTACION DEL ALUMNO
 * =====================================================
 *
 * Construye aqui:
 *
 * - tu capa de dominio;
 * - las clases que consideres necesarias;
 * - ControlAccesoServiceImpl.
 *
 * ControlAccesoServiceImpl debera implementar:
 *
 *      ControlAccesoService
 *
 * Los asistentes registrados deberan mantenerse
 * en una variable estatica dentro del servicio.
 *
 * No se especifica que estructuras de datos debes
 * utilizar. Selecciona las que consideres adecuadas
 * y justifica tu decision en el reporte.
 */
 
final class Asistente {
    private final int id;
    private final String nombre;
    private final TipoAsistente tipo;

    public Asistente(int id, String nombre, TipoAsistente tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoAsistente getTipo() {
        return tipo;
    }
}

class ControlAccesoServiceImpl implements ControlAccesoService {
    
    private static final Map<Integer, Asistente> asistentesRegistrados = new LinkedHashMap<>();
    private static final Set<Integer> asistentesDentro = new HashSet<>();

    public ControlAccesoServiceImpl() { 
        asistentesRegistrados.clear();
        asistentesDentro.clear();
    }

    @Override
    public boolean registrarAsistente(AsistenteDTO asistenteDTO) {
        int id = Integer.parseInt(asistenteDTO.getId());
         
        if (!asistentesRegistrados.containsKey(id)) {
            TipoAsistente tipo = TipoAsistente.valueOf(asistenteDTO.getTipo().toUpperCase());
            Asistente asistente = new Asistente(id, asistenteDTO.getNombre(), tipo);
            asistentesRegistrados.put(id, asistente);
            return true;
        }
        return false;
    }

    @Override
    public EstadoEntrada registrarEntrada(int idAsistente) {
        if (!asistentesRegistrados.containsKey(idAsistente)) {
            return EstadoEntrada.NO_REGISTRADO;
        }
        if (asistentesDentro.contains(idAsistente)) {
            return EstadoEntrada.YA_DENTRO;
        }
        if (asistentesDentro.size() >= AFORO_MAXIMO) {
            return EstadoEntrada.AFORO_COMPLETO;
        }
        
        asistentesDentro.add(idAsistente);
        return EstadoEntrada.AUTORIZADO;
    }

    @Override
    public EstadoSalida registrarSalida(int idAsistente) {
        if (!asistentesRegistrados.containsKey(idAsistente)) {
            return EstadoSalida.NO_REGISTRADO;
        }
        if (!asistentesDentro.contains(idAsistente)) {
            return EstadoSalida.NO_ESTA_DENTRO;
        }
        
        asistentesDentro.remove(idAsistente);
        return EstadoSalida.AUTORIZADA;
    }

    @Override
    public List<ResultadoEntradaDTO> registrarEntradasMasivas(List<Integer> identificadores) {
        List<ResultadoEntradaDTO> resultados = new ArrayList<>();
        
        for (int id : identificadores) {
            if (asistentesDentro.size() >= AFORO_MAXIMO) {
                break;
            }
            
            EstadoEntrada estado = registrarEntrada(id);
            resultados.add(new ResultadoEntradaDTO(id, estado));
            
            if (asistentesDentro.size() >= AFORO_MAXIMO) {
                break;
            }
        }
        return resultados;
    }

    @Override
    public ReporteDTO generarReporte() {
        int registrados = asistentesRegistrados.size();
        int aforo = asistentesDentro.size();
        int disponibles = AFORO_MAXIMO - aforo;
        int alumnos = 0;
        int profesores = 0;
        int invitados = 0;

        for (int id : asistentesDentro) {
            Asistente a = asistentesRegistrados.get(id);
            if (a.getTipo() == TipoAsistente.ALUMNO) alumnos++;
            else if (a.getTipo() == TipoAsistente.PROFESOR) profesores++;
            else if (a.getTipo() == TipoAsistente.INVITADO) invitados++;
        }

        double ocupacion = ((double) aforo / AFORO_MAXIMO) * 100.0;

        return new ReporteDTO(
            registrados, 
            disponibles, 
            aforo, 
            alumnos, 
            profesores, 
            invitados, 
            ocupacion
        );
    }
}




public class ControlAcceso {

    public static void main(String[] args)
            throws Exception {
        
        Locale.setDefault(Locale.US);

        BufferedReader br =
                new BufferedReader(
                    new InputStreamReader(System.in));

        System.out.println("--- 1. REGISTRO DE ASISTENTES ---");
        System.out.println("Formato: ID|Nombre|TIPO (separados por ;)");
        System.out.println("Ejemplo: 1|Ana|ALUMNO;2|Luis|PROFESOR");
        System.out.print("> ");

        String asistentes =
                br.readLine();

        System.out.println("\n--- 2. OPERACIONES ---");
        System.out.println("Formato: ACCION|ID (separados por ;)");
        System.out.println("Ejemplo: ENTRADA|1;MASIVA|2,3;SALIDA|1");
        System.out.print("> ");

        String operaciones =
                br.readLine();


        ControlAccesoService servicio =
                new ControlAccesoServiceImpl();

        StringBuilder salida = new StringBuilder();


        /* =================================================
           REGISTRO DE ASISTENTES
           ================================================= */

        if (asistentes != null
                && !asistentes.isBlank()
                && !asistentes.equals("-")) {

            String[] registros =
                    asistentes.split(";");


            for (String registro : registros) {

                String[] datos =
                        registro.split("\\|", 3);


                servicio.registrarAsistente(new AsistenteDTO(datos[0].trim(), datos[1].trim(), datos[2].trim()));
            }
        }


        /* =================================================
           OPERACIONES
           ================================================= */

        if (operaciones != null
                && !operaciones.isBlank()
                && !operaciones.equals("-")) {

            String[] lista =
                    operaciones.split(";");


            for (String operacion : lista) {

                String[] datos =
                        operacion.split("\\|", 2);

                String accion =
                        datos[0].trim();


                /* =========================================
                   ENTRADA
                   ========================================= */

                if (accion.equals("ENTRADA")) {

                    int id =
                            Integer.parseInt(
                                    datos[1].trim());

                    salida.append("ENTRADA ").append(id).append(" ").append(servicio.registrarEntrada(id)).append("\n");

                } else if (accion.equals("SALIDA")) {

                    int id = Integer.parseInt(datos[1].trim());
                    salida.append("SALIDA ").append(id).append(" ").append(servicio.registrarSalida(id)).append("\n");

                } else if (accion.equals("MASIVA")){

                    String[] ids = datos[1].split(",");
                    List<Integer> identificadores = new ArrayList<>();
                    for (String id : ids) identificadores.add(Integer.parseInt(id.trim()));
                    for (ResultadoEntradaDTO r : servicio.registrarEntradasMasivas(identificadores)){
                        salida.append("ENTRADA ").append(r.getIdAsistente()).append(" ").append(r.getEstado()).append("\n");
                    }
                }
            }
        }
    
            ReporteDTO reporte = servicio.generarReporte();
            salida.append(String.format("REPORTE %d %d %d %d %d %d %.2f\n",
                reporte.getRegistrados(), reporte.getDisponibles(), reporte.getAforo(),
                reporte.getAlumnos(), reporte.getProfesores(), reporte.getInvitados(), reporte.getOcupacion()));

            System.out.println("\n--- RESULTADO ---");
            System.out.print(salida.toString());
    }
}
