package fr.neatcraft.championship.championship;

import fr.neatcraft.championship.championship.controller.ChampionshipController;
import fr.neatcraft.championship.championship.repository.ChampionshipRepository;
import fr.neatcraft.championship.championship.service.ChampionshipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindAllTest {

    @Test
    public void Should_return_all_championship() {
        var dao = new ChampionshipStubDao();
        var controller = new ChampionshipController(
                new ChampionshipService(new ChampionshipRepository(dao))
        );

        var result = controller.findAll();

        Assertions.assertEquals(dao.championship.size(), result.size());
    }
}
