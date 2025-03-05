package pl.edu.dik.tks.model.account;

import org.bson.codecs.pojo.annotations.BsonProperty;

public enum Role {
    @BsonProperty("ADMIN")
    ADMIN,

    @BsonProperty("CLIENT")
    CLIENT,

    @BsonProperty("EMPLOYEE")
    EMPLOYEE
}
