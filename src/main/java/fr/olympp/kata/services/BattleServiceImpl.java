package fr.olympp.kata.services;

import fr.olympp.kata.models.*;
import fr.olympp.kata.repository.BattleReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class BattleServiceImpl implements BattleService {

    private final BattleReportRepository repository;

    // responsible to solve the battle between two clans
    public BattleReport battle(Clan clan1, Clan clan2) {

        BattleReport battleReport = BattleReport
                .builder()
                .initialClans(List.of(new Clan(clan1), new Clan(clan2)))
                .status(Status.PROCESSING)
                .history(new ArrayList<>())
                .build();

        while (Status.PROCESSING.equals(battleReport.getStatus())) {
            Optional<Army> armyClan1 = clan1.getArmies().stream()
                    .filter(isNotDestoy())
                    .findFirst();
            Optional<Army> armyClan2 = clan2.getArmies().stream()
                    .filter(isNotDestoy())
                    .findFirst();

            if (armyClan1.isEmpty() && armyClan2.isEmpty()) {
                battleReport.setStatus(Status.DRAW);
                break;
            } else if (armyClan1.isEmpty()) {
                battleReport.setStatus(Status.WIN);
                battleReport.setWinner(clan2.getName());
                break;
            } else if (armyClan2.isEmpty()) {
                battleReport.setStatus(Status.WIN);
                battleReport.setWinner(clan1.getName());
                break;
            }

            History history = startRound(armyClan1.get(), armyClan2.get());

            battleReport.getHistory().add(history);

            if(history.getDamageOnArmy1() <= 0 && history.getDamageOnArmy2() <=0) {
                battleReport.setStatus(Status.DRAW);
            }
        }

        repository.create(battleReport);
        return battleReport;
    }

    private Predicate<? super Army> isNotDestoy() {
        return army -> army.getFootSoldiers().getNbUnits() > 0;
    }

    private History startRound(Army army1, Army army2) {
        int damageOnArmy1 = calculateDamage(army2, army1);
        int damageOnArmy2 = calculateDamage(army1, army2);

        int nbRemainingSoldiersArmy1 = calculateRemaining(army1, damageOnArmy1);
        int nbRemainingSoldiersArmy2 = calculateRemaining(army2, damageOnArmy2);

        army1.getFootSoldiers().setNbUnits(nbRemainingSoldiersArmy1);
        army2.getFootSoldiers().setNbUnits(nbRemainingSoldiersArmy2);

        return History
                .builder()
                .nameArmy1(army1.getName())
                .nameArmy2(army2.getName())
                .damageOnArmy1(damageOnArmy1)
                .damageOnArmy2(damageOnArmy2)
                .nbRemainingSoldiersArmy1(nbRemainingSoldiersArmy1)
                .nbRemainingSoldiersArmy2(nbRemainingSoldiersArmy2)
                .build();
    }

    private int calculateDamage(Army attacker, Army defender) {
        return attacker.getFootSoldiers().getTotalAttack() - defender.getFootSoldiers().getTotalDefense();
    }

    private int calculateRemaining(Army army, int damages) {
        FootSoldiers soldiers = army.getFootSoldiers();
        int lostHealth = Math.max(0, damages);
        int nbKills = lostHealth / soldiers.getHealth();

        return Math.max(0, soldiers.getNbUnits() - nbKills);
    }

}
