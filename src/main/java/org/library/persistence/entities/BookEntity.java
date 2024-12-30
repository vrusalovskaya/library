package org.library.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class BookEntity {
    @EqualsAndHashCode.Include
    private Integer id;
    private String title;
    private String author;
    private Boolean isAvailable;
}
