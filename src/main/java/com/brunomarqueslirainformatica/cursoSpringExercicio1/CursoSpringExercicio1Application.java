package com.brunomarqueslirainformatica.cursoSpringExercicio1;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Categoria;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Cidade;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Cliente;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Endereco;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Estado;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.ItemPedido;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Pagamento;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.PagamentoComBoleto;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.PagamentoComCartao;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Pedido;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.Produto;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.enums.EstadoPagamento;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.domain.enums.TipoCliente;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.CategoriaRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.CidadeRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.ClienteRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.EnderecoRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.EstadoRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.ItemPedidoRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.PagamentoRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.PedidoRepository;
import com.brunomarqueslirainformatica.cursoSpringExercicio1.repositories.ProdutoRepository;

@SpringBootApplication
@Component
public class CursoSpringExercicio1Application implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired 
	private PedidoRepository pedidoRepository;
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	private Categoria cat1;
	private Categoria cat2;
	private Categoria cat3;
	private Categoria cat4;
	private Categoria cat5;
	private Categoria cat6;
	private Categoria cat7;

	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringExercicio1Application.class, args);
	}
	
	    @Override
	    public void run(String... args) throws Exception {
	        logger.info("ApplicationStartupRunner run method Started !!");
	        
	    	Produto p1 = new Produto(null, "Computador", 2000.00);
			Produto p2 = new Produto(null, "Impressora", 800.00);
			Produto p3 = new Produto(null, "Mouse", 80.00);
			Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
			Produto p5 = new Produto(null, "Toalha", 50.00);
			Produto p6 = new Produto(null, "Colcha", 200.00);
			Produto p7 = new Produto(null, "TV True color", 1200.00);
			Produto p8 = new Produto(null, "Roçadeira", 800.00);
			Produto p9 = new Produto(null, "Abajour", 100.00);
			Produto p10 = new Produto(null, "Pendente", 180.00);
			Produto p11 = new Produto(null, "Shampoo", 90.00);	
			
			p1.getCategorias().addAll(Arrays.asList(cat1));
			p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
			p3.getCategorias().addAll(Arrays.asList(cat1));
			p4.getCategorias().addAll(Arrays.asList(cat2));
			p5.getCategorias().addAll(Arrays.asList(cat3));
			p6.getCategorias().addAll(Arrays.asList(cat3));
			p7.getCategorias().addAll(Arrays.asList(cat4));
			p8.getCategorias().addAll(Arrays.asList(cat5));
			p9.getCategorias().addAll(Arrays.asList(cat6));
			p10.getCategorias().addAll(Arrays.asList(cat6));
			p11.getCategorias().addAll(Arrays.asList(cat7));
	   		
			cat1.getProdutos().addAll(Arrays.asList(p2, p4));
			cat2.getProdutos().addAll(Arrays.asList(p2, p4));
			cat3.getProdutos().addAll(Arrays.asList(p5, p6));
			cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
			cat5.getProdutos().addAll(Arrays.asList(p8));
			cat6.getProdutos().addAll(Arrays.asList(p9, p10));
			cat7.getProdutos().addAll(Arrays.asList(p11));

			categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
			produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));


	   		Estado est1 = new Estado(null, "Minas Gerais");
	   		Estado est2 = new Estado(null, "São Paulo");
	   		
	   		Cidade c1 = new Cidade(null, "Uberlândia", est1);
	   		Cidade c2 = new Cidade(null, "São Paulo", est2);
	   		Cidade c3 = new Cidade(null, "Campinas", est2);
	   		
	   		est1.getCidades().addAll(Arrays.asList(c1));
	   		est2.getCidades().addAll(Arrays.asList(c2, c3)); 
	   		
	   		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "39665211245", TipoCliente.PESSOAFISICA);
	   		Cliente cli2 = new Cliente(null, "Antonia Belmudes", "antonia@gmail.com", "49665211245", TipoCliente.PESSOAFISICA);
	   		Cliente cli3 = new Cliente(null, "Breno Hurst", "berno@gmail.com", "59665211245", TipoCliente.PESSOAFISICA);
	   		
	   		cli1.getTelefones().addAll(Arrays.asList("45142563", "969582325"));
	   		cli2.getTelefones().add("97652525");
	   		cli3.getTelefones().add("76980504");
	
	   		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Perdizes", "01258020", cli1, c1);
	   		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Sumaré", "91258420", cli1, c2);
	   		
	   		Endereco e3 = new Endereco(null, "Av Brasil", "1530", "", "Sumaré", "03258020", cli2, c2);
	   		Endereco e4 = new Endereco(null, "Av dos Pássaros", "15", "", "Praça das Àrvores", "20258420", cli3, c2);
	   		
	   		cli3.getEnderecos().add(e4);
	   		cli2.getEnderecos().add(e3);
	   		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

	   		estadoRepository.saveAll(Arrays.asList(est1, est2));
	   		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3)); 
	   		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
	   		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
   		
   			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
   			
   			Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
   			Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e1);
   			
   			Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
   			pagamentoRepository.save(pagto1);
   			ped1.setPagamento(pagto1);
   			
   			Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
   			pagamentoRepository.save(pagto2);
   			ped2.setPagamento(pagto2);
   			
   			
   			cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
   			
   			pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
   			
   			ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
   			ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
   			ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
   			
   			ped1.getItens().addAll(Arrays.asList(ip1, ip2));
   			ped2.getItens().addAll(Arrays.asList(ip3));
   			
   			p1.getItens().add(ip1);
   			p2.getItens().add(ip3);
   			p3.getItens().add(ip2);
   			
   			itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
        }
    
    }
