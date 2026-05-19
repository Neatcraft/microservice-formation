package fr.neatcraft.championship.managment.unit;

import fr.neatcraft.championship.managment.controller.ChampionshipController;
import fr.neatcraft.championship.managment.repository.ChampionshipRepository;
import fr.neatcraft.championship.managment.repository.MatchRepository;
import fr.neatcraft.championship.managment.service.ChampionshipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindAllTest {
    @Test
    public void Should_return_all_championship() {
        // Init
        var dao = new ChampionshipStubDao();
        var controller = new ChampionshipController(
                new ChampionshipService(
                        new ChampionshipRepository(dao),
                        new MatchRepository(new MatchStubDao())
                )
        );
        // Exec

        var championship = controller.findAll();

        // Validation
        Assertions.assertEquals(championship.size(), dao.championship.size());
    }
}
