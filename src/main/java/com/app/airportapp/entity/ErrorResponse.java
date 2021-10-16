package com.app.airportapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@Getter
@Setter
@XmlRootElement(name = "error")
public class ErrorResponse {
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
    //General error message about nature of error
    private String message;
    //Specific errors in API request processing
    private List<String> details;

}
