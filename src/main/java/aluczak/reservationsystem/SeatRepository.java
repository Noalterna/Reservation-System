package aluczak.reservationsystem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface SeatRepository extends CrudRepository<Seat, Long> {
}
