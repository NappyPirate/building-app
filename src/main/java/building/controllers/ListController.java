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

@Controller
public class ListController {
    @Autowired
    private BuildingDao buildingDao;

    @RequestMapping(value="/", method=RequestMethod.POST, params={"createBuilding"})
    public String createBuilding(Model model) {
        Building building = null;
        try {
            building = new Building("", "", "", "", "");
            buildingDao.save(building);
        }
        catch (Exception ex) {
            System.out.println("Error creating the building: " + ex.toString());
        }
        
        model.addAttribute("building", building);
        return "redirect:/building?id=" + building.getId();
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST, params={"deleteBuilding"})
    public String deleteBuilding(HttpServletRequest req, Model model) {
        try {
            long buildingId = Long.parseLong(req.getParameter("deleteBuilding"));
            buildingDao.delete(buildingId);
        }
        catch (Exception ex) {
            System.out.println("Error deleting the building: " + ex.toString());
        }
        
        return "redirect:/";
    }

    @RequestMapping(value="/",  method=RequestMethod.GET)
    public String allBuildings(Model model) {
        Iterable<Building> buildingList = null;
        try {
            buildingList = buildingDao.findAll();
        }
        catch (Exception ex) {
            System.out.println("Error retrieving the list of buildings: " + ex.toString());
        }
        
        model.addAttribute("buildingList", buildingList);
        return "list";
    }

}