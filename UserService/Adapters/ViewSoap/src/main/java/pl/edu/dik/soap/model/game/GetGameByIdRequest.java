package pl.edu.dik.soap.model.game;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.UUID;

@Data
@XmlRootElement(name = "getGameByIdRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id"})
public class GetGameByIdRequest {
    @XmlElement(required = true)
    private UUID id;
}
