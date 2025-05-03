package pl.edu.dik.soap.model.game;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "getAllGamesRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetAllGamesRequest {

}
