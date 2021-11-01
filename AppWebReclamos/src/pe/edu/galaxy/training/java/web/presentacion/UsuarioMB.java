package pe.edu.galaxy.training.java.web.presentacion;

import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.ACTUALIZACION;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.ELIMINACION;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.LISTADO;
import static pe.edu.galaxy.training.java.web.presentacion.constantes.CrudEnum.REGISTRO;

import java.util.List;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pe.edu.galaxy.training.java.web.entity.Producto;
import pe.edu.galaxy.training.java.web.persistencia.exception.DAOException;
import pe.edu.galaxy.training.java.web.persistencia.interfaces.ProductoDAO;
import pe.edu.galaxy.training.java.web.presentacion.constantes.Mensajes;


@Scope("session")
@Controller(value="productoMB")

@Data 	
@EqualsAndHashCode(callSuper=false)

public class UsuarioMB extends GenericoMB{
	
}
