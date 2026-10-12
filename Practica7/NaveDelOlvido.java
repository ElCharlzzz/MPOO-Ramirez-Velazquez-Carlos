import java.io.*;
import java.util.*;


/* =====================================================
   ENUMS
   ===================================================== */

enum TipoDestino {
    PLANETA,
    SATELITE_NATURAL
}


enum TipoRecurso {
    DRON,
    SATELITE_ARTIFICIAL,
    CARGA,
    TRIPULANTE
}


enum TipoMision {
    EXPLORACION,
    RESCATE,
    TRANSPORTE
}


enum Capacidad {
    COMUNICABLE,
    RASTREABLE,
    OPERABLE,
    ESCANEABLE
}


enum EstadoValidacion {
    OK,
    FALLIDA
}


enum EstadoPaso {
    OK,
    NO_EJECUTADO
}


enum ResultadoObjetivo {
    ESCANEO_COMPLETADO,
    RESCATE_COMPLETADO,
    CARGA_ENTREGADA,
    NO_EJECUTADO
}


/* =====================================================
   DTO DESTINO
   ===================================================== */

final class DestinoDTO {

    private final TipoDestino tipo;
    private final String codigo;
    private final String nombre;
    private final double gravedad;
    private final String datoEspecifico;


    public DestinoDTO(
            TipoDestino tipo,
            String codigo,
            String nombre,
            double gravedad,
            String datoEspecifico) {

        this.tipo = tipo;
        this.codigo = codigo;
        this.nombre = nombre;
        this.gravedad = gravedad;
        this.datoEspecifico = datoEspecifico;
    }


    public TipoDestino getTipo() {
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getGravedad() {
        return gravedad;
    }

    public String getDatoEspecifico() {
        return datoEspecifico;
    }
}


/* =====================================================
   DTO NAVE
   ===================================================== */

final class NaveDTO {

    private final String id;
    private final String nombre;
    private final String posicion;
    private final boolean operativa;


    public NaveDTO(
            String id,
            String nombre,
            String posicion,
            boolean operativa) {

        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
        this.operativa = operativa;
    }


    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public boolean isOperativa() {
        return operativa;
    }
}


/* =====================================================
   DTO BASE DE RECURSOS
   ===================================================== */

abstract class RecursoDTO {

    private final TipoRecurso tipo;
    private final String id;


    protected RecursoDTO(
            TipoRecurso tipo,
            String id) {

        this.tipo = tipo;
        this.id = id;
    }


    public TipoRecurso getTipo() {
        return tipo;
    }

    public String getId() {
        return id;
    }
}


/* =====================================================
   DTO DRON
   ===================================================== */

final class DronDTO extends RecursoDTO {

    private final String modelo;
    private final String posicion;
    private final boolean operativo;


    public DronDTO(
            String id,
            String modelo,
            String posicion,
            boolean operativo) {

        super(TipoRecurso.DRON, id);

        this.modelo = modelo;
        this.posicion = posicion;
        this.operativo = operativo;
    }


    public String getModelo() {
        return modelo;
    }

    public String getPosicion() {
        return posicion;
    }

    public boolean isOperativo() {
        return operativo;
    }
}


/* =====================================================
   DTO SATELITE ARTIFICIAL
   ===================================================== */

final class SateliteArtificialDTO extends RecursoDTO {

    private final String nombre;
    private final String posicion;
    private final boolean operativo;


    public SateliteArtificialDTO(
            String id,
            String nombre,
            String posicion,
            boolean operativo) {

        super(TipoRecurso.SATELITE_ARTIFICIAL, id);

        this.nombre = nombre;
        this.posicion = posicion;
        this.operativo = operativo;
    }


    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public boolean isOperativo() {
        return operativo;
    }
}


/* =====================================================
   DTO CARGA
   ===================================================== */

final class CargaDTO extends RecursoDTO {

    private final String descripcion;
    private final double peso;
    private final String posicion;


    public CargaDTO(
            String id,
            String descripcion,
            double peso,
            String posicion) {

        super(TipoRecurso.CARGA, id);

        this.descripcion = descripcion;
        this.peso = peso;
        this.posicion = posicion;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public String getPosicion() {
        return posicion;
    }
}


/* =====================================================
   DTO TRIPULANTE
   ===================================================== */

final class TripulanteDTO extends RecursoDTO {

