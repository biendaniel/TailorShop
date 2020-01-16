package dbien.demo.domain;


import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "img_url")
    private String url;


}