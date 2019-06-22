package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;


import io.github.jhipster.application.service.dto.MeetingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Meeting} and its DTO {@link MeetingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MeetingMapper extends EntityMapper<MeetingDTO, Meeting> {



    default Meeting fromId(Long id) {
        if (id == null) {
            return null;
        }
        Meeting employee = new Meeting();
        employee.setId(id);
        return employee;
    }
}
