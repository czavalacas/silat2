package silat.servicios_negocio.LNSF.IL;

import java.math.BigDecimal;

import javax.ejb.Local;

import silat.servicios_negocio.entidades.admin.ADParty;

@Local
public interface LN_C_SFPartyLocal {
    public ADParty grabarParty( String cidUbigeo,
                                String cDetalle,
                                String cEmail,
                                String cTelf);
    public ADParty modificarParty(  BigDecimal nidParty,
                                    String cidUbigeo,
                                    String cDetalle,
                                    String cEmail,
                                    String cTelf);
}