    private final String nombre;
    private final String posicion;


    public TripulanteDTO(
            String id,
            String nombre,
            String posicion) {

        super(TipoRecurso.TRIPULANTE, id);

        this.nombre = nombre;
        this.posicion = posicion;
    }


    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }
}


/* =====================================================
   DTO MISION
   ===================================================== */

final class MisionDTO {

    private final String id;
    private final String nombre;
    private final TipoMision tipo;
    private final List<String> idsRecursos;


    public MisionDTO(
            String id,
            String nombre,
            TipoMision tipo,
            List<String> idsRecursos) {

        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.idsRecursos =
                new ArrayList<String>(idsRecursos);
    }


    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoMision getTipo() {
        return tipo;
    }

    public List<String> getIdsRecursos() {
        return new ArrayList<String>(idsRecursos);
    }
}


/* =====================================================
   RESULTADO DE UNA MISION
   ===================================================== */

final class EjecucionMisionDTO {

    private final EstadoValidacion validacion;
    private final EstadoPaso preparacion;
    private final EstadoPaso viaje;

    private final ResultadoObjetivo objetivo;

    private final EstadoPaso finalizacion;
    private final EstadoPaso reporte;


    public EjecucionMisionDTO(
            EstadoValidacion validacion,
            EstadoPaso preparacion,
            EstadoPaso viaje,
            ResultadoObjetivo objetivo,
            EstadoPaso finalizacion,
            EstadoPaso reporte) {

        this.validacion = validacion;
        this.preparacion = preparacion;
        this.viaje = viaje;
        this.objetivo = objetivo;
        this.finalizacion = finalizacion;
        this.reporte = reporte;
    }


    public EstadoValidacion getValidacion() {
        return validacion;
    }

    public EstadoPaso getPreparacion() {
        return preparacion;
    }

    public EstadoPaso getViaje() {
        return viaje;
    }

    public ResultadoObjetivo getObjetivo() {
        return objetivo;
    }

    public EstadoPaso getFinalizacion() {
        return finalizacion;
    }

    public EstadoPaso getReporte() {
        return reporte;
    }
}


/* =====================================================
   CONTRATOS DE MAPPERS
   ===================================================== */

interface DestinoMapper {

    DestinoEspacial toEntity(
            DestinoDTO dto);
}


interface NaveMapper {

    Nave toEntity(
            NaveDTO dto);
}


interface RecursoMapper {

    Recurso toEntity(
            RecursoDTO dto);
}


/* =====================================================
   SERVICIO DE CREACION DE MISIONES
   ===================================================== */

interface MisionFactoryService {

    Mision crearMision(
            MisionDTO dto,
            Nave nave,
            DestinoEspacial destino,
            Map<String, Recurso> recursos);
}


/* =====================================================
   SERVICIO DE CAPACIDADES
   ===================================================== */

interface CapacidadService {

    List<Capacidad> obtenerCapacidades(
            Object entidad);
}

/*
 * =====================================================
 *
 
 * =====================================================
 * DESARROLLE SU SOLUCION A PARTIR DE AQUI
 * =====================================================
 */
 
interface Comunicable{
    String comunicar();
}

interface Rastreable{
    String obtenerPosicion();
}

interface Escaneable{
    String escanear();
}

interface Operable {
    boolean estaOperativo();
}

abstract class DestinoEspacial{
    private String codigo;
    private String nombre;
    private double gravedad; 
    
    public DestinoEspacial(String codigo, String nombre, double gravedad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.gravedad = gravedad;
    }
    
    public String getCodigo(){return codigo;}
    public String getNombre(){return nombre;}
    public double getGravedad(){return gravedad;}

}

class Planeta extends DestinoEspacial{
    private boolean tieneAtmosfera;
    
    public Planeta(String codigo, String nombre, double gravedad, boolean tieneAtmosfera){
        super(codigo, nombre, gravedad);
        this.tieneAtmosfera = tieneAtmosfera;
    }
    
