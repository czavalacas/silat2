package silat.servicios_negocio.LNSF.IR;

import java.math.BigDecimal;

import javax.ejb.Remote;

import silat.servicios_negocio.entidades.admin.ADParty;

@Remote
public interface LN_C_SFPartyRemote {
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
