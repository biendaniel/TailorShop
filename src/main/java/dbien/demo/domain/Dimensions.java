package dbien.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "dimensions")
class Dimensions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "collar_size")
    private Integer collarSize;

    @Column(name = "chest_size")
    private Integer chestSize;

    @Column(name = "sleeve_length")
    private Integer sleeveLength;

    @Column(name = "waist_size")
    private Integer waistSize;

    @Column(name = "shoulder_width")
    private Integer shoulderWidth;

    @Column(name = "leg_length")
    private Integer legLength;

    @Column(name = "leg_width")
    private Integer legWidth;

}