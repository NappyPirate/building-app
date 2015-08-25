package building.models;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Version
    private Long version;

    @Column(name = "room_name")
    private String roomName;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;
    
    public Room() {
    
    }

    public Room(String roomName, Floor floor) {
        this.roomName = roomName;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}