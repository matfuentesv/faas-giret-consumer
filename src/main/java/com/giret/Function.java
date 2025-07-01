package com.giret;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import java.util.logging.Logger;

public class Function {

    @FunctionName("EventGridEvents")
    public void run(
            @EventGridTrigger(name = "eventGridEvent") String content,
            final ExecutionContext context
    ) {
        Logger logger = context.getLogger();
        logger.info("Función con Event Grid trigger ejecutada.");

        try {
            // Deserializar el contenido del evento
            Gson gson = new Gson();
            JsonObject eventGridEvent = gson.fromJson(content, JsonObject.class);

            // Extraer campos importantes
            String eventType = eventGridEvent.get("eventType").getAsString();
            JsonObject data = eventGridEvent.getAsJsonObject("data");

            logger.info("Evento recibido: " + eventGridEvent.toString());
            logger.info("Tipo de evento: " + eventType);
            logger.info("Data del evento: " + data.toString());

            // Procesar según el tipo de evento
            switch (eventType) {
                case "Rol.CREATE":
                    logger.info("✅ Se recibió una solicitud de CREACIÓN de Rol.");
                    logger.info("Datos del nuevo Rol: " + data.toString());
                    break;

                case "Rol.UPDATE":
                    logger.info("✅ Se recibió una solicitud de ACTUALIZACIÓN de Rol.");
                    logger.info("Datos del Rol actualizado: " + data.toString());
                    break;

                case "Rol.DELETE":
                    logger.info("✅ Se recibió una solicitud de ELIMINACIÓN de Rol.");
                    logger.info("Datos del Rol eliminado: " + data.toString());
                    break;

                case "Rol.GET":
                    logger.info("✅ Se recibió una solicitud de CONSULTA de Rol.");
                    logger.info("ID consultado: " + data.get("id").getAsString());
                    break;
                case "PrestamoCreado.":
                    logger.info("✅ Se recibió un Prestamo Creado.");
                    logger.info("Datos del préstamo: " + data.toString());
                    break;

                default:
                    logger.warning("⚠️ Tipo de evento no reconocido: " + eventType);
                    break;
            }

        } catch (Exception e) {
            logger.severe("Error procesando evento: " + e.getMessage());
        }
    }
}



