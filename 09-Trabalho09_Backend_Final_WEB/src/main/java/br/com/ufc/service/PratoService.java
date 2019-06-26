package br.com.ufc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ufc.model.Prato;
import br.com.ufc.repository.PratoRepository;
import br.com.ufc.utils.RestauranteFileUtils;

@Service
public class PratoService {

	@Autowired
	private PratoRepository pratoRepository;

	public void cadastrarPrato(Prato prato, MultipartFile imagem) {
		pratoRepository.save(prato);
		
		String caminho = "src/main/resources/static/images/" + prato.getNome() + prato.getCodigo() + ".png";
		RestauranteFileUtils.salvarImagem(caminho, imagem);
	}

	public List<Prato> listarPratos() {
		return pratoRepository.findAll();
	}

	public void removerPrato(Long codigo) {
		pratoRepository.deleteById(codigo);
	}

	public Prato buscarPorId(Long codigo) {
		return pratoRepository.getOne(codigo);
	}
}
