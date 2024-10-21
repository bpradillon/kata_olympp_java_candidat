package fr.olympp.kata.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Army {

    private String name;
    private FootSoldiers footSoldiers;
    private Integer armyAttack;
    private Integer armyDefense;

    public Army(Army army) {
        this(army.name, new FootSoldiers(army.footSoldiers), army.armyAttack, army.armyDefense);
    }
}
