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
import com.wagneralmeida.cursomc.domain.ItemPedido;
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
import com.wagneralmeida.cursomc.repositories.ItemPedidoRepository;
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
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática"),
				  cat2 = new Categoria(null, "Escritório"),
				  cat3 = new Categoria(null, "Cama, mesa e banho"),
				  cat4 = new Categoria(null, "Eletrônicos"),
				  cat5 = new Categoria(null, "Jardinagem"),
				  cat6 = new Categoria(null, "Decoração"),
				  cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null, "Computador", 2000.00),
				p2 = new Produto(null, "Impressora", 800.00),
				p3 = new Produto(null, "Mouse", 80.00),
				p4 = new Produto(null, "Mesa de escritório", 300.00),
				p5 = new Produto(null, "Toalha", 50.00),
				p6 = new Produto(null, "Colcha", 200.00),
				p7 = new Produto(null, "TV true color", 1200.00),
				p8 = new Produto(null, "Roçadeira", 800.00),
				p9 = new Produto(null, "Abajour", 100.00),
				p10 = new Produto(null, "Pendente", 180.00),
				p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
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
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00),
				   ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00),
				   ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
	}
}
