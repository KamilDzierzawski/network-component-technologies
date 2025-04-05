package pl.edu.dik.soap.model.game;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.UUID;

@Data
@XmlRootElement(name = "deleteGameRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id"})
public class DeleteGameRequest {
    @XmlElement(required = true)
    private UUID id;
}