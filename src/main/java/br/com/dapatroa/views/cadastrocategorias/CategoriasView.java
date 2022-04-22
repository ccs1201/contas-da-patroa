package br.com.dapatroa.views.cadastrocategorias;

import br.com.dapatroa.controller.CategoriaController;
import br.com.dapatroa.data.TipoCategoria;
import br.com.dapatroa.data.entity.Categoria;
import br.com.dapatroa.views.FormBase;
import br.com.dapatroa.views.MainLayout;
import com.vaadin.flow.component.checkbox.Checkbox;

import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hibernate.exception.ConstraintViolationException;

import javax.annotation.security.PermitAll;
import javax.persistence.PersistenceException;

@PageTitle("Categorias")
@Route(value = "categorias", layout = MainLayout.class)
@PermitAll
public class CategoriasView extends FormBase {

    //@PropertyId("tipoCategoria")
    private RadioButtonGroup<TipoCategoria> radiobuttonGroupTipo;
    @PropertyId("descricao")
    private TextField txtDescricao;
    @PropertyId("obs")
    private TextArea textAreaObs;
    @PropertyId("ativa")
    private Checkbox ativo;

    private CategoriaController controller;
    private Binder<Categoria> binder;


    public CategoriasView(CategoriaController controller) {
        this.controller = controller;
        binder = new BeanValidationBinder<>(Categoria.class);
        binder.bindInstanceFields(this);
        initComponents();
        binder.forField(radiobuttonGroupTipo).bind(Categoria::getTipoCategoria, Categoria::setTipoCategoria);
    }

    private void initComponents() {
        ativo = new Checkbox("Ativo?");
        add(ativo, 2);

        configureRadiobuttonGroupTipo();

        txtDescricao = new TextField("Descrição:");
        // txtDescricao.setMaxWidth("100%");
        textAreaObs = new TextArea("Observações:");
        textAreaObs.setMinHeight("150px");

        add(txtDescricao, 2);
        add(textAreaObs);


    }

    private void configureRadiobuttonGroupTipo() {
        radiobuttonGroupTipo = new RadioButtonGroup<>();
        radiobuttonGroupTipo.setLabel("Tipo:");
        radiobuttonGroupTipo.setItems(TipoCategoria.values());
        add(radiobuttonGroupTipo, 2);
    }

    @Override
    protected void save() {

        try {
            binder.writeBean(controller.getCategoria());
            controller.save();
            showSucess("Categoria cadastrada com sucesso.");
        } catch (ConstraintViolationException cve) {
            showWarnig("Categoria já cadastrada verique.");
            cve.printStackTrace();
        } catch (PersistenceException pe) {
            showError("Erro ao cadastrar categoria.");
            pe.printStackTrace();
        } catch (ValidationException ve) {
            showWarnig("Verique os campos Obrigatórios.");
        }

    }

    @Override
    protected void deleteAction() {

        try {
            controller.delete();
            showSucess("Categoria removida com sucesso.");
        } catch (ConstraintViolationException cve) {
            showWarnig("Categoria não pode ser removida pois está em uso. \n Inative para que não seja mais exibida.");
            cve.printStackTrace();
        } catch (PersistenceException pe) {
            showError("Erro ao remover Categoria.");
            pe.printStackTrace();
        }
    }
}
