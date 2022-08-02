package simulator.models.exceptions;

public enum ErrorCode {

    NO_ASSIGNED_PROCESS("No hay procesos asignados para la ejecución"),
    NO_PROCESS_READY("No hay procesos listos en la cola para ejecutarse");

    public final String description;

    ErrorCode(String description) {
        this.description = description;
    }
}
