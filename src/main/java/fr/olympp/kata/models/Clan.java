package fr.olympp.kata.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Clan {

    private String name;
    private List<Army> armies;

    public Clan(Clan clan) {
        this(clan.name, clan.armies.stream().map(Army::new).toList());
    }
}
