package fr.olympp.kata.repository;

import fr.olympp.kata.models.BattleReport;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class BattleReportRepositoryImpl implements BattleReportRepository {
    @Cacheable("battleReports")
    public void create(BattleReport battleReport) {
        // Save battleReport in cache
    }
}
