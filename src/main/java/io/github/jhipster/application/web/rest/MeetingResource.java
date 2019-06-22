package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.Meeting;
import io.github.jhipster.application.service.MeetingService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.MeetingDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link Meeting}.
 */
@RestController
@RequestMapping("/api")
public class MeetingResource {

    private final Logger log = LoggerFactory.getLogger(MeetingResource.class);

    private static final String ENTITY_NAME = "microMeeting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetingService MeetingService;

    public MeetingResource(MeetingService MeetingService) {
        this.MeetingService = MeetingService;
    }

    /**
     * {@code POST  /Meetings} : Create a new Meeting.
     *
     * @param MeetingDTO the MeetingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new MeetingDTO, or with status {@code 400 (Bad Request)} if the Meeting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/Meetings")
    public ResponseEntity<MeetingDTO> createMeeting(@RequestBody MeetingDTO MeetingDTO) throws URISyntaxException {
        log.debug("REST request to save Meeting : {}", MeetingDTO);
        if (MeetingDTO.getId() != null) {
            throw new BadRequestAlertException("A new Meeting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeetingDTO result = MeetingService.save(MeetingDTO);
        return ResponseEntity.created(new URI("/api/Meetings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /Meetings} : Updates an existing Meeting.
     *
     * @param MeetingDTO the MeetingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated MeetingDTO,
     * or with status {@code 400 (Bad Request)} if the MeetingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the MeetingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/Meetings")
    public ResponseEntity<MeetingDTO> updateMeeting(@RequestBody MeetingDTO MeetingDTO) throws URISyntaxException {
        log.debug("REST request to update Meeting : {}", MeetingDTO);
        if (MeetingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeetingDTO result = MeetingService.save(MeetingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, MeetingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /Meetings} : get all the Meetings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of Meetings in body.
     */
    @GetMapping("/Meetings")
    public List<MeetingDTO> getAllMeetings() {
        log.debug("REST request to get all Meetings");
        return MeetingService.findAll();
    }

    /**
     * {@code GET  /Meetings/:id} : get the "id" Meeting.
     *
     * @param id the id of the MeetingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the MeetingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/Meetings/{id}")
    public ResponseEntity<MeetingDTO> getMeeting(@PathVariable Long id) {
        log.debug("REST request to get Meeting : {}", id);
        Optional<MeetingDTO> MeetingDTO = MeetingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(MeetingDTO);
    }

    /**
     * {@code DELETE  /Meetings/:id} : delete the "id" Meeting.
     *
     * @param id the id of the MeetingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/Meetings/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        log.debug("REST request to delete Meeting : {}", id);
        MeetingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
