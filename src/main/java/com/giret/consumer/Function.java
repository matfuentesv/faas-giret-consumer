package com.giret.consumer;

import java.util.logging.Logger;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.giret.consumer.model.Loan;
import com.giret.consumer.model.Resource;
import com.giret.consumer.services.ConsumerService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

@Component
public class Function {

    private final Gson gson = new Gson();

    @FunctionName("EventGridEvents")
    public void run(
            @EventGridTrigger(name = "eventGridEvent") String content,
            final ExecutionContext context
    ) {
        Logger logger = context.getLogger();
        logger.info("🚀 Función con Event Grid trigger ejecutada.");

        try {
            logger.info("✅ Antes de levantar contexto Spring");
            ApplicationContext ctx = new SpringApplicationBuilder(SpringBootAzureApp.class)
                    .web(org.springframework.boot.WebApplicationType.NONE)
                    .run();

            logger.info("✅ Contexto Spring levantado");
            ConsumerService consumerService = ctx.getBean(ConsumerService.class);
            logger.info("✅ Bean ConsumerService OK");

            logger.info("📦 Payload recibido: " + content);

            // ✅ Deserializar usando envoltorio
            EventGridEnvelope envelope = gson.fromJson(content, EventGridEnvelope.class);

            logger.info("🔑 Tipo de evento: " + envelope.getEventType());

            switch (envelope.getEventType()) {
                case "Recurso.PRESTADO":
                    logger.info("✅ Procesando: Recurso.PRESTADO");
                    // Deserializa como Resource
                    Resource recurso = gson.fromJson(envelope.getData(), Resource.class);
                    logger.info("📄 Data parseada: " + recurso);

                    Long recursoId = recurso.getIdRecurso();
                    logger.info("🔑 Recurso ID: " + recursoId);

                    consumerService.updateStateResource(recursoId, "prestado");
                    logger.info("📌 Estado actualizado: Recurso -> 'Prestado'");
                    break;

                case "Prestamo.CREADO":
                    logger.info("✅ Procesando: Prestamo.CREADO");
                    // Deserializa como LoanEvent
                    Loan prestamo = gson.fromJson(envelope.getData(), Loan.class);
                    logger.info("📄 Data parseada: " + prestamo);

                    Long idPrestamo = prestamo.getIdPrestamo();
                    logger.info("🔑 Recurso ID: " + idPrestamo);

                    consumerService.updateLoanByState( "atrasado",idPrestamo);
                    logger.info("📌 Estados actualizados: Prestamo -> 'atrasado'");
                    break;

                case "Prestamo.DEVUELTO":
                    logger.info("✅ Procesando: Prestamo.DEVUELTO");
                    // Deserializa como LoanEvent
                    JsonObject data = envelope.getData().getAsJsonObject();
                    Long prestamoIdDev = data.get("prestamoId").getAsLong();
                    Long recursoIdDev = data.get("recursoId").getAsLong();

                    logger.info("🔑 Prestamo ID: " + prestamoIdDev + ", Prestamo ID: " + prestamoIdDev);
                    logger.info("📌 Estados actualizados: Prestamo -> 'devuelto', Recurso -> 'Bodega'");
                    // Aquí pones tus actualizaciones reales:
                     consumerService.updateLoanByState("devuelto", prestamoIdDev);
                     consumerService.updateStateResource(recursoIdDev, "Bodega");
                    break;

                default:
                    logger.warning("⚠️ Tipo de evento no reconocido: " + envelope.getEventType());
            }

        } catch (Exception e) {
            logger.severe("💥 Error procesando evento: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * ✅ Clase interna para envolver el Event Grid
     */
    private static class EventGridEnvelope {
        private String eventType;
        private JsonElement data;

        public String getEventType() {
            return eventType;
        }

        public JsonElement getData() {
            return data;
        }
    }
}
