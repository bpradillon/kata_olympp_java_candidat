package fr.olympp.kata.repository;

import fr.olympp.kata.models.Clan;

import java.util.List;

public interface ClanRepository {

    void addClan(Clan clan);

    Clan updateClan(Clan clan);

    Clan getClan(String name);

    List<Clan> getClans();
}
