package pl.edu.dik.soap.model.game;

import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.UUID;

@Data
@XmlRootElement(name = "UpdateGameRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "pricePerDay"})
public class UpdateGameRequest {

    @XmlElement(required = true, name = "id")
    @NotNull(message = "Id is required")
    private UUID id;

    @XmlElement(required = true, name = "name")
    @NotEmpty(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @XmlElement(required = true, name = "pricePerDay")
    @Min(value = 1, message = "Price per day must specified and be at least 1")
    @Max(value = 100, message = "Price per day must be specified and be at most 100")
    private int pricePerDay;
}
