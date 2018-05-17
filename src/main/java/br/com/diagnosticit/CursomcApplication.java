package br.com.diagnosticit;

import br.com.diagnosticit.resources.domain.Categoria;
import br.com.diagnosticit.resources.domain.Cidade;
import br.com.diagnosticit.resources.domain.Estado;
import br.com.diagnosticit.resources.domain.Produto;
import br.com.diagnosticit.resources.repositories.CategoriaRepository;
import br.com.diagnosticit.resources.repositories.CidadeRepository;
import br.com.diagnosticit.resources.repositories.EstadoRepository;
import br.com.diagnosticit.resources.repositories.ProdutoRepository;
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
        
    }
}
