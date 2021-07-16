package aluczak.reservationsystem;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRestRepository extends PagingAndSortingRepository<Movie, Long> {

    @RestResource(path = "byScreeningTime", exported = false)
    List<Movie> findByScreeningDateTimeBetweenOrderByTitleAscScreeningDateTimeAsc(
            @RequestParam("screeningDateTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime screeningDateTime,
            @RequestParam("endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate);
}
