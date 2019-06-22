package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.MeetingService;
import io.github.jhipster.application.domain.Meeting;
import io.github.jhipster.application.repository.MeetingRepository;
import io.github.jhipster.application.service.MeetingService;
import io.github.jhipster.application.service.dto.MeetingDTO;
import io.github.jhipster.application.service.mapper.MeetingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Meeting}.
 */
@Service
@Transactional
public class MeetingServiceImpl implements MeetingService {

    private final Logger log = LoggerFactory.getLogger(MeetingServiceImpl.class);

    private final MeetingRepository MeetingRepository;

    private final MeetingMapper MeetingMapper;

    public MeetingServiceImpl(MeetingRepository MeetingRepository, MeetingMapper MeetingMapper) {
        this.MeetingRepository = MeetingRepository;
        this.MeetingMapper = MeetingMapper;
    }

    /**
     * Save a Meeting.
     *
     * @param MeetingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MeetingDTO save(MeetingDTO MeetingDTO) {
        log.debug("Request to save Meeting : {}", MeetingDTO);
        Meeting Meeting = MeetingMapper.toEntity(MeetingDTO);
        Meeting = MeetingRepository.save(Meeting);
        return MeetingMapper.toDto(Meeting);
    }

    /**
     * Get all the Meetings.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeetingDTO> findAll() {
        log.debug("Request to get all Meetings");
        return MeetingRepository.findAll().stream()
            .map(MeetingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one Meeting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MeetingDTO> findOne(Long id) {
        log.debug("Request to get Meeting : {}", id);
        return MeetingRepository.findById(id)
            .map(MeetingMapper::toDto);
    }

    /**
     * Delete the Meeting by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Meeting : {}", id);
        MeetingRepository.deleteById(id);
    }
}
