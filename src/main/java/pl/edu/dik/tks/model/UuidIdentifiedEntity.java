package pl.edu.dik.tks.model;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.UUID;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class UuidIdentifiedEntity {

    @BsonId
    @EqualsAndHashCode.Include
    private ObjectId id;
}
