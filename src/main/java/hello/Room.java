import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Column(name = "room_number")
    private String room_number;

    @ManyToOne
    private Floor floor;

    public Room(Integer room_number, Floor floor) {
        this.room_number = room_number;
        this.floor = floor;
    }

     public Room(Floor floor) {
        this.floor = floor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getRoomNumber() {
        return room_number;
    }

    public void setRoomNumber(Integer room_number) {
        this.room_number = room_number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}