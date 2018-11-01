package br.com.u2d.moneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.u2d.moneyapi.entity.Categoria;
import br.com.u2d.moneyapi.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> listaCategorias(){
		return categoriaService.listaCategoria();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscaPorId(@PathVariable Long codigo) {
		Categoria categoriaSalva = categoriaService.buscaPorId(codigo);
		return categoriaSalva != null ? ResponseEntity.ok().body(categoriaSalva): ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Categoria> salva(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaService.salva(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	
}