    public boolean tieneAtmosfera(){return tieneAtmosfera;}
}

class SateliteNatural extends DestinoEspacial{
    private String orbitaA;
    
    public SateliteNatural(String codigo, String nombre, double gravedad, String orbitaA){
        super(codigo, nombre, gravedad);
        this.orbitaA = orbitaA;
    }
    
    public String getOrbitaA(){return orbitaA;}
}

class Nave implements Comunicable, Rastreable, Operable{
    private String id;
    private String nombre;
    private String posicion;
    private boolean operativa;
    
    public Nave(String id, String nombre, String posicion, boolean operativa){
        this.id=id;
        this.nombre=nombre;
        this.posicion=posicion;
        this.operativa=operativa;
    }
    
    public String getId(){return id;}
    public String getNombre(){return nombre;}
    
    @Override
    public String comunicar(){return "Nave Comunicando";}
    
    @Override
    public String obtenerPosicion(){return posicion;}
    
    @Override
    public boolean estaOperativo(){return operativa;}
}


abstract class Recurso{
    private String id;
    
    public Recurso(String id){
        this.id=id;
    }
    
    public String getId(){return id;}
}

class Dron extends Recurso implements Comunicable, Rastreable, Operable, Escaneable {
    
    private String modelo;
    private String posicion;
    private boolean operativo;
    
    public Dron(String id, String modelo, String posicion, boolean operativo){
        super(id);
        this.modelo=modelo;
        this.posicion=posicion;
        this.operativo=operativo;
    }
    
    @Override public String comunicar(){return "Dron Comunicando";}
    @Override public String obtenerPosicion(){return posicion;}
    @Override public boolean estaOperativo(){return operativo;}
    @Override public String escanear(){return "Dron Escaneando";}
}

class SateliteArtificial extends Recurso implements Comunicable, Rastreable, Operable, Escaneable {
    private String nombre;
    private String posicion;
    private boolean operativo;

    public SateliteArtificial(String id, String nombre, String posicion, boolean operativo) {
        super(id);
        this.nombre = nombre;
        this.posicion = posicion;
        this.operativo = operativo;
    }

    @Override public String comunicar() { return "Satelite comunicando"; }
    @Override public String obtenerPosicion() { return posicion; }
    @Override public boolean estaOperativo() { return operativo; }
    @Override public String escanear() { return "Satelite escaneando"; }
}

class Carga extends Recurso implements Rastreable{
    private String descripcion;
    private double peso;
    private String posicion;
    
    public Carga(String id, String descripcion, double peso, String posicion){
        super(id);
        this.descripcion=descripcion;
        this.peso=peso;
        this.posicion=posicion;
    }
    
    @Override public String obtenerPosicion(){return posicion;}
}

class Tripulante extends Recurso implements Comunicable, Rastreable{
    private String nombre;
    private String posicion;
    
    public Tripulante(String id, String nombre, String posicion){
        super(id);
        this.nombre=nombre;
        this.posicion=posicion;
    }
    
    @Override public String comunicar(){return "Tripulante Comunicando";}
    @Override public String obtenerPosicion(){return posicion;}
}

abstract class Mision {
    protected String id;
    protected String nombre;
    protected Nave nave;
    protected DestinoEspacial destino;
    protected List<Recurso> recursos;

    public Mision(String id, String nombre, Nave nave, DestinoEspacial destino, List<Recurso> recursos) {
        this.id = id;
        this.nombre = nombre;
        this.nave = nave;
        this.destino = destino;
        this.recursos = recursos;
    }

    public final EjecucionMisionDTO ejecutarMision() {
        if (!nave.estaOperativo() || !validarRecursosRequeridos()) {
            return new EjecucionMisionDTO(
                    EstadoValidacion.FALLIDA,
                    EstadoPaso.NO_EJECUTADO,
                    EstadoPaso.NO_EJECUTADO,
                    ResultadoObjetivo.NO_EJECUTADO,
                    EstadoPaso.NO_EJECUTADO,
                    EstadoPaso.NO_EJECUTADO
            );
        }

        return new EjecucionMisionDTO(
                EstadoValidacion.OK,
                EstadoPaso.OK,
                EstadoPaso.OK,
                obtenerResultadoObjetivo(),
                EstadoPaso.OK,
                EstadoPaso.OK
        );
    }

