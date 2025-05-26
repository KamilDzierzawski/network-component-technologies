package pl.edu.dik.rabbitmq.model;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.UUID;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntityEnt {

    @BsonId
    @EqualsAndHashCode.Include
    private UUID id;
}
