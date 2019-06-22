package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Meeting;
import io.github.jhipster.application.service.dto.MeetingDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Meeting}.
 */
public interface MeetingService {

    /**
     * Save a Meeting.
     *
     * @param MeetingDTO the entity to save.
     * @return the persisted entity.
     */
    MeetingDTO save(MeetingDTO MeetingDTO);

    /**
     * Get all the Meetings.
     *
     * @return the list of entities.
     */
    List<MeetingDTO> findAll();


    /**
     * Get the "id" Meeting.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MeetingDTO> findOne(Long id);

    /**
     * Delete the "id" Meeting.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
