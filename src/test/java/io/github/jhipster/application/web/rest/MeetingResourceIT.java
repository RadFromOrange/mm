package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.VisioApp;
import io.github.jhipster.application.domain.Meeting;
import io.github.jhipster.application.repository.MeetingRepository;
import io.github.jhipster.application.service.MeetingService;
import io.github.jhipster.application.service.dto.MeetingDTO;
import io.github.jhipster.application.service.mapper.MeetingMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MeetingResource} REST controller.
 */
@SpringBootTest(classes = VisioApp.class)
public class MeetingResourceIT {

    private static final String DEFAULT_CLIENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ADVISOR_ID = "AAAAAAAAAA";
    private static final String UPDATED_ADVISOR_ID = "BBBBBBBBBB";

    private static final String DEFAULT_APPOINTMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_APPOINTMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROOM_ID = "AAAAAAAAAA";
    private static final String UPDATED_ROOM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private MeetingRepository MeetingRepository;

    @Autowired
    private MeetingMapper MeetingMapper;

    @Autowired
    private MeetingService MeetingService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restMeetingMockMvc;

    private Meeting Meeting;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MeetingResource MeetingResource = new MeetingResource(MeetingService);
        this.restMeetingMockMvc = MockMvcBuilders.standaloneSetup(MeetingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Meeting createEntity(EntityManager em) {
        Meeting Meeting = new Meeting()
            .clientId(DEFAULT_CLIENT_ID)
            .advisorId(DEFAULT_ADVISOR_ID)
            .appointmentId(DEFAULT_APPOINTMENT_ID)
            .roomId(DEFAULT_ROOM_ID)
            .token(DEFAULT_TOKEN)
            .endDate(DEFAULT_END_DATE)
            .startDate(DEFAULT_START_DATE);
        return Meeting;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Meeting createUpdatedEntity(EntityManager em) {
        Meeting Meeting = new Meeting()
            .clientId(UPDATED_CLIENT_ID)
            .advisorId(UPDATED_ADVISOR_ID)
            .appointmentId(UPDATED_APPOINTMENT_ID)
            .roomId(UPDATED_ROOM_ID)
            .token(UPDATED_TOKEN)
            .endDate(UPDATED_END_DATE)
            .startDate(UPDATED_START_DATE);
        return Meeting;
    }

    @BeforeEach
    public void initTest() {
        Meeting = createEntity(em);
    }

    @Test
    @Transactional
    public void createMeeting() throws Exception {
        int databaseSizeBeforeCreate = MeetingRepository.findAll().size();

        // Create the Meeting
        MeetingDTO MeetingDTO = MeetingMapper.toDto(Meeting);
        restMeetingMockMvc.perform(post("/api/Meetings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(MeetingDTO)))
            .andExpect(status().isCreated());

        // Validate the Meeting in the database
        List<Meeting> MeetingList = MeetingRepository.findAll();
        assertThat(MeetingList).hasSize(databaseSizeBeforeCreate + 1);
        Meeting testMeeting = MeetingList.get(MeetingList.size() - 1);
        assertThat(testMeeting.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testMeeting.getAdvisorId()).isEqualTo(DEFAULT_ADVISOR_ID);
        assertThat(testMeeting.getAppointmentId()).isEqualTo(DEFAULT_APPOINTMENT_ID);
        assertThat(testMeeting.getRoomId()).isEqualTo(DEFAULT_ROOM_ID);
        assertThat(testMeeting.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testMeeting.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testMeeting.getStartDate()).isEqualTo(DEFAULT_START_DATE);
    }

    @Test
    @Transactional
    public void createMeetingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = MeetingRepository.findAll().size();

        // Create the Meeting with an existing ID
        Meeting.setId(1L);
        MeetingDTO MeetingDTO = MeetingMapper.toDto(Meeting);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMeetingMockMvc.perform(post("/api/Meetings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(MeetingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Meeting in the database
        List<Meeting> MeetingList = MeetingRepository.findAll();
        assertThat(MeetingList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMeetings() throws Exception {
        // Initialize the database
        MeetingRepository.saveAndFlush(Meeting);

        // Get all the MeetingList
        restMeetingMockMvc.perform(get("/api/Meetings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(Meeting.getId().intValue())))
            .andExpect(jsonPath("$.[*].clientId").value(hasItem(DEFAULT_CLIENT_ID.toString())))
            .andExpect(jsonPath("$.[*].advisorId").value(hasItem(DEFAULT_ADVISOR_ID.toString())))
            .andExpect(jsonPath("$.[*].appointmentId").value(hasItem(DEFAULT_APPOINTMENT_ID.toString())))
            .andExpect(jsonPath("$.[*].roomId").value(hasItem(DEFAULT_ROOM_ID.toString())))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())));
    }

    @Test
    @Transactional
    public void getMeeting() throws Exception {
        // Initialize the database
        MeetingRepository.saveAndFlush(Meeting);

        // Get the Meeting
        restMeetingMockMvc.perform(get("/api/Meetings/{id}", Meeting.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(Meeting.getId().intValue()))
            .andExpect(jsonPath("$.clientId").value(DEFAULT_CLIENT_ID.toString()))
            .andExpect(jsonPath("$.advisorId").value(DEFAULT_ADVISOR_ID.toString()))
            .andExpect(jsonPath("$.appointmentId").value(DEFAULT_APPOINTMENT_ID.toString()))
            .andExpect(jsonPath("$.roomId").value(DEFAULT_ROOM_ID.toString()))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMeeting() throws Exception {
        // Get the Meeting
        restMeetingMockMvc.perform(get("/api/Meetings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeeting() throws Exception {
        // Initialize the database
        MeetingRepository.saveAndFlush(Meeting);

        int databaseSizeBeforeUpdate = MeetingRepository.findAll().size();

        // Update the Meeting
        Meeting updatedMeeting = MeetingRepository.findById(Meeting.getId()).get();
        // Disconnect from session so that the updates on updatedMeeting are not directly saved in db
        em.detach(updatedMeeting);
        updatedMeeting
            .clientId(UPDATED_CLIENT_ID)
            .advisorId(UPDATED_ADVISOR_ID)
            .appointmentId(UPDATED_APPOINTMENT_ID)
            .roomId(UPDATED_ROOM_ID)
            .token(UPDATED_TOKEN)
            .endDate(UPDATED_END_DATE)
            .startDate(UPDATED_START_DATE);
        MeetingDTO MeetingDTO = MeetingMapper.toDto(updatedMeeting);

        restMeetingMockMvc.perform(put("/api/Meetings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(MeetingDTO)))
            .andExpect(status().isOk());

        // Validate the Meeting in the database
        List<Meeting> MeetingList = MeetingRepository.findAll();
        assertThat(MeetingList).hasSize(databaseSizeBeforeUpdate);
        Meeting testMeeting = MeetingList.get(MeetingList.size() - 1);
        assertThat(testMeeting.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testMeeting.getAdvisorId()).isEqualTo(UPDATED_ADVISOR_ID);
        assertThat(testMeeting.getAppointmentId()).isEqualTo(UPDATED_APPOINTMENT_ID);
        assertThat(testMeeting.getRoomId()).isEqualTo(UPDATED_ROOM_ID);
        assertThat(testMeeting.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testMeeting.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testMeeting.getStartDate()).isEqualTo(UPDATED_START_DATE);
    }



    @Test
    @Transactional
    public void rad() throws Exception {
        int databaseSizeBeforeUpdate = MeetingRepository.findAll().size();

        // Create the Meeting
        MeetingDTO MeetingDTO = MeetingMapper.toDto(Meeting);
        MeetingDTO MeetingDTO2 = MeetingMapper.toDto(Meeting);
        MeetingDTO MeetingDTO3 = MeetingMapper.toDto(Meeting);


        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeetingMockMvc.perform(post("/api/Meetings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(MeetingDTO)))
            .andExpect(status().isCreated());

        restMeetingMockMvc.perform(post("/api/Meetings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(MeetingDTO2)))
            .andExpect(status().isCreated());

        restMeetingMockMvc.perform(post("/api/Meetings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(MeetingDTO3)))
            .andExpect(status().isCreated());


        Optional<io.github.jhipster.application.domain.Meeting> u = MeetingRepository.findById(MeetingRepository.findAll().get(2).getId());

        u.ifPresent(meeting -> {
            try {
                meeting.setAdvisorId("i ve beenmodified");
                restMeetingMockMvc.perform(put("/api/Meetings")
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(meeting)))
                    .andExpect(status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        u.ifPresent(
            meeting -> meeting.setAdvisorId(MeetingRepository.findById(meeting.getId()).get().getAdvisorId())
        );
        // Validate the Meeting in the database
        List<Meeting>  MeetingList = MeetingRepository.findAll();

        assertThat(MeetingList).hasSize(databaseSizeBeforeUpdate+3);
    }

    @Test
    @Transactional
    public void updateNonExistingMeeting() throws Exception {
        int databaseSizeBeforeUpdate = MeetingRepository.findAll().size();

        // Create the Meeting
        MeetingDTO MeetingDTO = MeetingMapper.toDto(Meeting);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeetingMockMvc.perform(put("/api/Meetings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(MeetingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Meeting in the database
        List<Meeting> MeetingList = MeetingRepository.findAll();
        assertThat(MeetingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMeeting() throws Exception {
        // Initialize the database
        MeetingRepository.saveAndFlush(Meeting);

        int databaseSizeBeforeDelete = MeetingRepository.findAll().size();

        // Delete the Meeting
        restMeetingMockMvc.perform(delete("/api/Meetings/{id}", Meeting.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Meeting> MeetingList = MeetingRepository.findAll();
        assertThat(MeetingList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Meeting.class);
        Meeting Meeting1 = new Meeting();
        Meeting1.setId(1L);
        Meeting Meeting2 = new Meeting();
        Meeting2.setId(Meeting1.getId());
        assertThat(Meeting1).isEqualTo(Meeting2);
        Meeting2.setId(2L);
        assertThat(Meeting1).isNotEqualTo(Meeting2);
        Meeting1.setId(null);
        assertThat(Meeting1).isNotEqualTo(Meeting2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeetingDTO.class);
        MeetingDTO MeetingDTO1 = new MeetingDTO();
        MeetingDTO1.setId(1L);
        MeetingDTO MeetingDTO2 = new MeetingDTO();
        assertThat(MeetingDTO1).isNotEqualTo(MeetingDTO2);
        MeetingDTO2.setId(MeetingDTO1.getId());
        assertThat(MeetingDTO1).isEqualTo(MeetingDTO2);
        MeetingDTO2.setId(2L);
        assertThat(MeetingDTO1).isNotEqualTo(MeetingDTO2);
        MeetingDTO1.setId(null);
        assertThat(MeetingDTO1).isNotEqualTo(MeetingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(MeetingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(MeetingMapper.fromId(null)).isNull();
    }
}
