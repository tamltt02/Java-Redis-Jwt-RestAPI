package solutions.ntq.social.NTQ_Social_Project.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "request_log")
public class RequestLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_time")
    private Date requestTime;

    @Column(name = "method")
    private String method;

    @Column(name = "path")
    private String path;

    private String userName;

}