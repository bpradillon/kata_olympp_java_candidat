package fr.olympp.kata.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BattleReport {

    private String winner;
    private Status status;
    private List<Clan> initialClans;
    private List<History> history;

}
