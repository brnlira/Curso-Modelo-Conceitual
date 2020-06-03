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

	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringExercicio1Application.class, args);
	}
	
	    @Override
	    public void run(String... args) throws Exception {
	        logger.info("ApplicationStartupRunner run method Started !!");
	        
	        Categoria cat1 = new Categoria(null, "Informatica");
	   		Categoria cat2 = new Categoria(null, "Escritorio");
	   		
	   		Produto p1 = new Produto(null, "Computador", 2000.00);
	   		Produto p2 = new Produto(null, "Impressora", 800.00);
	   		Produto p3 = new Produto(null, "Mouse", 80.00);
	   		   		
	   		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
	   		cat2.getProdutos().addAll(Arrays.asList(p2));
	   		
	   		p1.getCategorias().addAll(Arrays.asList(cat1));
	   		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
	   		p3.getCategorias().addAll(Arrays.asList(cat1));
	   		   		
	   		Estado est1 = new Estado(null, "Minas Gerais");
	   		Estado est2 = new Estado(null, "São Paulo");
	   		
	   		Cidade c1 = new Cidade(null, "Uberlândia", est1);
	   		Cidade c2 = new Cidade(null, "São Paulo", est2);
	   		Cidade c3 = new Cidade(null, "Campinas", est2);
	   		
	   		est1.getCidades().addAll(Arrays.asList(c1));
	   		est2.getCidades().addAll(Arrays.asList(c2, c3)); 
	   		
	   		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "39665211245", TipoCliente.PESSOAFISICA);
	   		
	   		cli1.getTelefones().addAll(Arrays.asList("45142563", "969582325"));  		
	
	   		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Perdizes", "01258020", cli1, c1);
	   		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Sumaré", "91258420", cli1, c2);
	   		
	   		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
	   		
	   		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	   		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	   		estadoRepository.saveAll(Arrays.asList(est1, est2));
	   		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3)); 
	   		clienteRepository.saveAll(Arrays.asList(cli1));
	   		enderecoRepository.saveAll(Arrays.asList(e1, e2));
   		
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
