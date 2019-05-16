package empresa.interfacesUsuario;

import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;

public interface Vista {
    ClienteParticular getClientePart();
    ClienteEmpresa getClienteEmp();


    void setModelLista();
    void setModelLlamadas();

}