    protected abstract boolean validarRecursosRequeridos();
    protected abstract ResultadoObjetivo obtenerResultadoObjetivo();
}

class MisionExploracion extends Mision {
    public MisionExploracion(String id, String nombre, Nave nave, DestinoEspacial destino, List<Recurso> recursos) {
        super(id, nombre, nave, destino, recursos);
    }

    @Override
    protected boolean validarRecursosRequeridos() {
        for (Recurso r : recursos) {
            if (r instanceof Escaneable) return true;
        }
        return false;
    }

    @Override
    protected ResultadoObjetivo obtenerResultadoObjetivo() {
        return ResultadoObjetivo.ESCANEO_COMPLETADO;
    }
}

class MisionRescate extends Mision {
    public MisionRescate(String id, String nombre, Nave nave, DestinoEspacial destino, List<Recurso> recursos) {
        super(id, nombre, nave, destino, recursos);
    }

    @Override
    protected boolean validarRecursosRequeridos() {
        for (Recurso r : recursos) {
            if (r instanceof Tripulante) return true;
        }
        return false;
    }

    @Override
    protected ResultadoObjetivo obtenerResultadoObjetivo() {
        return ResultadoObjetivo.RESCATE_COMPLETADO;
    }
}

class MisionTransporte extends Mision {
    public MisionTransporte(String id, String nombre, Nave nave, DestinoEspacial destino, List<Recurso> recursos) {
        super(id, nombre, nave, destino, recursos);
    }

    @Override
    protected boolean validarRecursosRequeridos() {
        for (Recurso r : recursos) {
            if (r instanceof Carga) return true;
        }
        return false;
    }

    @Override
    protected ResultadoObjetivo obtenerResultadoObjetivo() {
        return ResultadoObjetivo.CARGA_ENTREGADA;
    }
}

class DestinoMapperImpl implements DestinoMapper {
    @Override
    public DestinoEspacial toEntity(DestinoDTO dto) {
        if (dto.getTipo() == TipoDestino.PLANETA) {
            return new Planeta(dto.getCodigo(), dto.getNombre(), dto.getGravedad(), Boolean.parseBoolean(dto.getDatoEspecifico()));
        } else {
            return new SateliteNatural(dto.getCodigo(), dto.getNombre(), dto.getGravedad(), dto.getDatoEspecifico());
        }
    }
}

class NaveMapperImpl implements NaveMapper {
    @Override
    public Nave toEntity(NaveDTO dto) {
        return new Nave(dto.getId(), dto.getNombre(), dto.getPosicion(), dto.isOperativa());
    }
}

class RecursoMapperImpl implements RecursoMapper {
    @Override
    public Recurso toEntity(RecursoDTO dto) {
        switch (dto.getTipo()) {
            case DRON:
                DronDTO d = (DronDTO) dto;
                return new Dron(d.getId(), d.getModelo(), d.getPosicion(), d.isOperativo());
            case SATELITE_ARTIFICIAL:
                SateliteArtificialDTO sa = (SateliteArtificialDTO) dto;
                return new SateliteArtificial(sa.getId(), sa.getNombre(), sa.getPosicion(), sa.isOperativo());
            case CARGA:
                CargaDTO c = (CargaDTO) dto;
                return new Carga(c.getId(), c.getDescripcion(), c.getPeso(), c.getPosicion());
            case TRIPULANTE:
                TripulanteDTO t = (TripulanteDTO) dto;
                return new Tripulante(t.getId(), t.getNombre(), t.getPosicion());
            default:
                throw new IllegalArgumentException("Tipo de recurso no soportado");
        }
    }
}

class MisionFactoryServiceImpl implements MisionFactoryService {
    @Override
    public Mision crearMision(MisionDTO dto, Nave nave, DestinoEspacial destino, Map<String, Recurso> recursosMap) {
        List<Recurso> recursosAsignados = new ArrayList<>();
        for (String id : dto.getIdsRecursos()) {
            if (recursosMap.containsKey(id)) {
                recursosAsignados.add(recursosMap.get(id));
            }
        }

        switch (dto.getTipo()) {
            case EXPLORACION:
                return new MisionExploracion(dto.getId(), dto.getNombre(), nave, destino, recursosAsignados);
            case RESCATE:
                return new MisionRescate(dto.getId(), dto.getNombre(), nave, destino, recursosAsignados);
            case TRANSPORTE:
                return new MisionTransporte(dto.getId(), dto.getNombre(), nave, destino, recursosAsignados);
            default:
                throw new IllegalArgumentException("Tipo de mision desconocido");
        }
    }
}

class CapacidadServiceImpl implements CapacidadService {
    @Override
    public List<Capacidad> obtenerCapacidades(Object entidad) {
        List<Capacidad> capacidades = new ArrayList<>();
        
        if (entidad instanceof Comunicable) capacidades.add(Capacidad.COMUNICABLE);
        if (entidad instanceof Rastreable) capacidades.add(Capacidad.RASTREABLE);
        if (entidad instanceof Operable) capacidades.add(Capacidad.OPERABLE);
        if (entidad instanceof Escaneable) capacidades.add(Capacidad.ESCANEABLE);
        
        return capacidades;
    }
}


public class NaveDelOlvido {


