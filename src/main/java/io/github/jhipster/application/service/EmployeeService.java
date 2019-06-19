package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link io.github.jhipster.application.domain.Employee}.
 */
public interface EmployeeService {

    /**
     * Save a employee.
     *
     * @param employeeDTO the entity to save.
     * @return the persisted entity.
     */
    EmployeeDTO save(EmployeeDTO employeeDTO);

    /**
     * Get all the employees.
     *
     * @return the list of entities.
     */
    List<EmployeeDTO> findAll();


    /**
     * Get the "id" employee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmployeeDTO> findOne(Long id);

    /**
     * Delete the "id" employee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
