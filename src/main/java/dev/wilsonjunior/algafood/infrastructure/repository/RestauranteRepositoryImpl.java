package dev.wilsonjunior.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.wilsonjunior.algafood.domain.model.Restaurante;
import dev.wilsonjunior.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscarPorId(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Transactional
	@Override
	public Restaurante salvar(Restaurante restautante) {
		return manager.merge(restautante);
	}

	@Transactional
	@Override
	public void remover(Restaurante restautante) {
		restautante = buscarPorId(restautante.getId());
		manager.remove(restautante);
	}

}
