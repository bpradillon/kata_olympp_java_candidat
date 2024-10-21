package fr.olympp.kata.services;

import fr.olympp.kata.models.*;
import fr.olympp.kata.repository.BattleReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BattleServiceImplTest {

    @InjectMocks
    private BattleServiceImpl service;

    @Mock
    private BattleReportRepository repository;

    @Test
    void testBattle() {
        // Given
        Army troyArmy1 = Army
                .builder()
                .name("troyArmy1")
                .armyAttack(10000)
                .armyDefense(10000)
                .footSoldiers(FootSoldiers
                        .builder()
                        .nbUnits(100)
                        .attack(100)
                        .defense(100)
                        .health(100)
                        .build()
                )
                .build();

        Army troyArmy2 = Army
                .builder()
                .name("troyArmy2")
                .armyAttack(10000)
                .armyDefense(10000)
                .footSoldiers(FootSoldiers
                        .builder()
                        .nbUnits(100)
                        .attack(100)
                        .defense(1000)
                        .health(100)
                        .build()
                )
                .build();

        Clan clan1 = Clan
                .builder()
                .name("Troy")
                .armies(List.of(troyArmy1, troyArmy2))
                .build();

        Army athensArmy1 = Army

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

        Clan clan2 = Clan
                .builder()
                .name("Athens")
                .armies(List.of(athensArmy1))
                .build();

        // When
        BattleReport report = service.battle(clan1, clan2);

        // Then
        verify(repository).create(report);

        assertEquals(Status.DRAW, report.getStatus());
        assertEquals(2, report.getHistory().size());
        assertEquals("History(nameArmy1=troyArmy1, nameArmy2=athensArmy1, " +
                "damageOnArmy1=15000, damageOnArmy2=-15000, " +
                "nbRemainingSoldiersArmy1=0, nbRemainingSoldiersArmy2=50)", report.getHistory().get(0).toString());

        assertEquals("History(nameArmy1=troyArmy2, nameArmy2=athensArmy1, " +
                "damageOnArmy1=-75000, damageOnArmy2=-15000, " +
                "nbRemainingSoldiersArmy1=100, nbRemainingSoldiersArmy2=50)", report.getHistory().get(1).toString());
    }

}