package fr.olympp.kata.repository;

import fr.olympp.kata.models.Clan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClanRepositoryImpl implements ClanRepository {

    @Override
    public void addClan(Clan clan) {
    }

    @Override
    public Clan updateClan(Clan clan) {
        return null;
    }

    @Override
    @Cacheable("clan")
    public Clan getClan(String name) {
        return init(name);
    }

    @Override
    @Cacheable("clans")
    public List<Clan> getClans() {
        return List.of(getClan("troy"), getClan("athens"));
    }

    public Clan init(String name) {
        return Clan
                .builder()
                .name(name)
                .armies(new ArrayList<>())
                .build();
    }
}