    public static void main(String[] args) {
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            
            System.out.println("=================================================");
            System.out.println("   AGENCIA ESPACIAL AURORA - GESTOR DE MISIONES  ");
            System.out.println("=================================================\n");

            System.out.println("1. Ingrese los datos del Destino");
            System.out.println("   (Ejemplo: PLANETA | MARTE-1 | Marte | (Gravedad Superficial) 3.7 | (Tiene atmosfera?) true)");
            System.out.println("   (Ejemplo: SATELITE_NATURAL | LUNA-1 | Luna | (Gravedad Superficial) 3.7 | (Cuerpo que Orbita) Tierra)");
            System.out.print("   -> ");
            String destinoInput = br.readLine();

            System.out.println("\n2. Ingrese los datos de la Nave");
            System.out.println("   (Ejemplo: NAVE-01 | Apolo | Base-Tierra | (Esta en Funcionamiento?) true)");
            System.out.print("   -> ");
            String naveInput = br.readLine();

            System.out.println("\n3. Ingrese los datos de los Recursos separados por ';'");
            System.out.println("   (Ejemplo: DRON | D-1 | Explorador | Base-Tierra | true ; CARGA | C-1 | Medicamento | (Peso de la Carga) 5.5 | Base-Tierra)");
            System.out.println("   (Ejemplo: SATELITE_NATURAL | LUNA-1 | luna | (Gravedad Superficial) 1.62 | (Cuerpo que Orbita) Tierra ; TRIPULANTE | T-1 | Nombre | Base-Tierra)");
            System.out.print("   -> ");
            String recursosInput = br.readLine();

            System.out.println("\n4. Ingrese los datos de la Mision");
            System.out.println("   (Ejemplo: M-100 | Mision Alpha | (EXPLORACION, RESCATE o TRANSPORTE) | D-1)");
            System.out.print("   -> ");
            String misionInput = br.readLine();

            System.out.println("\n================ RESULTADO DE MISION ================\n");

            String resultado = procesarMision(
                    destinoInput,
                    naveInput,
                    recursosInput,
                    misionInput
            );

            System.out.print(resultado);
            System.out.println("\n\n=====================================================");

        } catch (Exception e) {
            System.err.println("Ocurrió un error al procesar la información: " + e.getMessage());
        }
    }


    /* =====================================================
       PROCESAMIENTO PRINCIPAL
       ===================================================== */

