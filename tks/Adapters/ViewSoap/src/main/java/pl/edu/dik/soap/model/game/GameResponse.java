package pl.edu.dik.soap.model.game;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.UUID;

@Data
@XmlRootElement(name = "GameResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "pricePerDay", "minPlayers", "maxPlayers", "rentalStatusCount"})
public class GameResponse {

    @XmlElement(required = true, name = "id")
    private UUID id;

    @XmlElement(required = true, name = "name")
    private String name;

    @XmlElement(required = true, name = "pricePerDay")
    private int pricePerDay;

    @XmlElement(required = true, name = "minPlayers")
    private int minPlayers;

    @XmlElement(required = true, name = "maxPlayers")
    private int maxPlayers;

    @XmlElement(required = true, name = "rentalStatusCount")
    private int rentalStatusCount;
}
