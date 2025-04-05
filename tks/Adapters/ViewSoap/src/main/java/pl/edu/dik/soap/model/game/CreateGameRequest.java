package pl.edu.dik.soap.model.game;

import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "createGameRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "pricePerDay", "minPlayers", "maxPlayers"})
public class CreateGameRequest {

    @XmlElement(required = true, name = "name")
    @NotNull(message = "Not null")
    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @XmlElement(required = true, name = "pricePerDay")
    @Min(value = 1, message = "Price per day must be at least 1")
    @Max(value = 100, message = "Price per day must be at most 100")
    private int pricePerDay;

    @XmlElement(required = true, name = "minPlayers")
    @Min(value = 1, message = "Min players must be at least 1")
    private int minPlayers;

    @XmlElement(required = true, name = "maxPlayers")
    @Min(value = 1, message = "Max players must be at least 1")
    private int maxPlayers;
}
