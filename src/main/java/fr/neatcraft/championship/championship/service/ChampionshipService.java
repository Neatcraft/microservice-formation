package fr.neatcraft.championship.championship.service;

import fr.neatcraft.championship.championship.repository.ChampionshipRepository;
import fr.neatcraft.championship.championship.repository.model.Championship;
import fr.neatcraft.championship.championship.service.command.ModifyDatesCommand;
import fr.neatcraft.championship.championship.service.command.ModifyStatusCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ChampionshipService {
    private final ChampionshipRepository championshipRepository;

    public ChampionshipService(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    public List<Championship> findAll() {
        return this.championshipRepository.findAll();
    }

    public Championship findOneById(UUID id) {
        return this.championshipRepository.findById(id)
                .orElseThrow(() -> new ChampionshipNotFoundException(id));
    }

    @Transactional
    public void create(Championship championship) {
        this.championshipRepository.create(championship);
    }

    @Transactional
    public void replace(UUID id, Championship championship) {
        if (this.championshipRepository.findById(id).isEmpty()) {
            throw new ChampionshipNotFoundException(id);
        }
        this.championshipRepository.replace(championship);
    }

    @Transactional
    public void modify(UUID id, ModifyDatesCommand command) {
        Championship updated = this.championshipRepository.findById(id).orElseThrow(() -> new ChampionshipNotFoundException(id));
                updated.changePeriod(command.startDate(), command.endDate());
        this.championshipRepository.replace(updated);
    }

    @Transactional
    public void modify(UUID id, ModifyStatusCommand command) {
        Championship updated = findOneById(id).changeStatus(command.status());
        this.championshipRepository.replace(updated);
    }


    @Transactional
    public void onMatchAdded(UUID championshipId) {
        findOneById(championshipId);
    }

    @Transactional
    public void delete(UUID id) {
        this.championshipRepository.delete(id);
    }
}
