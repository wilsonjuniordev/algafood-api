package dev.wilsonjunior.algafood.api.controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.wilsonjunior.algafood.domain.exception.EntidadeEmUsoException;
import dev.wilsonjunior.algafood.domain.exception.EntidadeNaoEncontradaException;
import dev.wilsonjunior.algafood.domain.model.Estado;
import dev.wilsonjunior.algafood.domain.repository.EstadoRepository;
import dev.wilsonjunior.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CadastroEstadoService cadastroEstadoService;

	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.listar();
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
		Estado estado = estadoRepository.buscar(estadoId);

		if (estado != null) {
			return ResponseEntity.ok(estado);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
		try {
			estado = estadoRepository.salvar(estado);

			return ResponseEntity.status(HttpStatus.CREATED).body(estado);

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{estadoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {

		try {
			Estado estadoAtual = estadoRepository.buscar(estadoId);

			if (estadoAtual != null) {
				estado.setId(estadoId);
				BeanUtils.copyProperties(estado, estadoAtual);

				cadastroEstadoService.salvar(estadoAtual);

				return ResponseEntity.ok(estadoAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {

		try {
			cadastroEstadoService.excluir(estadoId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}
