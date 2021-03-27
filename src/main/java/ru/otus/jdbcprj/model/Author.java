package ru.otus.jdbcprj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {

    public Author(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Id
    private String id;

    private String firstName;

    private String secondName;

    @Override
    public String toString() {
        return id + ", " + firstName + ", " + secondName;
    }
}
