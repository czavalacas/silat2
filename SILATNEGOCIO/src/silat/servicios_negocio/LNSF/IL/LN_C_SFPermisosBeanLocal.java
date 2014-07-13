package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import java.util.List;

import javax.ejb.Local;

import silat.servicios_negocio.Beans.BeanPermisos;
import silat.servicios_negocio.entidades.admin.ADPermiso;

@Local
public interface LN_C_SFPermisosBeanLocal {

    public boolean hayHijos(List<BeanPermisos> lista,String nombUser, BigDecimal nidRo);
    public BeanPermisos crearArbolAux(int nivel, List<BeanPermisos> hijos, BeanPermisos padre, BeanPermisos raiz,
                                      BigDecimal nidRol,
                                      String nombUser,
                                      List<BigDecimal> lstPermisos);
    public BeanPermisos setearHijos(BeanPermisos raiz, List<BeanPermisos> hijos,BeanPermisos padre,BigDecimal nidp, int pv,
                                    BigDecimal nidRol,
                                    String nombUser,
                                    List<BigDecimal> lstPermisos);
    List<BeanPermisos> getCrearArbolNuevo(BigDecimal nidRol,String nombUser);
    public BeanPermisos setBean(ADPermiso permiso,String indMostrar,List<BigDecimal> lstPermisos);
    List<BeanPermisos> getCrearArbolNuevoAdmPermisos(BigDecimal nidRol,String nombUser);
    List<BeanPermisos> getCrearArbolNuevoAllPermisos(List<BigDecimal> permisosUser);
    void print(Object o);
}