package br.com.dapatroa.data.entity;

import br.com.dapatroa.data.TipoCategoria;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "categoria_despesa")
public class Categoria extends AbstractEntity {

    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "Selecione um Tipo.")
    private TipoCategoria tipoCategoria;
    @NotNull(message = "Marque se, a categoria deve estar Ativa ou Não.")
    private boolean ativa;

    @Column(unique = true, length = 20)
    @NotNull(message = "Informe uma descrição.")
    private String descricao;
    @Column(length = 100)
    private String obs;

    public Categoria() {
    }

    public Categoria(TipoCategoria tipoCategoria, String descricao, String obs, boolean ativa) {
        this.tipoCategoria = tipoCategoria;
        this.descricao = descricao;
        this.obs = obs;
        this.ativa = ativa;
    }

    public TipoCategoria getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(TipoCategoria tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        if (!super.equals(o)) return false;

        Categoria categoria = (Categoria) o;

        if (ativa != categoria.ativa) return false;
        if (tipoCategoria != categoria.tipoCategoria) return false;
        if (!descricao.equals(categoria.descricao)) return false;
        return obs != null ? obs.equals(categoria.obs) : categoria.obs == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + tipoCategoria.hashCode();
        result = 31 * result + (ativa ? 1 : 0);
        result = 31 * result + descricao.hashCode();
        result = 31 * result + (obs != null ? obs.hashCode() : 0);
        return result;
    }
}