    public static String procesarMision(
            String destinoInput,
            String naveInput,
            String recursosInput,
            String misionInput) {


        DestinoDTO destinoDTO =
                parseDestino(destinoInput);

        NaveDTO naveDTO =
                parseNave(naveInput);

        List<RecursoDTO> recursosDTO =
                parseRecursos(recursosInput);

        MisionDTO misionDTO =
                parseMision(misionInput);


        /* MAPPERS DEL ALUMNO */

        DestinoMapper destinoMapper =
                new DestinoMapperImpl();

        NaveMapper naveMapper =
                new NaveMapperImpl();

        RecursoMapper recursoMapper =
                new RecursoMapperImpl();


        /* SERVICIOS DEL ALUMNO */

        MisionFactoryService factory =
                new MisionFactoryServiceImpl();

        CapacidadService capacidadService =
                new CapacidadServiceImpl();


        /* DTO -> ENTIDADES */

        DestinoEspacial destino =
                destinoMapper.toEntity(destinoDTO);

        Nave nave =
                naveMapper.toEntity(naveDTO);

        Map<String, Recurso> recursos =
                mapearRecursos(
                        recursosDTO,
                        recursoMapper
                );


        /* CREAR MISION */

        Mision mision =
                factory.crearMision(
                        misionDTO,
                        nave,
                        destino,
                        recursos
                );


        /* EJECUTAR MISION */

        EjecucionMisionDTO ejecucion =
                mision.ejecutarMision();


        /* HACKERRANK CONSTRUYE LA SALIDA */

        return construirSalida(
                destino,
                nave,
                recursos,
                misionDTO,
                ejecucion,
                capacidadService
        );
    }


    /* =====================================================
       MAPEAR RECURSOS
       ===================================================== */

    private static Map<String, Recurso> mapearRecursos(
            List<RecursoDTO> recursosDTO,
            RecursoMapper mapper) {


        Map<String, Recurso> recursos =
                new LinkedHashMap<String, Recurso>();


        for (RecursoDTO dto : recursosDTO) {

            Recurso recurso =
                    mapper.toEntity(dto);

            recursos.put(
                    recurso.getId(),
                    recurso
            );
        }


        return recursos;
    }


    /* =====================================================
       SALIDA
       ===================================================== */

    private static String construirSalida(
            DestinoEspacial destino,
            Nave nave,
            Map<String, Recurso> recursos,
            MisionDTO misionDTO,
            EjecucionMisionDTO ejecucion,
            CapacidadService capacidadService) {


        List<String> salida =
                new ArrayList<String>();


        salida.add(
                "MISION "
                        + misionDTO.getId());


        salida.add(
                "TIPO "
                        + misionDTO.getTipo());


        salida.add(
                construirDestino(destino));


        salida.add(
                construirCapacidades(
                        nave.getId(),
                        nave,
                        capacidadService
                )
        );


        for (String id
                : misionDTO.getIdsRecursos()) {


            Recurso recurso =
                    recursos.get(id);


            if (recurso != null) {

                salida.add(
                        construirCapacidades(
                                recurso.getId(),
                                recurso,
                                capacidadService
                        )
                );
            }
        }


        /* VALIDACION */

        if (ejecucion.getValidacion()
                == EstadoValidacion.FALLIDA) {


            salida.add(
                    "VALIDACION FALLIDA");

            salida.add(
                    "MISION ABORTADA");


            return String.join(
                    "\n",
                    salida
            );
        }


        salida.add(
                "VALIDACION OK");


        /* PREPARACION */

        if (ejecucion.getPreparacion()
                == EstadoPaso.OK) {

            salida.add(
                    "PREPARACION OK");
        }


        /* VIAJE */

        if (ejecucion.getViaje()
                == EstadoPaso.OK) {

            salida.add(
                    "VIAJE "
                            + destino.getNombre());
        }


        /* OBJETIVO */

        if (ejecucion.getObjetivo()
                != ResultadoObjetivo.NO_EJECUTADO) {

            salida.add(
                    "OBJETIVO "
                            + ejecucion
                                    .getObjetivo()
                                    .name());
        }


        /* FINALIZACION */

        if (ejecucion.getFinalizacion()
                == EstadoPaso.OK) {

            salida.add(
                    "FINALIZACION OK");
        }


        /* REPORTE */

        if (ejecucion.getReporte()
                == EstadoPaso.OK) {

            salida.add(
                    "REPORTE COMPLETADO");
        }


        return String.join(
                "\n",
                salida
        );
    }


    /* =====================================================
       DESTINO
       ===================================================== */

