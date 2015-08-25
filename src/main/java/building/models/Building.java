package building.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Floor> floors;
    
    public Building() {
    
    }

    public Building(String name, String address, String zip, String city, String state) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.state = state;

        if(this.floors == null) {
            this.floors = new ArrayList<Floor>();
        }

        this.floors.add(
            new Floor(
                    1,
                    this
            )
        );
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

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public void addFloor() {
        int max = 0;

        for (Floor i : floors){
            int floorNum = i.getFloorNumber();

            if (floorNum > max)
                max = floorNum;
        }

        this.floors.add(
            new Floor(
                max + 1,
                this
            )
        );
    }
    
    public Floor getFloorByNumber(int floorNumber) {
        int size = floors.size();
        
        for (int i = 0; i < size; i++) {
            Floor floor = floors.get(i);
            
            if (floor.getFloorNumber() == floorNumber)
                return floor;
        }
        
        return null;
    }
    
    public void removeFloorByNumber(int floorNumber) {
        int size = floors.size();
        
        if(size > 1) {
            int location = 0;
            
            for (int i = 0; i < size; i++) {
                if (floors.get(i).getFloorNumber() == floorNumber)
                    location = i;
            }
            
            for (int j = size - 1; j > location; j--)
            {
                Floor floor = this.floors.get(j);
                floor.setFloorNumber(floor.getFloorNumber() - 1);
            }
            this.floors.remove(location);
        }
    }
}