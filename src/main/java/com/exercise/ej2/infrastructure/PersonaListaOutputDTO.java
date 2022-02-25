package com.exercise.ej2.infrastructure;

import java.util.List;

public record PersonaListaOutputDTO(
        int total_items,
        List<PersonaOutputDTO> items
) {
}
