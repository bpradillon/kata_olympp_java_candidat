package fr.olympp.kata.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class History {
    
    private String nameArmy1;
    private String nameArmy2;
    private Integer damageOnArmy1;
    private Integer damageOnArmy2;
    private Integer nbRemainingSoldiersArmy1;
    private Integer nbRemainingSoldiersArmy2;

}
