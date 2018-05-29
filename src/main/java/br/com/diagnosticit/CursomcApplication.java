package br.com.diagnosticit;

import br.com.diagnosticit.resources.domain.Categoria;
import br.com.diagnosticit.resources.domain.Cidade;
import br.com.diagnosticit.resources.domain.Cliente;
import br.com.diagnosticit.resources.domain.Endereco;
import br.com.diagnosticit.resources.domain.Estado;
import br.com.diagnosticit.resources.domain.ItemPedido;
import br.com.diagnosticit.resources.domain.Pagamento;
import br.com.diagnosticit.resources.domain.PagamentoComBoleto;
import br.com.diagnosticit.resources.domain.PagamentoComCartao;
import br.com.diagnosticit.resources.domain.Pedido;
import br.com.diagnosticit.resources.domain.Produto;
import br.com.diagnosticit.resources.domain.enums.EstadoPagamento;
import br.com.diagnosticit.resources.domain.enums.TipoCliente;
import br.com.diagnosticit.resources.repositories.CategoriaRepository;
import br.com.diagnosticit.resources.repositories.CidadeRepository;
import br.com.diagnosticit.resources.repositories.ClienteRepository;
import br.com.diagnosticit.resources.repositories.EnderecoRepository;
import br.com.diagnosticit.resources.repositories.EstadoRepository;
import br.com.diagnosticit.resources.repositories.ItemPedidoRepository;
import br.com.diagnosticit.resources.repositories.PagamentoRepository;
import br.com.diagnosticit.resources.repositories.PedidoRepository;
import br.com.diagnosticit.resources.repositories.ProdutoRepository;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

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
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
                
        Categoria cat1 = new Categoria("Informática");
        Categoria cat2 = new Categoria("Escritório");
        
        Produto p1 = new Produto("Mouse", 80.00);
        Produto p2 = new Produto("Cadeira", 300.00);
        Produto p3 = new Produto("Teclado", 40.00);
        
        cat1.getProdutos().addAll( Arrays.asList( p1, p3 ));
        cat2.getProdutos().addAll( Arrays.asList( p2 ));
        
        p1.getCategorias().addAll( Arrays.asList( cat1, cat2 ));
        p2.getCategorias().addAll( Arrays.asList( cat1, cat2 ));
        p3.getCategorias().addAll( Arrays.asList( cat1 ));
        
        
        categoriaRepository.saveAll( Arrays.asList( cat1, cat2 ) );
        produtoRepository.saveAll( Arrays.asList( p1, p2, p3 ));
                
        Estado estadoSaopaulo     = new Estado("São Paulo");
        Estado estadoRiodejaneiro = new Estado("Rio de Janeiro");
                
        Cidade cidadeSaopaulo   = new Cidade( "São Paulo", estadoSaopaulo );
        Cidade cidadeBraganca   = new Cidade( "Bragança Paulista", estadoSaopaulo);
        Cidade cidadeFluminense = new Cidade( "Fluminense", estadoRiodejaneiro);
        
        estadoSaopaulo.getCidades().addAll( Arrays.asList( cidadeSaopaulo, cidadeBraganca ));
        estadoRiodejaneiro.getCidades().addAll( Arrays.asList( cidadeFluminense ));
        
        estadoRepository.saveAll( Arrays.asList( estadoSaopaulo, estadoRiodejaneiro ));
        cidadeRepository.saveAll( Arrays.asList( cidadeSaopaulo, cidadeBraganca, cidadeFluminense ));
        
        
        Cliente cli1 = new Cliente( null, "cristiano carvalho amaral", "cacofut@hotmail.com", "37346188861", TipoCliente.PESSOA_FISICA );
        cli1.getTelefones().addAll( Arrays.asList( "991874268", "37395268" ));
        
        Endereco end1 = new Endereco(null, "Rua Ernest Renam", "954", "bloco 1 - apt 221", "Paraisópolis", "056590020", cli1, cidadeSaopaulo );
        Endereco end2 = new Endereco(null, "Rua Joaquim floriano", "854", "bloco azul", "Vila Guilherme", "000000000", cli1, cidadeBraganca );
        
        
        clienteRepository.saveAll( Arrays.asList( cli1 ));
        enderecoRepository.saveAll( Arrays.asList( end1, end2 ));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32:00"), end1, cli1 );
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35:00"), end2, cli1 );
        
        Pagamento pagto1 = new PagamentoComCartao( null, 6, EstadoPagamento.QUITADO, ped1 );
        ped1.setPagamento(pagto1);
        Pagamento pagto2 = new PagamentoComBoleto( null, sdf.parse("20/10/2017 00:00:00"), null, EstadoPagamento.PENDENTE, ped2 );               
        ped2.setPagamento(pagto2);
        
        cli1.getPedidos().addAll( Arrays.asList( ped1, ped2 ));
        
        pedidoRepository.saveAll( Arrays.asList( ped1, ped2 ));
        pagamentoRepository.saveAll( Arrays.asList( pagto1, pagto2 ));
        
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 2000.00, 1);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 80.00, 2);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 800.00, 1);
        
        ped1.getItens().addAll(Arrays.asList( ip1, ip2 ));
        ped2.getItens().addAll(Arrays.asList( ip3 ));
        
        p1.getItens().addAll( Arrays.asList( ip1 ));
        p2.getItens().addAll( Arrays.asList( ip3 ));
        p3.getItens().addAll( Arrays.asList( ip2 ));
        
        itemPedidoRepository.saveAll( Arrays.asList( ip1, ip2, ip3 ));
        
        
    }
}
