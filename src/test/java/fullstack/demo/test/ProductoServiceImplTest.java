package fullstack.demo.test;

import fullstack.demo.DAO.ProductoDAO;
import fullstack.demo.Entidad.Categoria;
import fullstack.demo.Entidad.Producto;
import fullstack.demo.ServiciosImpl.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceImplTest {

    @Mock
    private ProductoDAO productoDAO;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearProductoCorrectamente() {

        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1);

        Producto producto = new Producto();

        producto.setProducto("Polo Nike");
        producto.setPrecioVenta(BigDecimal.valueOf(80));
        producto.setPrecioCompra(BigDecimal.valueOf(50));
        producto.setStock(15);
        producto.setDescripcion("Polo deportivo");
        producto.setEstado(true);
        producto.setTalla("M");
        producto.setColor("Negro");
        producto.setGenero(true);
        producto.setCategoria(categoria);

        when(productoDAO.save(producto)).thenReturn(producto);

        Producto resultado = productoService.crearProducto(producto);

        assertNotNull(resultado);

        assertEquals("Polo Nike", resultado.getProducto());
        assertEquals(15, resultado.getStock());

        verify(productoDAO, times(1)).save(producto);
    }
}