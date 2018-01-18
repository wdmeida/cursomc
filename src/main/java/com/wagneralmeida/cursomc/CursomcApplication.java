package com.wagneralmeida.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wagneralmeida.cursomc.domain.Categoria;
import com.wagneralmeida.cursomc.domain.Cidade;
import com.wagneralmeida.cursomc.domain.Cliente;
import com.wagneralmeida.cursomc.domain.Endereco;
import com.wagneralmeida.cursomc.domain.Estado;
import com.wagneralmeida.cursomc.domain.Pagamento;
import com.wagneralmeida.cursomc.domain.PagamentoComBoleto;
import com.wagneralmeida.cursomc.domain.PagamentoComCartao;
import com.wagneralmeida.cursomc.domain.Pedido;
import com.wagneralmeida.cursomc.domain.Produto;
import com.wagneralmeida.cursomc.domain.enums.EstadoPagamento;
import com.wagneralmeida.cursomc.domain.enums.TipoCliente;
import com.wagneralmeida.cursomc.repositories.CategoriaRepository;
import com.wagneralmeida.cursomc.repositories.CidadeRepository;
import com.wagneralmeida.cursomc.repositories.ClienteRepository;
import com.wagneralmeida.cursomc.repositories.EnderecoRepository;
import com.wagneralmeida.cursomc.repositories.EstadoRepository;
import com.wagneralmeida.cursomc.repositories.PagamentoRepository;
import com.wagneralmeida.cursomc.repositories.PedidoRepository;
import com.wagneralmeida.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática"),
				  cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00),
				p2 = new Produto(null, "Impressora", 800.00),
				p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais"),
			   est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1),
			   c2 = new Cidade(null, "São Paulo", est2),
			   c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12345678998", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("2344456786", "9876567898"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "380220834", cli1, c1),
				 e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38077012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1),
			   ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6),
				  pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
	}
}
