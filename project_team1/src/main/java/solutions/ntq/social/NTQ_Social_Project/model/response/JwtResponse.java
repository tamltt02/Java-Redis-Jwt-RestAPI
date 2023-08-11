package solutions.ntq.social.NTQ_Social_Project.model.response;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }


    public String getToken() {
        return this.jwttoken;
    }
}
