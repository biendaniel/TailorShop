package daniel.bien.tailor_shop.model.product;


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

    @Column(name = "file_name")
    private String fileName;


}