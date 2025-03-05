package pl.edu.dik.tks.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter // @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
public abstract class AbstractEntity {

    @EqualsAndHashCode.Include
    private final UUID id;
}
