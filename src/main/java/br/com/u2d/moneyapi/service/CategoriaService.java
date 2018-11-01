package br.com.u2d.moneyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.u2d.moneyapi.entity.Categoria;
import br.com.u2d.moneyapi.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria salva(Categoria categoria) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		return categoriaSalva;
	}
	
	public Categoria buscaPorId(Long codigo) {
		Categoria categoriaSalva = categoriaRepository.findOne(codigo);
		return categoriaSalva;
	}
	
	public List<Categoria> listaCategoria() {
		return categoriaRepository.findAll();
	}
}