    private static String construirDestino(
            DestinoEspacial destino) {


        if (destino instanceof Planeta) {


            Planeta planeta =
                    (Planeta) destino;


            return "DESTINO PLANETA "
                    + planeta.getNombre()
                    + " ATMOSFERA "
                    + (
                        planeta.tieneAtmosfera()
                                ? "SI"
                                : "NO"
                    );
        }


        if (destino instanceof SateliteNatural) {


            SateliteNatural satelite =
                    (SateliteNatural) destino;


            return "DESTINO SATELITE_NATURAL "
                    + satelite.getNombre()
                    + " ORBITA "
                    + satelite.getOrbitaA();
        }


        throw new IllegalArgumentException(
                "Destino no valido");
    }


    /* =====================================================
       CAPACIDADES
       ===================================================== */

    private static String construirCapacidades(
            String id,
            Object entidad,
            CapacidadService servicio) {


        List<Capacidad> capacidades =
                servicio.obtenerCapacidades(
                        entidad);


        List<String> nombres =
                new ArrayList<String>();


        for (Capacidad capacidad
                : capacidades) {

            nombres.add(
                    capacidad.name());
        }


        return "CAPACIDADES "
                + id
                + " "
                + String.join(
                        ",",
                        nombres
                );
    }


    /* =====================================================
       PARSE DESTINO
       ===================================================== */

    private static DestinoDTO parseDestino(
            String input) {


        String[] d =
                input.split("\\|", 5);


        return new DestinoDTO(
                TipoDestino.valueOf(
                        d[0].trim().toUpperCase()),
                d[1].trim(),
                d[2].trim(),
                Double.parseDouble(
                        d[3].trim()),
                d[4].trim()
        );
    }


    /* =====================================================
       PARSE NAVE
       ===================================================== */

    private static NaveDTO parseNave(
            String input) {


        String[] d =
                input.split("\\|", 4);


        return new NaveDTO(
                d[0].trim(),
                d[1].trim(),
                d[2].trim(),
                Boolean.parseBoolean(
                        d[3].trim())
        );
    }


    /* =====================================================
       PARSE RECURSOS
       ===================================================== */

    private static List<RecursoDTO> parseRecursos(
            String input) {


        List<RecursoDTO> recursos =
                new ArrayList<RecursoDTO>();


        if (input == null
                || input.trim().isEmpty()
                || input.equals("-")) {

            return recursos;
        }


        String[] registros =
                input.split(";");


        for (String registro : registros) {


            String[] d =
                    registro.split("\\|");


            TipoRecurso tipo =
                    TipoRecurso.valueOf(
                            d[0].trim().toUpperCase());


            switch (tipo) {


                case DRON:

                    recursos.add(
                            new DronDTO(
                                    d[1].trim(),
                                    d[2].trim(),
                                    d[3].trim(),
                                    Boolean.parseBoolean(
                                            d[4].trim())
                            )
                    );

                    break;


                case SATELITE_ARTIFICIAL:

                    recursos.add(
                            new SateliteArtificialDTO(
                                    d[1].trim(),
                                    d[2].trim(),
                                    d[3].trim(),
                                    Boolean.parseBoolean(
                                            d[4].trim())
                            )
                    );

                    break;


                case CARGA:

                    recursos.add(
                            new CargaDTO(
                                    d[1].trim(),
                                    d[2].trim(),
                                    Double.parseDouble(
                                            d[3].trim()),
                                    d[4].trim()
                            )
                    );

                    break;


                case TRIPULANTE:

                    recursos.add(
                            new TripulanteDTO(
                                    d[1].trim(),
                                    d[2].trim(),
                                    d[3].trim()
                            )
                    );

                    break;
            }
        }


        return recursos;
    }


    /* =====================================================
       PARSE MISION
       ===================================================== */

    private static MisionDTO parseMision(
            String input) {


        String[] d =
                input.split("\\|", 4);


        List<String> ids =
                new ArrayList<String>();


        if (d.length == 4
                && !d[3].trim().isEmpty()
                && !d[3].equals("-")) {


            String[] recursos =
                    d[3].split(",");


            for (String id : recursos) {

                ids.add(
                        id.trim());
            }
        }


        return new MisionDTO(
                d[0].trim(),
                d[1].trim(),
                TipoMision.valueOf(
                        d[2].trim().toUpperCase()),
                ids
        );
    }
}
