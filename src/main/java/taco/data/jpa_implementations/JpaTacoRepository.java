package taco.data.jpa_implementations;

import org.springframework.data.repository.CrudRepository;
import taco.data.TacoRepository;
import taco.domain.Taco;

public interface JpaTacoRepository extends CrudRepository<Taco,Long>, TacoRepository {
}
