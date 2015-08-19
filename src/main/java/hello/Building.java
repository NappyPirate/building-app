import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    @Column(name = "name")
    private String name;

    @Column(name = "street_address")
    private String address;

    @Column(name = "zip")
    private String zip;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "owner")
    private String owner;

    @OneToMany(mappedBy = "building")
    private Set<Floor> floors;

    public Building(String name, String address, String zip, String city, String state) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.state = state;

        
        if(this.floors == null) {
            this.floors = new HashSet<Floor>();
        }

        //create a new floor at ground level
        this.floors.add(
                new Floor(
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<Floor> getFloors() {
        return Floors;
    }

    public void setFloors(Set<Floor> floors) {
        this.floors = floors;
    }

    public void addFloor() {
        Iterator floorIter = this.floors.Iterator();

        int max = 0;

        while(floorIter.hasNext()){
            Floor floor = floorIter.next();
            Integer floorNum = floor.getFloorNumber();

            if (floorNum > max)
                max = floorNum;
        }

        this.floors.add(
            max + 1,
            building
        )
    }
}