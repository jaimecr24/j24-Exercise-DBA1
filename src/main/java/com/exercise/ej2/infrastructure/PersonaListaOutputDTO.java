package com.exercise.ej2.infrastructure;

import java.util.List;

public record PersonaListaOutputDTO(
        long total_items,
        List<PersonaOutputDTO> items
) {
}
