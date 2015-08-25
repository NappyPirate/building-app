package building.models;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
 
@Transactional
public interface BuildingDao extends CrudRepository<Building, Long> {
    public Building findByName(String name);
    public Building findById(long id);
}
