package com.giret.consumer;


import com.giret.consumer.model.LoanEvent;
import com.giret.consumer.repository.ResourceRepository;
import com.giret.consumer.services.ConsumerService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.EventGridTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;




@Component
public class Function {

    private final Gson gson = new Gson();

    private static final ApplicationContext context =
            new SpringApplicationBuilder(SpringBootAzureApp.class).run();

    //private final ConsumerService consumerService =
      //      context.getBean(ConsumerService.class); 

    @FunctionName("EventGridEvents")
    public void run(
            @EventGridTrigger(name = "eventGridEvent") String content,
            final ExecutionContext context
    ) {
        Logger logger = context.getLogger();
        logger.info("🚀 Función con Event Grid trigger ejecutada.");

        try {
            logger.info("📦 Payload recibido: " + content);

            // ✅ Deserializar correctamente usando JsonElement
            EventGridEnvelope envelope = gson.fromJson(content, EventGridEnvelope.class);

            logger.info("🔑 Tipo de evento: " + envelope.getEventType());

            LoanEvent eventData = gson.fromJson(envelope.getData(), LoanEvent.class);
            logger.info("📄 Data parseada: " + eventData);

            Long prestamoId = eventData.getMembers().getPrestamoId().getValue();
            Long recursoId = eventData.getMembers().getRecursoId().getValue();

            switch (envelope.getEventType()) {
                case "Prestamo.CREADO":
                    logger.info("✅ Procesando: Prestamo.CREADO");
                    //resourceRepository.updateState(recursoId, "Prestado");
                    //loanRepository.updateLoan(prestamoId, "Activo");
                    logger.info("📌 Estados actualizados: Recurso -> 'Prestado', Prestamo -> 'Activo'");
                    break;

                case "Prestamo.DEVUELTO":
                    logger.info("✅ Procesando: Prestamo.DEVUELTO");
                    //loanRepository.updateLoan(prestamoId, "Devuelto");
                    //resourceRepository.updateState(recursoId, "Bodega");
                    logger.info("📌 Estados actualizados: Prestamo -> 'Devuelto', Recurso -> 'Bodega'");
                    break;

                default:
                    logger.warning("⚠️ Tipo de evento no reconocido: " + envelope.getEventType());
            }

        } catch (Exception e) {
            logger.severe("💥 Error procesando evento: " + e.getMessage());
        }
    }

    /**
     * ✅ Clase interna para Event Grid.
     * Usa JsonElement para el campo `data` para evitar problemas de Gson.
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
