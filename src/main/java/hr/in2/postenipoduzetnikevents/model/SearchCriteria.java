package hr.in2.postenipoduzetnikevents.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SearchCriteria {

    private String name;
    private LocalDateTime startFrom;
    private LocalDateTime startTo;
    private LocalDateTime endFrom;
    private LocalDateTime endTo;
    private Boolean free;
    private List<City> cities;
}
