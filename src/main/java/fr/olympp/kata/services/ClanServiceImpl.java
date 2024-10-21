package fr.olympp.kata.services;

import fr.olympp.kata.models.Army;
import fr.olympp.kata.models.Clan;
import fr.olympp.kata.repository.ClanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClanServiceImpl implements ClanService {

    private final ClanRepository repository;

    @Override
    public Clan getClan(String clanName) {
        return repository.getClan(clanName);
    }

    @Override
    public List<Clan> getClans() {
        return repository.getClans();
    }

    @Override
    public void addArmy(String clanName, Army army) {
        Clan clan = getClan(clanName);
        clan.getArmies().add(army);
    }

    @Override
    public void removeArmy(String clanName, String armyName) {
        Clan clan = getClan(clanName);
        clan.setArmies(clan.getArmies().stream()
                .filter(army -> !army.getName().equals(armyName))
                .toList()
        );
    }
}
