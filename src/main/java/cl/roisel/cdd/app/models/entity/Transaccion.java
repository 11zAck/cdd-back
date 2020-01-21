package cl.roisel.cdd.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dvt__tl_trx")
public class Transaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




}
