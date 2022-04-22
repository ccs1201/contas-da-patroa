package br.com.dapatroa.data.entity;

import br.com.dapatroa.data.TipoCategoria;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "categoria_despesa")
public class Categoria extends AbstractEntity{

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private TipoCategoria tipoCategoria;

    @Column(unique = true, length = 20)
    @NotNull
    private String descricao;
    @Column(length = 100)
    private String obs;

    public Categoria() {
    }

    public Categoria(TipoCategoria tipoCategoria, String descricao, String obs) {
        this.tipoCategoria = tipoCategoria;
        this.descricao = descricao;
        this.obs = obs;
    }

    public TipoCategoria getTipoCategoria() {
        return tipoCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getObs() {
        return obs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        if (!super.equals(o)) return false;
        Categoria categoria = (Categoria) o;
        return getTipoCategoria() == categoria.getTipoCategoria() && getDescricao().equals(categoria.getDescricao()) && Objects.equals(getObs(), categoria.getObs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTipoCategoria(), getDescricao(), getObs());
    }
}
