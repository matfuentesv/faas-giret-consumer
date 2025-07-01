package com.giret.consumer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanEvent {
    private Members members;

    public Members getMembers() { return members; }

    public static class Members {
        private ValueWrapper prestamoId;
        private ValueWrapper recursoId;

        public ValueWrapper getPrestamoId() { return prestamoId; }
        public ValueWrapper getRecursoId() { return recursoId; }
    }

    public static class ValueWrapper {
        private Long value;
        public Long getValue() { return value; }
    }
}

