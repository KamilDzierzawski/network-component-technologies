package pl.edu.dik.soap.model.game;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "getAllGamesResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"games"})
public class GetAllGamesResponse {
    @XmlElement(name = "games")
    private List<GameResponse> games;
}
