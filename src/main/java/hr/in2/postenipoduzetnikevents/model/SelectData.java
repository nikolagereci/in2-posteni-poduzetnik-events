package hr.in2.postenipoduzetnikevents.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
public class SelectData {
    private String label;
    private String title;
    private String value;

    /**
     * Podržava samo OrgUnit i City liste
     * @param objects
     * @return
     */
    public static List<SelectData> objectListToSelectDataList(List<Object> objects){
        List<SelectData> selectData = new LinkedList<>();
        if (!objects.isEmpty()){
            ArrayList<Object> objList = (ArrayList<Object>) objects.get(0);
            if (!objList.isEmpty() && objList.get(0) instanceof OrgUnit){
                objList.forEach(o -> {
                    OrgUnit org = (OrgUnit) o;
                    selectData.add(new SelectData(org.getName(), org.getName(), String.valueOf(org.getId())));
                });
            }else if (!objList.isEmpty() && objList.get(0) instanceof City){
                objList.forEach(o -> {
                    City org = (City) o;
                    selectData.add(new SelectData(org.getName(), org.getName(), String.valueOf(org.getId())));
                });
            }
            return selectData;
        }else{
            return null;
        }
    }

}