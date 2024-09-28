package com.mei.vendasapi.domain.dto.flat;

import com.mei.vendasapi.domain.Classepermissao;
import com.mei.vendasapi.domain.Permissao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class ClassePermissaoFlat {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Boolean read =  Boolean.FALSE;
    private Boolean create=  Boolean.FALSE;
    private Boolean update=  Boolean.FALSE;
    private Boolean delete=  Boolean.FALSE;
    private Boolean status=  Boolean.FALSE;
    @OneToMany(mappedBy = "classepermissao")
    private PermissoesFlat permission;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String name) {
        this.nome = name;
    }
    public PermissoesFlat getPermission() {
        return permission;
    }
    public void setPermission(PermissoesFlat permission) {
        this.permission = permission;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClassePermissaoFlat other = (ClassePermissaoFlat) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    public ClassePermissaoFlat(Integer id, String nome, PermissoesFlat permission) {
        this.id = id;
        this.nome = nome;
        this.permission = permission;
    }
    public ClassePermissaoFlat() {
    }

    public ClassePermissaoFlat(Classepermissao objPorCategoria, List<Permissao> permissoes) {
        this.id = objPorCategoria.getId();
        this.nome = objPorCategoria.getNome();

        switch(objPorCategoria.getNome()) {
//            case "Atendimento": classePermissaoFlatItem(objPorCategoria, permissoes); break;
//            case "Exame": classePermissaoFlatProduto(objPorCategoria, permissoes); break;
//            case "Convênio": classePermissaoFlatPedido(objPorCategoria, permissoes); break;
//            case "Paciente": classePermissaoFlatCliente(objPorCategoria, permissoes); break;
            case "Usuario": classePermissaoFlatUsuario(objPorCategoria, permissoes); break;
//            case "Relatório": classePermissaoFlatRelatorio(objPorCategoria, permissoes); break;
            case "Empresa": classePermissaoFlatEmpresa(objPorCategoria, permissoes); break;
//            case "Cateogoria": classePermissaoFlatCategoria(objPorCategoria, permissoes); break;

        }

    }
    private void classePermissaoFlatCategoria(Classepermissao objPorCategoria, List<Permissao> permissoes) {
        for (Permissao permissao : permissoes) {
            switch(permissao.getDescricao()) {
                case "C_CAT": create =Boolean.TRUE ; break;
                case "U_CAT": update =Boolean.TRUE ; break;
                case "D_CAT": delete =Boolean.TRUE ; break;
                case "R_CAT": read =Boolean.TRUE ; break;
                case "S_CAT": status =Boolean.TRUE ; break;
            }
        }
        PermissoesFlat classepermissaoFlat = new PermissoesFlat(create, update, delete, read, status);
        setPermission(classepermissaoFlat);
        voltarStatusAtributos();

    }
    private void classePermissaoFlatEmpresa(Classepermissao objPorCategoria, List<Permissao> permissoes) {
        for (Permissao permissao : permissoes) {
            switch(permissao.getDescricao()) {
                case "C_EMP": create =Boolean.TRUE ; break;
                case "U_EMP": update =Boolean.TRUE ; break;
                case "D_EMP": delete =Boolean.TRUE ; break;
                case "R_EMP": read =Boolean.TRUE ; break;
                case "S_EMP": status =Boolean.TRUE ; break;
            }
        }
        PermissoesFlat classepermissaoFlat = new PermissoesFlat(create, update, delete, read, status);
        setPermission(classepermissaoFlat);
        voltarStatusAtributos();

    }
    private void classePermissaoFlatCliente(Classepermissao objPorCategoria, List<Permissao> permissoes) {
        for (Permissao permissao : permissoes) {
            switch(permissao.getDescricao()) {
                case "C_CLI": create =Boolean.TRUE ; break;
                case "U_CLI": update =Boolean.TRUE ; break;
                case "D_CLI": delete =Boolean.TRUE ; break;
                case "R_CLI": read =Boolean.TRUE ; break;
                case "S_CLI": status =Boolean.TRUE ; break;
            }
        }
        PermissoesFlat classepermissaoFlat = new PermissoesFlat(create, update, delete, read, status);
        setPermission(classepermissaoFlat);
        voltarStatusAtributos();

    }
    private void voltarStatusAtributos() {
        create =Boolean.FALSE;
        update =Boolean.FALSE;
        delete =Boolean.FALSE;
        read =Boolean.FALSE;
        status =Boolean.FALSE;
    }
    private void classePermissaoFlatItem(Classepermissao objPorCategoria, List<Permissao> permissoes) {
        for (Permissao permissao : permissoes) {
            switch(permissao.getDescricao()) {
                case "C_ITEM": create =Boolean.TRUE ; break;
                case "U_ITEM": update =Boolean.TRUE ; break;
                case "D_ITEM": delete =Boolean.TRUE ; break;
                case "R_ITEM": read =Boolean.TRUE ; break;
                case "S_ITEM": status =Boolean.TRUE ; break;
            }
        }
        PermissoesFlat classepermissaoFlat = new PermissoesFlat(create, update, delete, read, status);
        setPermission(classepermissaoFlat);
        voltarStatusAtributos();

    }
    private void classePermissaoFlatPedido(Classepermissao objPorCategoria, List<Permissao> permissoes) {
        for (Permissao permissao : permissoes) {
            switch(permissao.getDescricao()) {
                case "C_PEDI": create =Boolean.TRUE ; break;
                case "U_PEDI": update =Boolean.TRUE ; break;
                case "D_PEDI": delete =Boolean.TRUE ; break;
                case "R_PEDI": read =Boolean.TRUE ; break;
                case "S_PEDI": status =Boolean.TRUE ; break;
            }
        }
        PermissoesFlat classepermissaoFlat = new PermissoesFlat(create, update, delete, read, status);
        setPermission(classepermissaoFlat);
        voltarStatusAtributos();

    }
    private void classePermissaoFlatProduto(Classepermissao objPorCategoria, List<Permissao> permissoes){
        for (Permissao permissao : permissoes) {
            switch(permissao.getDescricao()) {
                case "C_PROD": create =Boolean.TRUE ; break;
                case "U_PROD": update =Boolean.TRUE ; break;
                case "D_PROD": delete =Boolean.TRUE ; break;
                case "R_PROD": read =Boolean.TRUE ; break;
                case "S_PROD": status =Boolean.TRUE ; break;
            }
        }
        PermissoesFlat classepermissaoFlat = new PermissoesFlat(create, update, delete, read, status);
        setPermission(classepermissaoFlat);
        voltarStatusAtributos();

    }
    private void classePermissaoFlatUsuario(Classepermissao objPorCategoria, List<Permissao> permissoes) {
        for (Permissao permissao : permissoes) {
            switch(permissao.getDescricao()) {
                case "C_USU": create =Boolean.TRUE ; break;
                case "U_USU": update =Boolean.TRUE ; break;
                case "D_USU": delete =Boolean.TRUE ; break;
                case "R_USU": read =Boolean.TRUE ; break;
                case "S_USU": status =Boolean.TRUE ; break;
            }
        }
        PermissoesFlat classepermissaoFlat = new PermissoesFlat(create, update, delete, read, status);
        setPermission(classepermissaoFlat);
        voltarStatusAtributos();

    }
    private void classePermissaoFlatRelatorio(Classepermissao objPorCategoria, List<Permissao> permissoes) {
        for (Permissao permissao : permissoes) {
            switch(permissao.getDescricao()) {

                case "R_REL": read =Boolean.TRUE ; break;

            }
        }
        PermissoesFlat classepermissaoFlat = new PermissoesFlat(read);
        setPermission(classepermissaoFlat);
        voltarStatusAtributos();

    }
}
