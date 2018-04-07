package com.marcus.cursomc;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcus.cursomc.domain.Categoria;
import com.marcus.cursomc.domain.Cidade;
import com.marcus.cursomc.domain.Cliente;
import com.marcus.cursomc.domain.Endereco;
import com.marcus.cursomc.domain.Estado;
import com.marcus.cursomc.domain.Produto;
import com.marcus.cursomc.repositories.CategoriaRepository;
import com.marcus.cursomc.repositories.CidadeRepository;
import com.marcus.cursomc.repositories.ClienteRepository;
import com.marcus.cursomc.repositories.EnderecoRepository;
import com.marcus.cursomc.repositories.EstadoRepository;
import com.marcus.cursomc.repositories.ProdutoRepository;
import com.marcus.cursomc.resources.enums.TipoCliente;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		
		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.00));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.00));
		Produto p3 = new Produto(null, "Computador", new BigDecimal(80.00));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia",est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Marcus Santos", "marcus817@gmail.com", "05386568400", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("996744186", "34323326"));
		
		Endereco end1 = new Endereco(null, "Rua do Jasmin", "52", "Apt 203", "Casa Caiada", "22333150", cli1, cid1);
		Endereco end2 = new Endereco(null, "Av. Paulista", "1043", "TI", "Centro", "11333000", cli1, cid2);
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
	}
}
