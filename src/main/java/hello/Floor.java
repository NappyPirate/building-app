import javax.persistence.*;

@Entity
@Table(name = "floor")
public class Floor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Column(name = "floor_number")
    private String floor_number;

    @ManyToOne
    private Building building;

    @OneToMany(mappedBy = "floor")
    private Set<Room> rooms;

    public Floor(Integer floor_number, Building building) {
        this.floor_number = floor_number;
        this.building = building;

        //create a new Room, floors must have at least one room
        this.rooms.add(
                new Room(
                        1,
                        this
                ))
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

    public String getFloorNumber() {
        return floor_number;
    }

    public void setFloorNumber(Integer floor_number) {
        this.floor_number = floor_number;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Set<Room> getRooms() {
        return Rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}