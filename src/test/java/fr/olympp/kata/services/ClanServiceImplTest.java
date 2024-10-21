package fr.olympp.kata.services;

import fr.olympp.kata.models.Army;
import fr.olympp.kata.models.Clan;
import fr.olympp.kata.models.FootSoldiers;
import fr.olympp.kata.repository.ClanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClanServiceImplTest {

    @InjectMocks
    private ClanServiceImpl service;

    @Mock
    private ClanRepository repository;

    @Test
    void testGetClan() {
        // Given
        String clanName = "troy";

        // When
        service.getClan(clanName);

        // Then
        verify(repository).getClan(clanName);
    }

    @Test
    void testGetClans() {
        // Given

        // When
        service.getClans();

        // Then
        verify(repository).getClans();
    }

    @Test
    void testAddArmy() {
        // Given
        String clanName = "athens";
        Army army = Army

                .builder()
                .name("athensArmy1")
                .armyAttack(25000)
                .armyDefense(25000)
                .footSoldiers(FootSoldiers
                        .builder()
                        .nbUnits(50)
                        .attack(500)
                        .defense(500)
                        .health(100)
                        .build()
                )
                .build();

        Clan mockClan = Clan.builder().armies(new ArrayList<>()).build();
        when(repository.getClan(clanName)).thenReturn(mockClan);

        // When
        service.addArmy(clanName, army);

        // Then
        assertEquals(1, mockClan.getArmies().size());
        assertEquals(army, mockClan.getArmies().get(0));
    }

    @Test
    void testRemoveArmy() {
        // Given
        String clanName = "athens";
        String armyName = "athensArmy1";

        Army army = Army
                .builder()
                .name(armyName)
                .build();

        Clan mockClan = Clan.builder().armies(List.of(army)).build();
        when(repository.getClan(clanName)).thenReturn(mockClan);

        // When
        service.removeArmy(clanName, armyName);

        // Then
        assertTrue(mockClan.getArmies().isEmpty());
    }
}