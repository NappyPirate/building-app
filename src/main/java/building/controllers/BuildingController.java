package building.controllers;

import building.models.*;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;

@Controller
@SessionAttributes("building")
public class BuildingController {
    @Autowired
    private BuildingDao buildingDao;
    
    @ModelAttribute("building")
    public Building initBuilding(@RequestParam("id") Long id) {
        Building building = null;
        try {
            building = this.buildingDao.findById(id);
        }
        catch (Exception ex) {
            System.out.println("Error retrieving the building: " + ex.toString());
        }
        
        return building;
    }

    @RequestMapping(value="/building",  method=RequestMethod.GET)
    public String greeting(@RequestParam(value="id", required=true) String id, Model model) {
        Building building = null;
        try {
            building = buildingDao.findById(Long.parseLong(id));
        }
        catch (Exception ex) {
            System.out.println("Error retrieving the building: " + ex.toString());
        }
        
        model.addAttribute("building", building);
        return "building";
    }
    
    @RequestMapping(value="/building",  method=RequestMethod.POST, params={"save"})
    public String saveBuilding(@Valid @ModelAttribute ("building") Building building, Model model) {
        try {
            buildingDao.save(building);
        }
        catch (Exception ex) {
            System.out.println("Error saving the building: " + ex.toString());
        }
        
        return "redirect:/building?id=" + building.getId();
    }
    
    @RequestMapping(value="/building", method=RequestMethod.POST, params={"addFloor"})
    public String addFloor(@Valid @ModelAttribute ("building") Building building, Model model) {
        try {
            building.addFloor();
        }
        catch (Exception ex) {
            System.out.println("Error adding a floor: " + ex.toString());
        }
        
        model.addAttribute("building", building);
        return "building";
    }
    
    @RequestMapping(value="/building", method=RequestMethod.POST, params={"removeFloor"})
    public String removeFloor(Building building, HttpServletRequest req, Model model) {
        try {
            int floorNum = Integer.valueOf(req.getParameter("removeFloor"));
        
            if (building.getFloors().size() > 1) {
                building.removeFloorByNumber(floorNum);
            }
            else {
                List<String> messages = new ArrayList<String>();
                messages.add("Error: Cannot remove ground floor, buildings must have at least one floor");
                model.addAttribute("messages", messages);
            }
        }
        catch (Exception ex) {
            System.out.println("Error removing the floor: " + ex.toString());
        }
        
        model.addAttribute("building", building);
        return "building";
    }
    
    @RequestMapping(value="/building", method=RequestMethod.POST, params={"addRoom"})
    public String addRoom(@Valid @ModelAttribute ("building") Building building, HttpServletRequest req, Model model) {
        try {
            int floorNum = Integer.valueOf(req.getParameter("addRoom"));
            building.getFloorByNumber(floorNum).addRoom();
        }
        catch (Exception ex) {
            System.out.println("Error adding a room: " + ex.toString());
        }
        
        model.addAttribute("building", building);
        return "building";
    }
    
    @RequestMapping(value="/building", method=RequestMethod.POST, params={"removeRoom"})
    public String removeRoom(Building building, HttpServletRequest req, Model model) {
        try {
            String[] params = req.getParameter("removeRoom").split(",");
        
            int floorNum = Integer.valueOf(params[0]);
            int roomIndex = Integer.valueOf(params[1]);
            
            if (building.getFloorByNumber(floorNum).getRooms().size() > 1) {
                building.getFloorByNumber(floorNum).removeRoomByIndex(roomIndex);
            }
            else {
                List<String> messages = new ArrayList<String>();
                messages.add("Error: Cannot remove room, floors must have at least one room");
                model.addAttribute("messages", messages);
            }
        }
        catch (Exception ex) {
            System.out.println("Error removing the room: " + ex.toString());
        }
        
        model.addAttribute("building", building);
        return "building";
    }
}