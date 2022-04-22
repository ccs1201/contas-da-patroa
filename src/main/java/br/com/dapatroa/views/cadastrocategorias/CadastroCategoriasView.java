package br.com.dapatroa.views.cadastrocategorias;

import br.com.dapatroa.data.TipoCategoria;
import br.com.dapatroa.views.FormBase;
import br.com.dapatroa.views.MainLayout;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Cadastro Categorias")
@Route(value = "cadastroCategorias", layout = MainLayout.class)
@PermitAll
public class CadastroCategoriasView extends FormBase {

    private CheckboxGroup<TipoCategoria> checkboxGroupTipo;

    public CadastroCategoriasView() {
       configureCheckboxGroupTipo();

    }

    private void configureCheckboxGroupTipo(){
        checkboxGroupTipo = new CheckboxGroup();
        checkboxGroupTipo.setLabel("Tipo");
        checkboxGroupTipo.setItems(TipoCategoria.values());
        add(checkboxGroupTipo);
    }

    @Override
    protected void save() {

    }

    @Override
    protected void delete() {

    }
}
